package uk.ac.kcl.tests.optimisation.moea

import com.google.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.TestModelHelper

import static org.junit.Assert.*
import static org.mockito.Mockito.*
import uk.ac.kcl.optimisation.UserModelProvider
import uk.ac.kcl.interpreter.OptimisationInterpreter
import uk.ac.kcl.ui.output.MDEOResultsOutput
import uk.ac.kcl.ui.output.MDEOBatch
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import java.util.Collections
import java.util.ArrayList
import uk.ac.kcl.mdeoptimise.EvolverType

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class MoeaOptimisationTests {

	@Inject
	ParseHelper<Optimisation> parser
	
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	private HenshinResourceSet henshinResourceSet

    private EPackage theMetamodel
	
	private List<Unit> henshinEvolvers = null
	
	
	def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(model.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.metamodel.location).head
        }

        theMetamodel
    }
    
    def getHenshinEvolvers() {
    	if (henshinEvolvers == null) {
            val hrs = resourceSet
            henshinEvolvers = model.evolvers.map [ e |
                hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
            ]
        }
    }
    
    //Some tests to run optimisation manually for now
	@Test
	def void runMoeaOptimisationNSGA2CRA() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_C.xmi>
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 200 population 40 experiments 2
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val otimisationInterpreter = new OptimisationInterpreter("", model) 
	            		val optimisationOutcome = otimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, 
	            			optimisationOutcome, otimisationInterpreter
	            		))		
						
						experimentId++
					} while(experimentId < model.optimisation.algorithmExperiments);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}

	@Test
	def void runMoeaOptimisationNSGA2CRACrossover() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_E.xmi>
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				breed using <exchangeClass.henshin> unit "exchangeClassBidirectional"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 200 population 40 experiments 2
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val otimisationInterpreter = new OptimisationInterpreter("", model) 
	            		val optimisationOutcome = otimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, 
	            			optimisationOutcome, otimisationInterpreter
	            		))			
						
						experimentId++
					} while(experimentId < model.optimisation.algorithmExperiments);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}

	@Test
	def void runMoeaOptimisationNSGA2Stack() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/stack/>
				metamodel <stack.ecore>
				model <model_five_stacks.xmi>
				objective CRA minimise java { "models.stack.MinimiseStandardDeviation" }
				mutate using <stack.henshin> unit "shiftLeft" parameters { amount => Random("[1-5]{1}") }
				mutate using <stack.henshin> unit "shiftRight" parameters { amount => Random("[1-5]{1}") }
				optimisation provider moea algorithm NSGAII variation mutation evolutions 100 population 30 experiments 5
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					
					do {            		
	            		val startTime = System.nanoTime;
	            		val otimisationInterpreter = new OptimisationInterpreter("", model) 
	            		val optimisationOutcome = otimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, 
	            			optimisationOutcome, otimisationInterpreter
	            		))		
						
						experimentId++
					
					} while(experimentId < model.optimisation.algorithmExperiments);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	@Test
	def void runMoeaOptimisationNSGA2Zoo() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/zoo/>
				metamodel <zoo.ecore>
				model <SimpleZoo.xmi>
				objective EmptyCages minimise java { "models.zoo.ZooFitnessFunction" }
				mutate using <zoo_evolution.henshin> unit "MoveAnimal"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 100 population 30 experiments 5
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					
					do {            		
	            		val startTime = System.nanoTime;
	            		val otimisationInterpreter = new OptimisationInterpreter("", model) 
	            		val optimisationOutcome = otimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, 
	            			optimisationOutcome, otimisationInterpreter
	            		))			
						
						experimentId++
					
					} while(experimentId < model.optimisation.algorithmExperiments);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	/**
	 * TODO This test fails because of an undefined error. However I cannot tell why this is happening.
	 */
	@Test
	def void runMoeaOptimisationNSGA2ZooComplex() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/zoo/>
				metamodel <zoo.ecore>
				model <MoreComplexZoo.xmi>
				objective EmptyCages minimise java { "models.zoo.ZooFitnessFunction" }
				mutate using <zoo_evolution.henshin> unit "MoveAnimal"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 100 population 30 experiments 5
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					
					do {            		
	            		val startTime = System.nanoTime;
	            		val otimisationInterpreter = new OptimisationInterpreter("", model) 
	            		val optimisationOutcome = otimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, 
	            			optimisationOutcome, otimisationInterpreter
	            		))		
						
						experimentId++
					
					} while(experimentId < model.optimisation.algorithmExperiments);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
		/**
	 * TODO This test fails because of an undefined error. However I cannot tell why this is happening.
	 */
	@Test
	def void moptGenerator() {
		
			val pathPrefix = "gen/configurations/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_E.xmi>
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				breed using <crossover.henshin> unit "exchangeClassBidirectional"
				optimisation provider moea algorithm NSGAII variation genetic(10, 10) evolutions 200 population 40 experiments 20
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues
			
			val inputModels = new ArrayList<String>();
			inputModels.add("TTC_InputRDG_A.xmi")
			inputModels.add("TTC_InputRDG_B.xmi")
			inputModels.add("TTC_InputRDG_C.xmi")
			inputModels.add("TTC_InputRDG_D.xmi")
			inputModels.add("TTC_InputRDG_E.xmi")
			
			val crossoverOperators = new ArrayList<String>();
			crossoverOperators.add("selectClass_addAsNewClass")
			crossoverOperators.add("selectRandomDependencies_addAsNewClass")
			crossoverOperators.add("selectRandomFeatures_addAsNewClass")
			crossoverOperators.add("selectRandomDependencies_preserveDistribution")
			crossoverOperators.add("selectRandomFeaturesSame_addAsNewClass")
			crossoverOperators.add("selectRandomFeatures_preserveDistribution")
			crossoverOperators.add("selectRandomFeaturesSame_preserveDistribution")
			
			val resourceSet = new ResourceSetImpl()
			
			for(var mutation_rate = 10; mutation_rate < 100; mutation_rate+=10){
				for(var crossover_rate = 10; crossover_rate < 100; crossover_rate+=10){
					
					val crossover_rate_value = crossover_rate.toString
					val mutation_rate_value = mutation_rate.toString
					
					inputModels.forEach[inputModel |
						
						model.model.location = inputModel
						
						crossoverOperators.forEach[operator | 
							
							model.evolvers.filter[x | x.evolverType == EvolverType.BREED].get(0).unit = operator
							model.optimisation.algorithmVariation.probabilityVariation.crossover_rate = crossover_rate_value
							model.optimisation.algorithmVariation.probabilityVariation.mutation_rate = mutation_rate_value
							
							val resource = resourceSet.createResource(
								URI.createFileURI(pathPrefix + 
									String.format("conf-%s-pop-%s-ev-%s-input-model-%s-crossover-%s-mutation-rate-%s-crossover-rate.mopt",
										model.optimisation.algorithmPopulation,
										model.optimisation.algorithmEvolutions,
										model.model.location,
										model.evolvers.filter[x | x.evolverType == EvolverType.BREED].get(0).unit,
										model.optimisation.algorithmVariation.probabilityVariation.mutation_rate,
										model.optimisation.algorithmVariation.probabilityVariation.crossover_rate
									)
								)
							)
			
							if (resource.loaded) {
								resource.contents.clear
							}
							resource.contents.add(model)
							resource.save(Collections.EMPTY_MAP)
							
						]
						
					]
					
				}	
			}
			
			

			val resource = resourceSet.createResource(URI.createFileURI(pathPrefix + "configuration.mopt"))
			
			if (resource.loaded) {
				resource.contents.clear
			}
			resource.contents.add(model)
			resource.save(Collections.EMPTY_MAP)
			
	}

}