package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelInitialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.UserModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionsFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection.LanguageClassLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.RulesGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class SearchSpecification implements ISearchSpecification {

  private Optimisation model;
  HenshinResourceSet henshinResourceSet;
  EPackage theMetamodel;

  List<Unit> breedingOperators;
  List<Unit> mutationOperators;
  IPath projectRootPath;

  Map<EPackage, List<Module>> generatedOperators;

  List<IGuidanceFunction> fitnessFunctions;
  List<IGuidanceFunction> constraintFunctions;

  public SearchSpecification(IPath projectRootPath, Optimisation model) {
    this.model = model;
    this.projectRootPath = projectRootPath;
  }

  public Optimisation getOptimisationModel() {
    return this.model;
  }

  public List<Unit> getBreedingOperators() {

    if (breedingOperators != null) {
      return this.breedingOperators;
    }

    breedingOperators = new LinkedList<Unit>();

    breedingOperators.addAll(
        this.getOptimisationModel().getSearch().getEvolvers().stream()
            .filter(operator -> operator.getEvolverType().getName().equals("BREED"))
            .map(
                operator ->
                    getResourceSet()
                        .getModule(URI.createURI(operator.getRule_location()), false)
                        .getUnit(operator.getUnit()))
            .collect(Collectors.toList()));

    return breedingOperators;
  }

  public List<Unit> getMutationOperators() {

    if (this.mutationOperators != null) {
      return this.mutationOperators;
    }

    mutationOperators = new LinkedList<>();

    mutationOperators.addAll(
        model.getSearch().getEvolvers().stream()
            .filter(operator -> operator.getEvolverType().getName().equals("MUTATE"))
            .map(
                operator ->
                    getResourceSet()
                        .getModule(URI.createURI(operator.getRule_location()), false)
                        .getUnit(operator.getUnit()))
            .collect(Collectors.toList()));

    // Automatically generate mutations operators
    var generatedMutations = this.getRulegenOperators();

    if (!generatedMutations.isEmpty()) {

      //			 For each of the automatically generated modules, add the generated mutations
      //			 to the list of evolvers
      //			 Are we ever going to have more than one metamodel? Perhaps this should be a
      //			 pair instead
      var metamodel = generatedMutations.keySet().iterator().next();
      var mutations = generatedMutations.get(metamodel);

      mutations.forEach(mutation -> mutationOperators.addAll(mutation.getAllRules()));
    }

    return mutationOperators;
  }

  public List<Multiplicity> getMultiplicityRefinements() {
    // A list of multiplicity refinements specified by the user in the DSL.
    // This is optional.
    var refinements = model.getGoal().getRefinements();

    var multiplicityRefinements = new ArrayList<Multiplicity>();

    if (!refinements.isEmpty()) {
      refinements.forEach(
          refinement ->
              multiplicityRefinements.add(
                  new Multiplicity(
                      refinement.getNode(),
                      refinement.getEdge(),
                      refinement.getLowerBound(),
                      refinement.getUpperBound(),
                      getMetamodel())));
    }

    return multiplicityRefinements;
  }

  public List<RuleSpec> getRulegenSpecs() {

    var rulegenSpecs = model.getSearch().getRulegen();

    var ruleSpecs = new ArrayList<RuleSpec>();

    if (!rulegenSpecs.isEmpty()) {

      rulegenSpecs.forEach(
          rulegenSpec -> {

            // Crete the spec for a node, if configured in the MOPT file or an edge generation
            if (rulegenSpec.getNodeSpec() != null) {
              ruleSpecs.add(
                  new RuleSpec(
                      rulegenSpec.getNodeSpec().getNode(),
                      rulegenSpec.getNodeSpec().getGenerationRestriction()));
            } else {
              ruleSpecs.add(
                  new RuleSpec(
                      rulegenSpec.getEdgeSpec().getNode(),
                      rulegenSpec.getEdgeSpec().getEdge(),
                      rulegenSpec.getEdgeSpec().getGenerationRestriction()));
            }
          });
    }

    return ruleSpecs;
  }

  /**
   * If there are any rule generation instructions present, then generate the corresponding rules.
   *
   * @return list of generated mutation operators
   * @throws Exception
   */
  public Map<EPackage, List<Module>> getRulegenOperators() {

    if (this.generatedOperators == null) {

      // Generate the list of modules that are automatically generated
      var mutations =
          new RulesGenerator(
              this.getMetamodel(), this.getMultiplicityRefinements(), this.getRulegenSpecs());

      this.generatedOperators = mutations.generateRules();
    }

    return this.generatedOperators;
  }

  public HenshinResourceSet getResourceSet() {
    if (henshinResourceSet == null) {
      henshinResourceSet =
          new HenshinResourceSet(
              projectRootPath
                  .append(this.getOptimisationModel().getProblem().getBasepath().getLocation())
                  .toPortableString());
    }

    return henshinResourceSet;
  }

  public EPackage getMetamodel() {
    if (theMetamodel == null) {

      if (!this.getOptimisationModel()
          .getProblem()
          .getMetamodel()
          .getLocation()
          .endsWith(".ecore")) {
        // The location is not an ecore file, assume it's a class name
        theMetamodel =
            LanguageClassLoader.load(this.getOptimisationModel().getProblem().getMetamodel());
      } else {
        theMetamodel =
            getResourceSet()
                .registerDynamicEPackages(
                    this.getOptimisationModel().getProblem().getMetamodel().getLocation())
                .get(0);
      }
    }

    return theMetamodel;
  }

  public IModelProvider getModelProvider() {

    // TODO Extend the DSL to allow the user to specify a generic extension to factory map. Then we
    // can
    // initialise that and dynamically load any type of model and handle it with the correct factory
    //    Resource.Factory.Registry.INSTANCE
    //        .getExtensionToFactoryMap()
    //        .put("*", new XMIResourceFactoryImpl());

    if (this.getOptimisationModel().getProblem().getModelInitialiser() != null) {
      return new UserModelProvider(
          this.getModelInitialiser(),
          getResourceSet(),
          this.getOptimisationModel().getProblem().getModel().getLocation());
    }

    return new UserModelProvider(
        getResourceSet(), this.getOptimisationModel().getProblem().getModel().getLocation());
  }

  public IModelInitialiser getModelInitialiser() {

    if (this.getOptimisationModel().getProblem().getModelInitialiser() != null) {

      return LanguageClassLoader.load(
          this.getOptimisationModel().getProblem().getModelInitialiser());
    }

    return null;
  }

  @Override
  public List<IGuidanceFunction> getConstraintFunctions() {
    if (this.constraintFunctions == null) {
      setConstraintFunctions();
    }

    return this.constraintFunctions;
  }

  public void setConstraintFunctions() {
    if (constraintFunctions == null) {
      this.constraintFunctions =
          getOptimisationModel().getGoal().getConstraints().stream()
              .map(
                  constraint ->
                      new GuidanceFunctionsFactory()
                          .loadFunction(new GuidanceFunctionAdapter(constraint)))
              .collect(Collectors.toList());
    }
  }

  @Override
  public List<IGuidanceFunction> getObjectiveFunctions() {
    if (this.fitnessFunctions == null) {
      setObjectiveFunctions();
    }

    return this.fitnessFunctions;
  }

  public void setObjectiveFunctions() {
    if (fitnessFunctions == null) {
      this.fitnessFunctions =
          getOptimisationModel().getGoal().getObjectives().stream()
              .map(
                  objective ->
                      new GuidanceFunctionsFactory()
                          .loadFunction(new GuidanceFunctionAdapter(objective)))
              .collect(Collectors.toList());
    }
  }
}
