package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application.RandomOperatorMutationStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.SkipOperatorRepair;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelection;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification.ISearchSpecification;

@ExtendWith(MockitoExtension.class)
public class RandomOperatorMutationStrategyTests {

  MutationStepSizeStrategy mutationStepSizeStrategy;
  RandomOperatorSelection operatorSelectionDecorator;

  HenshinExecutor henshinExecutor;
  Solution solution;
  private List<Unit> expectedAppliedOperators;

  @BeforeEach
  public void init(
      @Mock HenshinExecutor henshinExecutor,
      @Mock MutationStepSizeStrategy mutationStepSizeStrategy,
      @Mock RandomOperatorSelection operatorSelectionDecorator,
      @Mock Solution solution) {

    Unit operator_1 = mock(Unit.class);
    lenient().when(operator_1.getName()).thenReturn("test");

    Unit operator_2 = mock(Unit.class);
    lenient().when(operator_2.getName()).thenReturn("test2");

    Unit operator_3 = mock(Unit.class);
    lenient().when(operator_3.getName()).thenReturn("test3");

    Unit operator_4 = mock(Unit.class);
    lenient().when(operator_4.getName()).thenReturn("test4");

    Unit operator_5 = mock(Unit.class);
    lenient().when(operator_5.getName()).thenReturn("test5");

    lenient().when(mutationStepSizeStrategy.getNextStepSize(mock(Solution.class))).thenReturn(5);
    lenient()
        .when(operatorSelectionDecorator.getNextOperator(solution))
        .thenReturn(operator_1, operator_2, operator_3, operator_4, operator_5);

    lenient()
        .when(
            henshinExecutor.operatorApplied(
                any(Unit.class), any(EGraphImpl.class), any(Solution.class)))
        .thenReturn(true);

    this.operatorSelectionDecorator = operatorSelectionDecorator;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
    this.henshinExecutor = henshinExecutor;
    this.solution = solution;
    this.expectedAppliedOperators =
        List.of(operator_1, operator_2, operator_3, operator_4, operator_5);
  }

  @Test
  public void assertThatOperatorsAreExecutedRandomly() {

    EGraphImpl mockEGraph = mock(EGraphImpl.class);

    ISearchSpecification mockSearchSpecification = mock(ISearchSpecification.class);
    Optimisation optimisation = mock(Optimisation.class);
    SolverSpec solver = mock(SolverSpec.class);
    AlgorithmSpec algorithmSpec = mock(AlgorithmSpec.class);

    lenient().when(mockSearchSpecification.getOptimisationModel()).thenReturn(optimisation);
    lenient().when(optimisation.getSolver()).thenReturn(solver);
    lenient().when(solver.getAlgorithm()).thenReturn(algorithmSpec);

    SearchOperatorConfiguration searchOperatorConfiguration =
        new SearchOperatorConfiguration(this.henshinExecutor, mockSearchSpecification, null, null);

    var mutationStrategy =
        new RandomOperatorMutationStrategy(
            searchOperatorConfiguration,
            mutationStepSizeStrategy,
            operatorSelectionDecorator,
            new SkipOperatorRepair());

    var outcome = mutationStrategy.applyOperators(solution, mockEGraph);

    // Check step size
    assertEquals(5, outcome.size());

    // Check step transformations applied in expected order
    assertEquals(expectedAppliedOperators, outcome);

    // Step size was queried once
    verify(mutationStepSizeStrategy, times(1)).getNextStepSize(mock(Solution.class));

    // Operator to apply was queried once
    verify(operatorSelectionDecorator, times(5)).getNextOperator(solution);

    // Flushed tried operators after each step ending
    verify(operatorSelectionDecorator, times(5)).flushTriedOperators();
  }
}
