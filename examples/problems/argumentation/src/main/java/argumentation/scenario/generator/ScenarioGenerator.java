package argumentation.scenario.generator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;
import net.sf.tweety.arg.dung.util.DefaultDungTheoryGenerator;
import net.sf.tweety.arg.dung.util.DungTheoryGenerationParameters;

public class ScenarioGenerator {
	
	// Properties of Persuaders AF if none provided:
	private final static int DEFAULT_SIZE = 20;
	private final static double DEFAULT_PROBABILITY = 0.003;
	private final static boolean DEFAULT_SELF = true;
	private final static boolean DEFAULT_TREE = true;
	private int persuaderFrameworkSize;
	
	private DungTheory globalFramework;
	
	public ScenarioGenerator(){
		// if no framework provided, use the default generator to make one
		this.globalFramework = generatePersuaderFramework();
	}
	
	public ScenarioGenerator(File frameworkFile) throws IOException{
		this(TgfParser.parse(frameworkFile));
	}
	
	public ScenarioGenerator(DungTheory pf){
		globalFramework = pf;
	}
	
	public ScenarioGenerator(int persuaderFrameworkSize) {
		
		this.persuaderFrameworkSize = persuaderFrameworkSize;
		this.globalFramework = generatePersuaderFramework();
	
	}
	
	private DungTheory generatePersuaderFramework(){
		DungTheoryGenerationParameters params = new DungTheoryGenerationParameters();
		
		if(this.persuaderFrameworkSize == 0) {
			params.numberOfArguments = DEFAULT_SIZE;
		} else {
			params.numberOfArguments = this.persuaderFrameworkSize;
		}
		
		params.attackProbability = DEFAULT_PROBABILITY;
		params.avoidSelfAttacks = DEFAULT_SELF;
		params.enforceTreeShape = DEFAULT_TREE;
		
		DefaultDungTheoryGenerator persuaderFramework = new DefaultDungTheoryGenerator(params);
		return persuaderFramework.generate();
	}
	
	public Scenario generate(int audienceSize, int audienceBeliefSize){
		Argument topic = generateTopic(globalFramework);
		return generateWithTopic(audienceSize, calculateBeliefSizePercentage(audienceBeliefSize), topic);
	}
	
	public Scenario generateATopic(int audienceSize, int percentage){
		Argument topic = new Argument("a");
		return generateWithTopic(audienceSize, calculateBeliefSizePercentage(percentage), topic);
	}
	
	public int calculateBeliefSizePercentage(int percentage) {
		
		int size = (int) Math.ceil(globalFramework.size() * (percentage/100.0));
		
		return size ;		
	}
	
	public Scenario generateWithTopic(int audienceSize, int audienceBeliefSize, Argument topic){
		DungTheory pf = globalFramework;
		ArrayList<DungTheory> afs = generateAudienceFrameworks(audienceSize, audienceBeliefSize, pf, topic);
		Scenario s = new Scenario(pf, afs, topic);
		 return s;
	}
	
	private Argument generateTopic(DungTheory persuadersFramework) {
		// for now, topic is a random argument from persuaders framework
		ArrayList<Argument> allArguments = new ArrayList<Argument>(persuadersFramework.getNodes());
		Collections.shuffle(allArguments);
		return allArguments.get(0);
	}

	private DungTheory generatePersuadersFramework(){
		// for now, persuader knows everything
		return globalFramework;
	}
	
	private ArrayList<DungTheory> generateAudienceFrameworks(int audienceSize, int audienceBeliefSize, DungTheory persuadersFramework, Argument topic){
		ArrayList<DungTheory> persuadeeArgumentationFrameworks = new ArrayList<DungTheory>();
		for (int i = 0; i < audienceSize; i++){
			persuadeeArgumentationFrameworks.add(generateAudienceMember(audienceBeliefSize, persuadersFramework, topic));
		}
		return persuadeeArgumentationFrameworks;
	}
	
	private DungTheory generateAudienceMember(int audienceBeliefSize, DungTheory persuadersFramework, Argument topic) {
		// for now, an audience member's framework is a random subgraph of persuaders framework
		DungTheory audienceMember = new DungTheory();
		
		ArrayList<Argument> allArguments = new ArrayList<Argument>(persuadersFramework.getNodes());
		Collections.shuffle(allArguments);
		audienceMember.add(topic); // for now, assume am's know the topic
		
		allArguments.remove(topic);
		
		ArrayList<Argument> audienceArguments = new ArrayList<Argument>(allArguments.subList(0, audienceBeliefSize));
		
		audienceMember.addAll(audienceArguments);
		
		allArguments.add(topic);
		
		Set<Attack> allAttacks = persuadersFramework.getAttacks();
		
		audienceArguments.add(topic);
		
		for (Attack att : allAttacks){
			if (  audienceArguments.contains(att.getAttacked()) && audienceArguments.contains(att.getAttacker()) ){
				audienceMember.add(att);
			}
		}
		
		return audienceMember;
	}

	public static void main(String[] args) throws IOException{

		ScenarioGenerator sg = new ScenarioGenerator();
		Scenario s = sg.generate(10, 50);
		System.out.println(s);
		
//		ScenarioGenerator sg = new ScenarioGenerator(new File("/home/josh/Desktop/A/BA_60_70_3.tgf"));
//		Scenario s = sg.generate(30, 20);
////		System.out.println(s);
	}
	
}
