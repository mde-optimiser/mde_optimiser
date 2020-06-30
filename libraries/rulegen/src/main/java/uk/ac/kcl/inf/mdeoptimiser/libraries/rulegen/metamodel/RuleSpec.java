package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel;

public class RuleSpec {

  String node;
  String edge;
  String action;

  public RuleSpec(String node) {
    this(node, null);
  }

  public RuleSpec(String node, String action) {
    this.node = node;
    this.action = action;
  }

  public RuleSpec(String node, String edge, String action) {
    this.node = node;
    this.edge = edge;
    this.action = action;
  }

  public String getNode() {
    return this.node;
  }

  public String getEdge() {
    return this.edge;
  }

  /** Get what types of rules we are allowed to created. Should perhaps be an enum */
  public String getActions() {

    if (action == null) {
      return "ALL";
    } else {
      return action;
    }
  }

  public boolean isNode() {
    return this.edge == null;
  }

  public boolean isEdge() {
    return this.edge != null;
  }
}
