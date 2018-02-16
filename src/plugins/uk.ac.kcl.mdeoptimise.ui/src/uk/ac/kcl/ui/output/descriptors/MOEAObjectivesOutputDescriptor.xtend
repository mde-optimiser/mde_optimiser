package uk.ac.kcl.ui.output.descriptors

import org.eclipse.core.runtime.IPath
import uk.ac.kcl.ui.output.MDEOBatch
import java.io.File
import org.moeaframework.core.PopulationIO
import java.util.ArrayList
import org.moeaframework.core.Solution

class MOEAObjectivesOutputDescriptor implements ResultsDescriptor {
	
	override generateDescription(IPath batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		var objectivesOutputPath = batchPath.append(String.format("batch-%s-serialized-objectives.pf", batch.id));
		
		batchOverallOutput.append("Serialized objectives saved to: " + objectivesOutputPath.toPortableString)
		batchOverallOutput.append(System.getProperty("line.separator"));
		
		var outputFile = new File(objectivesOutputPath.toPortableString);
		
		val solutions = new ArrayList<Solution>();
		
		batch.solutions.forEach[ solution | solutions.add(solution as Solution)]
		
		PopulationIO.writeObjectives(outputFile, solutions)
	}
}