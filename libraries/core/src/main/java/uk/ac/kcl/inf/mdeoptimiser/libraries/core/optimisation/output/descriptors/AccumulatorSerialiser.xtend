package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.io.File
import org.eclipse.core.runtime.IPath
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ResultsDescriptor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Path

class AccumulatorSerialiser implements ResultsDescriptor {

	String serializedOutputFileName = "data-steps.csv";

	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		//If there are no solutions, then don't generate this file.
		if(batch.instrumenter === null){
			return;
		}
	
		var batchRootDirectory = new File(batchPath.toString);

		var serializedOutputFile = new File(String.format("%s/batch-%s-%s",batchRootDirectory, batch.id, this.serializedOutputFileName))

		batch.instrumenter.lastAccumulator.saveCSV(serializedOutputFile)
	}
	
}
