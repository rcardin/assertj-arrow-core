package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailWithButSucceedsWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailWith.Companion.shouldFailWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWith.Companion.shouldSucceedWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWithButFailed.Companion.shouldSucceedWithButFailed
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
    SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>,
    ERROR : Any,
    VALUE : Any,
> internal constructor(
    lambda: Raise<ERROR>.() -> VALUE,
) : AbstractAssert<
        SELF,
        Raise<ERROR>.() -> VALUE,
    >(lambda, AbstractRaiseAssert::class.java) {
    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the function in the [Raise] context succeeds with the given value.
     * @param expectedValue the expected value returned by the function.
     */
    fun succeedsWith(expectedValue: VALUE) {
        fold(
            block = actual,
            recover = { actualError: ERROR ->
                throwAssertionError(
                    shouldSucceedWithButFailed(
                        expectedValue,
                        actualError,
                    ),
                )
            },
            transform = { actualValue ->
                if (!comparisonStrategy.areEqual(actualValue, expectedValue)) {
                    throwAssertionError(shouldSucceedWith(expectedValue, actualValue))
                }
            },
        )
    }

    /**
     * Verifies that the function in the [Raise] context succeeded. No check on the value returned by the function is
     * performed.
     *
     * @see succeedsWith
     */
    fun succeeds() {
        fold(
            block = actual,
            recover = { actualError: ERROR -> throwAssertionError(shouldSucceedButFailed(actualError)) },
            transform = { _ -> },
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
                    shouldFailWithButSucceedsWith(expectedError, actualValue),
                )
            },
        )
    }

    /**
     * Verifies that the function in the [Raise] context fails, no matter the type of the logical error.
     *
     * @see raises
     */
    fun fails() {
        fold(
            block = actual,
            recover = { _ -> },
            transform = { actualValue ->
                throwAssertionError(
                    shouldFailButSucceedsWith(actualValue),
                )
            },
        )
    }
}
