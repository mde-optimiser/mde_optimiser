package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import org.moeaframework.analysis.plot.Plot
import org.moeaframework.core.Population
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Path
import java.nio.file.Paths

class ParetoChartOutputDescriptor implements ResultsDescriptor {

	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

		// If there is only one objectove, don't show the chart
		if (!batch.solutions.empty && batch.solutions.head.objectives.length < 2) {
			return
		}

		var objectivesOutputPath = Paths.get(batchPath.toAbsolutePath.toString,
			String.format("batch-%s-pareto-front.jpeg", batch.id));

		batchOverallOutput.append("Serialized objectives saved to: " + objectivesOutputPath.toAbsolutePath.toString)
		batchOverallOutput.append(System.getProperty("line.separator"));

		var outputFile = objectivesOutputPath.toAbsolutePath.toFile

		var population = new Population();
		population.addAll(batch.solutions)

		new Plot().add(String.format("Pareto front for batch %s", batch.id), population).setXLabel(
			batch.solutions.head.formattedObjectives.keySet.get(0)).setYLabel(
			batch.solutions.head.formattedObjectives.keySet.get(1)).save(outputFile)

	}

}
