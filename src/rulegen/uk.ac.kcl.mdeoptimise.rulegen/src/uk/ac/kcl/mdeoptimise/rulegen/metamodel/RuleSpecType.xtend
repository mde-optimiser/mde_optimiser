package uk.ac.kcl.mdeoptimise.rulegen.metamodel

enum RuleSpecType {
	
	//Create node and generate a NAC if required
	CREATE,
	DELETE,
	
	//This is a combination of create and delete
	ALL
}