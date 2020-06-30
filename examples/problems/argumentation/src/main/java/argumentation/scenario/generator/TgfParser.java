package argumentation.scenario.generator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;


public class TgfParser {

	public static DungTheory parse(File frameworkFile) throws IOException{
		DungTheory framework = new DungTheory();
		
		try (BufferedReader br = new BufferedReader(new FileReader(frameworkFile))) {
		    String line;
		    boolean flag = false;
		    while ((line = br.readLine()) != null) {
		    	if (line.equals("#")){
		    		flag = true;
		    	}
		    	else{
		    		if (!flag){
		    			framework.add(new Argument(line));
		    		}
		    		else{
		    			String[] args = line.split(" ");
		    			framework.add(new Attack(new Argument(args[0]), new Argument(args[1])));
		    		}
		    	}
		    }
		}
		
		return framework;
		
	}
	
	public static void main(String[] args) throws IOException{
		File f = new File("/home/josh/Desktop/A/BA_60_70_3.tgf");
		DungTheory dt = TgfParser.parse(f);
		System.out.println(dt);
	}
	
}
