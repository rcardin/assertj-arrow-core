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

abstract class AbstractRaiseAssert<
        SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR : Any, VALUE : Any,
        >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java) {

    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

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
