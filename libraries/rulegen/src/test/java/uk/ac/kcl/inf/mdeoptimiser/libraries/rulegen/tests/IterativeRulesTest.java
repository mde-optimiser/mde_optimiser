package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests;

import static uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils.ExpectedRulesHelper.writeModel;

import java.util.*;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.RulesGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils.MetamodelLoader;

@ExtendWith(InjectionExtension.class)
public class IterativeRulesTest {

  EPackage metamodel;
  List<Multiplicity> multiplicities;
  MetamodelLoader metamodelLoader;

  @BeforeEach
  public void rulegenInitialise() {
    this.metamodelLoader = new MetamodelLoader();
    this.metamodel = metamodelLoader.fakeGenericIterativeMetamodel();
    this.multiplicities = new LinkedList<Multiplicity>();
  }

  @Test
  @DisplayName("Test iterative repair rules and repairs generation")
  public void assertThatTheCorrectIterativeRulesAreGeneratedForGivenMultiplicities2() {

    // Original metamodel with 0..* 0..* multiplicities
    // this.multiplicities.add(new Multiplicity("A", "edgeAB", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    // this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 2, this.metamodel));
    // this.multiplicities.add(new Multiplicity("C", "edgeCD", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("D", "edgeDE", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("E", "edgeEF", 1, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    List<RuleSpec> ruleSpecs = List.of(new RuleSpec("B"));

    var rulesGenerator = new RulesGenerator(this.metamodel, this.multiplicities, ruleSpecs);

    var createdRules =
        rulesGenerator.generateRules().values().stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    outputRules(createdRules, new HashMap<String, String>(), "iterative");

    rulesGenerator.generateRules();
  }

  public void outputRules(List<Module> createdRules, Map<String, String> rules, String action) {

    var outputPath = String.format("src/resources/iterative/new/%s/", action);

    createdRules.forEach(
        module -> {
          var expectedModuleName =
              (!rules.containsKey(module.getName()))
                  ? module.getName()
                  : rules.get(module.getName());

          writeModel(module, outputPath, expectedModuleName + ".henshin");
        });
  }
}
