package uk.ac.kcl.ui.output.descriptors

import java.io.File
import org.eclipse.core.runtime.IPath
import org.moeaframework.analysis.plot.Plot
import org.moeaframework.core.Population
import uk.ac.kcl.ui.output.MDEOBatch

class ParetoChartOutputDescriptor implements ResultsDescriptor {
	
	override generateDescription(IPath batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		//If there is only one objectove, don't show the chart
		if(!batch.solutions.empty && batch.solutions.head.objectives.length < 2){
			return
		}
		
		var objectivesOutputPath = batchPath.append(String.format("batch-%s-pareto-front.jpeg", batch.id));
		
		batchOverallOutput.append("Serialized objectives saved to: " + objectivesOutputPath.toPortableString)
		batchOverallOutput.append(System.getProperty("line.separator"));
		
		var outputFile = new File(objectivesOutputPath.toPortableString);
		
		var population = new Population();
		population.addAll(batch.solutions)
		
		new Plot()
			.add(String.format("Pareto front for batch %s", batch.id), population)
			.setXLabel(batch.solutions.head.formattedObjectives.keySet.get(0))
			.setYLabel(batch.solutions.head.formattedObjectives.keySet.get(1))
			.save(outputFile)
	
	}
	
}