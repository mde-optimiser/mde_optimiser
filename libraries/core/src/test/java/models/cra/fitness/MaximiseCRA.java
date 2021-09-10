package models.cra.fitness;

import java.util.ArrayList;
import java.util.List;
import models.cra.fitness.architectureCRA.Attribute;
import models.cra.fitness.architectureCRA.Class;
import models.cra.fitness.architectureCRA.ClassModel;
import models.cra.fitness.architectureCRA.Feature;
import models.cra.fitness.architectureCRA.Method;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

/**
 * Java implementation taken from:
 * https://github.com/martin-fleck/momot/blob/master/examples/at.ac.tuwien.big.momot.examples.cra/src/icmt/tool/momot/demo/FitnessCalculator.java
 */
public class MaximiseCRA implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {
    var classModel = (ClassModel) solution.getModel();

    return -1 * (calculateCohesion(classModel) - calculateCoupling(classModel));
  }

  static int mmi(final Class classSource, final Class classTarget) {
    int mmi = 0;
    for (final Method methodSource : getMethodsClass(classSource)) {
      for (final Method methodTarget : getMethodsClass(classTarget)) {
        if (methodSource.getFunctionalDependency().contains(methodTarget)) {
          mmi++;
        }
      }
    }
    return mmi;
  }

  static int mai(final Class classSource, final Class classTarget) {
    int mai = 0;
    for (final Method method : getMethodsClass(classSource)) {
      for (final Attribute attribute : getAttributesClass(classTarget)) {
        if (method.getDataDependency().contains(attribute)) {
          mai++;
        }
      }
    }
    return mai;
  }

  static double calculateCohesion(final ClassModel model) {
    double cohesionRatio = 0.0;
    for (final Class clazz : model.getClasses()) {
      if (getMethodsClass(clazz).size() == 0) {
        cohesionRatio += 0.0;
      } else if (getMethodsClass(clazz).size()
          == 1) { // Here, the second part of the addition is still 0
        if (getAttributesClass(clazz).size() == 0) { // and now, also the first part is 0
          cohesionRatio += 0.0;
        } else { // now, the first part is not 0
          cohesionRatio +=
              mai(clazz, clazz)
                  / (double) (getMethodsClass(clazz).size() * getAttributesClass(clazz).size());
        }
      } else { // Here, we have more than one method in the clazz
        if (getAttributesClass(clazz).size()
            == 0) { // Now, the first part of the addition will be 0
          cohesionRatio +=
              mmi(clazz, clazz)
                  / (double) (getMethodsClass(clazz).size() * (getMethodsClass(clazz).size() - 1));
        } else { // Here, we have more than 0 attributes and more than 1 method, so we use the whole
          // formula
          cohesionRatio +=
              mai(clazz, clazz)
                      / (double) (getMethodsClass(clazz).size() * getAttributesClass(clazz).size())
                  + mmi(clazz, clazz)
                      / (double)
                          (getMethodsClass(clazz).size() * (getMethodsClass(clazz).size() - 1));
        }
      }
    }
    return cohesionRatio;
  }

  static double calculateCoupling(final ClassModel model) {
    double couplingRatio = 0;
    for (final Class clazz : model.getClasses()) {
      couplingRatio += calculateCoupling(clazz, model);
    }
    return couplingRatio;
  }

  static double calculateCoupling(final Class classSource, final ClassModel model) {
    double couplingRatio = 0;
    for (final Class classTarget : model.getClasses()) {
      if (classSource != classTarget) {
        if (getMethodsClass(classSource).size() == 0) {
          couplingRatio += 0.0;
        } else { // From here, |M(clsi)|>0
          if (getMethodsClass(classTarget).size() <= 1) { // The second part of the addition is 0
            if (getAttributesClass(classTarget).size()
                == 0) { // Now, also the first part of the addition is 0
              couplingRatio += 0.0;
            } else { // Now, the first part of the addition is not 0
              couplingRatio +=
                  mai(classSource, classTarget)
                      / (double)
                          (getMethodsClass(classSource).size()
                              * getAttributesClass(classTarget).size());
            }
          } else { // Now, the second part of the addition is not 0
            if (getAttributesClass(classTarget).size() == 0) {
              couplingRatio +=
                  mmi(classSource, classTarget)
                      / (double)
                          (getMethodsClass(classSource).size()
                              * (getMethodsClass(classTarget).size() - 1));
            } else { // Now, non of the parts is 0
              couplingRatio +=
                  mai(classSource, classTarget)
                          / (double)
                              (getMethodsClass(classSource).size()
                                  * getAttributesClass(classTarget).size())
                      + mmi(classSource, classTarget)
                          / (double)
                              (getMethodsClass(classSource).size()
                                  * (getMethodsClass(classTarget).size() - 1));
            }
          }
        }
      }
    }
    return couplingRatio;
  }

  static List<Attribute> getAttributesClass(final Class clazz) {
    final List<Attribute> attributes = new ArrayList<>();
    for (final Feature feature : clazz.getEncapsulates()) {
      if (feature instanceof Attribute) {
        attributes.add((Attribute) feature);
      }
    }
    return attributes;
  }

  static List<Method> getMethodsClass(final Class clazz) {
    final List<Method> methods = new ArrayList<>();
    for (final Feature feature : clazz.getEncapsulates()) {
      if (feature instanceof Method) {
        methods.add((Method) feature);
      }
    }
    return methods;
  }

  @Override
  public String getName() {
    return "Maximise CRA";
  }
}
