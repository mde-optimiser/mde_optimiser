package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver

import com.google.common.base.Charsets
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileWriter
import org.apache.commons.io.FileUtils
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileReader

class MDEOModelSerialiser {

	/**
	 * Serializes the hyper-parameters configuration to the specified JSON file.
	 */
	static def void writeModel(MDEOHyperparametersConfiguration model, File file) {

		var modelFileWriter = new FileWriter(file)

		var gsonSerialiser = new GsonBuilder().setPrettyPrinting().create();
		gsonSerialiser.toJson(model, modelFileWriter)

		modelFileWriter.flush
		modelFileWriter.close
	}

	static def MDEOHyperparametersConfiguration readModel(File file) {

		var gson = new Gson();
		var reader = new JsonReader(new FileReader(file));
		
		return gson.fromJson(reader, MDEOHyperparametersConfiguration);
	}

	/**
	 * Saves the score file to the specified file.
	 */
	static def void writeScore(Double score, File scoreFile) {
		FileUtils.writeStringToFile(scoreFile, String.valueOf(score), Charsets.UTF_8);
	}
}
