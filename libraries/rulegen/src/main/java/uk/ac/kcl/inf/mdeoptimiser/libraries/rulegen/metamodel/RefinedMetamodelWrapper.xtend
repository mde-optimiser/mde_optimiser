package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel

import java.io.IOException
import java.util.HashMap
import java.util.List
import java.util.Stack
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException

class RefinedMetamodelWrapper {

	EPackage refinedMetamodel;
	EPackage originalMetamodel;
	public HenshinResourceSet resourceSet;
	List<Multiplicity> refinedMultiplicities;
	EClassifierInfoManagement originalMetamodelAnalyser;
	EClassifierInfoManagement refinedMetamodelAnalyser;
	
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities) {
		this.originalMetamodel = EcoreUtil.copy(metamodel);
		this.refinedMetamodel = metamodel;
		this.refinedMultiplicities = refinedMultiplicities;
		this.resourceSet = new HenshinResourceSet()
		this.setOriginalMetamodelAnalyser();
		this.setRefinedMetamodelAnalyser();
	}
	
	def void setOriginalMetamodelAnalyser(){
			
		val metamodels = new Stack<EPackage>();
		metamodels.add(this.refinedMetamodel)
		
		this.originalMetamodelAnalyser = new EClassifierInfoManagement();
		this.originalMetamodelAnalyser.gatherInformation(false, metamodels);
	}
	
	def void setRefinedMetamodelAnalyser(){
		
		refinedMultiplicities.forEach[multiplicity | 
			multiplicity.metamodel = this.refinedMetamodel;
			updateEdgeMultiplicities(multiplicity)
		]
		
		val metamodels = new Stack<EPackage>();
		metamodels.add(this.refinedMetamodel)
		
		this.refinedMetamodelAnalyser = new EClassifierInfoManagement();
		this.refinedMetamodelAnalyser.gatherInformation(false, metamodels);
	}
	
	def EClassifierInfoManagement getRefinedMetamodelAnalyser(){
		return this.refinedMetamodelAnalyser;
	}
	
	def EClassifierInfoManagement getOriginalMetamodelAnalyser(){
		return this.originalMetamodelAnalyser;
	}
	

	def getRefinedMetamodel() {
		return this.refinedMetamodel;
	}

	def reloadOriginalMetamodel() {
		return this.originalMetamodel;
	}

	def saveMetamodel(String destinationPath) {
		
		var resource = resourceSet.createResource(URI.createURI(destinationPath))
		resource.contents.add(this.refinedMetamodel)

		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		try {
			resource.save(options);
			EPackage.Registry.INSTANCE.remove(this.refinedMetamodel.nsURI);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	def updateEdgeMultiplicities(Multiplicity multiplicity) {
		this.updateEdgeLowerBound(multiplicity);
		this.updateEdgeUpperBound(multiplicity);
		val edge = getRefinedEdge(multiplicity);

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity)
		}
	}

	private def updateEdgeLowerBound(Multiplicity multiplicity) {
		var edge = getRefinedEdge(multiplicity)

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
		var edge = getRefinedEdge(multiplicity)

		if ((edge.upperBound === -1 && multiplicity.upper >= edge.upperBound) ||
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

	def List<Multiplicity> getRefinedMultiplicities() {
		return this.refinedMultiplicities
	}

	/**
	 * Get the original node without reined multiplicities
	 */
	def EClass getOriginalNode(String nodeName){
		
		val nodeClassifier = originalMetamodel.EClassifiers
			.filter[classifier | (classifier as EClass).name.equals(nodeName)].head as EClass
			
		return nodeClassifier
	}
	
	def EClass getRefinedNode(String nodeName){
		
		val nodeClassifier = refinedMetamodel.EClassifiers
			.filter[classifier | (classifier as EClass).name.equals(nodeName)].head as EClass
			
		return nodeClassifier
	}
	
	def EReference getRefinedEdge(Multiplicity multiplicity) {
		getRefinedEdge(multiplicity.source, multiplicity.edge);
	}
	
	def EReference getRefinedEdge(EReference originalMetamodelEdge){
		
		var node = getRefinedNode(originalMetamodelEdge.EContainingClass.name);
		
		return this.getRefinedEdge(node.name, originalMetamodelEdge.name);
	}
	
	def EReference getRefinedEdge(String edgeSource, String edgeName) {
		
		var container = refinedMetamodel.EClassifiers.filter[classifier|classifier.name.equals(edgeSource)].head as EClass;
		var references = container.EAllStructuralFeatures.filter[reference|reference.name.equals(edgeName)].toList

		references.head as EReference;
	}
	
	/**
	 * Returns a list of bidirectional references for which our algorithm can generate repair operations.
	 * For each of these references, we will then run the repair generation algorithm.
	 */
	def List<EReference> getBidirectionalReferences(EClass node) {
		
		return node.EAllReferences.filter[reference | reference.EOpposite !== null].toList
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
	def boolean hasEdgesForSingleRepair(EClass node){
		
		return node.EAllReferences.filter[reference | reference.EOpposite !== null].filter[reference |
			reference.lowerBound === 1 && reference.EOpposite.lowerBound === 1
		].empty === false
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
	def boolean hasEdgesForMultiRepair(EClass node){
		
		return node.EAllReferences.filter[reference | reference.EOpposite !== null].filter[reference |
			reference.lowerBound > 1 && reference.EOpposite.lowerBound > 1
		].empty === false
	}
	
	def boolean hasInvalidEdgesForDeleteNode(EClass node) {
		
		//Check if the node has any opposide nodes with fixed multiplicity that would require a repair
		//operation for this node to be deleted
		var hasOppositeFixedReferences = node.EAllReferences.filter[reference | reference.EOpposite !== null].filter[ reference |
			reference.EOpposite.lowerBound === reference.EOpposite.upperBound
		].empty
	
		//Check if the node has any unidirectional income edges that have fixed multiplicity and
		//would require a repair operation for this node to be deleted.
		var incomingReferenceSettings = EcoreUtil.UsageCrossReferencer.find(node, refinedMetamodel);
				
		var hasIncomingLbReferences = incomingReferenceSettings.filter[incomingReference | incomingReference instanceof EReference].filter[reference |
			var ref = (reference as EReference);
			(ref.EOpposite === null && ref.EType.equals(node) && ref.lowerBound === ref.upperBound && ref.lowerBound > 0) return true
			
		].empty
		
		return !hasOppositeFixedReferences || !hasIncomingLbReferences
	}
}
