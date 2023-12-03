package uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.emf.ecore.EPackage;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MetaModelSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ModelInitialiserSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ProblemPartSpecifier;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ReportInterpreterSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelInitialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IProblemPartSpecifier;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.EvolverParameterAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;

public class LanguageClassLoader {

  /**
   * Load an evolver parameter class instance from a specified file in the mopt file.
   *
   * @param functionAdapter object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static IEvolverParametersFunction load(EvolverParameterAdapter functionAdapter) {
    return (IEvolverParametersFunction) load(functionAdapter.function);
  }

  /**
   * Load a fitness function (constraint or objective) class instance from a specified file in the
   * mopt file.
   *
   * @param functionAdapter object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static IGuidanceFunction load(GuidanceFunctionAdapter functionAdapter) {
    return (IGuidanceFunction) load(functionAdapter.functionSpec);
  }

  /**
   * Load a fitness function (constraint or objective) class instance from a specified file in the
   * mopt file.
   *
   * @param functionSpec object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static IGuidanceFunction load(ReportInterpreterSpec functionSpec) {
    return (IGuidanceFunction) load(functionSpec.getReportSpec());
  }

  /**
   * Load a custom model initialiser class instance from a specified file in the mopt file.
   *
   * @param functionSpec object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static EPackage load(MetaModelSpec functionSpec) {

    try {

      var packageInterface = (Class<EPackage>) loadClass(functionSpec.getLocation());

      return (EPackage) packageInterface.getDeclaredField("eINSTANCE").get(null);

    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
      throw new RuntimeException(
          "Could not load metamodel from given instance class: " + functionSpec.getLocation());
    }
  }

  /**
   * Load a custom model initialiser class instance from a specified file in the mopt file.
   *
   * @param functionSpec object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static IModelInitialiser load(ModelInitialiserSpec functionSpec) {
    return (IModelInitialiser) load(functionSpec.getInitialiser());
  }

  /**
   * Load a class instance specifying the problem part of the model from a specified file in the
   * mopt file.
   *
   * @param functionSpec object containing the interpreted mopt specification for this class.
   * @return an instance of the loaded class
   */
  public static IProblemPartSpecifier load(ProblemPartSpecifier functionSpec) {
    return (IProblemPartSpecifier) load(functionSpec.getProblemPartSpecifier());
  }

  private static Class<?> loadClass(String name) {

    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException(String.format("Invalid class path: %s", name));
    }
  }

  private static Object load(String name) {
    try {
      return loadClass(name).getDeclaredConstructor().newInstance();

    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException(
          String.format("Illegal access exception encountered when accessing class: %s", name));
    } catch (InstantiationException e) {
      e.printStackTrace();
      throw new RuntimeException(
          String.format("Instantiation exception when trying to load class: %s", name));
    } catch (NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();

      System.out.println(
          String.format(
              String.join(
                  System.getProperty("line.separator"),
                  "Could not load class at: %s.",
                  "This is a fatal error. Execution has stopped."),
              name));
      // TODO Need a better stopping mechanism here. Perhaps these checks should be made in the
      // language.
      // The problem is that the user may specify libs on the classpath which are outside of the
      // scope of the mopt file.
      throw new RuntimeException(String.format("Could not instantiate class: %s", name));
    }
  }
}
