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
	def void runMoeaOptimisationNSGA2() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>			
				objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <craEvolvers.henshin> unit "createClass"
				evolve using <craEvolvers.henshin> unit "assignFeature"
				evolve using <craEvolvers.henshin> unit "moveFeature"
				evolve using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm NSGAII evolutions 100000 population 100
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)
			
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}	
	
	@Test
	@Ignore
	def void runMoeaOptimisationSPEA2() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>			
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <craEvolvers.henshin> unit "createClass"
				evolve using <craEvolvers.henshin> unit "assignFeature"
				evolve using <craEvolvers.henshin> unit "moveFeature"
				evolve using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm SPEA2 evolutions 10000 population 100
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)
			
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
	
	@Test

	def void runMoeaOptimisationeMOEA() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <craEvolvers.henshin> unit "createClass"
				evolve using <craEvolvers.henshin> unit "assignFeature"
				evolve using <craEvolvers.henshin> unit "moveFeature"
				evolve using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm NSGAII evolutions 1000000 population 1000
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}

//				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
//				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
//				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
//				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"	
	
	@Test
	def void runMoeaOptimisationeSerge() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"	
				optimisation provider moea algorithm NSGAII evolutions 50000 population 50
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
	
	
	@Test
	def void runMoeaOptimisationeConstrainedTransformationRulesSlowPerformance() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 10000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
	/*
	 * This experiment gets stuck in local optima
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Calculated CRA : -79.24777777777778
Classless features:0
Classless features:0
Calculated CRA : -79.24777777777778
Classless features:0
Classless features:0
Calculated CRA : -79.24777777777778
Classless features:0
Classless features:0
Calculated CRA : -79.24777777777778
Classless features:0
Classless features:0
Calculated CRA : -79.24777777777778
Classless features:0
Classless features:0
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	def void runMoeaOptimisationeConstrainedTransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 100000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedLb1TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/lb1/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 100000 population 50
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
		
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipTransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <CHANGE_REFERENCE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "changeClass_encapsulates_TO_Feature"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
			
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipCase1TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/case1>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}


			
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipCase2TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/case2>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}

			
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipCase3TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/case3>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}

				
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipCase4TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/case4>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
				
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	def void runMoeaOptimisationeConstrainedWipCase5TransformationRules() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/wip/case1>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel"
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature"
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				optimisation provider moea algorithm NSGAII evolutions 10000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}
	
	
	/**
	 * This is a test with the default serge rules generated when the isEncapsulatedBy constraint is set to 1 lb
	 * When this is being ran the optimisation works until it gets stuck in local optima until all the features are assigned to a class
	 * after which some rules cannot be applied any longer such as delete class/delete class encapsulates
	 */
	@Test
	@Ignore
	def void runMoeaOptimisationeConstrainedWipTransformationRulesAllElements() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/constrained/allelements/>
				metamodel <config/architectureCRA.ecore>
				objective Coupling minimise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <CREATE_Class_IN_ClassModel_(classes)_execute.henshin> unit "createClass_IN_ClassModel" 
				evolve using <DELETE_Class_IN_ClassModel_(classes)_execute.henshin> unit "deleteClass_IN_ClassModel" 
				evolve using <ADD_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "addToClass_encapsulates_Feature"
				evolve using <REMOVE_Class_(encapsulates)_TGT_Feature_execute.henshin> unit "removeFromClass_encapsulates_Feature" 
				evolve using <SET_REFERENCE_Attribute_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setAttribute_isEncapsulatedBy_TO_Class"
				evolve using <UNSET_REFERENCE_Attribute_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setAttribute_isEncapsulatedBy_TO_Class"
				evolve using <SET_REFERENCE_Method_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setMethod_isEncapsulatedBy_TO_Class"
				evolve using <UNSET_REFERENCE_Method_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setMethod_isEncapsulatedBy_TO_Class"
				evolve using <SET_REFERENCE_Feature_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setFeature_isEncapsulatedBy_TO_Class"
				evolve using <UNSET_REFERENCE_Feature_(isEncapsulatedBy)_TGT_Class_execute.henshin> unit "setFeature_isEncapsulatedBy_TO_Class"
				optimisation provider moea algorithm NSGAII evolutions 50000 population 100
				''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											oclModelProvider, 
											getMetamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
			optimisation
				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}


}