package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import com.google.common.io.Files;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import oshi.SystemInfo;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class SystemInformationDescriptor implements ResultsDescriptor {

  SystemInfo systemInfo;

  public SystemInformationDescriptor() {
    this.systemInfo = new SystemInfo();
  }

  @Override
  public void generateDescription(
      Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    var batchInfoPath =
        Paths.get(
            batchOutputPath.toAbsolutePath().toString(),
            String.format("batch-%s-system-info.txt", batch.id));

    var outputFile = batchInfoPath.toAbsolutePath().toFile();
    try {
      Files.createParentDirs(outputFile);

      var batchWriter = new PrintWriter(outputFile);

      batchWriter.write(getOperatingSystem());
      batchWriter.println();
      batchWriter.write(getJavaVersion());
      batchWriter.println();
      //   batchWriter.write(getHardwareInfo());
      batchWriter.close();

    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not write system descriptor file to disk: " + outputFile);
    }
  }

  /**
   * Get information about the operating system used to run the current batch.
   *
   * @return string containing operating system information
   */
  public String getOperatingSystem() {
    var operatingSystem = systemInfo.getOperatingSystem();
    return String.format(
        "Operating system: %s %s", operatingSystem.getFamily(), operatingSystem.getVersion());
  }

  /** Get information about the JDK used to run the current search batch. */
  public String getJavaVersion() {
    var javaRuntime = System.getProperty("java.runtime.name");
    var javaVersion = System.getProperty("java.runtime.version");

    return String.format("Java Runtime: %s Version: %s", javaRuntime, javaVersion);
  }

  /**
   * Get some basic hardware info to help identify the machine type on which the current batch has
   * been executed.
   *
   * @return string containing hardware information
   */
  public String getHardwareInfo() {
    var hardwareAbstractionLayer = systemInfo.getHardware();
    var centralProcessor = hardwareAbstractionLayer.getProcessor();
    return String.join(
        "\n",
        String.format("Processor: %s", centralProcessor.getName()),
        String.format(
            "Processor Cores: %s physical %s logical",
            centralProcessor.getPhysicalProcessorCount(),
            centralProcessor.getLogicalProcessorCount()),
        String.format(
            "Memory: %s GB", hardwareAbstractionLayer.getMemory().getTotal() / 1073741824d));
  }
}
