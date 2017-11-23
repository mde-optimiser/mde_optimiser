package uk.ac.kcl.mdeoptimise.rulegen.tests.utils

import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import uk.ac.kcl.mdeoptimise.rulegen.exceptions.MultiplicityException
import org.eclipse.emf.ecore.EClass
import java.util.List

class MetamodelGenerator {
	
	private EPackage metamodel;
	
	new(EPackage metamodel){
		this.metamodel = EcoreUtil.copy(metamodel) 
	}
	
	def EPackage generate(List<Multiplicity> multiplicities){	
		
		multiplicities.forEach[multiplicity | 
			updateEdgeLowerBound(multiplicity)
			updateEdgeUpperBound(multiplicity)
		]
		
		return this.metamodel;
	}
	
	private def updateEdgeLowerBound(Multiplicity multiplicity){ 
    var edge = getEdge(multiplicity) 
    
    edge.lowerBound = multiplicity.lower; 	
     
    if(!Multiplicity.checkMultiplicityRangeValidity(edge)) { 
      throw new MultiplicityException(multiplicity) 
    } 
  } 
   
  private def updateEdgeUpperBound(Multiplicity multiplicity){ 
    var edge = getEdge(multiplicity) 
    
	edge.upperBound = multiplicity.upper;
	    
    if(!Multiplicity.checkMultiplicityRangeValidity(edge)) { 
      throw new MultiplicityException(multiplicity) 
    }
  } 
   
  def EReference getEdge(Multiplicity multiplicity){ 
    getEdge(multiplicity.source, multiplicity.edge); 
  }
  
  def EReference getEdge(String edgeSource, String edgeName){ 
    var container = metamodel.EClassifiers.filter[classifier | classifier.name.equals(edgeSource)].get(0) as EClass; 
    var references = container.EAllStructuralFeatures.filter[ reference | reference.name.equals(edgeName)].toList 
     
    references.get(0) as EReference; 
  }
}