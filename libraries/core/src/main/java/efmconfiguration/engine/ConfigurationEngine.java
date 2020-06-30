package efmconfiguration.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptEngine;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.Change;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.AttributeChangeImpl;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.CompoundChangeImpl;
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;

/**
 * Tailored Henshin engine for applying generated CPOS rules to configurations.
 * 
 * Exploits two main assumptions: 1. For each class, there exists exactly one
 * object; 2. Only a small subset of Henshin concepts are used (all nodes
 * "preserve"; edges are irrelevant; only attributes are changed; multi-rules
 * have only one nesting level; no application conditions).
 * 
 * @author Daniel Strüber
 *
 */
public class ConfigurationEngine implements Engine {
	Map<Rule, MatchingInfo> rule2matchingInfo = new HashMap<Rule, MatchingInfo>();

//	public static boolean OPTION_DETERMINISTIC = false;
//	public static boolean OPTION_STOP_AFTER_FIRST = true;

	@Override
	public Iterable<Match> findMatches(Rule rule, EGraph graph, Match partialMatch) {
		MatchingInfo matchingInfo = getMatchingInfo(rule, graph);

		Map<Node, EObject> mappingsConcrete = new HashMap<Node, EObject>();
		boolean matchPossible = populateMappingsConcrete(graph, matchingInfo, mappingsConcrete);
		if (!matchPossible)
			return new ArrayList<Match>();

		// If no abstract elements exist, return exactly one match;
		// otherwise return an actual iterable.
		if (matchingInfo.abstract2concrete.keySet().isEmpty() && rule.getMultiRules().isEmpty()) {
			return wrapMappingsIntoMatchList(rule, matchingInfo, mappingsConcrete, graph);
		} else {
			if ((Boolean) this.getOptions().get("OPTION_STOP_AFTER_FIRST")) {
				return createOneMatch(rule, matchingInfo, mappingsConcrete, graph);
			} else {
				return createMatches(rule, matchingInfo, mappingsConcrete, graph);
			}
		}
	}

	private boolean populateMappingsConcrete(EGraph graph, MatchingInfo matchingInfo,
			Map<Node, EObject> mappingsConcrete) {
		for (Node n : matchingInfo.concreteElements.keySet()) {
			EObject o = matchingInfo.concreteElements.get(n);
			mappingsConcrete.put(n, o);

			// If the LHS nodes and actual objects do not agree
			// in one assigned attribute, no match exists
			if (!matchingInfo.attributesAgree(n, o))
				return false;
		}
		return true;
	}

	private Iterable<Match> wrapMappingsIntoMatchList(Rule rule, MatchingInfo matchingInfo,
			Map<Node, EObject> mappingsConcrete, EGraph graph) {
		Match match = wrapAssignmentIntoMatch(rule, matchingInfo, mappingsConcrete, graph, false);
		List<Match> resultList = new ArrayList<Match>();
		resultList.add(match);
		return resultList;
	}

	private Match wrapAssignmentIntoMatch(Rule rule, MatchingInfo matchingInfo, Map<Node, EObject> mappingsConcrete,
			EGraph graph, boolean forMultiMatch) {
		Match match = new MatchImpl(rule);

		for (Entry<Node, EObject> m : mappingsConcrete.entrySet()) {
			match.setNodeTarget(m.getKey(), m.getValue());
		}

		if (!forMultiMatch) {
			for (Rule multiRule : matchingInfo.multiRules) {
				Map<Node, EObject> initialAssignments = new HashMap<Node, EObject>();
				for (Entry<Node, Node> entry : matchingInfo.contextNodesOfMultiRules.get(multiRule).entrySet()) {
					initialAssignments.put(entry.getKey(), mappingsConcrete.get(entry.getValue()));
				}
				match.getMultiMatches(multiRule)
						.addAll(computeMultiMatches(multiRule, matchingInfo, graph, initialAssignments));
			}
		}

		return match;
	}

	private List<Match> computeMultiMatches(Rule multiRule, MatchingInfo matchingInfo, EGraph graph,
			Map<Node, EObject> initialAssignments) {
		// We enumerate all possible combinations of abstract-to-concrete mappings,
		// checking for each of them if they produce a valid match (i.e., attribute
		// values agree and no double assignments). "node2pointer" maintains the current
		// indexes, which are incremented through the enumeration.
		LinkedHashMap<Node, Integer> node2pointer = new LinkedHashMap<Node, Integer>();
		for (Node node : matchingInfo.actualNodesOfMultiRules.get(multiRule)) {
			if (!initialAssignments.containsKey(node)) {
				if (matchingInfo.concreteElements.containsKey(node)) {
					initialAssignments.put(node, matchingInfo.concreteElements.get(node));
				} else {
					node2pointer.put(node, 0);
				}
			}
		}

		Boolean done = false;
		List<Match> result = new ArrayList<Match>();

		while (!done) {
			Map<Node, EObject> assignmentMulti = new HashMap<Node, EObject>();
			boolean matchPossible = populateAssignmentMulti(assignmentMulti, multiRule, matchingInfo, initialAssignments,
					node2pointer);
			done = !CombinationProvider.countOneUp(matchingInfo, node2pointer);
			if (matchPossible) {
				Match m = wrapAssignmentIntoMatch(multiRule, matchingInfo, assignmentMulti, graph, true);
				result.add(m);
			}
		}
		return result;
	}

	private boolean getNextCombination(MatchingInfo matchingInfo, LinkedHashMap<Node, Integer> node2pointer,
			long possibleCombinations, Set<String> usedCombinations) {
		if ((Boolean) this.getOptions().get("OPTION_DETERMINISTIC")) {
			return CombinationProvider.countOneUp(matchingInfo, node2pointer);
		} else {
			return CombinationProvider.getRandomAssignment(matchingInfo, node2pointer, possibleCombinations,
					usedCombinations);
		}

	}

	private boolean populateAssignmentMulti(Map<Node, EObject> assignment, Rule multiRule, MatchingInfo matchingInfo,
			Map<Node, EObject> initialAssignments, LinkedHashMap<Node, Integer> node2pointer) {
		Set<EObject> used = new HashSet<EObject>();
		assignment.putAll(initialAssignments);
		used.addAll(initialAssignments.values());

		for (Entry<Node, Integer> entry : node2pointer.entrySet()) {
			Node node = entry.getKey();
			int index = entry.getValue();
			EObject value = matchingInfo.abstract2concrete.get(node).get(index);
			if (used.contains(value) || !matchingInfo.attributesAgree(node, value)) {
				return false;
			}

			assignment.put(node, value);
			used.add(value);
		}
		return true;

	}

	private void assignInitially(MatchingInfo matchingInfo, Node node, Map<Node, Integer> node2pointer) {
		if ((Boolean) this.getOptions().get("OPTION_DETERMINISTIC")) {
			node2pointer.put(node, 0);
		} else {
			node2pointer.put(node, CombinationProvider.getRandomPosition(matchingInfo, node));
		}

	}

	private Iterable<Match> createOneMatch(Rule rule, MatchingInfo matchingInfo, Map<Node, EObject> mappingsConcrete,
			EGraph graph) {
		// We enumerate all possible combinations of abstract-to-concrete mappings,
		// checking for each of them if they produce a valid match (i.e., attribute
		// values agree and no double assignments).
		// "node2pointer" maintains the current indexes, which are incremented through
		// the enumeration.
		LinkedHashMap<Node, Integer> node2pointer = new LinkedHashMap<Node, Integer>();

		Boolean done = false;
		Set<String> usedCombinations = new HashSet<String>();
		long possibleCombinations = 1L;

		// Initialising
		if (node2pointer.isEmpty()) {
			for (Node node : rule.getLhs().getNodes()) {
				if (!mappingsConcrete.containsKey(node)) {
					assignInitially(matchingInfo, node, node2pointer);
					int n = matchingInfo.abstract2concrete.get(node).size();
					possibleCombinations = possibleCombinations * n;
				}
			}
		}

		do {
			Map<Node, EObject> assignment = new HashMap<Node, EObject>();
			boolean matchPossible = populateAssignment(assignment, node2pointer, mappingsConcrete, matchingInfo);
			if (matchPossible)
				return wrapMappingsIntoMatchList(rule, matchingInfo, assignment, graph);
			done = !getNextCombination(matchingInfo, node2pointer, possibleCombinations, usedCombinations);
		} while (!done); 
		return null;
	}

	private Iterable<Match> createMatches(Rule rule, MatchingInfo matchingInfo, Map<Node, EObject> mappingsConcrete,
			EGraph graph) {
		return new Iterable<Match>() {
			@Override
			public Iterator<Match> iterator() {
				return createMatchIterator(rule, matchingInfo, mappingsConcrete, graph);
			}

			private Iterator<Match> createMatchIterator(Rule rule, MatchingInfo matchingInfo,
					Map<Node, EObject> mappingsConcrete, EGraph graph) {
				return new Iterator<Match>() {

					// We enumerate all possible combinations of abstract-to-concrete mappings,
					// checking for each of them if they produce a valid match (i.e., attribute
					// values agree and no double assignments).
					// "node2pointer" maintains the current indexes, which are incremented through
					// the enumeration.
					LinkedHashMap<Node, Integer> node2pointer = new LinkedHashMap<Node, Integer>();

					Match next = null;
					Boolean done = false;
					Set<String> usedCombinations = new HashSet<String>();
					long possibleCombinations = 1L;

					@Override
					public boolean hasNext() {
						if (next == null) {
							next = findNext();
						}
						return next != null;
					}

					@Override
					public Match next() {
						if (next == null) {
							next = findNext();
						}
						Match result = next;
						next = null;
						return result;
					}

					private Match findNext() {
						// Initialising
						if (node2pointer.isEmpty()) {
							for (Node node : rule.getLhs().getNodes()) {
								if (!mappingsConcrete.containsKey(node)) {
									assignInitially(matchingInfo, node, node2pointer);
									int n = matchingInfo.abstract2concrete.get(node).size();
									possibleCombinations = possibleCombinations * n;
								}
							}
						}

						// Actual find next
						while (!done) {
							Map<Node, EObject> assignment = new HashMap<Node, EObject>();
							boolean matchPossible = populateAssignment(assignment, node2pointer, mappingsConcrete,
									matchingInfo);
							done = !getNextCombination(matchingInfo, node2pointer, possibleCombinations,
									usedCombinations);
							if (matchPossible)
								return wrapAssignmentIntoMatch(rule, matchingInfo, assignment, graph, false);
						}
						return null;
					}

				};
			}

		};
	}

	private boolean populateAssignment(Map<Node, EObject> assignment, Map<Node, Integer> node2pointer,
			Map<Node, EObject> mappingsConcrete, MatchingInfo matchingInfo) {
		Set<EObject> used = new HashSet<EObject>();
		assignment.putAll(mappingsConcrete);
		used.addAll(mappingsConcrete.values());

		for (Entry<Node, Integer> entry : node2pointer.entrySet()) {
			Node node = entry.getKey();
			int index = entry.getValue();
			EObject value = matchingInfo.abstract2concrete.get(node).get(index);
			if (used.contains(value) || !matchingInfo.attributesAgree(node, value)) {
				return false;
			}

			assignment.put(node, value);
			used.add(value);
		}
		return true;
	}

	private MatchingInfo getMatchingInfo(Rule rule, EGraph graph) {
		MatchingInfo result = rule2matchingInfo.get(rule);

		if (result == null) {
			result = new MatchingInfo(rule, graph);
			rule2matchingInfo.put(rule, result);
		}

		return result;
	}

	@Override
	public Change createChange(Rule rule, EGraph graph, Match completeMatch, Match resultMatch) {
		CompoundChangeImpl compoundChange = new CompoundChangeImpl(graph);
		MatchingInfo matchingInfo = getMatchingInfo(rule, graph);

		List<EObject> changedObjects = new ArrayList<EObject>();
		createAttributeChanges(rule, graph, completeMatch, compoundChange, changedObjects, matchingInfo);
		for (Rule multiRule : matchingInfo.multiRules) {
			for (Match multiMatch : completeMatch.getMultiMatches(multiRule)) {
				createAttributeChanges(multiRule, graph, multiMatch, compoundChange, changedObjects, matchingInfo);
			}
		}

		resultMatch = completeMatch;
		return compoundChange;
	}

	private void createAttributeChanges(Rule rule, EGraph graph, Match match, CompoundChangeImpl change,
			List<EObject> changedObjects, MatchingInfo matchingInfo) {
		for (Mapping mapping : rule.getMappings()) {
			if (!mapping.getImage().getAttributes().isEmpty()) {
				EObject object = match.getNodeTarget(mapping.getOrigin());
				EAttribute attribute = matchingInfo.node2attributeType.get(mapping.getImage());
				Boolean value = matchingInfo.node2attributeValue.get(mapping.getImage());
				AttributeChangeImpl ac = new AttributeChangeImpl(graph, object, attribute, value);

				// Avoid multiple changes for the same object.
				if (!changedObjects.contains(object)) {
					change.getChanges().add(ac);
					changedObjects.add(object);
				}
			}
		}
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return null;
	}

	private Map<String, Object> options = null;
	
	@Override
	public Map<String, Object> getOptions()  {
		if (options == null) {
			options = new HashMap<>();
			options.put("OPTION_DETERMINISTIC", false);
			options.put("OPTION_STOP_AFTER_FIRST", true);
		}
		return options;
	}

	@Override
	public void shutdown() {
	}
}
