package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel

class RuleSpec {
	
	String node;
	String edge;
	String action;
	
	new(String node, String action){
		this.node = node;
		this.action = action
	}
	
	new(String node, String edge, String action){
		this.node = node;
		this.edge = edge;
		this.action = action;
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
