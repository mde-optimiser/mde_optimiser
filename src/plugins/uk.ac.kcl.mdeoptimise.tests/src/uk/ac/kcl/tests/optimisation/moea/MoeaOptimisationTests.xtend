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
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.interpreter.OptimisationInterpreter
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.UserModelProvider
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.mdeoptimise.dashboard.api.macaddress.MacAddressRetriever
import uk.ac.kcl.mdeoptimise.dashboard.api.hashing.Hashing
import java.io.File
import uk.ac.kcl.ui.launch.RunOptimisation
import uk.ac.kcl.MDEOptimiseStandaloneSetup

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
    
    def runMoeaOptimisationStandalone(String projectPath, String moptFilePath) {
    	val injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration()
    	var runOptimisationStandalone = injector.getInstance(RunOptimisation)
    	
    	runOptimisationStandalone.run(projectPath, moptFilePath)
    }

    @Test
    def void runMoeaOptimisationStandaloneCrossoverTest(){
		var projectFile = new File("./")
    	var moptFileLocation = new File("src/models/cra/crossover_experiment.mopt");

    	runMoeaOptimisationStandalone(projectFile.absolutePath, moptFileLocation.absolutePath)
    }

    @Test
    def void runMoeaOptimisationStandaloneScrumPlanningTest(){
		var projectFile = new File("./")
    	var moptFileLocation = new File("src/models/cra/scrum_planning_base_case.mopt");

    	runMoeaOptimisationStandalone(projectFile.absolutePath, moptFileLocation.absolutePath)
    }

    //Some tests to run optimisation manually for now
	@Test
	def void runMoeaOptimisationNSGA2() {
			
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_C.xmi>
				objective CRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				breed using <exDependencies.henshin> unit "exchangeMultipleDependencies" 
					parameters { number3 => Random("[0-9]{0,2}"), number => "models.moea.RandomEvolverParameter" }
				optimisation provider moea algorithm NSGAII variation genetic evolutions 10 population 30
			''')

			//Assert that there are no grammar issues
			model.assertNoIssues

			model.evolvers.get(0).parameters

			val oclModelProvider = new UserModelProvider(getResourceSet(), "TTC_InputRDG_C.xmi")
			
			val optimisationInterpreter = new OptimisationInterpreter("", model)
			var MacAddressRetriever macAddressRetriever = new MacAddressRetriever();
			var macAddress = macAddressRetriever.getMacAddress();
			var moptId = Hashing.generateMoptId(
				model.getModel().getLocation(), 
				model.getMetamodel().getLocation());
			var dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			var date = new Date();
			var startTime = dateFormat.format(date);
			var experimentId = Hashing.generateExperimentId(macAddress, moptId, startTime);
			
			var solutionGenerator = new SolutionGenerator(
											model, 
											optimisationInterpreter.breedingOperators, 
											optimisationInterpreter.mutationOperators, 
											oclModelProvider, 
											optimisationInterpreter.metamodel, experimentId);

			var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)
			// /home/alxbrd/projects/alxbrd/github/mde_optimiser/src/plugins
			optimisation
				.forEach[m | oclModelProvider.storeModelAndInfo(m, "/Users/tammaramanasieva/Documents/Workspace/MDEO/src/plugins/uk.ac.kcl.mdeoptimise.tests/" + pathPrefix + "/final", model)]
	}

}