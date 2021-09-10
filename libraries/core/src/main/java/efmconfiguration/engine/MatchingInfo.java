package efmconfiguration.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;

/**
 * Maintains information on how nodes from one particular rule can be bound to different concrete
 * classes.
 *
 * @author danstru
 */
public class MatchingInfo {
  // Map each node (from the rule or one of its multi-rules)
  // with a concrete type to its type.
  Map<Node, EObject> concreteElements;

  // Map each node (from the rule or one of its multi-rules)
  // with an abstract type to the possible options to bind
  // it to concrete type.
  // (We use a list rather than set to enable systematic
  // iteration over all elements.)
  Map<Node, List<EObject>> abstract2concrete;

  // Map each class to the object instantiating it
  Map<EClass, EObject> class2object;

  // Efficiently store attribute-related information
  Map<Node, Boolean> node2attributeValue;
  Map<Node, EAttribute> node2attributeType;

  // Efficiently store multirule-related information
  Set<Rule> multiRules;
  Map<Rule, Map<Node, Node>> contextNodesOfMultiRules;
  Map<Rule, Set<Node>> actualNodesOfMultiRules;

  public MatchingInfo(Rule rule, EGraph graph) {
    concreteElements = new HashMap<Node, EObject>();
    abstract2concrete = new HashMap<Node, List<EObject>>();
    class2object = new HashMap<EClass, EObject>();
    node2attributeValue = new HashMap<Node, Boolean>();
    node2attributeType = new HashMap<Node, EAttribute>();

    multiRules = new HashSet<Rule>();
    contextNodesOfMultiRules = new HashMap<Rule, Map<Node, Node>>();
    actualNodesOfMultiRules = new HashMap<Rule, Set<Node>>();

    List<Node> allLhsNodes = new ArrayList<Node>(rule.getLhs().getNodes());
    for (Rule multiRule : rule.getMultiRules()) {
      allLhsNodes.addAll(multiRule.getLhs().getNodes());
    }
    for (Node node : allLhsNodes) {
      if (!node.getType().isAbstract()) {
        concreteElements.put(node, graph.getDomain(node.getType(), true).get(0));
      }
    }

    for (Node node : allLhsNodes) {
      if (node.getType().isAbstract()) {
        abstract2concrete.put(node, graph.getDomain(node.getType(), false));
      }
    }

    for (Node node : allLhsNodes) {
      if (!node.getAttributes().isEmpty()) {
        Attribute attr = node.getAttributes().get(0);
        node2attributeValue.put(node, Boolean.parseBoolean((String) attr.getValue()));
        node2attributeType.put(node, attr.getType());
      }
    }

    for (Rule multiRule : rule.getMultiRules()) {
      Set<Node> actualNodes = new HashSet<Node>();
      Map<Node, Node> contextNodes = new HashMap<Node, Node>();
      for (Node node : multiRule.getLhs().getNodes()) {
        Node original = multiRule.getMultiMappings().getOrigin(node);
        if (original != null) {
          contextNodes.put(node, original);
        } else {
          actualNodes.add(node);
        }
      }
      multiRules.add(multiRule);
      contextNodesOfMultiRules.put(multiRule, contextNodes);
      actualNodesOfMultiRules.put(multiRule, actualNodes);
    }
  }

  public boolean attributesAgree(Node node, EObject object) {
    Object attributeValue = node2attributeValue.get(node);
    if (attributeValue == null) return true;
    else {
      return attributeValue.equals(object.eGet(node2attributeType.get(node)));
    }
  }
}
