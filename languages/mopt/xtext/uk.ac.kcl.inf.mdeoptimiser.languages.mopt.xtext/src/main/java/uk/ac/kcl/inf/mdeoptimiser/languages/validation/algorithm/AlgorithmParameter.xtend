package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

class AlgorithmParameter {
	
	String name;
	String value;
	boolean required;
	
	new(String name) {
		this.name = name;
		this.value = value;
		this.required = true;
	}
	
	new(String name, String defaultValue) {
		this.name = name;
		this.value = defaultValue;
		this.required = false;
	}
	
	def void setValue(String value) {
		this.value = value;
	}
	
	def String getName() {
		return this.name;
	}
	
	def boolean isRequired(){
		return this.required;
	}
}