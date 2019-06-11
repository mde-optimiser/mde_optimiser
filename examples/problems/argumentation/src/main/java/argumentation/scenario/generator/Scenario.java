package argumentation.scenario.generator;
import java.util.ArrayList;
import java.util.List;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;


public class Scenario {

	DungTheory persuadersFramework;
	ArrayList<DungTheory> audienceFrameworks;
	Argument topic;
	
	
	public Scenario(DungTheory pf, ArrayList<DungTheory> afs, Argument t){
		persuadersFramework = pf;
		audienceFrameworks = afs;
		topic = t;
	}
	
	public DungTheory getPersuadersFramework() {
		return this.persuadersFramework;
	}
	
	public Argument getTopic() {
		return this.topic;
	}
	
	public List<DungTheory> getPersuadeeArgumentationFrameworks() {
		return this.audienceFrameworks;
	}
	
	public String toString(){
		String s = "PERSUADER: " + persuadersFramework.toString();
		s += "\nAUDIENCE:";
		for (DungTheory dt : audienceFrameworks){
			s += "\n\t @" + dt.toString();
		}
		s += "\nTOPIC: " + topic;
		return s;
	}
	
}
