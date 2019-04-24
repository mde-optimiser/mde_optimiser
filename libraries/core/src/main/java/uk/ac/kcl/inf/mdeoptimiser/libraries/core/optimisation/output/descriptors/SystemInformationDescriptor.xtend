package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.nio.file.Path
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Paths
import java.io.PrintWriter
import com.google.common.io.Files
import oshi.SystemInfo

class SystemInformationDescriptor implements ResultsDescriptor {
	
	
	SystemInfo systemInfo;
	
	new(){
		this.systemInfo = new SystemInfo();
	}
	
	override generateDescription(Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		var batchInfoPath = Paths.get(batchOutputPath.toAbsolutePath.toString, String.format("batch-%s-system-info.txt", batch.id))
		
		var outputFile = batchInfoPath.toAbsolutePath.toFile
		Files.createParentDirs(outputFile)
		
		val batchWriter = new PrintWriter(outputFile)
	
		batchWriter.write(getOperatingSystem)
		batchWriter.println()
		batchWriter.write(getHardwareInfo)
		batchWriter.close
	}
	
	/**
	 * Get information about the operating system used to run the current batch.
	 * 
	 * @return string containing operating system information
	 */
	def String getOperatingSystem(){
		var operatingSystem = systemInfo.getOperatingSystem();
		return String.format("Operating system: %s %s", operatingSystem.family, operatingSystem.version);
	}
	
	/**
	 * Get some basic hardware info to help identify the machine type on which the current batch has been executed.
	 * 
	 * @return string containing hardware information
	 */
	def String getHardwareInfo() {
		var hardwareAbstractionLayer = systemInfo.getHardware();
		var centralProcessor = hardwareAbstractionLayer.getProcessor();
		return String.join("\n", 
			String.format("Processor: %s %s Model: %s Arch: %s", centralProcessor.vendor, centralProcessor.family, centralProcessor.model),
			String.format("Processor Cores: %s physical %s logical", centralProcessor.physicalProcessorCount, centralProcessor.logicalProcessorCount),
			String.format("Memory: %s", hardwareAbstractionLayer.memory.total)
		);
	}
}