package uk.ac.kcl.ui.output.descriptors

import uk.ac.kcl.ui.output.MDEOBatch
import org.eclipse.core.runtime.IPath

interface ResultsDescriptor {
	def void generateDescription(IPath batchPath, MDEOBatch batch, StringBuilder batchOverallOutput)
}