package uk.ac.kcl.tests.optimisation.moea

import com.google.inject.Inject
import java.util.Date
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector

import uk.ac.kcl.interpreter.OptimisationInterpreter
import uk.ac.kcl.ui.output.MDEOResultsOutput
import uk.ac.kcl.ui.output.MDEOBatch
import org.eclipse.core.runtime.Path

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class MoeaOptimisationTests {

	@Inject
	ParseHelper<Optimisation> parser
	
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	HenshinResourceSet henshinResourceSet

    EPackage theMetamodel
	
	List<Unit> henshinEvolvers = null
	
	def getResourceSet() {
        if (henshinResourceSet === null) {
            henshinResourceSet = new HenshinResourceSet(model.problem.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel === null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.problem.metamodel.location).head
        }

        theMetamodel
    }
    
    def getHenshinEvolvers() {
    	if (henshinEvolvers === null) {
            val hrs = resourceSet
            henshinEvolvers = model.search.evolvers.map [ e |
                hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
            ]
        }
    }
    
    //Some tests to run optimisation manually for now
	@Test
	def void runMoeaOptimisationNSGA2CRA() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				problem {
					basepath <src/models/cra/>
					metamodel <architectureCRA.ecore>
					model <TTC_InputRDG_D.xmi>
				}
				goal {
					objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
					objective MinimiseTransformations minimise java { "models.moea.MinimiseTransformations" }
					constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				}
				search {
					mutate using <craEvolvers.henshin> unit "createClass"
					mutate using <craEvolvers.henshin> unit "assignFeature"
					mutate using <craEvolvers.henshin> unit "moveFeature"
					mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				}
				solver {
					optimisation provider moea algorithm NSGAII variation mutation
					parameters {
						population: 100
					}
					termination {
						evolutions: 50
					}
					batches 1
				}
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}

	@Test
	def void runMoeaOptimisationNSGA2CRACrossover() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_D.xmi>
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 750 population 40 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
						val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
							
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

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
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					
					} while(experimentId < model.solver.optimisation.algorithmBatches);

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
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
							
						
						experimentId++
					
					} while(experimentId < model.solver.optimisation.algorithmBatches);

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
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
								
						
						experimentId++
					
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	    //Some tests to run optimisation manually for now
	@Test
	def void runMoeaOptimisationNSGA2RulegenSimple() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_C.xmi>
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate {"Class"}
				optimisation provider moea algorithm NSGAII variation mutation evolutions 10 population 30 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
		    //Some tests to run optimisation manually for now
	@Test
	def void runMoeaOptimisationNSGA2RulegenRefined() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_D.xmi>
				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
				objective CRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate {"Class"}
				optimisation provider moea algorithm NSGAII variation mutation evolutions 20 population 40 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		optimisationInterpreter.enableManualRandomMatching = true
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	@Test
	def void runMoeaOptimisationNSGA2RulegenManual() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/gcm/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_D.xmi>
				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				mutate using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				mutate using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				mutate using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"	
				mutate using <CREATE_Class_IN_ClassModel_(classes)_Refined_execute.henshin> unit "createClass_IN_ClassModel_refined"
				mutate using <DELETE_Class_IN_ClassModel_(classes)_Refined_execute.henshin> unit "deleteClass_IN_ClassModel_refined"
				mutate using <REMOVE_Class_(encapsulates)_TGT_Feature_Refined_execute.henshin> unit "removeFromClass_encapsulates_Feature_refined"	
				optimisation provider moea algorithm NSGAII variation mutation evolutions 500 population 40 batches 10
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	@Test
	def void runNRPRulegen() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/nrp/>
				metamodel <nextReleaseProblem.ecore>
				model <nrp-model-5-cus-25-req-63-sa.xmi>
				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
				objective MinimiseCost minimise java { "models.nrp.MinimiseCost" }
				objective MaximiseSatisfaction maximise java { "models.nrp.MaximiseSatisfaction" }
				mutate {"Solution","selectedArtifacts"}
				optimisation provider moea algorithm NSGAII variation mutation evolutions 50 population 40 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	
	@Test
	def void runNRPManual() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/nrp/>
				metamodel <nextReleaseProblem.ecore>
				model <nrp-model-5-cus-25-req-63-sa.xmi>
				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
				objective MinimiseCost minimise java { "models.nrp.MinimiseCost" }
				objective MaximiseSatisfaction maximise java { "models.nrp.MaximiseSatisfaction" }
				mutate using <mutation.henshin> unit "modifySelectionWithHierarchy"
				mutate using <mutation.henshin> unit "modifySingleSelection"
				mutate using <mutation.henshin> unit "selectHighestRealisation"
				mutate using <mutation.henshin> unit "fixDependencies"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 500 population 40 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}

	
	@Test
	def void runCraCrossoverOperators() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_C.xmi>
				objective CRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseEmptyClasses java { "models.moea.MinimiseEmptyClasses" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				breed using <crossover.henshin> unit "selectRandomFeatures_injectAsNewClass" parameters { matchingSelection => "models.cra.BooleanEvolverParameter" } 
				optimisation provider moea algorithm NSGAII variation genetic(50, 100) evolutions 500 population 30 batches 1
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}
	
	@Test
	def void runScrumPlanningRulegen() {
		
			val pathPrefix = "gen/"
			
			model = parser.parse('''
				basepath <src/models/scrum/>
				metamodel <SprintPlanning.ecore>
				model <sprint-planning-model-5-stakeholders-119-items.xmi>
				refine metamodel {"WorkItem", "isPlannedFor", 1, 1}
				objective MinimiseCustomerSatisfactionIndex minimise java { "models.scrum.MinimiseCustomerSatisfactionIndex" }
				objective MinimiseSprintEffortDeviation minimise java { "models.scrum.MinimiseSprintEffortDeviation" }
				constraint HasNoUnassignedWorkItems java { "models.scrum.HasNoUnassignedWorkItems" }
				constraint HasTheAllowedMaximalNumberOfSprints java { "models.scrum.HasTheAllowedMaximalNumberOfSprints" }
				mutate {"Sprint"}
				optimisation provider moea algorithm NSGAII variation mutation
				parameters {
					population: 30
				}
				termination {
					time: 15
				}
				batches 2
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			if(model !== null){
					
					val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(pathPrefix), new Path(""), model);	
					
					var experimentId = 0;
					do {
							            		
	            		val startTime = System.nanoTime;
	            		val optimisationInterpreter = new OptimisationInterpreter("", model);
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	            		
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, optimisationInterpreter.rulegenOperators))		
						
						experimentId++
					} while(experimentId < model.solver.optimisation.algorithmBatches);

	            	mdeoResultsOutput.saveOutcome();
	        }
	}

}