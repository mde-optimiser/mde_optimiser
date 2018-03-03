package uk.ac.kcl.ui.output.descriptors

import java.io.File
import org.eclipse.core.runtime.IPath
import uk.ac.kcl.ui.output.MDEOBatch
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import uk.ac.kcl.optimisation.moea.MoeaOptimisationProblem
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.indicator.Hypervolume
import com.google.common.io.Files
import java.io.PrintWriter
import org.moeaframework.core.indicator.Normalizer
import com.google.common.collect.Lists
import org.moeaframework.core.PopulationIO

class HypervolumeDescriptor implements ResultsDescriptor {
	
	override generateDescription(IPath batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		//If there is only one objectove, don't show the chart
		if(batch.solutions.empty || batch.solutions.head.objectives.length < 2 || batch.solutions.length < 2){
			return
		}
		
		var hypervolumeOutputPath = batchPath.append("hypervolume/" + String.format("hypervolume-batch-%s.txt", batch.id));
		
		var nonDominatedPopulation = new NondominatedPopulation();
		nonDominatedPopulation.addAll(batch.solutions)
		
		
		var outputFile = new File(hypervolumeOutputPath.toPortableString);
		
		Files.createParentDirs(outputFile)
		
		val batchWriter = new PrintWriter(outputFile)

		//Save the hypervolume
		var hypervolume = Hypervolume.calculateHypervolume(Lists.newArrayList(nonDominatedPopulation), 
			nonDominatedPopulation.size(), batch.solutions.head.objectives.length
		);
		
		batchWriter.write(hypervolume.toString);
		
		batchWriter.close();
	}
	
}