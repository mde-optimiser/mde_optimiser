package argumnetation.scenario.generator.tests

import argumentation.model.generator.ScenarioModelGenerator
import argumentation.scenario.generator.Scenario
import argumentation.scenario.generator.ScenarioGenerator
import com.google.common.collect.Sets
import com.google.gson.GsonBuilder
import java.io.FileWriter
import java.io.PrintWriter
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsGroundReasoner
import net.sf.tweety.commons.util.Triple
import org.eclipse.emf.ecore.EObject
import org.junit.Test
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsArgMatSat
import argumentation.scenario.generator.IGraphScenarioGenerator
import argumentation.scenario.generator.CycleGenerator
import argumentation.scenario.generator.LadderGenerator
import argumentation.scenario.generator.TreeGenerator
import java.util.Set
import java.util.List
import java.io.File
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class ScenarioModelGeneratorTests {

	@Test
	def void generateInputModelsForConfiguration1() {
		
		var size = new HashSet<Integer>();
		size.add(11)
		size.add(21)
		size.add(51)
		
		var pafs = new HashSet<Integer>();
		pafs.add(1)
		pafs.add(2)
		pafs.add(5)
		
		//Belief size percentages out of PF framework
		var beliefSizes = new HashSet<Integer>();
		beliefSizes.add(25)
		beliefSizes.add(50)
		beliefSizes.add(75)
		
		var modelCombinations = Sets.cartesianProduct(size, pafs, beliefSizes)
		
		generateModels(modelCombinations);

	}

	@Test
	def void generateInputModelsForConfiguration2() {
		
		var size = new HashSet<Integer>();
		size.add(51)
		size.add(101)
		size.add(201)
		
		var pafs = new HashSet<Integer>();
		pafs.add(10)
		pafs.add(50)
		pafs.add(100)
		
		//Belief size percentages out of PF framework
		var beliefSizes = new HashSet<Integer>();
		beliefSizes.add(25)
		beliefSizes.add(50)
		beliefSizes.add(75)
		
		var modelCombinations = Sets.cartesianProduct(size, pafs, beliefSizes)
		
		generateModels(modelCombinations);

	}
	
	

	@Test
	def void generateInputModelsForConfiguration3() {
		
		//Already set in the TGF file		
//		var size = new HashSet<Integer>();
//		size.add(51)
//		size.add(101)
//		size.add(201)
		
		var pafs = new HashSet<Integer>();
		pafs.add(50)
		
		//Belief size percentages out of PF framework
		var beliefSizes = new HashSet<Integer>();
		beliefSizes.add(50)
		
		//Two objectives, run for MDEO only
		
		var modelCombinations = Sets.cartesianProduct(pafs, beliefSizes)
		
		var tgfGraphs = new ArrayList<String>();
		
		
		//tgfGraphs.add("src/main/resources/models/argumentation/configuration-3/afinput_exp_cycles_indvary2_step1_batch_yyy10.tgf");
		//tgfGraphs.add("src/main/resources/models/argumentation/configuration-3/BA_160_20_3.tgf")
		tgfGraphs.add("src/main/resources/models/argumentation/configuration-3/bw3.pfile-3-04.pddl.2.cnf.tgf")
		
		generateModelsFromTgfSource(tgfGraphs, modelCombinations);

	}
	
	def void generateModelsFromTgfSource(List<String> tgfGraphs, Set<List<Integer>> modelCombinations) {	
		
		tgfGraphs.forEach[ graphFile |
			modelCombinations.forEach[modelCombination | 
				
				
				//Use the generated graph type
				var scenarioGenerator = new ScenarioGenerator(new File(graphFile));
					
				var scenario = scenarioGenerator.generate(modelCombination.get(0), modelCombination.get(1));
	
				var scenarioModelGenerator = new ScenarioModelGenerator(scenario)
				
				scenarioModelGenerator.getParsedScenario.result
				
				//Try to find an acceptable topic with the ground reasoner 50 times
				serializeFiles("Competition", scenario, scenarioModelGenerator)
				
			]
		]
	}
		
	
	def void generateModels(Set<List<Integer>> modelCombinations) {	
		
		val scenarioGenerationList = new ArrayList<Triple<Integer, Integer, Integer>>();
		
		modelCombinations.forEach[combination |			
			scenarioGenerationList.add(new Triple<Integer, Integer, Integer>(
				combination.get(0), combination.get(1), combination.get(2)
			))
		]
		
		val graphTypes = new ArrayList<IGraphScenarioGenerator>();
		graphTypes.add(new CycleGenerator());
		graphTypes.add(new LadderGenerator());
		graphTypes.add(new TreeGenerator());	
		
		
		graphTypes.forEach[graphGenerator |
			
			scenarioGenerationList.forEach[triple |
				println(String.format("Persuader framework size %s - audience members %s - audience member belief size - %s", triple.first, triple.second, triple.third))
				
					
				var generatedPFGraph = graphGenerator.generate(triple.first);
				
				//Use the generated graph type
				var scenarioGenerator = new ScenarioGenerator(generatedPFGraph);
				
				var scenario = scenarioGenerator.generateATopic(triple.second, triple.third);
	
				var scenarioModelGenerator = new ScenarioModelGenerator(scenario)
				
				scenarioModelGenerator.getParsedScenario.result
				
				//Try to find an acceptable topic with the ground reasoner 50 times
				serializeFiles(graphGenerator.graphTypeName, scenario, scenarioModelGenerator)
			]
		]
	}

	def void serializeFiles(String graphTypeName, Scenario scenario, ScenarioModelGenerator scenarioModelGenerator) {

		// Serialize the model generator
		var modelName = scenarioModelGenerator.generate(graphTypeName)

		serializeText(scenario, modelName, scenarioModelGenerator.parsedScenario.result)
		serializeJson(scenario, modelName);
	}

	def void serializeJson(Scenario scenario, String modelFilePath) {

		// Serialize the scenario
		try {
			var writer = new FileWriter(modelFilePath + ".json")
			var gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(scenario, writer);

			writer.close
		} catch (Exception e) {
			e.printStackTrace
		}

	}

	def void serializeText(Scenario scenario, String modelFilePath, EObject model) {
		try {
			var printWriter = new PrintWriter(modelFilePath + ".txt");
			
			//Pretty print the scenario
			printWriter.println(scenario.toString);
			
			//Print some information about the fitness of this model
			printWriter.println(getModelFitnessGroundReasoner(model))
			printWriter.println(getModelFitnessArgMatSatReasoner(model))
			
			printWriter.close
		} catch (Exception e) {
			e.printStackTrace
		}

	}
	
	def String getModelFitnessGroundReasoner(EObject model){
		
		var solution = new Solution(model);
		
		var fitness = new MaximisePFWithAcceptableTopicsGroundReasoner().computeFitness(solution);
		
		return String.format("Acceptable topics with the Ground Reasoner: %s", fitness);
	}
	
	def String getModelFitnessArgMatSatReasoner(EObject model){
		
		var solution = new Solution(model);
		
		var fitness = new MaximisePFWithAcceptableTopicsArgMatSat().computeFitness(solution);
		return String.format("Acceptable topics with the ArgMatSat: %s", fitness);
	}
}