package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Path

interface ResultsDescriptor {
	def void generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput)
}
