package uk.ac.kcl.mdeoptimise.rulegen.generator.specs

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference

class RepairSpec {
	
	private EClass node;
	private EReference edge;
	private RepairSpecType repairSpecType;
	
	new(EClass node, EReference edge, RepairSpecType repairSpecType){
		this.node = node;
		this.edge = edge;
		this.repairSpecType = repairSpecType;
		
	}
	
	def EClass getNode(){
		return this.node;
	}
	
	def EReference getEdge(){
		return this.edge;
	}
	
	def RepairSpecType getRepairSpecType(){
		return this.repairSpecType;
	}
	
	override boolean equals(Object o){
		
		if(o === null) return false;
		
		if (!(o instanceof RepairSpec)) return false;
		
		var repairSpec = o as RepairSpec
	
		return node.name.equals(repairSpec.node.name)
		&& edge.name.equals(repairSpec.edge.name)
		&& repairSpecType.equals(repairSpec.repairSpecType)
		
	}
	
	override toString(){
		return String.format("Node %s Edge %s Repair %s \n", node.name, edge.name, repairSpecType);
	}
}