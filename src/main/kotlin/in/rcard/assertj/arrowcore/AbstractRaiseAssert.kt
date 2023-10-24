package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeded.Companion.shouldFailButSucceeded
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailWith.Companion.shouldFailWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWith.Companion.shouldSucceedWith
import org.assertj.core.api.AbstractAssert
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for functions within a [Raise] context.
 *
 * @param VALUE type of the value returned by the function.
 * @param ERROR type of the logical error raised by the function.
 * @author Riccardo Cardin
 * @since 0.2.0
 */
abstract class AbstractRaiseAssert<
        SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR : Any, VALUE : Any,
        >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java) {

    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the function in the [Raise] context succeeds with the given value.
     * @param expectedValue the expected value returned by the function.
     */
    fun succeedsWith(expectedValue: VALUE) {
        fold(
            block = actual,
            recover = { actualError: ERROR -> throwAssertionError(shouldSucceedButFailed(expectedValue, actualError)) },
            transform = { actualValue ->
                if (!comparisonStrategy.areEqual(actualValue, expectedValue)) {
                    throwAssertionError(shouldSucceedWith(expectedValue, actualValue))
                }
            },
        )
    }

    /**
     * Verifies that the function in the [Raise] context fails with the given error.
     * @param expectedError the expected error raised by the function.
     */
    fun raises(expectedError: ERROR) {
        fold(
            block = actual,
            recover = { actualError ->
                if (!comparisonStrategy.areEqual(actualError, expectedError)) {
                    throwAssertionError(shouldFailWith(expectedError, actualError))
                }
            },
            transform = { actualValue ->
                throwAssertionError(
                    shouldFailButSucceeded(expectedError, actualValue)
                )
            },
        )
    }
}
