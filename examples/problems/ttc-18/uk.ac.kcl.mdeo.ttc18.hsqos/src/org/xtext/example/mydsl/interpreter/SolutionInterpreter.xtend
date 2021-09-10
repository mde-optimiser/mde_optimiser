package org.xtext.example.mydsl.interpreter

import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.xtext.example.mydsl.myDsl.Assignment
import org.xtext.example.mydsl.myDsl.Property
import org.xtext.example.mydsl.myDsl.Clause
import org.xtext.example.mydsl.myDsl.ClauseType
import org.xtext.example.mydsl.myDsl.Component
import org.xtext.example.mydsl.myDsl.ComponentMapping
import org.xtext.example.mydsl.myDsl.ComponentRequirement
import org.xtext.example.mydsl.myDsl.Implementation
import org.xtext.example.mydsl.myDsl.Instance
import org.xtext.example.mydsl.myDsl.LiteralExpression
import org.xtext.example.mydsl.myDsl.MetaParameter
import org.xtext.example.mydsl.myDsl.Objective
import org.xtext.example.mydsl.myDsl.Request
import org.xtext.example.mydsl.myDsl.Solution

import static extension org.xtext.example.mydsl.interpreter.ExpressionInterpreter.*
import java.util.ArrayList
import org.xtext.example.mydsl.myDsl.Resource
import java.util.List
import org.xtext.example.mydsl.myDsl.Model
import org.xtext.example.mydsl.myDsl.ResourceRequirement
import org.xtext.example.mydsl.myDsl.ResourceType

class SolutionInterpreter {

	// Architectural constraints
	/**
	 * Ensure that the solution contains all the problem requests
	 */
	static def int checkThatAllRequestsAreFulfilled(Solution solution) {		
		val requests = (solution.eContainer as Model).requests
		
		requests.size - solution.assignments.map[a | a.request].filter[
			r | requests.contains(r)
		].size
	}

	/**
	 * Check that requests are not added twice to the solution with mapped components
	 */
	static def int checkEachResourceHasAtMostOneImplementationMapped(Solution solution){

		val resources = solution.assignments		
			.map[ a | a.findResourceMappings(new ArrayList<Resource>)]
			.reduce[a, b | a.addAll(b) return a]
					
		return resources.length - new HashSet<Resource>(resources).length	
	}


	static def int checkComponentsOnlyMappedOnce(Solution solution) {
		solution.assignments.map[a|solution.checkRequestOnlyMapsEachComponentOnce(a.request)].reduce [count1, count2 |
			count1 + count2
		]
	}
	
	/**
	 * Returns the number of components that are mapped to more than one implementation for the given request and solution
	 */
	private static def int checkRequestOnlyMapsEachComponentOnce(Solution solution, Request r) {
		solution.assignments.filter[a|a.request === r].map[a|a.findComponentMappings].map [ cm |
			cm.keySet.filter[component|cm.get(component).size !== 1].size
		].reduce[count1, count2|count1 + count2]
	}

	// Request constraints
	
	static def int checkAllRequestRequiredComponentsAreImplemented(Solution solution) {
		
		val requestCompnents = (solution.eContainer as Model).requests.map[r | r.component]
		
		var components = solution.assignments.map[a | a.implementation.eContainer as Component]
		
			return components.filter[ c | !requestCompnents.contains(c)].size
		
	}
	
	
	/**
	 * Ensure that request non-functional requirements are met by its mapped component
	 */
	 static def int checkAllRequestNonFunctionalRequirementsAreMet(Solution solution){
	 	
	 	val requests = solution.assignments.fold(0, [count, assignment | 
	 		count + assignment.checkRequestNonFunctionalRequirementsAreMet
	 	]);
	 	
	 	return requests;
	 }

	 
	 private static def int checkRequestNonFunctionalRequirementsAreMet(Assignment a){
	 	
	 	return a.request.requirements.fold(0, [count, requirement | 
	 		
	 		val providedProperties = a.implementation.clauses
	 				.findFirst[ fp | fp.type === ClauseType.PROVIDING && fp.LHS.property === requirement.LHS.property ]
	 		
	 		if(!satisfiedProvidingClause(requirement, providedProperties, getEnvironment(a.request))) {
	 			return count + 1
	 		}
	 		
	 		count
	 	])	 
	 }
	 
	 private static def boolean satisfiedProvidingClause(Clause requested, Clause implemented, Map<MetaParameter, LiteralExpression> env) {
		val rhsValue = requested.RHS.eval(env)
		val lhsValue = implemented.RHS.eval(env)

		switch (requested.comp) {
			case EQ: return lhsValue == rhsValue
			case GE: return lhsValue >= rhsValue
			case GT: return lhsValue > rhsValue
			case LE: return lhsValue <= rhsValue
			case LT: return lhsValue < rhsValue
			case NE: return lhsValue != rhsValue
		}
	}
	
	// Negotiation constraints
	
	/**
	 * Ensure that non-functional requirements are met by depending implementations
	 */
	static def int checkAllImplementationRequirementsAreMet(Solution solution) {
			solution.assignments.map[a|solution.checkAllImplementationRequirementsAreMet(a.request, false, false, true)].reduce [count1, count2 |
				count1 + count2
			]
	}
	
	
	static def int checkAllImplementationRequirementsAreMet(Solution solution, boolean brokenRequirements, boolean assignedResources, boolean requiringClausesSatisfaction) {
			solution.assignments.map[a|solution.checkAllImplementationRequirementsAreMet(a.request, 
				brokenRequirements, assignedResources, requiringClausesSatisfaction
			)].reduce [count1, count2 | count1 + count2]
	}
	
	private static def int checkAllImplementationRequirementsAreMet(Solution solution, Request r, 
		boolean brokenRequirements, boolean assignedResources, boolean requiringClausesSatisfaction
	) {
			solution.assignments.filter[a|a.request === r]
			.map[a|a.findBrokenRequirements(a.request.environment, brokenRequirements, assignedResources, requiringClausesSatisfaction)]
			.reduce [count1, count2 | count1 + count2]
	}

	// Objective function
	
	static def double calculateObjective(Solution solution, Objective objective) {
		val propertyValues = new ArrayList<Double>();
		
		solution.assignments.forEach[
			
			assignment | 
				propertyValues.add(assignment.calculatePropertyValue(objective.property))
				
				assignment.componentMappings.iterator.forEach[component | 
					propertyValues.add(component.assignment.calculatePropertyValue(objective.property))
				]
			]
	
		switch (objective.agg) {
			case MAX: return propertyValues.fold(0.0, [acc, pv|Math.max(acc, pv)])
			case SUM: return propertyValues.fold(0.0, [acc, pv|acc + pv])
		}
	}
	

	private static def List<Resource> findResourceMappings(Assignment a, ArrayList<Resource> found) {
		
		found.addAll(a.resourceMappings.map [rm | rm.usedResource])
		a.componentMappings.forEach [ rm | rm.assignment.findResourceMappings(found)]

		found
	}
	

	private static def double calculatePropertyValue(Assignment a, Property p) {
		
		val clauses = a.implementation.clauses.filter[c|c.type === ClauseType.PROVIDING && c.LHS.property === p]

		if (clauses.size == 0) {
			
			return Integer.MAX_VALUE
		
		} else if(clauses.size > 1) {
			//throw new IllegalStateException("More than one providing clause for property " + p.name)
			
			println("More than one providing clause for property ")
			return Integer.MAX_VALUE
		} else {
			
			// If we call this from a component mapping
			if(a.request === null){			
				return clauses.head.RHS.eval((a.eContainer.eContainer as Assignment).request.environment)
			}
			
			clauses.head.RHS.eval(a.request.environment)
		
		}
	}
	

	private static def findComponentMappings(Assignment a) {
		a.findComponentMappings(new HashMap<Component, Set<Implementation>>)
	}
	

	private static def Map<Component, Set<Implementation>> findComponentMappings(Assignment a, Map<Component, Set<Implementation>> found) {
		val component = if (a.request !== null) {
				a.request.component
			} else {
				(a.eContainer as ComponentMapping).component.component
			}

		var mappingForComponent = found.get(component)
		if (mappingForComponent === null) {
			mappingForComponent = new HashSet<Implementation>
			found.put(component, mappingForComponent)
		}

		mappingForComponent.add(a.implementation)

		a.componentMappings.forEach [ cm |
			cm.assignment.findComponentMappings(found)
		]

		found
	}


	private static def int findBrokenRequirements(Assignment a, Map<MetaParameter, LiteralExpression> env, 
		boolean brokenRequirements, boolean assignedResources, boolean requiringClausesSatisfaction
	) {
		// Check that we're assigning implementations for all component requirements
		var componentRequirements = a.implementation.componentRequirements
		
		var brokenRequirementsCount = 0
		// Separated in ACMinimiseNumberOfMissingComponentImplementations
		if(brokenRequirements){
				
			brokenRequirementsCount = componentRequirements.fold(0, [ acc, cr |
				 if(a.componentMappings.exists[cm|cm.component === cr.instance]) {
					return acc
				} else {
					//println("Missing component " + cr.instance.name)
					return acc + 1
				}
			]) 	
		}
				
		 // Check that we're assigning resources for all resource requirements
		var assignedResourcesCount = 0 
		
		if(assignedResources) {
			assignedResourcesCount = a.implementation.resourceRequirements.fold(0, [ acc, rr |
				acc + if(a.resourceMappings.exists[rm|rm.requiredResource === rr.instance]) 0 else 1
			])  // Check that we're satisfying all requiring clauses		
		}

		var requiringClausesSatisfactionCount = 0
		
		if(requiringClausesSatisfaction) {
			requiringClausesSatisfactionCount = a.implementation.clauses.filter[c|c.type === ClauseType.REQUIRING].fold(0, [ acc, c |
				acc + if(c.satisfiedRequiringClause(a, env)) 0 else 1
			]) 
		}

		// Check that all sub-assignments are also valid
		var recursiveCheckCount = a.componentMappings.fold(0, [ acc, cm |
			acc + cm.assignment.findBrokenRequirements(env, brokenRequirements, assignedResources, requiringClausesSatisfaction)
		])
	
		return brokenRequirementsCount + assignedResourcesCount + recursiveCheckCount + requiringClausesSatisfactionCount
	}
	
	private static def boolean satisfiedRequiringClause(Clause requested, Assignment assignment, Map<MetaParameter, LiteralExpression> env) {

		// Check clause type / resource or component
			
		val requestedProperty = requested.LHS.property
		
		val requestedValue = requested.RHS.eval(env)
				
		var implementationValue = 0d
		
		
		if(requested.LHS.instance.eContainer instanceof ResourceRequirement){
		
			try {
				val reqRes = (requested.LHS.instance.eContainer.eContainer as ResourceRequirement).instance
		
				var usedValue = assignment.resourceMappings.findFirst[rm | rm.requiredResource === reqRes]
					.subMappings.findFirst[sm | sm.requiredResource == requested.LHS.instance]
					.usedResource.values.findFirst[v | v.property == requested.LHS.property]
							
				implementationValue = usedValue.value.eval(env)
			} catch (Exception e){
				println(e.message)
				return false;
			}

		} else {
							
			val implementationComponent = (assignment.componentMappings.findFirst[cm | cm.component === requested.LHS.instance])
		
			//Is there an implementation to check against?
			if(implementationComponent === null ) {
				return false
			}
		
			implementationValue = implementationComponent.assignment.implementation.clauses
								.findFirst[clause | clause.type === ClauseType.PROVIDING && clause.LHS.property === requestedProperty].RHS.eval(env)
		}
		
		// For the asigned resource/component, check that the clause is satisfied.

		switch (requested.comp) {
			case EQ: return requestedValue == implementationValue
			case GE: return implementationValue >= requestedValue
			case GT: return implementationValue > requestedValue
			case LE: return implementationValue <= requestedValue
			case LT: return implementationValue < requestedValue
			case NE: return implementationValue != requestedValue
		}
	}	

	private static def getEnvironment(Request r) {
		val metaParametersMap = new HashMap<MetaParameter, LiteralExpression>
		if(r !== null){
			r.parameterAssignments.forEach [ parameter |
				metaParametersMap.put(parameter.parameter, parameter.value)
			]
		}
		metaParametersMap
	}

	private static def getComponent(Instance i) {
		(i.eContainer as ComponentRequirement).type
	}
	
	
	def static Resource findClausesSatisfyingResource(Request request, Implementation i){
		
		
		val model = request.eContainer as Model
		
		val requiredResourceClauses = i.clauses.filter[c | c.type === ClauseType.REQUIRING && c.LHS.property.eContainer instanceof ResourceType].toList
		
		val env = getEnvironment(request)
		
		
		val requiredResourceValues = requiredResourceClauses.fold(new HashMap<Clause, Double>, 
			[ map, c | map.put(c, c.RHS.eval(env)) return map ])
		
		
		return model.hardwareModel.resources.filter[r |
			r.checkResourceValidity(requiredResourceValues, env)
		].head
	
	}
	
	private static def boolean checkResourceValidity(Resource containingResource, Map<Clause, Double> required,  Map<MetaParameter, LiteralExpression> env){

		var valid = false;
		
		val containedResources = containingResource.resources.fold(new HashMap<Property, Double>, [list, r | list.putAll(r.values.fold(new HashMap<Property, Double>, [map, v | map.put(v.property, v.value.eval(env)) return map])) return list])
		
		for(value : containedResources.keySet){
			
			var requiredValue = required.keySet.findFirst[p | p.LHS.property.name === value.name]
						
			if(requiredValue.compareResourceValues(required.get(requiredValue), containedResources.get(value))){
				valid = true
			} else {
				valid = false
			}
		}
		
		return valid
	}
	
	private static def compareResourceValues(Clause requiredClause, Double requireValue, Double providedValue){
		
		switch (requiredClause.comp) {
			case EQ: return requireValue == providedValue
			case GE: return requireValue >= providedValue
			case GT: return requireValue > providedValue
			case LE: return requireValue <= providedValue
			case LT: return requireValue < providedValue
			case NE: return requireValue != providedValue
		}
	}
}
