package uk.ac.kcl.interpreter

import java.util.ArrayList
import java.util.Iterator
import java.util.LinkedList
import java.util.List
import java.util.Map
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Module
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.mdeoptimise.rulegen.RulesGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.UserModelProvider
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import org.moeaframework.Instrumenter

class OptimisationInterpreter {
	
	private Optimisation model
	
	private HenshinResourceSet henshinResourceSet
	private EPackage theMetamodel
	
	private List<Unit> breedingOperators
	private List<Unit> mutationOperators
	private IPath projectRootPath;
	private boolean enableManualRandomMatching = false;
	private  Map<EPackage, List<Module>> generatedOperators;
	
	new (String projectPath, Optimisation model){
		this.model = model;
		this.projectRootPath = new Path(projectPath);
	}
	
	def Instrumenter start() {
		
		//This model provider loads the model given by the user in the DSL
		var solutionGenerator = new SolutionGenerator(model, 
											getBreedingOperators, 
											getMutationOperators, 
											getModelProvider, 
											getMetamodel);

		//TODO Find a better way to do this stuff
		if(this.enableManualRandomMatching){
			solutionGenerator.setEnableManualRandomMatching(this.enableManualRandomMatching);
		}
		
		return new MoeaOptimisation().execute(model.optimisation, solutionGenerator)
	}
	
	def IModelProvider getModelProvider(){
		
		if(model.modelInitialiser !== null){
			return new UserModelProvider(getModelInitialiser(), getResourceSet(projectRootPath.append(model.basepath.location).toPortableString), model.model.location)
		}
		
		return new UserModelProvider(getResourceSet(projectRootPath.append(model.basepath.location).toPortableString), model.model.location)
	}
	
	def IModelInitialiser getModelInitialiser(){
		
		if(model.modelInitialiser !== null){
			return Class.forName(model.modelInitialiser.initialiser).newInstance() as IModelInitialiser
		}
		
	}

	def getResourceSet(String basePath) {
        if (henshinResourceSet === null) {

            henshinResourceSet = new HenshinResourceSet(basePath);
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel === null) {
        	if (!model.metamodel.location.endsWith(".ecore")) {
        		// The location is not an ecore file, assume it's a class name
        		val packageInterface = (Class.forName(model.metamodel.location) as Class<EPackage>)
        		theMetamodel = packageInterface.getDeclaredField("eINSTANCE").get(null) as EPackage
        	} else {
 				theMetamodel = getResourceSet(projectRootPath.append(model.basepath.location).toPortableString).registerDynamicEPackages(model.metamodel.location).head	
        	}
        }

        theMetamodel
    }
    
    def getBreedingOperators() {
    	if(breedingOperators === null){
			
			breedingOperators = new LinkedList
			
			breedingOperators.addAll(model.evolvers.filter[ operator | operator.evolverType.getName.equals("BREED")]
				.map[ operator | getResourceSet(projectRootPath.append(model.basepath.location).toPortableString).getModule(URI.createURI(operator.rule_location), false).getUnit(operator.unit)]
			)
    		
    	}
    	
    	breedingOperators
    }
    
    def getMutationOperators() {
    	
    	//TODO: Doesn't make sense?
    	if(mutationOperators === null){
			
			mutationOperators = new LinkedList
			
			mutationOperators.addAll(model.evolvers.filter[ operator | operator.evolverType.getName.equals("MUTATE")]
				.map[ operator | getResourceSet(projectRootPath.append(model.basepath.location).toPortableString).getModule(URI.createURI(operator.rule_location), false).getUnit(operator.unit)]
			)
    		
    	}
    	
    	//Automatically generate mutations operators
    	var generatedMutations = getRulegenOperators();
    	
    	if(!generatedMutations.empty){
    		
    		//For each of the automatically generated modules, add the generated mutations to the list of evolvers
    		//Are we ever going to have more than one metamodel? Perhaps this should be a pair instead
    		
    		var metamodel = generatedMutations.keySet.head;
    		var mutations = generatedMutations.get(metamodel);
    		
    		mutations.forEach[mutation |
    			mutationOperators.addAll(mutation.allRules)
    		]
    	}
    	
    	mutationOperators
    }
    
    def List<Multiplicity> getMultiplicityRefinements(){
    	 //A list of multiplicity refinements specified by the user in the DSL.
    	//This is optional.
    	var refinements = model.refinements;
 		
 		val multiplicityRefinements = new ArrayList<Multiplicity>();
 		
 		if(!refinements.empty){
 			refinements.forEach[refinement | 
 				multiplicityRefinements.add(new Multiplicity(refinement.node, refinement.edge, refinement.lowerBound, refinement.upperBound, getMetamodel));
 			]
 		}
 		
 		return multiplicityRefinements;
    }
    
    def List<RuleSpec> getRulegenSpecs(){
    	
    	var rulegenSpecs = model.rulegen;
    	
    	val ruleSpecs = new ArrayList<RuleSpec>();
 
 		if(!rulegenSpecs.empty) {
 			
 			rulegenSpecs.forEach[rulegenSpec |
 				
 				//Crete the spec for a node or an edge generation
 				if(rulegenSpec.nodeSpec !== null){
 					ruleSpecs.add(new RuleSpec(rulegenSpec.nodeSpec.node, rulegenSpec.nodeSpec.generationRestriction))
 				} else {
 					ruleSpecs.add(new RuleSpec(rulegenSpec.edgeSpec.node, rulegenSpec.edgeSpec.edge, rulegenSpec.edgeSpec.generationRestriction))
 				}
 			]
 		}
    	
    	return ruleSpecs;
    }
    
    /**
     * If there are any rule generation instructions present, then generate the corresponding rules.
     * @return list of generated mutation operators
     */
    def Map<EPackage, List<Module>> getRulegenOperators(){
 		
 		if(this.generatedOperators === null){
 			var multiplicityRefinements = getMultiplicityRefinements();
	    	var rulegenSpecs = getRulegenSpecs();
	    	
	  		//Generate the list of modules that are automatically generated
	    	var mutations = new RulesGenerator(getMetamodel, multiplicityRefinements, rulegenSpecs);
	    	    	
	    	this.generatedOperators = mutations.generateRules;
 		}
 		
 		return this.generatedOperators  	   	

    }
	
	def setEnableManualRandomMatching(boolean enableRandomMatching) {
		this.enableManualRandomMatching = enableRandomMatching;
	}
	
}