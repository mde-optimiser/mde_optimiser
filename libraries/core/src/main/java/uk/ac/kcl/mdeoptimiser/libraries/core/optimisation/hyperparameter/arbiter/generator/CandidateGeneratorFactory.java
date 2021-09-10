package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.generator;

import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator;
import org.deeplearning4j.arbiter.optimize.api.ParameterSpace;
import org.deeplearning4j.arbiter.optimize.generator.RandomSearchGenerator;
import org.deeplearning4j.arbiter.optimize.parameter.integer.IntegerParameterSpace;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterSearchSpec;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOParameterSpace;

public class CandidateGeneratorFactory {

  static CandidateGeneratorFactory factoryInstance;

  private CandidateGeneratorFactory() {}

  public static synchronized CandidateGeneratorFactory getINSTANCE() {

    if (factoryInstance == null) {
      factoryInstance = new CandidateGeneratorFactory();
    }

    return factoryInstance;
  }

  public CandidateGenerator get(ParameterSearchSpec parameterSearchSpec) {

    var parameterSpace = getParameterSpace(parameterSearchSpec);
    var parameterSearchAlgorithm = getParameterSearchAlgorithmName(parameterSearchSpec);

    switch (parameterSearchAlgorithm) {
      case "random":
        {
          return new RandomSearchGenerator(parameterSpace, null);
        }
        // TODO Would be interesting to add Grid and SMAC
      default:
        throw new IllegalArgumentException(
            String.format(
                "Unexpected parameter search candidate generator algorithm name: %s",
                parameterSearchAlgorithm));
    }
  }

  /**
   * Get the configured algorithm algorithm name to use for generating parameter values.
   *
   * @return algorithm name
   */
  public String getParameterSearchAlgorithmName(ParameterSearchSpec spec) {
    var algorithmParameter = getParameter(spec, "strategy");
    // These are just strategy names: random (should add grid, smac etc)
    return algorithmParameter.getValue().getFunctional();
  }

  public MDEOParameterSpace getParameterSpace(ParameterSearchSpec parameterSearchSpec) {
    var parameterSpace = new MDEOParameterSpace();

    parameterSpace.setEvolutions(getParameterSpace(parameterSearchSpec, "evolutions"));
    parameterSpace.setPopulationSize(getParameterSpace(parameterSearchSpec, "population"));

    return parameterSpace;
  }

  public ParameterSpace<Integer> getParameterSpace(ParameterSearchSpec spec, String key) {

    var populationParameter = getParameter(spec, key);

    var populationStart = getParameterValue(populationParameter.getValue().getParams().get(0));
    var populationEnd = getParameterValue(populationParameter.getValue().getParams().get(1));

    return new IntegerParameterSpace(populationStart, populationEnd);
  }

  // TODO This is repeated several times in other parts. Need to add a parameter interpreter class.
  public Integer getParameterValue(XExpression expression) {

    var numberLiteral = (XNumberLiteral) expression;
    return Integer.parseInt(numberLiteral.getValue());
  }

  public Parameter getParameter(ParameterSearchSpec parameterSearchSpec, String keyName) {

    var parameter =
        parameterSearchSpec.getParameters().stream()
            .filter(p -> p.getName().equals(keyName))
            .findFirst();

    if (parameter.isPresent()) {
      return parameter.get();
    }

    throw new RuntimeException(String.format("Unable to find parameter %s. ", keyName));
  }
}
