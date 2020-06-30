package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException;

import java.util.List;
import java.util.Stack;

public class MetamodelWrapper {
	
	EPackage metamodel;
	String ruleType;
	
	EClassifierInfoManagement metamodelAnalyser;
	
	public MetamodelWrapper(EPackage metamodel) {
		
		setMetamodel(metamodel);
		this.setMetamodelAnalyser();
		this.ruleType = "P";
	}
	
	public MetamodelWrapper(EPackage metamodel, List<Multiplicity> multiplicityRefinements) {
		this.setMetamodel(metamodel, multiplicityRefinements);
		this.setMetamodelAnalyser();
		this.ruleType = "S";
	}
	
	public String ruleType(){
		return this.ruleType;
	}
	
	public EPackage getMetamodel(){
		return this.metamodel;
	}
	
	private void setMetamodel(EPackage metamodel) {
		this.metamodel = metamodel;
	}
	
	private void setMetamodel(EPackage metamodel, List<Multiplicity> multiplicityRefinements) {
		
		this.metamodel = metamodel;
		
		multiplicityRefinements.forEach(multiplicity -> {
      multiplicity.metamodel = this.getMetamodel();
      updateEdgeMultiplicities(multiplicity);
    });
	}


  public EClassifierInfoManagement getMetamodelAnalyser(){
		return this.metamodelAnalyser;
	}
	
	private void setMetamodelAnalyser(){
		
		var metamodels = new Stack<EPackage>();
		metamodels.add(this.metamodel);
		
		this.metamodelAnalyser = new EClassifierInfoManagement();
		this.metamodelAnalyser.gatherInformation(false, metamodels);
	}

  public void updateEdgeMultiplicities(Multiplicity multiplicity) {
		this.updateEdgeLowerBound(multiplicity);
		this.updateEdgeUpperBound(multiplicity);
		var edge = getEdge(multiplicity);

		if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
			throw new MultiplicityException(multiplicity);
		}
	}


	private void updateEdgeLowerBound(Multiplicity multiplicity) {
		var edge = getEdge(multiplicity);

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
		var edge = getEdge(multiplicity);

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


  public EClass getNode(String nodeName){
		
		return (EClass) this.getMetamodel().getEClassifiers().stream()
			.filter(classifier -> classifier.getName().equals(nodeName)).findFirst().get();
	}

  public EReference getEdge(Multiplicity multiplicity) {
		return getEdge(multiplicity.source, multiplicity.edge);
	}


  public EReference getEdge(EReference originalMetamodelEdge){
		
		var node = getNode(originalMetamodelEdge.getEContainingClass().getName());
		
		return this.getEdge(node.getName(), originalMetamodelEdge.getName());
	}

  public EReference getEdge(String edgeSource, String edgeName) {
		
		var container = (EClass) this.getMetamodel().getEClassifiers().stream().filter(classifier -> classifier.getName().equals(edgeSource)).findFirst().get();
		return (EReference) container.getEAllStructuralFeatures().stream().filter(reference -> reference.getName().equals(edgeName)).findFirst().get();
	}
}
