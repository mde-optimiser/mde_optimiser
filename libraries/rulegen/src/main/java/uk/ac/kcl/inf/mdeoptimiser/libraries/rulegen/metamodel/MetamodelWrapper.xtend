package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel

import java.util.List
import java.util.Stack
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException

class MetamodelWrapper {
	
	EPackage metamodel;
	String ruleType;
	
	EClassifierInfoManagement metamodelAnalyser;
	
	new(EPackage metamodel) {
		
		setMetamodel(metamodel)
		this.setMetamodelAnalyser();
		this.ruleType = "P"
	}
	
	new(EPackage metamodel, List<Multiplicity> multiplicityRefinements) {
		this.setMetamodel(metamodel, multiplicityRefinements);
		this.setMetamodelAnalyser();
		this.ruleType = "S"
	}
	
	def String ruleType(){
		this.ruleType;
	}
	
	def EPackage getMetamodel(){
		return this.metamodel;
	}
	
	private	def void setMetamodel(EPackage metamodel) {
		this.metamodel = metamodel;
	}
	
	private def void setMetamodel(EPackage metamodel, List<Multiplicity> multiplicityRefinements) {
		
		this.metamodel = metamodel
		
		multiplicityRefinements.forEach[multiplicity | 
			multiplicity.metamodel = this.getMetamodel;
			updateEdgeMultiplicities(multiplicity)
		]
	}
	
	
	def EClassifierInfoManagement getMetamodelAnalyser(){
		return this.metamodelAnalyser;
	}
	
	private def void setMetamodelAnalyser(){
		
		val metamodels = new Stack<EPackage>();
		metamodels.add(this.metamodel)
		
		this.metamodelAnalyser = new EClassifierInfoManagement();
		this.metamodelAnalyser.gatherInformation(false, metamodels);
	}
	
	def updateEdgeMultiplicities(Multiplicity multiplicity) {
		this.updateEdgeLowerBound(multiplicity);
		this.updateEdgeUpperBound(multiplicity);
		val edge = getEdge(multiplicity);

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity)
		}
	}


	private def updateEdgeLowerBound(Multiplicity multiplicity) {
		var edge = getEdge(multiplicity)

		if (edge.lowerBound <= multiplicity.lower) {
			edge.lowerBound = multiplicity.lower;
		} else {
			throw new MultiplicityException(
				"Invalid multiplicity range given for upper bound refinements. ",
				multiplicity.lower,
				multiplicity.upper,
				edge.lowerBound,
				edge.upperBound
			);
		}

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity)
		}
	}

	private def updateEdgeUpperBound(Multiplicity multiplicity) {
		var edge = getEdge(multiplicity)

		if ((edge.upperBound == -1 && multiplicity.upper >= edge.upperBound) ||
			(edge.upperBound > -1 && multiplicity.upper <= edge.upperBound)) {
			edge.upperBound = multiplicity.upper;
		} else {
			throw new MultiplicityException(
				"Invalid multiplicity range given for upper bound refinements. ",
				multiplicity.lower,
				multiplicity.upper,
				edge.lowerBound,
				edge.upperBound
			);
		}

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity)
		}
	}
	
	
	def EClass getNode(String nodeName){
		
		val nodeClassifier = this.getMetamodel.EClassifiers
			.filter[classifier | (classifier as EClass).name.equals(nodeName)].head as EClass
			
		return nodeClassifier
	}
	
	def EReference getEdge(Multiplicity multiplicity) {
		getEdge(multiplicity.source, multiplicity.edge);
	}
	
	
	def EReference getEdge(EReference originalMetamodelEdge){
		
		var node = getNode(originalMetamodelEdge.EContainingClass.name);
		
		return this.getEdge(node.name, originalMetamodelEdge.name);
	}
	
	
	def EReference getEdge(String edgeSource, String edgeName) {
		
		var container = this.getMetamodel.EClassifiers.filter[classifier|classifier.name.equals(edgeSource)].
			get(0) as EClass;
		var references = container.EAllStructuralFeatures.filter[reference|reference.name.equals(edgeName)].toList

		references.get(0) as EReference;
	}
}
