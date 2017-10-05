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
	@Ignore
	def void runMoeaOptimisationNSGA2() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_A.xmi>
				objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
				objective MinimiseEmptyClasses minimise java { "models.moea.MinimiseEmptyClasses" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				optimisation provider moea algorithm NSGAII variation mutation evolutions 40000 population 30
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			val oclModelProvider = new UserModelProvider(getResourceSet(), "TTC_InputRDG_A.xmi")
			
			val optimisationInterpreter = new OptimisationInterpreter("", model)
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											optimisationInterpreter.breedingOperators, 
											optimisationInterpreter.mutationOperators, 
											oclModelProvider, 
											optimisationInterpreter.metamodel);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)
			
//			optimisation
//				.forEach[model | oclModelProvider.storeModelAndInfo(model, pathPrefix + "/final", oclModelProvider.modelPaths.head)]
	}

}