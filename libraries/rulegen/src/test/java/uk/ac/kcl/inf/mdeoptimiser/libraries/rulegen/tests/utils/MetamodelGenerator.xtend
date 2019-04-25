package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils

import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import java.util.List
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException

class MetamodelGenerator {
	
	EPackage metamodel;
	
	new(EPackage metamodel){
		this.metamodel = metamodel
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
