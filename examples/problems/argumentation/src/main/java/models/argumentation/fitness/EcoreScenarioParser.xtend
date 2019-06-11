package models.argumentation.fitness

import org.eclipse.emf.ecore.EObject
import net.sf.tweety.arg.dung.DungTheory
import java.util.ArrayList
import org.eclipse.emf.common.util.EList
import net.sf.tweety.arg.dung.syntax.Argument
import net.sf.tweety.arg.dung.syntax.Attack
import java.util.Map
import java.util.List
import java.util.HashMap
import net.sf.tweety.arg.dung.PreferredReasoner
import net.sf.tweety.logics.pl.sat.SatSolver
import net.sf.tweety.logics.pl.sat.Sat4jSolver
import net.sf.tweety.arg.dung.GroundReasoner
import net.sf.tweety.commons.util.Shell
import net.sf.tweety.arg.dung.prover.ProboSolver
import net.sf.tweety.arg.dung.semantics.Problem
import net.sf.tweety.arg.dung.parser.FileFormat
import net.sf.tweety.arg.dung.semantics.Semantics
import argumentation.scenario.generator.Scenario
import java.io.File
import net.sf.tweety.arg.dung.writer.DungWriter
import java.util.regex.Pattern

class EcoreScenarioParser {
	
	private EObject solutionModel = null;
	private Map<String, Argument> argumentationFrameworkArguments;
	private Argument topic;
	
	new(EObject object) {		
		solutionModel = object;		
	}


	def Scenario getScenario(){
		val arguments = new ArrayList<Argument>();
		
		this.getArgumentationFrameworkArguments.forEach[p1, p2|
			arguments.add(p2)
		]
	
		val attacks = new ArrayList<Attack>();
		
		arguments.forEach[argument |
			attacks.addAll(getArgumentAttacks(argument, getArgumentationFrameworkArguments()))
		]
		 
		var persuaderFramework = new DungTheory();
		
		persuaderFramework.addAll(arguments);
		persuaderFramework.addAllAttacks(attacks);
		
		new Scenario(persuaderFramework, getPersuadeeArgumentationFrameworksFromSolution, getTopic);
	}

	override String toString(){
		var scenario = getScenario()
		return scenario.toString;
	}

	def Map<String, Argument> getArgumentationFrameworkArguments() {
		
		if(this.argumentationFrameworkArguments !== null){
			return this.argumentationFrameworkArguments;
		}
		
		val mappedArguments = new HashMap<String, Argument>();
		
		val frameworkArguments = this.solutionModel.getList("hasArgument");
	
		frameworkArguments.forEach[argument |
			
			val argumentName = argument.getString("name");
			mappedArguments.put(argumentName, new Argument(argumentName));
		]

		this.argumentationFrameworkArguments = mappedArguments;

		return mappedArguments;
	}
	
	def List<Attack> getArgumentAttacks(Argument attacker, Map<String, Argument> frameworkArguments){
		
		val attacks = new ArrayList<Attack>()
		
		var targets = this.solutionModel.getList("hasArgument")
			.filter[frameworkArgument | frameworkArgument.getString("name").equals(attacker.name)].head.getList("attacks");
			
		targets.forEach[ target |
			
			if(target !== null){
				
				var dungAttacker = attacker;		
				var dungTarget = frameworkArguments.get(target.getString("name"));
				attacks.add(new Attack(dungAttacker, dungTarget));
			}
		]

		return attacks;
	}

	def Argument getTopic(){
		
		if(this.topic === null){
			
			var topicName = this.solutionModel.getObject("hasTopic").getString("name")
			
			this.topic = getArgumentationFrameworkArguments().get(topicName);
		} 
		
		return topic;
	}

	def List<Argument> getArgumentsPutForward(Map<String, Argument> frameworkArguments){
		
		//List of the dung arguments put forward
		val dungArgumentsPutForward = new ArrayList<Argument>();
		
		var persuader = this.solutionModel.getObject("hasPersuader")
		
		var argumentsPutForward = persuader.getList("putsForward");
		
		argumentsPutForward.forEach[argumentPutForward |
			
			var argumentName = argumentPutForward.getString("name");
			
			var argument = frameworkArguments.get(argumentName);
			
			//Should be fixed in the EMF model
			if(!dungArgumentsPutForward.contains(argument)) {
				dungArgumentsPutForward.add(argument);
			}
				
		]
		
		return dungArgumentsPutForward
	}
	
	def ArrayList<DungTheory> getPersuadeeArgumentationFrameworksFromSolution() {
		
		val persuadeeArgumentatonFrameworks = new ArrayList<DungTheory>();
		
		val frameworkArguments = getArgumentationFrameworkArguments();
		
		val argumentsPutForward = getArgumentsPutForward(frameworkArguments);
		
		
		//Add all arguments from a paf to a list for that paf's arguments
		//Add all arguments put forward to the same list
		
		//For all the arguments in the list, check who they attack in the main framework
		//and if the target exists in the list, add the attack to the paf graph
		
		
		(this.solutionModel.getList("persuadeeArgumentationFramework")).forEach[persuadeeArgumentationFramework | 
			
			val persuadeeArgumentationFrameworkArguments = new ArrayList<Argument>();
			val persuadeeArgumentationFrameworkAttacks = new ArrayList<Attack>();
			
			//Add all arguments from a paf to a list for that paf's arguments		
			(persuadeeArgumentationFramework.getList("hasArgument")).forEach[
				pafArgument |
				
				val dungArgument = frameworkArguments.get(pafArgument.getString("name"));	
				persuadeeArgumentationFrameworkArguments.add(dungArgument);
			]
			
			//Add the arguments put forward to this PAF
			argumentsPutForward.forEach[argument|

				if(!persuadeeArgumentationFrameworkArguments.contains(argument)){
					persuadeeArgumentationFrameworkArguments.add(argument)
				}
			]
			
			//For all the arguments in the list for this PAF, check who they attack in the main framework
			//and if the target exists in the list, add the attack to the paf graph
			persuadeeArgumentationFrameworkArguments.forEach[argument | 
				
				var attacks = getArgumentAttacks(argument, frameworkArguments)
				
				attacks.forEach[attack |
									
					if(persuadeeArgumentationFrameworkArguments.contains(attack.attacker) &&
						persuadeeArgumentationFrameworkArguments.contains(attack.attacked)
					){
						persuadeeArgumentationFrameworkAttacks.add(attack)
					}
				]
			]
		
			val dungTheoryGraph = new DungTheory();
			
			dungTheoryGraph.addAll(persuadeeArgumentationFrameworkArguments)
			dungTheoryGraph.addAllAttacks(persuadeeArgumentationFrameworkAttacks)
			dungTheoryGraph.add(getTopic)

			//TODO Check that newly arguments put forward, are attacked by any existing arguments in the framework.
			persuadeeArgumentatonFrameworks.add(dungTheoryGraph)
		]
		
		return persuadeeArgumentatonFrameworks;
	}
		
	def double countAcceptableTopicsPreferredReasoner(){

		var persuadeeArgumentationFrameworks = getPersuadeeArgumentationFrameworksFromSolution();
		
		val topic = getTopic();
	
		return persuadeeArgumentationFrameworks.fold(0d)[result, pafGraph | 
			var reasoner = new PreferredReasoner(pafGraph, Semantics.CREDULOUS_INFERENCE);
			var query =  reasoner.query(topic).getAnswerBoolean();
			
			if(query){
				return result + 1
			}
			
			return result  
		]
	}
	
	def double countAcceptableTopicsGroundReasoner(){
				
		var persuadeeArgumentationFrameworks = getPersuadeeArgumentationFrameworksFromSolution();
		
		val topic = getTopic();
	
		return persuadeeArgumentationFrameworks.fold(0d)[result, pafGraph | 
			
			var reasoner = new GroundReasoner(pafGraph);
			var query =  reasoner.query(topic).getAnswerBoolean();
			
			if(query){
				
				return result + 1
			}
			
			return result  
		]
	}
	
	
	def double countAcceptableTopicsEqArgSolver(){
		return countTopicsWithNativeSolver("eqargsolver")
	}
	
	def double countAcceptableTopicsArgMatSat(){
		return countTopicsWithNativeSolver("argmatsat")
	}
	
	
	def double countTopicsWithNativeSolver(String solver){
		var persuadeeArgumentationFrameworks = getPersuadeeArgumentationFrameworksFromSolution();
		
		val topic = getTopic();
	
		return persuadeeArgumentationFrameworks.fold(0d)[result, pafGraph | 
			
			var nativeSolver = new ProboSolver(solver, Shell.getNativeShell())
			var query =  justify(nativeSolver, Problem.DC_PR, pafGraph, FileFormat.TGF, topic)
			
			if(query){
				return result + 1
			}
			
			return result  
		]
	}
	
	def boolean justify(ProboSolver solver, Problem problem, DungTheory aaf, FileFormat format, Argument arg) {
		
		if (!problem.isJustificationProblem())
			throw new RuntimeException("Fail: "+problem+"is not a justification problem.");
		
		val temp = File.createTempFile("aaf-", "."+format.extension());

		val writer = DungWriter.getWriter(format);
		writer.write(aaf, temp);
		
		val str = solver.solve(problem, temp, format, " -a "+arg);
		
		//Delete the temporary file now to avoid temp disk filling
		temp.delete
		
		if (Pattern.matches("\\s*YES\\s*",str)) {
			return true;
		} else if (Pattern.matches("\\s*NO\\s*",str)) {
			return false;
		} else {
			throw new RuntimeException("Calling executable did not return useful output");
		}
	}
	
	/**
	 * Helper function getting the value of the named feature (if it exists) for the given EObject.
	 */
	def Object getFeature (EObject o, String feature) {
		
		if(o === null){
			println(String.format("Null object given %s", feature));
			return null;
		}
		
		o.eGet(o.eClass.getEStructuralFeature(feature))
		
	}
	
	def EObject getObject (EObject o, String feature) {
		
		var object = getFeature(o, feature)
		
		if(object !== null){
			return object as EObject;
		}
		
		null;
	}
	
	def List<EObject> getList (EObject o, String feature) {
		
		var object = getFeature(o, feature)
		
		if(object !== null){
			return object as EList<EObject>;
		}
		
		new ArrayList<EObject>();
	}
	
	def String getString(EObject o, String feature) {
		
		var object = getFeature(o, feature)
		
		if(object !== null){
			return object as String;
		}
		
		null;
	}
	
}