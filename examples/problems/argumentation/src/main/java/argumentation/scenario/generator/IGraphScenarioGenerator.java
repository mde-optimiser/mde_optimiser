package argumentation.scenario.generator;

import net.sf.tweety.arg.dung.DungTheory;

public interface IGraphScenarioGenerator {
	 DungTheory generate(int size);
	 String getGraphTypeName();
}
