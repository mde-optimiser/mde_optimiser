package efmconfiguration.engine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.Change;
import org.eclipse.emf.henshin.interpreter.Change.CompoundChange;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import efmconfiguration.engine.ConfigurationEngine;;

/**
 * Tests the engine with the default setting (only one
 * match is found). TestConfigurationEngineAllMatches
 * tests under the setting that all possible matches are to be found.
 * 
 * @author Daniel Strueber
 *
 */
public class TestConfigurationEngine {

	
	@Test
	public void testActSMSTransfer() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleActSMSTransfer = (Rule) module.getUnit("Act_SMSTransfer");

		ConfigurationEngine engine = new ConfigurationEngine();
		
		Iterable<Match> matches = engine.findMatches(ruleActSMSTransfer, graph, null);
		Iterator<Match> it = matches.iterator();
		assertTrue(it.hasNext());
		Match match = it.next();
		assertTrue(match.getNodeTargets().size() == 4);
		assertFalse(it.hasNext());

		Change change = engine.createChange(ruleActSMSTransfer, graph, match, new MatchImpl(ruleActSMSTransfer));
		assertTrue(change instanceof CompoundChange);
		assertEquals(4, ((CompoundChange) change).getChanges().size());
	}

	@Test
	public void testDeScreen3SimplifiedInapplicable() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen1active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3Simplified = (Rule) module.getUnit("De_Screen3_Simplified");

		ConfigurationEngine engine = new ConfigurationEngine();
	
		Iterable<Match> matches = engine.findMatches(ruleDeScreen3Simplified, graph, null);
		Iterator<Match> it = matches.iterator();
		assertFalse(it.hasNext());
	}

	@Test
	public void testDeScreen3SimplifiedApplicable() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3Simplified = (Rule) module.getUnit("De_Screen3_Simplified");

		ConfigurationEngine engine = new ConfigurationEngine();

		Iterable<Match> matches = engine.findMatches(ruleDeScreen3Simplified, graph, null);
		Iterator<Match> it = matches.iterator();
		assertTrue(it.hasNext());
		Match match = it.next();
		assertTrue(match.getNodeTargets().size() == 3);
		assertFalse(it.hasNext());
		}

	@Test
	public void testDeScreen3SimplifiedAdditionalOrGroup() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3Simplified = (Rule) module.getUnit("De_Screen3_Simplified_AdditionalOrGroup");

		ConfigurationEngine engine = new ConfigurationEngine();

		Iterable<Match> matches = engine.findMatches(ruleDeScreen3Simplified, graph, null);
		Iterator<Match> it = matches.iterator();

		int countMatches = 0;
		while (it.hasNext()) {
			it.next();
			countMatches++;
		}

		assertEquals(1, countMatches);
	}

	@Test
	public void testDeScreen3Applicable() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3 = (Rule) module.getUnit("De_Screen3");
		Rule multiRule1 = ruleDeScreen3.getMultiRules().get(0);

		ConfigurationEngine engine = new ConfigurationEngine();

		Iterable<Match> matches = engine.findMatches(ruleDeScreen3, graph, null);
		Iterator<Match> it = matches.iterator();
		assertTrue(it.hasNext());
		Match match = it.next();
		assertEquals(match.getNodeTargets().size(), 3);
		assertEquals(match.getMultiMatches(multiRule1).size(), 1);
		assertEquals(match.getMultiMatches(multiRule1).get(0).getNodeTargets().size(), 4);

		assertFalse(it.hasNext());

		Change change = engine.createChange(ruleDeScreen3, graph, match, new MatchImpl(ruleDeScreen3));
		assertTrue(change instanceof CompoundChange);
		assertEquals(4, ((CompoundChange) change).getChanges().size());
//		((CompoundChange) change).getChanges().forEach(c -> System.out.println(((AttributeChange) c).getObject() + " " + ((AttributeChange) c).getNewValue()));
		
	}

	@Test
	public void testDeScreen3AdditionalOrGroupApplicable() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3 = (Rule) module.getUnit("De_Screen3_AdditionalOrGroup");
		Rule multiRule1 = ruleDeScreen3.getMultiRules().get(0);

		ConfigurationEngine engine = new ConfigurationEngine();

		List<Match> matches = new ArrayList<Match>();
		engine.findMatches(ruleDeScreen3, graph, null).forEach(matches::add);

		assertEquals(matches.size(), 1);

		Match match1 = matches.get(0);
		assertEquals(match1.getNodeTargets().size(), 4);
		assertEquals(match1.getMultiMatches(multiRule1).size(), 3);
		assertEquals(match1.getMultiMatches(multiRule1).get(0).getNodeTargets().size(), 6);

		Change change = engine.createChange(ruleDeScreen3, graph, match1, new MatchImpl(ruleDeScreen3));
		assertTrue(change instanceof CompoundChange);
		assertEquals(8, ((CompoundChange) change).getChanges().size());
	}

	@Test
	public void testDeScreen3AdditionalOrGroup2MultiRulesApplicable() {
		HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/java/efmconfiguration/engine/test");

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource model = resourceSet.getResource("config_screen3active.xmi");
		EGraph graph = new EGraphImpl(model);

		Module module = resourceSet.getModule("SimpleConfigurationRules.henshin");
		Rule ruleDeScreen3 = (Rule) module.getUnit("De_Screen3_AdditionalOrGroup_2MultiRules");
		Rule multiRule1 = ruleDeScreen3.getMultiRules().get(0);
		Rule multiRule2 = ruleDeScreen3.getMultiRules().get(1);

		ConfigurationEngine engine = new ConfigurationEngine();

		List<Match> matches = new ArrayList<Match>();
		engine.findMatches(ruleDeScreen3, graph, null).forEach(matches::add);

		assertEquals(matches.size(), 1);

		Match match1 = matches.get(0);
		assertEquals(match1.getNodeTargets().size(), 4);
		assertEquals(match1.getMultiMatches(multiRule1).size(), 1);
		assertEquals(match1.getMultiMatches(multiRule1).get(0).getNodeTargets().size(), 5);
		assertEquals(match1.getMultiMatches(multiRule2).size(), 3);
		assertEquals(match1.getMultiMatches(multiRule2).get(0).getNodeTargets().size(), 5);

		Change change = engine.createChange(ruleDeScreen3, graph, match1, new MatchImpl(ruleDeScreen3));
		assertTrue(change instanceof CompoundChange);
		assertEquals(8, ((CompoundChange) change).getChanges().size());
	}
}
