package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit;

import java.util.*;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.model.NamedElement;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public abstract class AbstractOperatorCreditAssignment implements OperatorCreditStrategy {

  protected SearchOperatorConfiguration searchOperatorConfiguration;
  protected List<Double> compoundOperatorsCredits = new ArrayList<>();
  private int currentAlgorithmStep = 0;

  public AbstractOperatorCreditAssignment(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  /**
   * Distribute credit from a compound operator to all the underlying operators.
   *
   * @param credit
   * @param compoundOperator
   * @param operatorCredits
   */
  public void calculateIndividualOperatorCredits(
      Double credit, LinkedList<Unit> compoundOperator, Map<Unit, Double> operatorCredits) {

    this.compoundOperatorsCredits.add(credit);
    // Credit per individual operator
    var unitCredit = credit / compoundOperator.size();

    for (var atomicOperator : compoundOperator) {
      var atomicOperatorCredit = unitCredit;

      if (operatorCredits.containsKey(atomicOperator)) {
        atomicOperatorCredit += operatorCredits.get(atomicOperator);
      }
      operatorCredits.put(atomicOperator, atomicOperatorCredit);
    }
  }

  /**
   * Get the latest compound operator from the transformation chain.
   *
   * @param solution
   * @return list containing the operators applied to the solution
   */
  protected LinkedList<Unit> getCompoundOperator(MoeaOptimisationSolution solution) {

    // Get the transformation chain used to evolve the parent
    return solution.getModel().getTransformationsChain().getLast();
  }

  /**
   * Normalise the calculated operator credits between [0,1] using the number of credit rewarded
   * operators
   *
   * @param operatorCredits
   */
  protected void normaliseOperatorCredits(HashMap<Unit, Double> operatorCredits) {

    var creditSum = this.compoundOperatorsCredits.stream().reduce(0d, Double::sum);
    for (var key : operatorCredits.keySet()) {
      var credit = operatorCredits.get(key);
      operatorCredits.put(key, credit / creditSum);
    }
  }

  /**
   * Get a unique key for a compound operator by combining the names of the underlying units. This
   * allows the operators to have the same key for different lists containing the same operators in
   * the same order.
   *
   * @param left
   * @return compoound operator unique key obtained by combining the names of the units inside the
   *     operator.
   */
  public String compoundOperatorKey(List<Unit> left) {
    return left.stream().map(NamedElement::getName).collect(Collectors.joining());
  }
}
