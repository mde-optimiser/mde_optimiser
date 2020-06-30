package argumentation.model.generator

import argumentation.scenario.generator.Scenario
import org.eclipse.emf.ecore.EObject

import static org.dynemf.ResourceSetWrapper.rset;
import org.dynemf.EObjectWrapper
import java.util.HashMap
import org.dynemf.EPackageWrapper
import org.dynemf.ResourceSetWrapper
import net.sf.tweety.arg.dung.syntax.Argument
import java.util.Map
import net.sf.tweety.arg.dung.syntax.Attack
import java.util.Set
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet

class ScenarioModelGenerator {
	
	private Scenario scenario;
	private ResourceSetWrapper resourceSetWrapper;
	private EPackageWrapper metamodel;
	private EObjectWrapper<EObject> parsedScenario;
	
	new(Scenario scenario){
		
		var henshinResourceSet = new HenshinResourceSet("src/main/resources/models/argumentation/generated/");
		var loadedMetamodel = henshinResourceSet.registerDynamicEPackages("argumentation.ecore").head
		this.scenario = scenario;
		this.resourceSetWrapper = rset().copyPackageRegistry(henshinResourceSet)
		this.metamodel = this.resourceSetWrapper.ePackage(loadedMetamodel.nsURI);
	}
	
	def EObjectWrapper<EObject> getParsedScenario(){
		
		if(this.parsedScenario === null){
			this.parsedScenario = parseScenario();
		}
		
		return this.parsedScenario;
	}
	
	private def EObjectWrapper<EObject> parseScenario(){
		//Create ArgumentationFramework
		val argumentationFramework = this.metamodel.create("ArgumentationFramework");
	
		//Create Persuader
		val persuader = this.metamodel.create("Persuader");
		argumentationFramework.set("hasPersuader", persuader);
		
		
		val argumentsMap = new HashMap<Argument, EObjectWrapper>();
		
		//Create topic
		val topic = getEmfArgument(this.scenario.topic);
		argumentationFramework.add("hasArgument", topic);
		argumentationFramework.set("hasTopic", topic);
		
		//argumentsMap.put(this.scenario.topic, topic);
		
		//Create arguments
		this.scenario.persuadersFramework.forEach[dungArgument |
		
			var EObjectWrapper<EObject> emfArgument = null;
			
			if(dungArgument.name.equals(this.scenario.topic.name)){
				emfArgument = topic;
			} else {
				emfArgument = this.getEmfArgument(dungArgument);
			}
		
			argumentsMap.put(dungArgument, emfArgument);	
		
		]
				
		//Map Scenario PAF attacks to EMF arguments attacks
		mapDungAttacksToEmf(this.scenario.persuadersFramework.attacks, argumentsMap);
		
		//Add Arguments to AF
		argumentsMap.forEach[dungArgument, argument | 
			argumentationFramework.add("hasArgument", argument)
		]
		
		//create Persuadee Argumentation Frameworks
		
		this.scenario.persuadeeArgumentationFrameworks.forEach[dungPersuadeeArgumentationFramework | 

			//Create the Persuadee Argumentation Framework
			val persuadeeArgumentationFramework = this.metamodel.create("PersuadeeArgumentationFramework");
			argumentationFramework.add("persuadeeArgumentationFramework", persuadeeArgumentationFramework)
			
			//create persuadee
			val persuadee = this.metamodel.create("Persuadee");
			persuadeeArgumentationFramework.set("hasPersuadee", persuadee);
			
			//Technically these should already be in this list
			dungPersuadeeArgumentationFramework.forEach[ dungArgument |
				if(!argumentsMap.containsKey(dungArgument)) {
					
					println(String.format("PF argument missing from PAF. Missing argument name: %s", dungArgument.name))
					argumentsMap.put(dungArgument, this.getEmfArgument(dungArgument))
				}
				
				persuadeeArgumentationFramework.add("hasArgument", argumentsMap.get(dungArgument))
			]
		]
		
		//Return the resulting EObject
		return argumentationFramework
	}
	
	def String generate(String graphTypeName) {
			
		val Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		
		var modelName = String.format("src/main/resources/models/argumentation/generated/PF-%s-%s-args-Audience-%s-Members-PAF-size-%s-args.xmi", 
			graphTypeName, 
			scenario.persuadersFramework.size, 
			scenario.persuadeeArgumentationFrameworks.size, 
			scenario.persuadeeArgumentationFrameworks.head.size
		)
		
		rset.create(modelName).add(getParsedScenario).save(options)
		
		return modelName;
	}
	
	def void mapDungAttacksToEmf(Set<Attack> dungAttacks, Map<Argument, EObjectWrapper> argumentsMap) {
		
		dungAttacks.forEach[ attack |
			var attacker = argumentsMap.get(attack.attacker);
			var attacked = argumentsMap.get(attack.attacked);
					
			//Define the attack relationship between PAF arguments
			attacker.add("attacks", attacked);
		]
	}
	
	def EObjectWrapper getEmfArgument(Argument dungArgument){
		
		var argument = this.metamodel.create("Argument");
		argument.set("name", dungArgument.name);
		
		return argument;
	}
}