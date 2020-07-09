package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.Module;
import org.sidiff.common.henshin.HenshinModuleAnalysis;
import org.sidiff.serge.util.RuleSemanticsChecker;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.node.RuleGenerationCommandFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.SpecsGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class RulesGenerator {

  List<Multiplicity> refinedMultiplicities;

  EPackage metamodel;
  List<RuleSpec> ruleSpecs;

  SpecsGenerator specsGenerator;
  RuleGenerationCommandFactory ruleGenerationCommandFactory;

  // TODO The metamodel should ideally be a list as there are some cases where this is requested
  public RulesGenerator(
      EPackage metamodel, List<Multiplicity> refinedMultiplicities, List<RuleSpec> rulegenSpecs) {
    this.refinedMultiplicities = refinedMultiplicities;
    this.metamodel = metamodel;
    this.ruleSpecs = rulegenSpecs;
    this.specsGenerator = new SpecsGenerator();
  }

  public RuleGenerationCommandFactory getRuleGenerationCommandFactory() {

    if (this.ruleGenerationCommandFactory == null) {
      this.ruleGenerationCommandFactory = new RuleGenerationCommandFactory();
    }

    return this.ruleGenerationCommandFactory;
  }

  public Map<EPackage, List<Module>> generateRules() {

    var generatedRules = new HashMap<EPackage, List<Module>>();

    var generatedRulesListProblem = new ArrayList<Module>();

    var generatedRulesListSolution = new ArrayList<Module>();

    var problemMetamodelWrapper = new MetamodelWrapper(this.metamodel);

    // Get each of the rule generation specs specs
    ruleSpecs.forEach(
        ruleSpec -> {

          // Generate the set of repair spec combinations
          var repairSpecs =
              this.specsGenerator.getRepairsForRuleSpec(ruleSpec, problemMetamodelWrapper);

          // Generate the rules for those combinations
          generatedRulesListProblem.addAll(
              this.generateSpecRules(ruleSpec, repairSpecs, problemMetamodelWrapper));
        });

    if (this.refinedMultiplicities.size() > 0) {
      var solutionMetamodelWrapper =
          new MetamodelWrapper(this.metamodel, this.refinedMultiplicities);

      // Get each of the rule generation specs specs
      ruleSpecs.forEach(
          ruleSpec -> {

            // Generate the set of repair spec combinations
            var repairSpecs =
                this.specsGenerator.getRepairsForRuleSpec(ruleSpec, solutionMetamodelWrapper);

            // Generate the rules for those combinations
            generatedRulesListSolution.addAll(
                this.generateSpecRules(ruleSpec, repairSpecs, solutionMetamodelWrapper));
          });

      // Code above touches a singleton, so must be restored back
      this.refinedMultiplicities.forEach(
          multiplicity -> {
            solutionMetamodelWrapper.revertEdgeMultiplicities(multiplicity);
          });
    }

    generatedRules.put(
        this.metamodel,
        removeDupplicateRules(generatedRulesListProblem, generatedRulesListSolution));

    return generatedRules;
  }

  private List<Module> removeDupplicateRules(
      List<Module> problemRules, List<Module> solutionRules) {

    var uniqueRules = new ArrayList<Module>();

    problemRules.forEach(
        module -> {
          if (!isRuleInCollection(module, uniqueRules)) {
            uniqueRules.add(module);
          }
        });

    solutionRules.forEach(
        module -> {
          if (!isRuleInCollection(module, uniqueRules)) {
            uniqueRules.add(module);
          }
        });

    return uniqueRules;
  }

  private boolean isRuleInCollection(Module rule, List<Module> uniqueRules) {

    var leftRule = HenshinModuleAnalysis.getAllRules(rule).get(0);

    for (var module : uniqueRules) {

      var rightRule = HenshinModuleAnalysis.getAllRules(module).get(0);
      var checker = new RuleSemanticsChecker(leftRule, rightRule);

      if (checker.isEqual()) {
        return true;
      }
    }

    return false;
  }

  private List<Module> runRuleGenerationCommands(List<IRuleGenerationCommand> rulesGenerationList) {

    var generatedRules = new ArrayList<Module>();

    rulesGenerationList.forEach(command -> generatedRules.add(command.generate()));

    return generatedRules;
  }

  private List<Module> generateSpecRules(
      RuleSpec ruleSpec,
      Map<String, Set<List<RepairSpec>>> mappedRepairSpecs,
      MetamodelWrapper metamodelWrapper) {

    var rulesGenerationList = new ArrayList<IRuleGenerationCommand>();

    mappedRepairSpecs.forEach(
        (repairType, repairSpecsSet) -> {

          // For each repair spec type, generate rules for all the combinations
          repairSpecsSet.forEach(
              repairSpecs ->
                  // for each repair spec, pick a command from the factory and add it to the command
                  // executor
                  rulesGenerationList.addAll(
                      this.getRuleGenerationCommandFactory()
                          .makeCommand(repairType, metamodelWrapper, ruleSpec, repairSpecs)));
        });

    // Execute the rule generation commands
    return this.runRuleGenerationCommands(rulesGenerationList);
  }
}
