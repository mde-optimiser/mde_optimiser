package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.functions;

import com.mifmif.common.regex.Generex;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.lang3.math.NumberUtils;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class RandomEvolverParameter implements IEvolverParametersFunction {

  Generex randomParameterGenerator;

  public RandomEvolverParameter(String parameter) {

    if (checkRegexParameter(parameter)) {
      randomParameterGenerator = new Generex(parameter);
    }
  }

  public String sample(List<Solution> model) {

    var randomParameter = randomParameterGenerator.random();

    if (NumberUtils.isCreatable(randomParameter)) {
      return NumberUtils.createNumber(randomParameter).toString();
    }

    return randomParameter;
  }

  public boolean checkRegexParameter(String parameter) {

    try {
      Pattern.compile(parameter);
      return true;
    } catch (PatternSyntaxException exception) {
      throw new RuntimeException("Invalid Random function regex parameter: " + parameter);
    }
  }
}
