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

class RefinedMetamodelWrapper {

	EPackage refinedMetamodel;
	EPackage originalMetamodel;
	public HenshinResourceSet resourceSet;
	List<Multiplicity> refinedMultiplicities;

	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities) {
		this.originalMetamodel = metamodel;
		this.refinedMetamodel = EcoreUtil.copy(metamodel);
		this.refinedMultiplicities = refinedMultiplicities;
		this.resourceSet = new HenshinResourceSet()
		
		var resource = resourceSet.createResource(URI.createURI("metamodel.ecore"))
		resource.contents.add(this.refinedMetamodel)
		
		refinedMultiplicities.forEach[multiplicity | 
			multiplicity.metamodel = this.refinedMetamodel;
			updateEdgeMultiplicities(multiplicity)
		]
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

	def EReference getEdge(Multiplicity multiplicity) {
		getEdge(multiplicity.source, multiplicity.edge);
	}

	def EReference getEdge(String edgeSource, String edgeName) {
		var container = refinedMetamodel.EClassifiers.filter[classifier|classifier.name.equals(edgeSource)].
			get(0) as EClass;
		var references = container.EAllStructuralFeatures.filter[reference|reference.name.equals(edgeName)].toList

		references.get(0) as EReference;
	}

	def List<Multiplicity> getRefinedMultiplicities() {
		return this.refinedMultiplicities
	}
	
	def EClass getNode(String nodeName){
		
		val nodeClassifier = refinedMetamodel.EClassifiers
			.filter[classifier | (classifier as EClass).name.equals(nodeName)].head as EClass
			
		return nodeClassifier
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
			reference.lowerBound == 1
		].empty
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
	def boolean hasEdgesForMultiRepair(EClass node){
		
		return node.EAllReferences.filter[reference | reference.EOpposite != null].filter[reference |
			reference.lowerBound > 1
		].empty
	}
}
