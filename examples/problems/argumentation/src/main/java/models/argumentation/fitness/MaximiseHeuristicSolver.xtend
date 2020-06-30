package models.argumentation.fitness

import org.eclipse.emf.ecore.EObject
import java.util.HashMap
import net.sf.tweety.arg.dung.DungTheory
import net.sf.tweety.arg.dung.syntax.Argument
import java.util.HashSet
import java.util.ArrayList
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MaximiseHeuristicSolver implements IGuidanceFunction {
	
	override computeFitness(Solution arg0) {
		
		
//		DungTheory dt = s.persuadersFramework;
//		Argument t = s.topic;
//		
//		System.out.println(HeuristicSolver.getWeights(dt, t, 6,2));
// I assume that this can be used to calculate the weights of the arguments put forward, and this is something we want to maximise?


		return 0
		
	}
	
	override getName() {
		"Heuristic arguments guidance"
	}
	
	// depth is number of attack levels to consider: greater depth->better accuracy, lesser depth->faster computation
	// decay rate (!=0) can be adjusted to suit specific application
	def HashMap<Argument, Double> getWeights(DungTheory argumentationFramework, Argument topic, int depth, double decayRate){
		
		var weights = initialiseMap(argumentationFramework);
		
		
		var currentLevelWeight = new Double(-1);
		
		var currentLevel = new HashSet<Argument>( argumentationFramework.getAttackers(topic) );
		
		for (var i = 0; i < depth; i++){
			var nextLevel = new HashSet<Argument>();
			
			for(Argument a : currentLevel){
				var argWeight = weights.get(a);
				var newWeight = new Double( Double.valueOf(argWeight) + Double.valueOf(currentLevelWeight) );
				weights.put(a, newWeight);
				
				nextLevel.addAll( argumentationFramework.getAttackers(a) );
			}
			
			currentLevelWeight = new Double ( (Double.valueOf(currentLevelWeight) / decayRate) * -1.0 );
			currentLevel = nextLevel;
		}
		
		return weights;
	}
	
	def HashMap<Argument, Double> initialiseMap(DungTheory argumentationFramework) {
		var weights = new HashMap<Argument, Double>();
		
		var args = new ArrayList<Argument>( argumentationFramework.getNodes());
		for (Argument a : args){
			weights.put(a, 0.0);
		}
		
		return weights;
	}
	
//	
//	public static void main(String[] args) throws IOException{
//		ScenarioGenerator sg =  new ScenarioGenerator(new File("/home/josh/Desktop/A/BA_60_70_3.tgf"));
//		Scenario s = sg.generate(1,1);
//		DungTheory dt = s.persuadersFramework;
//		Argument t = s.topic;
//		
//		System.out.println(HeuristicSolver.getWeights(dt, t, 6,2));
//	}	
	
	
}