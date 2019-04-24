package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.termination.condition

import java.util.List
import org.deeplearning4j.arbiter.optimize.api.termination.MaxCandidatesCondition
import org.deeplearning4j.arbiter.optimize.api.termination.TerminationCondition
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterSearchSpec

class TerminationConditionFactory {
	
	static TerminationConditionFactory factoryInstance
	
	private new(){}
	
	def static synchronized TerminationConditionFactory getINSTANCE() {
		
		if(factoryInstance === null) {
			factoryInstance = new TerminationConditionFactory();
		}
		
		return factoryInstance;
	}
	
	//TODO Make this a proper factory. What other termination conditions can we support 
	//Not sure about the time limit one?
	def List<TerminationCondition> get(ParameterSearchSpec parameterSearchSpec){
		
		var terminationConditionLimit = getCandidatesTerminationCondition(parameterSearchSpec);
		return #[new MaxCandidatesCondition(terminationConditionLimit)]
	}
	
	/**
	 * Get the configured algorithm algorithm name to use for generating parameter values.
	 * 
	 * @return algorithm name
	 */
	private def getCandidatesTerminationCondition(ParameterSearchSpec spec) {
		var algorithmParameter = getParameter(spec, "candidates")
		//These are just strategy names: random (should add grid, smac etc)
		return Integer.parseInt(algorithmParameter.value.numeric)
	}
	
	private def getParameter(ParameterSearchSpec parameterSearchSpec, String keyName){
		return parameterSearchSpec.parameters.filter[parameter | parameter.name.equals(keyName)].head
	}
}