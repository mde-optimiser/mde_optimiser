package argumentation.scenario.generator;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.util.DefaultDungTheoryGenerator;
import net.sf.tweety.arg.dung.util.DungTheoryGenerationParameters;

public class TreeGenerator implements IGraphScenarioGenerator {
	
	private final static double DEFAULT_PROBABILITY = 0.9;
	private final static boolean DEFAULT_SELF = true;
	private final static boolean DEFAULT_TREE = true;
	
	public DungTheory generateTree(int size){
		DungTheoryGenerationParameters params = new DungTheoryGenerationParameters();
		params.numberOfArguments = size;
		params.attackProbability = DEFAULT_PROBABILITY;
		params.avoidSelfAttacks = DEFAULT_SELF;
		params.enforceTreeShape = DEFAULT_TREE;
		
		DefaultDungTheoryGenerator g = new DefaultDungTheoryGenerator(params);
		return g.generate();
	}

	@Override
	public DungTheory generate(int size) {
		return this.generateTree(size);
	}

	@Override
	public String getGraphTypeName() {
		return "Tree";
	}

}