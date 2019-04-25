package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin

import java.util.Arrays
import java.util.List
import org.eclipse.emf.henshin.interpreter.EGraph
import org.eclipse.emf.henshin.interpreter.Engine
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import org.eclipse.emf.henshin.model.HenshinPackage
import org.eclipse.emf.henshin.model.ParameterKind
import org.eclipse.emf.henshin.model.Unit
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFactory
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine.HenshinEngineFactory

class HenshinExecutor {
	
	Engine engine;
	
	/**
	 * Using only the UnitApplicationImpl class to run both Units
	 * and Rules as this class implements functionality to run a single Rule.
	 */
	UnitApplicationImpl unitRunner
	RuleApplicationImpl ruleRunner
	
	IEvolverParametersFactory evolverParametersFactory
	
	List<Unit> mutationOperators;
	List<Unit> crossoverOperators;
	
	new(IEvolverParametersFactory evolverParametersFactory, List<Unit> mutationOperators, List<Unit> crossoverOperators, SolverSpec solverSpec) {
		
		this.engine = new HenshinEngineFactory(solverSpec).create

		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);

		this.unitRunner = new MdeoUnitApplicationImpl(engine)
		this.ruleRunner = new MdeoRuleApplicationImpl(engine)
		
		this.mutationOperators = mutationOperators;
		this.crossoverOperators = crossoverOperators;
	}

	
	def boolean runRuleOperator(Unit operator, EGraph graph, List<Solution> object) {

		ruleRunner.EGraph = graph
		ruleRunner.unit = operator

		if (operator.parameters !== null) {
			// TODO Not sure about this filter. Check what kind of parameter we would expect people
			// to pass in
			var inParameters = operator.parameters.filter[parameter|parameter.kind.equals(ParameterKind.IN)]

			if (!inParameters.empty) {
				inParameters.forEach [ parameter |
					ruleRunner.setParameterValue(
						parameter.name,
						evolverParametersFactory.getParameterValue(operator, parameter, object)
					)
				]
			}
		}

		// Run the selected Henshin Rule
		return ruleRunner.execute(null)

	}

	def boolean runUnitOperator(Unit operator, EGraph graph, List<Solution> object) {

		unitRunner.EGraph = graph
		unitRunner.unit = operator

		var inParameters = operator.parameters.filter[parameter|parameter.kind.equals(ParameterKind.IN)]

		if (!inParameters.empty) {
			inParameters.forEach [ parameter |
				unitRunner.setParameterValue(
					parameter.name,
					evolverParametersFactory.getParameterValue(operator, parameter, object)
				)
			]
		}

		// Run the selected Henshin Unit
		return unitRunner.execute(null)
	}
	
	def boolean operatorApplied(Unit operator, EGraphImpl graph, Solution candidateSolution) {
		
		if (operator.eClass().getClassifierID() === HenshinPackage.RULE) {
			return this.runRuleOperator(operator, graph, Arrays.asList(candidateSolution))
		} 

		return this.runUnitOperator(operator, graph, Arrays.asList(candidateSolution))
	}
	
	def List<Unit> getMutationOperators() {
		return this.mutationOperators
	}
	
	def List<Unit> getCrossoverOperators() {
		return this.crossoverOperators
	}
	
	def Engine getEngine(){
		return this.engine;
	}
	
}