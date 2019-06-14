package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.UserModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection.LanguageClassLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.RulesGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class OptimisationInterpreter {

  Optimisation model;

  HenshinResourceSet henshinResourceSet;
  EPackage theMetamodel;

  List<Unit> breedingOperators;
  List<Unit> mutationOperators;
  IPath projectRootPath;

  Map<EPackage, List<Module>> generatedOperators;

  public OptimisationInterpreter(String projectPath, Optimisation model) {
    this.model = model;
    this.projectRootPath = new Path(projectPath);
  }

  public SearchResult start() {

    // This model provider loads the model given by the user in the DSL
    var algorithmConfiguration =
        new MoeaFrameworkAlgorithmConfiguration(model.getSolver(), this.getSolutionGenerator());
    var moeaOptimisation = new MoeaOptimisation();
    return moeaOptimisation.execute(algorithmConfiguration);
  }

  public SolutionGenerator getSolutionGenerator() {
    return new SolutionGenerator(
        model,
        this.getBreedingOperators(),
        this.getMutationOperators(),
        this.getModelProvider(),
        this.getMetamodel());
  }

  public IModelProvider getModelProvider() {

    // TODO Extend the DSL to allow the user to specify a generic extension to factory map. Then we
    // can
    // initialise that and dynamically load any type of model and handle it with the correct factory
    Resource.Factory.Registry.INSTANCE
        .getExtensionToFactoryMap()
        .put("nrp", new XMIResourceFactoryImpl());

    if (model.getProblem().getModelInitialiser() != null) {
      return new UserModelProvider(
          this.getModelInitialiser(),
          getResourceSet(),
          model.getProblem().getModel().getLocation());
    }

    return new UserModelProvider(getResourceSet(), model.getProblem().getModel().getLocation());
  }

  public IModelInitialiser getModelInitialiser() {

    if (model.getProblem().getModelInitialiser() != null) {

      return LanguageClassLoader.load(model.getProblem().getModelInitialiser());
    }
    return null;
  }

  public HenshinResourceSet getResourceSet() {
    if (henshinResourceSet == null) {
      henshinResourceSet =
          new HenshinResourceSet(
              projectRootPath
                  .append(model.getProblem().getBasepath().getLocation())
                  .toPortableString());
    }

    return henshinResourceSet;
  }

  public EPackage getMetamodel() {
    if (theMetamodel == null) {

      if (!model.getProblem().getMetamodel().getLocation().endsWith(".ecore")) {
        // The location is not an ecore file, assume it's a class name
        theMetamodel = LanguageClassLoader.load(model.getProblem().getMetamodel());
      } else {
        theMetamodel =
            getResourceSet()
                .registerDynamicEPackages(model.getProblem().getMetamodel().getLocation())
                .get(0);
      }
    }

    return theMetamodel;
  }

  public List<Unit> getBreedingOperators() {
    if (breedingOperators == null) {

      breedingOperators = new LinkedList<Unit>();

      breedingOperators.addAll(
          model.getSearch().getEvolvers().stream()
              .filter(operator -> operator.getEvolverType().getName().equals("BREED"))
              .map(
                  operator ->
                      getResourceSet()
                          .getModule(URI.createURI(operator.getRule_location()), false)
                          .getUnit(operator.getUnit()))
              .collect(Collectors.toList()));
    }

    return breedingOperators;
  }

  public List<Unit> getSpecifiedOperators(String type) {

    var evolvers =
        model.getSearch().getEvolvers().stream()
            .filter(o -> o.getEvolverType().getName().equalsIgnoreCase(type))
            .map(
                evolver ->
                    getResourceSet()
                        .getModule(URI.createURI(evolver.getRule_location()), false)
                        .getUnit(evolver.getUnit()))
            .iterator();

    return null;
  }

  public List<Unit> getMutationOperators() {

    // TODO: Doesn't make sense?
    if (mutationOperators == null) {

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
    }

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

            // Crete the spec for a node or an edge generation
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
}
