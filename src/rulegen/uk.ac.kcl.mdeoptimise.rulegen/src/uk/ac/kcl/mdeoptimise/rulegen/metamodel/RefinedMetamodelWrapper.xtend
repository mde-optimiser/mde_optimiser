package uk.ac.kcl.mdeoptimise.rulegen.metamodel

import org.eclipse.emf.ecore.EPackage
import java.util.Set
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

class RefinedMetamodelWrapper { 
   
  EPackage refinedMetamodel; 
  EPackage originalMetamodel;
  public HenshinResourceSet resourceSet;
  List<Multiplicity> refinedMultiplicities; 
   
   
  new(EPackage metamodel, List<Multiplicity> refinedMultiplicities){ 
    this.originalMetamodel = metamodel; 
    this.refinedMetamodel = EcoreUtil.copy(metamodel);
    this.refinedMultiplicities = refinedMultiplicities;
  } 
   
  def getMetamodelWithUpperBoundRefinements(){ 
     
    refinedMultiplicities.forEach[multiplicity | this.updateEdgeUpperBound(multiplicity)] 
    return this.refinedMetamodel 
  } 
   
  def getRefinedMetamodel(){ 
    return this.refinedMetamodel; 
  } 
   
  def reloadOriginalMetamodel(){
    return this.originalMetamodel; 
  } 
 
  def EPackage loadMetamodel(String basePath, String metamodelPath){   
     
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(  
        "ecore", new EcoreResourceFactoryImpl());  
 
    this.resourceSet = new HenshinResourceSet(basePath); 
     
    var resource = resourceSet.registerDynamicEPackages(metamodelPath).head;     
    EPackage.Registry.INSTANCE.put(resource.getNsURI(), resource); 
 
    if (resource instanceof EPackage) { 
      return resource; 
    }     
     
    throw new InvalidObjectException("Loaded metamodel does not have a root element of type EPackage. Loaded metamodel: " + metamodelPath); 
  } 
   
  def saveMetamodel(String destinationPath){ 
    var resource = resourceSet.createResource(URI.createURI(destinationPath)) 
   
    resource.getContents().add(this.refinedMetamodel) 
   
    var options = new HashMap<String, Boolean>(); 
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);   
     
    try { 
      var ostream = new FileOutputStream(new File(destinationPath)); 
      resource.save(ostream, options); 
      EPackage.Registry.INSTANCE.remove(this.refinedMetamodel.nsURI); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 
   
  def updateEdgeMultiplicities(Multiplicity multiplicity){ 
    this.updateEdgeLowerBound(multiplicity); 
    this.updateEdgeUpperBound(multiplicity); 
    val edge = getEdge(multiplicity);
     
    if(!Multiplicity.checkMultiplicityRangeValidity(edge)) { 
      throw new MultiplicityException(multiplicity) 
    }
  }
   
  private def updateEdgeLowerBound(Multiplicity multiplicity){ 
    var edge = getEdge(multiplicity) 
    
    if(edge.lowerBound <= multiplicity.lower){
    	edge.lowerBound = multiplicity.lower; 	
    } else {
    	throw new MultiplicityException("Invalid multiplicity range given for upper bound refinements. ", 
    		multiplicity.lower, multiplicity.upper, edge.lowerBound, edge.upperBound
    	);
    }
     
    if(!Multiplicity.checkMultiplicityRangeValidity(edge)) { 
      throw new MultiplicityException(multiplicity) 
    } 
  } 
   
  private def updateEdgeUpperBound(Multiplicity multiplicity){ 
    var edge = getEdge(multiplicity) 
    
    if((edge.upperBound == -1 && multiplicity.upper >= edge.upperBound)
    	|| (edge.upperBound > -1 && multiplicity.upper <= edge.upperBound)){
    	edge.upperBound = multiplicity.upper;
    } else {
    	throw new MultiplicityException("Invalid multiplicity range given for upper bound refinements. ", 
    		multiplicity.lower, multiplicity.upper, edge.lowerBound, edge.upperBound
    	);
    }   
    
    if(!Multiplicity.checkMultiplicityRangeValidity(edge)) { 
      throw new MultiplicityException(multiplicity) 
    }
  } 
   
  def EReference getEdge(Multiplicity multiplicity){ 
    getEdge(multiplicity.source, multiplicity.edge); 
  } 
   
  def EReference getEdge(String edgeSource, String edgeName){ 
    var container = refinedMetamodel.EClassifiers.filter[classifier | classifier.name.equals(edgeSource)].get(0) as EClass; 
    var references = container.EAllStructuralFeatures.filter[ reference | reference.name.equals(edgeName)].toList 
     
    references.get(0) as EReference; 
  } 
   
  def List<Multiplicity> getRefinedMultiplicities(){ 
    return this.refinedMultiplicities 
  }
  
  def String getOclConstraint(Multiplicity multiplicity){
  	
  }
}