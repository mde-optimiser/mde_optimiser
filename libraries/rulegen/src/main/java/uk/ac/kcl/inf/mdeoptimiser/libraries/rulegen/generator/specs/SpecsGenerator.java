package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EReference;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class SpecsGenerator {

  public Map<String, Set<List<RepairSpec>>> getRepairsForRuleSpec(
      RuleSpec ruleSpec, MetamodelWrapper metamodelWrapper) {

    var generatedRepairSpecs = new HashMap<String, Set<List<RepairSpec>>>();

    switch (ruleSpec.getActions()) {
      case "ALL":
        {
          if (ruleSpec.isEdge()) {
            generatedRepairSpecs.put(
                "ADD",
                generateNodeRepairCombinations(generateEdgeAddRules(ruleSpec, metamodelWrapper)));
            generatedRepairSpecs.put(
                "REMOVE",
                generateNodeRepairCombinations(
                    generateEdgeRemoveRules(ruleSpec, metamodelWrapper)));
          } else {
            generatedRepairSpecs.put(
                "CREATE",
                generateNodeRepairCombinations(
                    generateNodeCreateRules(ruleSpec, metamodelWrapper)));
            generatedRepairSpecs.put(
                "DELETE",
                generateNodeRepairCombinations(
                    generateNodeDeleteRules(ruleSpec, metamodelWrapper)));
            generatedRepairSpecs.put(
                "ADD",
                generateNodeRepairCombinations(generateEdgeAddRules(ruleSpec, metamodelWrapper)));
            generatedRepairSpecs.put(
                "REMOVE",
                generateNodeRepairCombinations(
                    generateEdgeRemoveRules(ruleSpec, metamodelWrapper)));
          }
          break;
        }
      case "CREATE":
        {
          generatedRepairSpecs.put(
              "CREATE",
              generateNodeRepairCombinations(generateNodeCreateRules(ruleSpec, metamodelWrapper)));

          break;
        }
      case "DELETE":
        {
          generatedRepairSpecs.put(
              "DELETE",
              generateNodeRepairCombinations(generateNodeDeleteRules(ruleSpec, metamodelWrapper)));

          break;
        }

      case "ADD":
        {
          generatedRepairSpecs.put(
              "ADD",
              generateNodeRepairCombinations(generateEdgeAddRules(ruleSpec, metamodelWrapper)));

          break;
        }
      case "REMOVE":
        {
          generatedRepairSpecs.put(
              "REMOVE",
              generateNodeRepairCombinations(generateEdgeRemoveRules(ruleSpec, metamodelWrapper)));

          break;
        }
    }

    return generatedRepairSpecs;
  }

  public Set<List<RepairSpec>> generateNodeRepairCombinations(
      Map<EReference, Set<RepairSpec>> repairSpecs) {

    var repairs = new ArrayList<Set<RepairSpec>>();

    repairSpecs.keySet().forEach(key -> repairs.add(repairSpecs.get(key)));

    return Sets.cartesianProduct(repairs);
  }

  public Map<EReference, Set<RepairSpec>> generateNodeCreateRules(
      RuleSpec ruleSpec, MetamodelWrapper metamodelWrapper) {

    var repairSpecs = new HashMap<EReference, Set<RepairSpec>>();

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    node.getEReferences()
        .forEach(
            edge -> {
              var repairs = new HashSet<RepairSpec>();

              if (edge.getEOpposite() == null) {
                repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
              } else {

                if (edge.getLowerBound() == 0) {

                  // Create node only, no mandatory neighbours needed
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

                } else if (edge.getLowerBound() != edge.getUpperBound()) {

                  if (edge.getEOpposite().getLowerBound() >= 0
                      && edge.getEOpposite().getLowerBound()
                          != edge.getEOpposite().getUpperBound()) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
                  }

                  if (edge.getLowerBound() >= 0 && edge.getEOpposite().getUpperBound() != -1) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
                  }

                  if (edge.getLowerBound() > 1 && edge.getEOpposite().getUpperBound() != -1) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
                  }

                } else if (edge.getLowerBound() == edge.getUpperBound()) {
                  if (edge.getEOpposite().getLowerBound() != edge.getEOpposite().getUpperBound()) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
                  }
                }
              }

              repairSpecs.put(edge, repairs);
            });

    return repairSpecs;
  }

  public Map<EReference, Set<RepairSpec>> generateNodeDeleteRules(
      RuleSpec ruleSpec, MetamodelWrapper metamodelWrapper) {

    var repairSpecs = new HashMap<EReference, Set<RepairSpec>>();

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    node.getEReferences()
        .forEach(
            edge -> {
              var repairs = new HashSet<RepairSpec>();

              if (edge.getEOpposite() == null || edge.getEOpposite().getLowerBound() == 0) {

                repairs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

              } else {

                if (edge.getEOpposite().getLowerBound() > 0
                    && (edge.getEOpposite().getUpperBound() > edge.getEOpposite().getLowerBound()
                        || edge.getEOpposite().getUpperBound() == -1)) {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
                }

                // Second set
                if (edge.getEOpposite().getLowerBound() == 1
                    && edge.getEOpposite().getUpperBound() == 1) {

                  if (edge.getLowerBound() != edge.getUpperBound()) {

                    repairs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
                  }
                }

                if (edge.getEOpposite().getLowerBound() == edge.getEOpposite().getUpperBound()
                    && edge.getEOpposite().getLowerBound() > 1) {

                  if (edge.getLowerBound() != edge.getUpperBound()) {

                    repairs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
                    if (edge.getLowerBound() == 0 || edge.getLowerBound() > 1) {
                      repairs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
                    }
                  }
                }
              }

              repairSpecs.put(edge, repairs);
            });

    return repairSpecs;
  }

  public Map<EReference, Set<RepairSpec>> generateEdgeAddRules(
      RuleSpec ruleSpec, MetamodelWrapper metamodelWrapper) {

    var repairSpecs = new HashMap<EReference, Set<RepairSpec>>();

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    List<EReference> references = node.getEReferences();

    //If one edge is specified only, filter out the rest.
    if(ruleSpec.isEdge()){
      references = references.stream().filter(reference -> reference.getName().equals(ruleSpec.getEdge())).collect(Collectors.toList());
    }

    references
        .forEach(
            edge -> {
              var repairs = new HashSet<RepairSpec>();

              if (edge.getEOpposite() == null || edge.getEOpposite().getLowerBound() == 0) {

                if (edge.getLowerBound() == edge.getUpperBound()) {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));
                } else {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
                }

              } else {

                //				if(edge.upperBound != -1) {
                //					if(edge.EOpposite.lowerBound == edge.EOpposite.upperBound) {
                //							repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
                //					} else {
                //							repairs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
                //					}
                //				}
                //
                //				if(edge.upperBound == -1) {
                //					if(edge.EOpposite.lowerBound == edge.EOpposite.upperBound) {
                //							repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
                //					} else {
                //							repairs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
                //					}
                //				}

                if (edge.getLowerBound() == edge.getUpperBound()) {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));
                } else {
                  if (edge.getEOpposite().getLowerBound() == edge.getEOpposite().getUpperBound()) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
                  } else {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
                  }
                }
              }

              repairSpecs.put(edge, repairs);
            });

    return repairSpecs;
  }

  public Map<EReference, Set<RepairSpec>> generateEdgeRemoveRules(
      RuleSpec ruleSpec, MetamodelWrapper metamodelWrapper) {

    var repairSpecs = new HashMap<EReference, Set<RepairSpec>>();

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    List<EReference> references = node.getEReferences();

    //If one edge is specified only, filter out the rest.
    if(ruleSpec.isEdge()){
      references = references.stream().filter(reference -> reference.getName().equals(ruleSpec.getEdge())).collect(Collectors.toList());
    }

    references
        .forEach(
            edge -> {
              var repairs = new HashSet<RepairSpec>();

              if (edge.getEOpposite() == null || edge.getEOpposite().getLowerBound() == 0) {

                if (edge.getLowerBound() == edge.getUpperBound()) {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));
                } else {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));
                }

              } else {

                if (edge.getLowerBound() == edge.getUpperBound()) {
                  repairs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));
                } else {
                  if (edge.getEOpposite().getLowerBound() == edge.getEOpposite().getUpperBound()) {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
                  } else {
                    repairs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));
                  }
                }

                //				if(edge.lowerBound !== -1){
                //
                //					if(edge.EOpposite.lowerBound == edge.EOpposite.upperBound){
                //						repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE))
                //					} else {
                //						repairs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE))
                //					}
                //				}
                //
                //				if(edge.lowerBound == -1){
                //					if(edge.EOpposite.lowerBound == edge.EOpposite.upperBound){
                //						repairs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE))
                //					} else {
                //						repairs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE))
                //					}
                //				}

              }

              repairSpecs.put(edge, repairs);
            });

    return repairSpecs;
  }
}
