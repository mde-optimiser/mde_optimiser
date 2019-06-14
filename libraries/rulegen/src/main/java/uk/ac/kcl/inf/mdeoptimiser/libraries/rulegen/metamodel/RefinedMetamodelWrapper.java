package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException;

public class RefinedMetamodelWrapper {

	EPackage refinedMetamodel;
	EPackage originalMetamodel;
	public HenshinResourceSet resourceSet;
	List<Multiplicity> refinedMultiplicities;
	EClassifierInfoManagement originalMetamodelAnalyser;
	EClassifierInfoManagement refinedMetamodelAnalyser;
	
	public RefinedMetamodelWrapper(EPackage metamodel, List<Multiplicity> refinedMultiplicities) {
		this.originalMetamodel = EcoreUtil.copy(metamodel);
		this.refinedMetamodel = metamodel;
		this.refinedMultiplicities = refinedMultiplicities;
		this.resourceSet = new HenshinResourceSet();
		this.setOriginalMetamodelAnalyser();
		this.setRefinedMetamodelAnalyser();
	}

  public void setOriginalMetamodelAnalyser(){
			
		var metamodels = new Stack<EPackage>();
		metamodels.add(this.refinedMetamodel);
		
		this.originalMetamodelAnalyser = new EClassifierInfoManagement();
		this.originalMetamodelAnalyser.gatherInformation(false, metamodels);
	}

  public void setRefinedMetamodelAnalyser(){
		
		refinedMultiplicities.forEach(multiplicity -> {
			multiplicity.metamodel = this.refinedMetamodel;
			updateEdgeMultiplicities(multiplicity);
		});
		
		var metamodels = new Stack<EPackage>();
		metamodels.add(this.refinedMetamodel);
		
		this.refinedMetamodelAnalyser = new EClassifierInfoManagement();
		this.refinedMetamodelAnalyser.gatherInformation(false, metamodels);
	}

  public EClassifierInfoManagement getRefinedMetamodelAnalyser(){
		return this.refinedMetamodelAnalyser;
	}

  public EClassifierInfoManagement getOriginalMetamodelAnalyser(){
		return this.originalMetamodelAnalyser;
	}


  public EPackage getRefinedMetamodel() {
		return this.refinedMetamodel;
	}

  public EPackage reloadOriginalMetamodel() {
		return this.originalMetamodel;
	}

  public void saveMetamodel(String destinationPath) {
		
		var resource = resourceSet.createResource(URI.createURI(destinationPath));
		resource.getContents().add(this.refinedMetamodel);

		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		try {
			resource.save(options);
			EPackage.Registry.INSTANCE.remove(this.refinedMetamodel.getNsURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

  public void updateEdgeMultiplicities(Multiplicity multiplicity) {
		this.updateEdgeLowerBound(multiplicity);
		this.updateEdgeUpperBound(multiplicity);
		var edge = getRefinedEdge(multiplicity);

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity);
		}
	}

	private void updateEdgeLowerBound(Multiplicity multiplicity) {
		var edge = getRefinedEdge(multiplicity);

		if (edge.getLowerBound() <= multiplicity.lower) {
			edge.setLowerBound(multiplicity.lower);
		} else {
			throw new MultiplicityException(
				"Invalid multiplicity range given for upper bound refinements. ",
				multiplicity.lower,
				multiplicity.upper,
				edge.getLowerBound(),
				edge.getUpperBound()
			);
		}

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity);
		}
	}

	private void updateEdgeUpperBound(Multiplicity multiplicity) {
		var edge = getRefinedEdge(multiplicity);

		if ((edge.getUpperBound() == -1 && multiplicity.upper >= edge.getUpperBound()) ||
			(edge.getUpperBound() > -1 && multiplicity.upper <= edge.getUpperBound())) {
			edge.setUpperBound(multiplicity.upper);
		} else {
			throw new MultiplicityException(
				"Invalid multiplicity range given for upper bound refinements. ",
				multiplicity.lower,
				multiplicity.upper,
				edge.getLowerBound(),
				edge.getUpperBound()
			);
		}

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity);
		}
	}

  public List<Multiplicity> getRefinedMultiplicities() {
		return this.refinedMultiplicities;
	}

	/**
	 * Get the original node without reined multiplicities
	 */
  public EClass getOriginalNode(String nodeName){
		
		var nodeClassifier = (EClass) originalMetamodel.getEClassifiers().stream()
            .filter(classifier -> ((EClass) classifier).getName().equals(nodeName)).findFirst().get();
			
		return nodeClassifier;
	}

  public EClass getRefinedNode(String nodeName){
		
		var nodeClassifier = (EClass) refinedMetamodel.getEClassifiers().stream()
			.filter(classifier -> ((EClass) classifier).getName().equals(nodeName)).findFirst().get();
			
		return nodeClassifier;
	}

  public EReference getRefinedEdge(Multiplicity multiplicity) {
		return getRefinedEdge(multiplicity.source, multiplicity.edge);
	}

  public EReference getRefinedEdge(EReference originalMetamodelEdge){
		
		var node = getRefinedNode(originalMetamodelEdge.getEContainingClass().getName());
		
		return this.getRefinedEdge(node.getName(), originalMetamodelEdge.getName());
	}

  public EReference getRefinedEdge(String edgeSource, String edgeName) {
		
		var container = (EClass) refinedMetamodel.getEClassifiers().stream().filter(classifier -> classifier.getName().equals(edgeSource)).findFirst().get();

    return (EReference) container.getEAllStructuralFeatures().stream().filter(ref -> ref.getName().equals(edgeName)).findFirst().get();
	}
	
	/**
	 * Returns a list of bidirectional references for which our algorithm can generate repair operations.
	 * For each of these references, we will then run the repair generation algorithm.
	 */
  public List<EReference> getBidirectionalReferences(EClass node) {
		
		return node.getEAllReferences().stream().filter(reference -> reference.getEOpposite() != null).collect(Collectors.toList());
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
  public boolean hasEdgesForSingleRepair(EClass node){
		
		return node.getEAllReferences().stream().anyMatch(reference -> reference.getEOpposite() != null &&
			reference.getLowerBound() == 1 && reference.getEOpposite().getLowerBound() == 1);
	}
	
	/**
	 * Checks if the specified node has any outgoing edges requiring a multi repair
	 */
  public boolean hasEdgesForMultiRepair(EClass node){
		
		return node.getEAllReferences().stream().anyMatch(reference -> reference.getEOpposite() != null &&
			reference.getLowerBound() > 1 && reference.getEOpposite().getLowerBound() > 1);
	}

  public boolean hasInvalidEdgesForDeleteNode(EClass node) {

    //Check if the node has any opposide nodes with fixed multiplicity that would require a repair
    //operation for this node to be deleted
    var hasOppositeFixedReferences = node.getEAllReferences().stream().anyMatch(reference -> reference.getEOpposite() != null &&
            reference.getEOpposite().getLowerBound() == reference.getEOpposite().getUpperBound());

    //Check if the node has any unidirectional income edges that have fixed multiplicity and
    //would require a repair operation for this node to be deleted.
    var incomingReferenceSettings = EcoreUtil.UsageCrossReferencer.find(node, refinedMetamodel);

    var hasIncomingLbReferences = incomingReferenceSettings
            .stream()
            .filter(incomingReference -> incomingReference instanceof EReference)
            .anyMatch(reference -> {
              var ref = (EReference) reference;
              return ref.getEOpposite() == null && ref.getEType().equals(node) && ref.getLowerBound() == ref.getUpperBound() && ref.getLowerBound() > 0;
            });

    return hasOppositeFixedReferences || hasIncomingLbReferences;
  }
}
