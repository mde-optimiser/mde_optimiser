package uk.ac.kcl.mdeoptimise.rulegen.metamodel

class RuleSpec {
	
	private String node;
	private String edge;
	private String action;	
	private boolean isEdgeRepair = false;
	
	new(String node, String action){
		this.node = node;
		this.action = action
	}
	
	new(String node, String edge, String action){
		this.node = node;
		this.edge = edge;
		this.action = action;
		this.isEdgeRepair = true;
	}
	
	def String getNode(){
		return this.node;
	}
	
	def String getEdge(){
		return this.edge;
	}
	
	/**
	 * Get what types of rules we are allowed to created.
	 * Should perhaps be an enum
	 */
	def String getActions(){
		
		if(action === null){
			return "ALL";
		} else {
			return action;
		}
	}
	
	def boolean isNode(){
		return this.edge === null
	}
	
	def boolean isEdge(){
		return this.edge !== null;
	}
	
}