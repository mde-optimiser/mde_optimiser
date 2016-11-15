package uk.ac.kcl.tests.optimisation.moea

import uk.ac.kcl.mdeoptimise.Optimisation
import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import uk.ac.kcl.tests.FullTestInjector
import org.junit.Test
import uk.ac.kcl.interpreter.objectives.ObjectivesFactory
import uk.ac.kcl.tests.interpreter.objectives.ocl.OclModelProvider
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.common.util.URI
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import java.text.SimpleDateFormat
import java.util.Date
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors
import org.eclipse.emf.ecore.EObject
import org.junit.Ignore

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class MoeaOptimisationTests {

	@Inject
	ParseHelper<Optimisation> parser
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	OclModelProvider oclModelProvider
	
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

	@Test
	def void runMoeaOptimisation() {
		
			val pathPrefix = "gen/models/ttc/" + new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
			
			model = parser.parse('''
				basepath <src/models/cra/>
				metamodel <architectureCRA.ecore>
				objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
				objective MaximiseCRA maximise java { "models.moea.MaximiseCRA" }
				evolve using <craEvolvers.henshin> unit "createClass"
				evolve using <craEvolvers.henshin> unit "assignFeature"
				evolve using <craEvolvers.henshin> unit "moveFeature"
				evolve using <craEvolvers.henshin> unit "deleteEmptyClass"
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
}