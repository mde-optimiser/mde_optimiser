package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import java.util.List
import java.util.ArrayList
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import java.util.LinkedList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.Unit
import java.util.Random
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import org.eclipse.emf.henshin.model.HenshinPackage

class GraphTransformationCrossover implements CrossoverStrategy {
	
	HenshinExecutor henshinExecutor
	List<Unit> breedingOperators
	
	new(HenshinExecutor henshinExecutor, List<Unit> breedingOperators) {
		this.henshinExecutor = henshinExecutor
		this.breedingOperators = breedingOperators
	}
	
	override breed(List<Solution> parents) {
		
		val crossoverParents = parents.fold(new ArrayList<Solution>) [crossover, parent |
			
			crossover.add(new Solution(parent))	
			return crossover
		];

		// TODO This seems a bit off, but perhaps we get rid of crossover altogether anyway
		val graph = new EGraphImpl(crossoverParents.fold(new LinkedList<EObject>()) [ parentsList, parent |

			parentsList.add(parent.getModel)
			return parentsList;
		])

		val triedOperators = new ArrayList<Unit>()

		// Randomly pick one unit
		var operator = breedingOperators.get(new Random().nextInt(breedingOperators.size()))

		while (triedOperators.length < breedingOperators.length) {

			if (operator.eClass().getClassifierID() == HenshinPackage.RULE) {
				// Run the selected Henshin Rule
				if (henshinExecutor.runRuleOperator(operator, graph, parents)) {
			
					return graph.roots.fold(new LinkedList<Solution>()) [ parentsList, root |

						parentsList.add(new Solution(root, new LinkedList<String>))
						return parentsList
					]
				}
			} else {
				if (henshinExecutor.runUnitOperator(operator, graph, parents)) {
			
					return graph.roots.fold(new LinkedList<Solution>()) [ parentsList, root |

						parentsList.add(new Solution(root, new LinkedList<String>()))
						return parentsList

					]
				}
			}

			triedOperators.add(operator)
			var remainingRules = breedingOperators.filter[o|!triedOperators.contains(o)];

			if (remainingRules.size == 0) {
				return parents
			}

			operator = remainingRules.get(new Random().nextInt(remainingRules.size()))

		}

		// We didn't find any applicable evolvers...
		System.out.println("Model with no crossover evolvers applicable.....")

		return parents
	}
	
}