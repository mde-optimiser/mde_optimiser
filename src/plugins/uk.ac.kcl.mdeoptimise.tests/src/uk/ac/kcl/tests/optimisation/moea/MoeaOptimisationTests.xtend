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
				objective name maximise ocl { "Class.allInstances()->size()" }
				objective name maximise ocl { 
				"Method.allInstances()->select(m : Method | 
							m.dataDependency->exists(a : Attribute | a.isEncapsulatedBy <> m.isEncapsulatedBy) or
							m.functionalDependency->exists(m1 : Method | m1.isEncapsulatedBy <> m.isEncapsulatedBy))
					   ->size()"
							}
				evolve using <craEvolvers.henshin> unit "createClass"
				evolve using <craEvolvers.henshin> unit "assignFeature"
				optimisation provider moea algorithm nsga-II evolutions 100 population 1000
			''')
			
			
//new(Optimisation optimisationModel, List<Unit> henshinEvolvers, HenshinResourceSet henshinResourceSet, IModelProvider modelProvider){
			
			val oclModelProvider = new MoeaModelProvider()
			
			var solutionGenerator = new SolutionGenerator(model, henshinEvolvers, henshinResourceSet, oclModelProvider, getMetamodel);


//override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {


			var optimisation = new MoeaOptimisation().execute(model.optimisation, solutionGenerator)
	
			oclModelProvider.storeModels(optimisation, pathPrefix + "/final")
	
			model.assertNoIssues


	}
}