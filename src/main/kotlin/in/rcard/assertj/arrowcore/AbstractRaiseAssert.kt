package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailWithButSucceedsWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailWith.Companion.shouldFailWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWith.Companion.shouldSucceedWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWithButFailed.Companion.shouldSucceedWithButFailed
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
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
    raiseResult: RaiseResult<ERROR, VALUE>,
) : AbstractAssert<
        SELF,
        RaiseResult<ERROR, VALUE>,
    >(raiseResult, AbstractRaiseAssert::class.java) {
    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the function in the [Raise] context succeeds with the given value.
     * @param expectedValue the expected value returned by the function.
     */
    fun succeedsWith(expectedValue: VALUE) =
        when (actual) {
            is RaiseResult.Failure<ERROR> -> {
                throwAssertionError(
                    shouldSucceedWithButFailed(expectedValue, (actual as RaiseResult.Failure<ERROR>).error),
                )
            }

            is RaiseResult.FailureWithException -> {
                throw (actual as RaiseResult.FailureWithException).exception
            }

            is RaiseResult.Success<VALUE> -> {
                val actualValue = (actual as RaiseResult.Success<VALUE>).value
                if (!comparisonStrategy.areEqual(actualValue, expectedValue)) {
                    throwAssertionError(shouldSucceedWith(expectedValue, actualValue))
                } else {
                    // Nothing to do
                }
            }
        }

    /**
     * Verifies that the function in the [Raise] context succeeded. No check on the value returned by the function is
     * performed.
     *
     * @see succeedsWith
     */
    fun succeeds() =
        when (actual) {
            is RaiseResult.Failure<ERROR> ->
                throwAssertionError(
                    shouldSucceedButFailed((actual as RaiseResult.Failure<ERROR>).error),
                )

            is RaiseResult.FailureWithException -> {
                throw (actual as RaiseResult.FailureWithException).exception
            }

            is RaiseResult.Success<VALUE> -> {
                // Nothing to do
            }
        }

    /**
     * Verifies that the function in the [Raise] context fails with the given error.
     * @param expectedError the expected error raised by the function.
     */
    fun raises(expectedError: ERROR) =
        when (actual) {
            is RaiseResult.Failure<ERROR> -> {
                val actualError = (actual as RaiseResult.Failure<ERROR>).error
                if (!comparisonStrategy.areEqual(actualError, expectedError)) {
                    throwAssertionError(shouldFailWith(expectedError, actualError))
                } else {
                    // Nothing to do
                }
            }

            is RaiseResult.FailureWithException -> {
                throw (actual as RaiseResult.FailureWithException).exception
            }

            is RaiseResult.Success<VALUE> -> {
                throwAssertionError(
                    shouldFailWithButSucceedsWith(expectedError, (actual as RaiseResult.Success<VALUE>).value),
                )
            }
        }

    /**
     * Verifies that the function in the [Raise] context fails, no matter the type of the logical error.
     *
     * @see raises
     */
    fun fails() =
        when (actual) {
            is RaiseResult.Failure<ERROR> -> {
                // Nothing to do
            }

            is RaiseResult.FailureWithException -> {
                throw (actual as RaiseResult.FailureWithException).exception
            }

            is RaiseResult.Success ->
                throwAssertionError(
                    shouldFailButSucceedsWith((actual as RaiseResult.Success<VALUE>).value),
                )
        }

    fun result(): AbstractObjectAssert<*, VALUE> {
        succeeds()
        return Assertions.assertThat((actual as RaiseResult.Success<VALUE>).value)
    }
}

sealed interface RaiseResult<out ERROR : Any, out VALUE : Any> {
    data class Success<out VALUE : Any>(
        val value: VALUE,
    ) : RaiseResult<Nothing, VALUE>

    data class Failure<out ERROR : Any>(
        val error: ERROR,
    ) : RaiseResult<ERROR, Nothing>

    data class FailureWithException(
        val exception: Throwable,
    ) : RaiseResult<Nothing, Nothing>
}
