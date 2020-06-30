package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.MatcherAssert.assertThat;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils.MetamodelLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.SpecsGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpecType;

@ExtendWith(InjectionExtension.class)
public class SpecGeneratorTests {

  EPackage metamodel;
  List<Multiplicity> multiplicities;

  @BeforeEach
  public void rulegenInitialise() {
    this.metamodel = new MetamodelLoader().fakeGenericMetamodel();
    this.multiplicities = new LinkedList<Multiplicity>();
  }

  public void writeModel(EObject model, String modelPathPrefix, String modelFilename) {

    var reg = Resource.Factory.Registry.INSTANCE;
    var m = reg.getExtensionToFactoryMap();
    m.put("henshin", new HenshinResourceFactory());

    var options = new HashMap<String, Boolean>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

    var resourceSet = new HenshinResourceSet(modelPathPrefix);

    var resource = resourceSet.createResource(URI.createFileURI(modelFilename));

    if (resource.isLoaded()) {
      resource.getContents().clear();
    }

    resource.getContents().add(model);
    try {
      resource.save(options);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<RepairSpec> getRepairSpecsFromSet(Set<List<RepairSpec>> combinations) {
    var generatedSpecs = new ArrayList<RepairSpec>();

    combinations.forEach ( combination -> generatedSpecs.addAll(combination));

    return generatedSpecs;
  }

  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // Create Node Rule tests
  //
  //
  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // 0 .. 1
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([0..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());

    });

  }
  // 0 .. l
  /*
   * Create node multiplicity pattern ([0..1] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([0..1] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([0..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([0..1] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([0..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([0..1] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([0..1] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([0..1] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 0 .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([0..n] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([0..n] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([0..n] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([0..n] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([0..n] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([0..n] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([0..n] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([0..n] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([0..n] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 0 .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([0..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([0..*] to [0..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([0..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([0..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([0..*] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([0..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 2 .. 2
  /*
   * Create node multiplicity pattern ([0..*] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 2 .. l
  /*
   * Create node multiplicity pattern ([0..*] to [2..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_2l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 2 .. *
  /*
   * Create node multiplicity pattern ([0..*] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. 1
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([1..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([1..1] to [0..5])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([1..1] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([1..1] to [1..1])
   */
  @Test
  @Disabled
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([1..1] to [1..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([1..1] to [1..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([1..1] to [k..k])
   */
  @Test
  @Disabled
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // k .. l
  /*
   * Create node multiplicity pattern ([1..1] to [k..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([1..1] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([1..n] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([1..n] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([1..n] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    });

  }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([1..n] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([1..n] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([1..n] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([1..n] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([1..n] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([1..n] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([1..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([1..*] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([1..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([1..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([1..*] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([1..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([1..*] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([1..*] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([1..*] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([n..n] to [0..1]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([n..n] to [0..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([n..n] to [0..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([n..n] to [1..1]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([n..n] to [1..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      // expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR)));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([n..n] to [1..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([n..n] to [n..n]
   */
  @Test
  @Disabled
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_nn_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

//		node.getEReferences().forEach [edge |
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
//				]



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  /*
   * Create node multiplicity pattern ([n..n] to [k..k]
   */
  @Test
  @Disabled
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 3, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

//		node.getEReferences().forEach (edge ->
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
//					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
//    );



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // k .. l
  /*
   * Create node multiplicity pattern ([n..n] to [k..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([n..n] to [k..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. m
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([n..m] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([n..m] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([n..m] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([n..m] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([n..m] to [1..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([n..m] to [1..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([n..m] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([n..m] to [k..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([n..m] to [k..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Create node multiplicity pattern ([n..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 0 .. l
  /*
   * Create node multiplicity pattern ([n..*] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 0 .. *
  /*
   * Create node multiplicity pattern ([n..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "CREATE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Create node multiplicity pattern ([n..*] to [1..1]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 1 .. l
  /*
   * Create node multiplicity pattern ([n..*] to [1..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // 1 .. *
  /*
   * Create node multiplicity pattern ([n..*] to [1..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Create node multiplicity pattern ([n..*] to [k..k]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Create node multiplicity pattern ([n..*] to [k..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(3, combinations.size());
    }); }

  // k .. *
  /*
   * Create node multiplicity pattern ([n..*] to [k..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));





      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // Multiple edges
  // TODO add tests for multiple edges and iterative repairs
  @Test
  @Disabled
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_multi_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("B", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));




      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // Delete Node Rule tests
  //
  //
  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // 0 .. 1
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([0..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([0..1] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([0..1] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([0..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([0..1] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([0..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([0..1] to [k..k])
   */
  // TODO This I think is a bug as you can't do single repair, when ub of the opposite is > ub of the target node for repair
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([0..1] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([0..1] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 0 .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([0..n] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([0..n] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([0..n] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([0..n] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([0..n] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([0..n] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([0..n] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([0..n] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([0..n] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 0 .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([0..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([0..*] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([0..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([0..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([0..*] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([0..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 2 .. 2
  /*
   * Delete node multiplicity pattern ([0..*] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // 2 .. l
  /*
   * Delete node multiplicity pattern ([0..*] to [2..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_2l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 2 .. *
  /*
   * Delete node multiplicity pattern ([0..*] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. 1
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([1..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([1..1] to [0..5])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([1..1] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([1..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([1..1] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([1..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([1..1] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();



    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // k .. l
  /*
   * Delete node multiplicity pattern ([1..1] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([1..1] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([1..n] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([1..n] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([1..n] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([1..n] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([1..n] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([1..n] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([1..n] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([1..n] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([1..n] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));


      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // 1 .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([1..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([1..*] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([1..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([1..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([1..*] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([1..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([1..*] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([1..*] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([1..*] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. n
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([n..n] to [0..1]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));


      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([n..n] to [0..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([n..n] to [0..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([n..n] to [1..1]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();





    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([n..n] to [1..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([n..n] to [1..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([n..n] to [k..k]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_nn_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();





    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  /*
   * Delete node multiplicity pattern ([n..n] to [k..k]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 3, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();





    assertThat(getRepairSpecsFromSet(combinations),
            IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
    Assertions.assertEquals(0, combinations.size());
  }

  // k .. l
  /*
   * Delete node multiplicity pattern ([n..n] to [k..l]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([n..n] to [k..*]
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ALL");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);

    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);

    var node = metamodelWrapper.getNode(ruleSpec.getNode());

    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));



      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. m
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([n..m] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([n..m] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([n..m] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([n..m] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      // expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY)));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([n..m] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([n..m] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([n..m] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([n..m] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
      // expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      // expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY)));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([n..m] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  //
  //
  // n .. *
  //
  //
  //
  // 0 .. 1
  /*
   * Delete node multiplicity pattern ([n..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. l
  /*
   * Delete node multiplicity pattern ([n..*] to [0..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_0l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 0 .. *
  /*
   * Delete node multiplicity pattern ([n..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. 1
  /*
   * Delete node multiplicity pattern ([n..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. l
  /*
   * Delete node multiplicity pattern ([n..*] to [1..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_1l_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // 1 .. *
  /*
   * Delete node multiplicity pattern ([n..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. k
  /*
   * Delete node multiplicity pattern ([n..*] to [k..k])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kk_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(2, combinations.size());
    }); }

  // k .. l
  /*
   * Delete node multiplicity pattern ([n..*] to [k..l])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kl_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // k .. *
  /*
   * Delete node multiplicity pattern ([n..*] to [k..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kStar_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "DELETE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // Add Edge Rule tests
  //
  //
  // /////////////////////////////////////////////////////////////////////////
  /*
   * Add edge multiplicity pattern ([0..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([0..1] to [1..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_12_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([0..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([0..1] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([0..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([0..1] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_01_22_Multiplic22y() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [1..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_12_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_22_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [1..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_12_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..1] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_11_22_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Add edge multiplicity pattern ([1..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "ADD");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  // /////////////////////////////////////////////////////////////////////////
  //
  //
  // Remove Edge Rule tests
  //
  //
  // /////////////////////////////////////////////////////////////////////////
  /*
   * Remove edge multiplicity pattern ([1..*] to [0..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_01_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([0..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_0Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([0..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_0Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([0..*] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_0Star_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([0..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_0Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([0..*] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_0Star_22_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..*] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..*] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..*] to [2..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_2Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..*] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..*] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_22_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..1] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_11_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..1] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_11_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([1..1] to [1..1])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_11_11_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([2..2] to [0..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_22_0Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([2..2] to [1..*])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_22_1Star_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

  /*
   * Remove edge multiplicity pattern ([2..2] to [2..2])
   */
  @Test
  public void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_22_22_Multiplicity() {

    // Original metamodel with 0..* 0..* multiplicities
    this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
    this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
    this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));

    // Refined metamodel wrapper containing the metamodel with the refined multiplicities
    var ruleSpec = new RuleSpec("C", "REMOVE");

    var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities);

    var specGenerator = new SpecsGenerator();

    var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);
    var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
    var node = metamodelWrapper.getNode(ruleSpec.getNode());
    var expectedSpecs = new ArrayList<RepairSpec>();

    node.getEReferences().forEach(edge -> {
      expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.SWAP));

      assertThat(getRepairSpecsFromSet(combinations),
              IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray()));
      Assertions.assertEquals(1, combinations.size());
    }); }

}
