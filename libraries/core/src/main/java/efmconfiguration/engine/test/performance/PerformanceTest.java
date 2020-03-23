package efmconfiguration.engine.test.performance;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

import efmconfiguration.engine.ConfigurationEngine;

public class PerformanceTest {
	static boolean OPTION_SINGLE = false; // single or double experiment?

	public static void main(String[] args) {

		String path = "src/main/java/efmconfiguration/engine/test/performance";
		HenshinResourceSet resourceSet = new HenshinResourceSet(path);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		resourceSet.registerDynamicEPackages("WeaFqaConfiguration.ecore");
		Resource model = resourceSet.getResource("WeaFqaConfiguration.xmi");

		List<Rule> rules = new ArrayList<Rule>();
		File[] listOfFiles = new File(path).listFiles();
		for (File file : listOfFiles) {
			if (file.getName().endsWith(".henshin")) {
				for (Unit u : resourceSet.getModule(file.getName()).getUnits()) {
					rules.add((Rule) u);
				}
			}
		}
//		rules.add((Rule) resourceSet.getModule("Act_Security.henshin").getUnit("Act_Security_r1"));

		EGraph graph = new EGraphImpl(model);

		Engine oldengine = new EngineImpl();
		Engine newengine = new ConfigurationEngine();

		System.out.println("Finished setup");

		if (OPTION_SINGLE) {
			doSingleExperiment(rules, graph, newengine, true);
			doSingleExperiment(rules, graph, newengine, false);
		} else {
			doDoubleExperiment(rules, graph, newengine, oldengine, true);
			doDoubleExperiment(rules, graph, newengine, oldengine, false);
		}
	}

	private static void doSingleExperiment(List<Rule> rules, EGraph graph, Engine engine, boolean warmup) {
		int numIterations = 5;
		int numberExecutions = 2;

		for (Rule rule : rules) {
			if (!warmup)
				System.out.println(rule.getName());
			for (int i = 1; i <= numIterations; i++) {

				long duration = doExperimentWithNew(graph, engine, numberExecutions, rule);

				if (!warmup)
					System.out.println(numberExecutions + " | " + duration);

			}
		}
	}

	private static void doDoubleExperiment(List<Rule> rules, EGraph graph, Engine newengine, Engine oldengine,
			boolean warmup) {
		if (!warmup)
			System.out.println("Legend: #Executions | Time in ns (new engine) | Time in ns (old engine) | Gain\r\n");
		int numIterations = 5;
		int numberExecutions = 10;

		for (Rule rule : rules) {
			if (!warmup)
				System.out.println(rule.getName());
			for (int i = 1; i <= numIterations; i++) {

				Engine engine1 = (Math.random() < 0.5) ? newengine : oldengine;
				Engine engine2 = (engine1 == newengine) ? oldengine : newengine;

				long duration1 = doExperimentWithNew(graph, engine1, numberExecutions, rule);
				long duration2 = doExperimentWithNew(graph, engine2, numberExecutions, rule);

				long durationOld = (engine1 == oldengine) ? duration1 : duration2;
				long durationNew = (engine1 == newengine) ? duration1 : duration2;

				if (!warmup)
					System.out.println(numberExecutions + " | " + durationNew + " | " + durationOld + " | "
							+ ((Math.floor(100.0 * durationOld / durationNew * 1.0)) / 100.0));

			}
		}
	}

	private static long doExperimentWithNew(EGraph graph, Engine newengine, int numberExecutions, Rule rule) {
		long startTimeNew = System.nanoTime();
		for (int j = 1; j < numberExecutions; j++) {

//			System.out.println();
			Iterable<Match> matches = newengine.findMatches(rule, graph, null);
//			long time0 = System.nanoTime();
			Iterator<Match> iterator = matches.iterator();
//		long time1 = System.nanoTime();
			if (iterator.hasNext()) {
//			long time2 = System.nanoTime();
				newengine.createChange(rule, graph, iterator.next(), null);
//			long time3 = System.nanoTime();

//			System.out.println(time0- startTimeNew);
//			System.out.println(time1- time0);
//			System.out.println(time2- time1);
//			System.out.println(time3- time2);
			}
		}
		long endTimeNew = System.nanoTime();
		long durationNew = (endTimeNew - startTimeNew);
		return durationNew;
	}

}
