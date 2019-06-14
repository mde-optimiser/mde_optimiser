package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.RandomOperatorMutationStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector;

@ExtendWith(MockitoExtension.class)
public class RandomOperatorMutationStrategyTests {

  MutationStepSizeStrategy mutationStepSizeStrategy;
  RandomOperatorSelector operatorSelectionDecorator;

  HenshinExecutor henshinExecutor;

  @BeforeEach
  public void init(
      @Mock HenshinExecutor henshinExecutor,
      @Mock MutationStepSizeStrategy mutationStepSizeStrategy,
      @Mock RandomOperatorSelector operatorSelectionDecorator) {

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

    lenient().when(mutationStepSizeStrategy.getNextStepSize()).thenReturn(5);
    lenient()
        .when(operatorSelectionDecorator.getNextOperator())
        .thenReturn(operator_1, operator_2, operator_3, operator_4, operator_5);

    lenient()
        .when(
            henshinExecutor.operatorApplied(
                any(Unit.class), any(EGraphImpl.class), any(Solution.class)))
        .thenReturn(true);

    this.operatorSelectionDecorator = operatorSelectionDecorator;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
    this.henshinExecutor = henshinExecutor;
  }

  @Test
  public void assertThatOperatorsAreExecutedRandomly() {

    Solution solution = mock(Solution.class);
    EGraphImpl mockEGraph = mock(EGraphImpl.class);

    var mutationStrategy =
        new RandomOperatorMutationStrategy(
            henshinExecutor, mutationStepSizeStrategy, operatorSelectionDecorator);

    var outcome = mutationStrategy.applyOperators(solution, mockEGraph);

    var expectedOperators = List.of("test", "test2", "test3", "test4", "test5");
    var appliedOperators =
        outcome.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());

    // Check step size
    assertEquals(5, outcome.size());

    // Check step transformations applied in expected order
    assertEquals(expectedOperators, appliedOperators);

    // Step size was queried once
    verify(mutationStepSizeStrategy, times(1)).getNextStepSize();

    // Operator to apply was queried once
    verify(operatorSelectionDecorator, times(5)).getNextOperator();

    // Flushed tried operators after each step ending
    verify(operatorSelectionDecorator, times(5)).flushTriedOperators();
  }
}
