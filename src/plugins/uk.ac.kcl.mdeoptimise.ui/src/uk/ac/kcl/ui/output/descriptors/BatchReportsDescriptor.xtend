package uk.ac.kcl.ui.output.descriptors

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.List
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import uk.ac.kcl.interpreter.IGuidanceFunction
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.ui.output.MDEOBatch
import java.util.ArrayList
import uk.ac.kcl.optimisation.moea.problem.MoeaOptimisationSolution

class BatchReportsDescriptor implements ResultsDescriptor {
	
	private ResourceSet resourceSet;
	private List<IGuidanceFunction> reportFunctions;
	
	new(Optimisation moptOptimisation){
		this.resourceSet = new ResourceSetImpl();
		this.loadReportFunctions(moptOptimisation)
	}
	
	override generateDescription(IPath batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

		if(this.reportFunctions.empty){
			return;
		}

		var objectivesOutputPath = batchOutputPath.append(String.format("batch-%s-serialized-reports.txt", batch.id));
		
		batchOverallOutput.append("Serialized batch reports saved to: " + objectivesOutputPath.toPortableString)
		batchOverallOutput.append(System.getProperty("line.separator"));
		
		writeReportInfo(new File(objectivesOutputPath.toPortableString), batch)
	}
	
	def void loadReportFunctions(Optimisation moptOptimisation){
		
		this.reportFunctions = new ArrayList<IGuidanceFunction>();
		
		moptOptimisation.reports.forEach[function |	
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