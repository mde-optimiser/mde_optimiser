package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import com.google.common.collect.Lists
import com.google.common.io.Files
import java.io.PrintWriter
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.indicator.Hypervolume
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ResultsDescriptor
import java.nio.file.Path
import java.nio.file.Paths

class HypervolumeDescriptor implements ResultsDescriptor {

	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

		// If there is only one objective, don't show the chart
		if (batch.solutions.empty || batch.solutions.head.objectives.length < 2 || batch.solutions.length < 2) {
			return
		}

		var hypervolumeOutputPath = Paths.get(batchPath.toAbsolutePath.toString, "hypervolume",
			String.format("hypervolume-batch-%s.txt", batch.id));

		var nonDominatedPopulation = new NondominatedPopulation();
		nonDominatedPopulation.addAll(batch.solutions)

		var outputFile = hypervolumeOutputPath.toAbsolutePath.toFile

		Files.createParentDirs(outputFile)

		val batchWriter = new PrintWriter(outputFile)

		// Save the hypervolume
		var hypervolume = Hypervolume.calculateHypervolume(
			Lists.newArrayList(nonDominatedPopulation),
			nonDominatedPopulation.size(),
			batch.solutions.head.objectives.length
		);

		batchWriter.write(hypervolume.toString);

		batchWriter.close();
	}

}
