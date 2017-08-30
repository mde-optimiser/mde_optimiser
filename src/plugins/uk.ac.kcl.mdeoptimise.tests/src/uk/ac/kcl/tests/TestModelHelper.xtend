package uk.ac.kcl.tests

import com.google.inject.Inject
import org.eclipse.xtext.junit4.util.ParseHelper
import uk.ac.kcl.mdeoptimise.Optimisation

class TestModelHelper {
	
	@Inject
	ParseHelper<Optimisation> parser = new ParseHelper<Optimisation>()
	
	//optimisation helpers
	def Optimisation getParsedFullValidModel(String model) {
		return parser.parse(model)
	}
	
	def Optimisation getParsedFullValidModel() {
		return parser.parse(getFullValidModel())
	}
	
	def Optimisation getParsedFullValidModelWithCustomOptimisation(String optimisation) {
		return parser.parse(getFullValidModelWithCustomOptimisation(optimisation))
	}
	
	def Optimisation getParsedFullValidModelWithCustomObjectives(String objectives) {
		return parser.parse(getFullValidModelWithCustomObjectives(objectives))
	}
	
	def Optimisation getParsedFullValidModelWithCustomConstraints(String constraints) {
		return parser.parse(getFullValidModelWithCustomConstraints(constraints))
	}
	
	//String helpers
	def String getFullValidModel(){		
		return getValidBasePath +
			   getValidMetamodel +
			   getValidModelPathSet +
			   getValidObjectivesSet +
			   getValidConstraintsSet +
			   getValidEvolverSet +
			   getValidOptimisation
	}
	
	def String getFullValidModelWithCustomOptimisation(String optimisation) {
		return getValidBasePath +
			   getValidMetamodel +
			   getValidModelPathSet +
			   getValidObjectivesSet +
			   getValidConstraintsSet +
			   getValidEvolverSet +
			   optimisation
	}
	
	def String getFullValidModelWithCustomObjectives(String objectives) {
		return getValidBasePath +
			   getValidMetamodel +
			   getValidModelPathSet +
			   objectives +
			   getValidConstraintsSet +
			   getValidEvolverSet +
			   getValidOptimisation
	}
	
	def String getFullValidModelWithCustomConstraints(String constraints) {
		return getValidBasePath +
			   getValidMetamodel +
			   getValidModelPathSet +
			   getValidObjectivesSet +
			   constraints +
			   getValidEvolverSet +
			   getValidOptimisation
	}
	
	def String getValidBasePath() {
		return "basepath <src/models/cra/>"
	}
	
	def String getValidMetamodel() {
		return "metamodel <architectureCRA.ecore>"
	}
	
	def String getValidOclObjective() {
		return "objective MaximiseClassCount maximise ocl { \"Class.allInstances()->size()\" }"
	}
	
	def String getValidJavaObjective() {
		return "objective MinimiseClasslessFeatures minimise java { \"models.moea.MinimiseClasslessFeatures\" }"
	}
	
	def String getValidObjectivesSet() {
		return '''
			objective MinimiseClasslessFeatures minimise java { "models.moea.MinimiseClasslessFeatures" }
			objective MinimiseCoupling maximise java { "models.moea.MaximiseCRA" }
		'''
	}
	
	def String getValidOclConstraint() {
		return "constraint MaximiseClassCount ocl { \"Class.allInstances()->size()\" }"
	}
	
	def String getValidJavaConstraint() {
		return "constraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }"
	}
	
	def String getValidConstraintsSet() {
		//TODO we need two constraints here for the sake of testing
		return getValidJavaConstraint
	}
	
	def String getValidEvolver() {
		return "breed using <craEvolvers.henshin> unit \"createClass\""
	}
	
	def String getValidEvolverSet() {
		return '''mutate using <craEvolvers.henshin> unit "createClass"
				  mutate using <craEvolvers.henshin> unit "assignFeature"
				  breed using <craEvolvers.henshin> unit "moveFeature"
				  breed using <craEvolvers.henshin> unit "deleteEmptyClass"'''
	}
	
	def String getValidModelPathSet() {
		return '''model <model.xmi>'''
	}
	
	def String getValidOptimisation() {
		return "optimisation provider moea algorithm NSGAII variation genetic evolutions 100000 population 100"
	}
	
}