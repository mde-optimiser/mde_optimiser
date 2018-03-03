package uk.ac.kcl.mdeoptimise.rulegen.metamodel

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import java.util.HashMap
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.emf.ecore.resource.Resource
import java.io.InvalidObjectException
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.XMIResource
import java.io.FileOutputStream
import java.io.File
import java.io.IOException
import org.eclipse.emf.ecore.EReference
import uk.ac.kcl.mdeoptimise.rulegen.exceptions.MultiplicityException
import org.eclipse.emf.ecore.EClass
import java.util.List
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import java.util.Stack

class RefinedMetamodelWrapper {

	EPackage refinedMetamodel;
	EPackage originalMetamodel;
	public HenshinResourceSet resourceSet;
	List<Multiplicity> refinedMultiplicities;
	private EClassifierInfoManagement originalMetamodelAnalyser;
	private EClassifierInfoManagement refinedMetamodelAnalyser;
	
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities) {
		this.originalMetamodel = EcoreUtil.copy(metamodel);
		this.refinedMetamodel = metamodel;
		this.refinedMultiplicities = refinedMultiplicities;
		this.resourceSet = new HenshinResourceSet()
		
		//TODO check what to do about this - I think we can stick with the solution metamodel to run the search on after the refinements have been applied.
		//var resource = resourceSet.createResource(URI.createURI("metamodel.ecore"))
		//resource.contents.add(this.refinedMetamodel)
		
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
		
		var container = refinedMetamodel.EClassifiers.filter[classifier|classifier.name.equals(edgeSource)].
			get(0) as EClass;
		var references = container.EAllStructuralFeatures.filter[reference|reference.name.equals(edgeName)].toList

		references.get(0) as EReference;
	}
	
	/**
	 * Returns a list of bidirectional references for which our algorithm can generate repair operations.
	 * For each of these references, we will then run the repair generation algorithm.
	 */
	def List<EReference> getBidirectionalReferences(EClass node) {
		
		return node.EAllReferences.filter[reference | reference.EOpposite != null].toList
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
	def boolean hasEdgesForSingleRepair(EClass node){
		
		return node.EAllReferences.filter[reference | reference.EOpposite != null].filter[reference |
			reference.lowerBound == 1 && reference.EOpposite.lowerBound == 1
		].empty == false
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
	def boolean hasEdgesForMultiRepair(EClass node){
		
		return node.EAllReferences.filter[reference | reference.EOpposite != null].filter[reference |
			reference.lowerBound > 1 && reference.EOpposite.lowerBound > 1
		].empty == false
	}
	
	def boolean hasInvalidEdgesForDeleteNode(EClass node) {
		
		//Check if the node has any opposide nodes with fixed multiplicity that would require a repair
		//operation for this node to be deleted
		var hasOppositeFixedReferences = node.EAllReferences.filter[reference | reference.EOpposite != null].filter[ reference |
			reference.EOpposite.lowerBound == reference.EOpposite.upperBound
		].empty
	
		//Check if the node has any unidirectional income edges that have fixed multiplicity and
		//would require a repair operation for this node to be deleted.
		var incomingReferenceSettings = EcoreUtil.UsageCrossReferencer.find(node, refinedMetamodel);
				
		var hasIncomingLbReferences = incomingReferenceSettings.filter[incomingReference | incomingReference instanceof EReference].filter[reference |
			var ref = (reference as EReference);
			
			if(ref.EOpposite == null && ref.EType.equals(node) && ref.lowerBound == ref.upperBound && ref.lowerBound > 0) {
				return true
			}
			
		].empty
		
		return !hasOppositeFixedReferences || !hasIncomingLbReferences
	}
}
