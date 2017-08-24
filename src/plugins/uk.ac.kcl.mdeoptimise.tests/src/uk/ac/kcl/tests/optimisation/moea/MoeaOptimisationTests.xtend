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
				model <TTC_InputRDG_A.xmi>
				objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseEmptyClasses java { "models.moea.MinimiseClasslessFeatures" }
				evolve using <craEvolvers.henshin> unit "createClass" type "mutation"
				evolve using <craEvolvers.henshin> unit "assignFeature" type "mutation"
				evolve using <craEvolvers.henshin> unit "moveFeature" type "mutation"
				evolve using <craEvolvers.henshin> unit "deleteEmptyClass" type "mutation"
				evolve using <exchangeClass.henshin> unit "exchangeClassBidirectional" type "crossover"
				optimisation provider moea algorithm NSGAII variation genetic evolutions 40000 population 10
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
				model <TTC_InputRDG_A.xmi>
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

}