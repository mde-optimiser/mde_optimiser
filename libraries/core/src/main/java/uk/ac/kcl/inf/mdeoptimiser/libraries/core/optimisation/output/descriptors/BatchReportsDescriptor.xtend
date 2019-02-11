package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.List
import java.util.ArrayList
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution
import java.nio.file.Path
import java.nio.file.Paths

class BatchReportsDescriptor implements ResultsDescriptor {
	
	List<IGuidanceFunction> reportFunctions;
	
	new(Optimisation moptOptimisation){
		this.loadReportFunctions(moptOptimisation)
	}
	
	override generateDescription(Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

		if(this.reportFunctions.empty){
			return;
		}

		var objectivesOutputPath = Paths.get(batchOutputPath.toString, String.format("batch-%s-serialized-reports.txt", batch.id));
		
		batchOverallOutput.append("Serialized batch reports saved to: " + objectivesOutputPath.toAbsolutePath.toString)
		batchOverallOutput.append(System.getProperty("line.separator"));
		
		writeReportInfo(new File(objectivesOutputPath.toAbsolutePath.toString), batch)
	}
	
	def void loadReportFunctions(Optimisation moptOptimisation){
		
		this.reportFunctions = new ArrayList<IGuidanceFunction>();
		
		moptOptimisation.search.reports.forEach[function |	
			this.reportFunctions.add(Class.forName(function.reportSpec).newInstance() as IGuidanceFunction)
		]	
	}
	
	def void writeReportInfo(File outputFile, MDEOBatch batch){
		
		var writer = new BufferedWriter(new FileWriter(outputFile));
		
		try {
			
			for(var i = 0; i < batch.solutions.length; i++){
				
				var solution = batch.solutions.get(i) as MoeaOptimisationSolution
				
				for (var j = 0; j < reportFunctions.size; j++) {
					
					writer.write(Double.toString(reportFunctions.get(j).computeFitness(solution.model)))
					
					if(j < reportFunctions.size)
						writer.write(" ");
				}

				writer.newLine();
			}
		} finally {
			if (writer !== null) {
				writer.close();
			}
		}	
	}
	
}
