package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.io.File
import org.moeaframework.core.PopulationIO
import java.util.ArrayList
import org.moeaframework.core.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Path
import java.nio.file.Paths

class MOEAObjectivesOutputDescriptor implements ResultsDescriptor {
	
	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		var objectivesOutputPath = Paths.get(batchPath.toAbsolutePath.toString, String.format("batch-%s-serialized-objectives.pf", batch.id));
		
		batchOverallOutput.append("Serialized objectives saved to: " + objectivesOutputPath.toAbsolutePath.toString)
		batchOverallOutput.append(System.getProperty("line.separator"));
		
		var outputFile = new File(objectivesOutputPath.toAbsolutePath.toString);
		
		val solutions = new ArrayList<Solution>();
		
		batch.solutions.forEach[ solution | solutions.add(solution as Solution)]
		
		PopulationIO.writeObjectives(outputFile, solutions)
	}
}
