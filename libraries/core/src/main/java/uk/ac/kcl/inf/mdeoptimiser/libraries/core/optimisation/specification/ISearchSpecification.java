package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelInitialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public interface ISearchSpecification {

  Optimisation getOptimisationModel();

  List<Unit> getBreedingOperators();

  List<Unit> getMutationOperators();

  List<Multiplicity> getMultiplicityRefinements();

  List<RuleSpec> getRulegenSpecs();

  Map<EPackage, List<Module>> getRulegenOperators();

  HenshinResourceSet getResourceSet();

  EPackage getMetamodel();

  IModelProvider getModelProvider();

  IModelInitialiser getModelInitialiser();

  List<IGuidanceFunction> getObjectiveFunctions();

  List<IGuidanceFunction> getConstraintFunctions();
}
