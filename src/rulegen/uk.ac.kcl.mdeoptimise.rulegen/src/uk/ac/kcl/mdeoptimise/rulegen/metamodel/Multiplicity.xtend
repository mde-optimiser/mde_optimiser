package uk.ac.kcl.mdeoptimise.rulegen.metamodel

import uk.ac.kcl.mdeoptimise.rulegen.exceptions.MultiplicityException
import org.eclipse.emf.ecore.EReference

class Multiplicity {
	
	public Integer lower;
	public Integer upper;
	public String source;
	public String edge;
	
	new(String sourceNode, String edge, Integer lower, Integer upper){
		this.source = sourceNode;
		this.edge = edge;
		
		if(!checkMultiplicityRangeValidity(lower, upper)){
			throw new MultiplicityException("Invalid multiplicity values given.", lower, upper);
		}
	
		setLower(lower);
		setUpper(upper);
	}
	
	new(EReference edge){
		this(edge.EType.name, edge.name, edge.lowerBound, edge.upperBound);
		
	}
	
	private def void setUpper(Integer upper){
		this.upper = upper;
	}
	
	private def void setLower(Integer lower){
		this.lower = lower;
	}
	
	static def boolean checkMultiplicityRangeValidity(EReference edge){
		return checkMultiplicityRangeValidity(edge.lowerBound, edge.upperBound);
	}
	
	static def boolean checkMultiplicityRangeValidity(Integer lower, Integer upper){
		
		// 0..n case
		if((lower == 0 && upper > lower)){
			return true;
		}
		
		// 1,n..m case case
		if(lower > 0 && upper >= lower){
			return true;
		}
		
		// 0,1,n..* case
		if(lower > -1 && upper == -1 ){
			return true;
		}
		
		return false;
	}
}