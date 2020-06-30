package argumentation.scenario.generator;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;


public class CycleGenerator implements IGraphScenarioGenerator {
	
	
	private DungTheory generateCycle(int size){
		int depth =  (((size)-1)/2);
		
		DungTheory cycle = new DungTheory();
		
		Argument topic = new Argument("a");
		cycle.add(topic);
		
		Argument bDepth = new Argument("b" + (depth-1));
		Argument cDepth = new Argument("c" + (depth-1));
		
		cycle.add(bDepth);
		cycle.add(cDepth);
		
		cycle.add(new Attack(bDepth, topic));
		cycle.add(new Attack(cDepth, bDepth));
		
		for (int i=depth-1; i > 0; i--){
			Argument b = new Argument("b" + i);
			Argument c = new Argument("c" + i);
			
			cycle.add(b);
			cycle.add(c);
			
			cycle.add(new Attack(b, topic));
			cycle.add(new Attack(c,b));
			
			cycle.add(new Attack(new Argument("b" + (i-1)),b));
			cycle.add(new Attack(new Argument("c" + (i-1)),c));
		}
		
		Argument b0 = new Argument("b0");
		Argument c0 = new Argument("c0");
		
		cycle.add(b0);
		cycle.add(c0);
		
		cycle.add(new Attack(b0, topic));
		cycle.add(new Attack(c0, b0));
		
		cycle.add(new Attack( bDepth, b0 ));
		cycle.add(new Attack( cDepth, c0 ));
		
		return cycle;
	}

	@Override
	public DungTheory generate(int size) {
		return this.generateCycle(size);
	}

	@Override
	public String getGraphTypeName() {
		return "Cycle";
	}
	

}
