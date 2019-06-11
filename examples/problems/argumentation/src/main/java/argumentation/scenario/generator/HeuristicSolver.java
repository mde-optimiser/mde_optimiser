package argumentation.scenario.generator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;

public class HeuristicSolver {


	// depth is number of attack levels to consider: greater depth->better accuracy, lesser depth->faster computation
	// decay rate (!=0) can be adjusted to suit specific application
	public static HashMap<Argument, Double> getWeights(DungTheory argumentationFramework, Argument topic, int depth, double decayRate){
		HashMap<Argument, Double> weights = initialiseMap(argumentationFramework);
		
		
		Double currentLevelWeight = new Double(-1);
		
		HashSet<Argument> currentLevel = new HashSet<Argument>( argumentationFramework.getAttackers(topic) );
		
		for (int i=0; i<depth; i++){
			HashSet<Argument> nextLevel = new HashSet<Argument>();
			
			for(Argument a : currentLevel){
				Double argWeight = weights.get(a);
				Double newWeight = new Double( Double.valueOf(argWeight) + Double.valueOf(currentLevelWeight) );
				weights.put(a, newWeight);
				
				nextLevel.addAll( argumentationFramework.getAttackers(a) );
			}

			currentLevelWeight = new Double ( (Double.valueOf(currentLevelWeight) / decayRate) * -1.0 );
			currentLevel = nextLevel;
		}
		
		return weights;
	}
	
	private static HashMap<Argument, Double> initialiseMap(DungTheory argumentationFramework) {
		HashMap<Argument, Double> weights = new HashMap<Argument, Double>();
		
		ArrayList<Argument> args = new ArrayList<Argument>( argumentationFramework.getNodes());
		for (Argument a : args){
			weights.put(a, 0.0);
		}
		
		return weights;
	}

	public static void main(String[] args) throws IOException{
		ScenarioGenerator sg =  new ScenarioGenerator(new File("/home/josh/Desktop/A/BA_60_70_3.tgf"));
		Scenario s = sg.generate(1,1);
		DungTheory dt = s.persuadersFramework;
		Argument t = s.topic;
		
		System.out.println(HeuristicSolver.getWeights(dt, t, 100,2));
	}
	
}
