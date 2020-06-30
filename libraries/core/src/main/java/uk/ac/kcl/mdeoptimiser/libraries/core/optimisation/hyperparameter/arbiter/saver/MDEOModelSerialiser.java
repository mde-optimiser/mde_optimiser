package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.*;
import org.apache.commons.io.FileUtils;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

public class MDEOModelSerialiser {

  /** Serializes the hyper-parameters configuration to the specified JSON file. */
  public static void writeModel(MDEOHyperparametersConfiguration model, File file) {

    try {
      var modelFileWriter = new FileWriter(file);

      var gsonSerialiser = new GsonBuilder().setPrettyPrinting().create();
      gsonSerialiser.toJson(model, modelFileWriter);

      modelFileWriter.flush();
      modelFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Canot serialise model to file: " + file.getAbsolutePath());
    }
  }

  public static MDEOHyperparametersConfiguration readModel(File file) {

    try {

      var gson = new Gson();
      var reader = new JsonReader(new FileReader(file));

      return gson.fromJson(reader, MDEOHyperparametersConfiguration.class);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not read JSON model from path: " + file.getAbsolutePath());
    }
  }

  /** Saves the score file to the specified file. */
  public static void writeScore(Double score, File scoreFile) {
    try {
      FileUtils.writeStringToFile(scoreFile, String.valueOf(score), Charsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not write score to file: " + scoreFile.getAbsolutePath());
    }
  }
}
