package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.io.File
import java.nio.file.Path
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch

class AccumulatorSerialiser implements ResultsDescriptor {

	String serializedOutputFileName = "data-steps.csv";

	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		//If there are no solutions, then don't generate this file.
		if(batch.searchResult === null){
			return;
		}
	
		var batchRootDirectory = new File(batchPath.toString);

		var serializedOutputFile = new File(String.format("%s/batch-%s-%s",batchRootDirectory, batch.id, this.serializedOutputFileName))

		batch.searchResult.accumulator.saveCSV(serializedOutputFile)
	}
	
}
