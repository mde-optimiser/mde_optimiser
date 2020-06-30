package argumentation.scenario.generator;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;


public class LadderGenerator implements IGraphScenarioGenerator {
	
	private DungTheory generateLadder(int size){
		
		int depth = (((size)-1)/2);
		
		DungTheory ladder = new DungTheory();
		
		Argument topic = new Argument("a");
		ladder.add(topic);
		
		//b0 and c0 attack the topic
		Argument b0 = new Argument("b0");
		Argument c0 = new Argument("c0");
		ladder.add(b0);
		ladder.add(c0);
		
		ladder.add(new Attack(b0, topic));
		ladder.add(new Attack(c0, topic));
		ladder.add(new Attack(b0, c0));
		
		for (int i = 1; i < depth; i++){
			Argument b = new Argument("b" + i);
			Argument c = new Argument("c" + i);
			ladder.add(b);
			ladder.add(c);
			
			ladder.add(new Attack(b,c));
			ladder.add(new Attack(b, new Argument("b"+(i-1))));
			ladder.add(new Attack(c, new Argument("c"+(i-1))));
		}
		
		return ladder;
	}

	@Override
	public DungTheory generate(int size) {
		return this.generateLadder(size);
	}

	@Override
	public String getGraphTypeName() {
		return "Ladder";
	}
	
}
