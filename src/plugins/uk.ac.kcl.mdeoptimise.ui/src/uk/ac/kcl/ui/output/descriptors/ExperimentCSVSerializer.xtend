package uk.ac.kcl.ui.output.descriptors

import org.eclipse.core.runtime.IPath
import uk.ac.kcl.ui.output.MDEOBatch
import java.io.File
import java.util.ArrayList
import java.util.LinkedHashMap
import uk.ac.kcl.optimisation.moea.problem.MoeaOptimisationSolution
import com.google.common.base.Joiner
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.PrintWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date

class ExperimentCSVSerializer implements ResultsDescriptor {

	String serializedOutputFileName = "experiment-data.csv";

	override generateDescription(IPath batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		//If there are no solutions, then don't generate this file.
		if(batch.solutions.size == 0){
			return;
		}
	
		val batchRows = new ArrayList<LinkedHashMap<String, String>>();
		
		batch.solutions.forEach[solution |
			
			val rowMap = new LinkedHashMap<String, String>();
		
			rowMap.put("batch-id", batch.id.toString);
			rowMap.put("duration", formattedDuration(batch.duration))
			
			solution.formattedObjectives.forEach [ name, value |
				rowMap.put(name, value.toString)
			]
	
			solution.formattedConstraints.forEach [ name, value |
				rowMap.put(name, value.toString)
			]
			
			rowMap.put("solutions-found", batch.solutions.length.toString);
			val modelPath = batchPath + String.format("%08X", solution.model.hashCode) + ".xmi"
			rowMap.put("solution-model", modelPath)
			batchRows.add(rowMap)
		]
	

		
		var outputFile = getSerializedOutputFile(batch.solutions.head, batchPath)
		
		printSerializedDataRow(outputFile, batchRows)
	}

	def String formattedDuration(long batchDuration) {

		var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		return formatter.format(new Date(batchDuration).getTime())

	}

	def void printSerializedDataRow(File serializedDataFile, ArrayList<LinkedHashMap<String, String>> rows) {
		try {

			val out = new PrintWriter(new BufferedWriter(new FileWriter(serializedDataFile, true)));
			rows.forEach[row | 
				out.println(Joiner.on(",").join(row.values.iterator));
			]
			out.close();

		// more code
		} catch (IOException e) {
			System.err.println(e.message)
			e.printStackTrace
		}
	}

	def File createSerializedOutputFile(File serializedOutputFile, MoeaOptimisationSolution solution) {
		if (!serializedOutputFile.exists) {

			if (serializedOutputFile.createNewFile) {

				val headers = new ArrayList<String>();
				headers.add("batch-id")
				headers.add("duration")
				
				solution.formattedObjectives.forEach[name, value|headers.add("o-" + name)]
				solution.formattedConstraints.forEach[name, value|headers.add("c-" + name)]

				headers.add("solutions-found")
				headers.add("solution-model")
				
				try {

					var out = new PrintWriter(new BufferedWriter(new FileWriter(serializedOutputFile, true)));
					out.println(Joiner.on(",").join(headers.iterator));
					out.close();

				// more code
				} catch (IOException e) {
					System.err.println(e.message)
					e.printStackTrace
				}
			}
		}

		return serializedOutputFile;
	}

	def File getSerializedOutputFile(MoeaOptimisationSolution solution, IPath batchPath) {

		var batchRootDirectory = new File(batchPath.toPortableString);

		var serializedOutputFile = new File(batchRootDirectory.parent + "/" + this.serializedOutputFileName)

		if (serializedOutputFile.exists) {
			return serializedOutputFile;
		}

		return this.createSerializedOutputFile(serializedOutputFile, solution)

	}
}
