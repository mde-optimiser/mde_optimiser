package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.tests

import java.util.ArrayList
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.model.Unit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.RandomOperatorMutationStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.ArgumentMatchers.*
import static org.mockito.Mockito.*

@ExtendWith(MockitoExtension)
class RandomOperatorMutationStrategyTests {

	MutationStepSizeStrategy mutationStepSizeStrategy;
	RandomOperatorSelector operatorSelectionDecorator;

	HenshinExecutor henshinExecutor

	@BeforeEach
	def void init(
		@Mock HenshinExecutor henshinExecutor,
		@Mock MutationStepSizeStrategy mutationStepSizeStrategy,
		@Mock RandomOperatorSelector operatorSelectionDecorator
	) {


		var Unit operator_1 = mock(Unit);
		lenient().when(operator_1.name).thenReturn("test");

		var Unit operator_2 = mock(Unit);
		lenient().when(operator_2.name).thenReturn("test2");

		var Unit operator_3 = mock(Unit);
		lenient().when(operator_3.name).thenReturn("test3");

		var Unit operator_4 = mock(Unit);
		lenient().when(operator_4.name).thenReturn("test4");

		var Unit operator_5 = mock(Unit);
		lenient().when(operator_5.name).thenReturn("test5");


		lenient().when(mutationStepSizeStrategy.nextStepSize).thenReturn(5);
		lenient().when(operatorSelectionDecorator.nextOperator).thenReturn(operator_1, operator_2, operator_3, operator_4, operator_5);
		
		lenient().when(henshinExecutor.operatorApplied(any(Unit), any(EGraphImpl), any(Solution))).thenReturn(true);

		this.operatorSelectionDecorator = operatorSelectionDecorator;
		this.mutationStepSizeStrategy = mutationStepSizeStrategy;
		this.henshinExecutor = henshinExecutor;

	}

	@Test
	def void assertThatOperatorsAreExecutedRandomly() {

		var Solution solution = mock(Solution);
		var EGraphImpl mockEGraph = mock(EGraphImpl);

		var mutationStrategy = new RandomOperatorMutationStrategy(henshinExecutor, mutationStepSizeStrategy,
			operatorSelectionDecorator);

		val outcome = mutationStrategy.applyOperators(solution, mockEGraph);

		val expectedOperators = #["test", "test2", "test3", "test4", "test5"];
		val appliedOperators = outcome.entrySet.fold(new ArrayList<String>) [ seed, entry |
			seed.add(entry.value)
			return seed;
		]

		// Check step size
		assertEquals(5, outcome.size);

		// Check step transformations applied in expected order
		assertEquals(expectedOperators, appliedOperators);

		// Step size was queried once
		verify(mutationStepSizeStrategy, times(1)).nextStepSize

		// Operator to apply was queried once
		verify(operatorSelectionDecorator, times(5)).nextOperator
		
				// Flushed tried operators after each step ending
		verify(operatorSelectionDecorator, times(5)).flushTriedOperators
	}

}
