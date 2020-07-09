package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

public class RepairSpec {

  EClass node;
  EReference edge;
  RepairSpecType repairSpecType;

  public RepairSpec(EClass node, EReference edge, RepairSpecType repairSpecType) {
    this.node = node;
    this.edge = edge;
    this.repairSpecType = repairSpecType;
  }

  public EClass getNode() {
    return this.node;
  }

  public EReference getEdge() {
    return this.edge;
  }

  public RepairSpecType getRepairSpecType() {
    return this.repairSpecType;
  }

  @Override
  public boolean equals(Object o) {

    if (o == null) return false;

    if (!(o instanceof RepairSpec)) return false;

    var repairSpec = (RepairSpec) o;

    return node.getName().equals(repairSpec.node.getName())
        && edge.getName().equals(repairSpec.edge.getName())
        && repairSpecType.equals(repairSpec.repairSpecType);
  }

  @Override
  public String toString() {
    return String.format(
        "Node %s Edge %s Repair %s \n", node.getName(), edge.getName(), repairSpecType);
  }
}
