package org.xtext.example.mydsl.interpreter

import java.util.Map
import org.xtext.example.mydsl.myDsl.AddExpression
import org.xtext.example.mydsl.myDsl.ComponentMapping
import org.xtext.example.mydsl.myDsl.ComponentRequirement
import org.xtext.example.mydsl.myDsl.Expression
import org.xtext.example.mydsl.myDsl.Implementation
import org.xtext.example.mydsl.myDsl.Instance
import org.xtext.example.mydsl.myDsl.LiteralExpression
import org.xtext.example.mydsl.myDsl.MetaParameter
import org.xtext.example.mydsl.myDsl.MetaParameterDesignator
import org.xtext.example.mydsl.myDsl.Model
import org.xtext.example.mydsl.myDsl.Property
import org.xtext.example.mydsl.myDsl.PropertyDesignator
import org.xtext.example.mydsl.myDsl.Resource
import org.xtext.example.mydsl.myDsl.ResourceMapping

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.xtext.example.mydsl.myDsl.ClauseType
import org.xtext.example.mydsl.myDsl.SubExpression
import org.xtext.example.mydsl.myDsl.MultExpression
import org.xtext.example.mydsl.myDsl.DivExpression
import org.xtext.example.mydsl.myDsl.PowExpression
import org.xtext.example.mydsl.myDsl.PropertyDesignatorWithOptionalInstance

class ExpressionInterpreter {
	static dispatch def double eval(Expression exp, Map<MetaParameter, LiteralExpression> env) { throw new RuntimeException("Expression should not be instantiated directly!") }
	
	static dispatch def double eval(AddExpression exp, Map<MetaParameter, LiteralExpression> env) {
		exp.left.eval(env) + exp.right.eval(env)
	}
	
	static dispatch def double eval(SubExpression exp, Map<MetaParameter, LiteralExpression> env) {
		exp.left.eval(env) - exp.right.eval(env)
	}
	

	static dispatch def double eval(MultExpression exp, Map<MetaParameter, LiteralExpression> env) {
		exp.left.eval(env) * exp.right.eval(env)
	}
	
	static dispatch def double eval(DivExpression exp, Map<MetaParameter, LiteralExpression> env) {
		exp.left.eval(env) / exp.right.eval(env)
	}
	
	static dispatch def double eval(PowExpression exp, Map<MetaParameter, LiteralExpression> env) {
		return Math.pow(exp.left.eval(env), exp.right.eval(env))
	}
	
	
	static dispatch def double eval(MetaParameterDesignator exp, Map<MetaParameter, LiteralExpression> env) {
		env.get(exp.metaParameter).eval(env)
	}
	
	static dispatch def double eval(PropertyDesignator pd, Map<MetaParameter, LiteralExpression> env) {
		pd.instance.getPropertyValue(pd.property, env)
	}

	static dispatch def double eval(PropertyDesignatorWithOptionalInstance pd, Map<MetaParameter, LiteralExpression> env) {
		if (pd.instance !== null){
			pd.instance.getPropertyValue(pd.property, env)
		} else {
			throw new IllegalArgumentException("Cannot calculate the value of a PropertyDesignatorWithOptionalInstance.")
		}
	}
	
	static dispatch def double eval(LiteralExpression exp, Map<MetaParameter, LiteralExpression> env) {
		exp.value
	}
	
	static dispatch def double getPropertyValue(Instance instance, Property property, Map<MetaParameter, LiteralExpression> env) {
		// TODO: Handle the case of no instance, so we can also compute values for clauses?
		val instanceContext = instance.eContainer
		
		if (instanceContext instanceof ComponentRequirement) {
			var componentMapping = (instance.rootContainer as Model).solution.getAllContentsOfType(ComponentMapping).findFirst[cm | cm.component === instance]
			
			if(componentMapping !== null){
				return componentMapping.assignment.implementation.getPropertyValue(property, env)
			}
			//TODO: What to do if the component is not present in the solution yet.
			return Integer.MAX_VALUE
			//throw new IllegalArgumentException("Cannot calculate the value of a PropertyDesignatorWithOptionalInstance.")
		} else {
			var resourceContainer = (instance.rootContainer as Model).solution.getAllContentsOfType(ResourceMapping)
			
			var resource = resourceContainer.findFirst[rm | rm.requiredResource === instance]
			
			if(resource != null){
				resource.usedResource.getPropertyValue(property, env)
			} else {
				return Integer.MAX_VALUE
				//throw new IllegalArgumentException("Cannot calculate the value of a PropertyDesignatorWithOptionalInstance.")
			}
		}
	}

	static dispatch def double getPropertyValue(Implementation implementation, Property property, Map<MetaParameter, LiteralExpression> env) {
		val clause = implementation.clauses.findFirst[c | c.LHS.property === property && c.type === ClauseType.PROVIDING]
		if (clause !== null) {
			clause.RHS.eval(env)
		} else {
			throw new IllegalStateException("Couldn't find suitable providing clause for property " + property)
		}
	}

	static dispatch def double getPropertyValue(Resource resource, Property property, Map<MetaParameter, LiteralExpression> env) {
		val value = resource.values.findFirst[crv | crv.property === property]
		
		if (value !== null) {
			value.value.eval(env)
		} else {
			throw new IllegalStateException("Couldn't find suitable providing clause for property " + property)
		
			//return 0
		}
	}
}