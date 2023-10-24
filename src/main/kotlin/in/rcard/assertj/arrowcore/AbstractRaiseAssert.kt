package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWith
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

abstract class AbstractRaiseAssert<
        SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR : Any, VALUE : Any,
        >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java) {

    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    fun succeedsWith(expected: VALUE) {
        fold(
            block = actual,
            recover = { error: ERROR -> throwAssertionError(shouldSucceedButFailed(expected, error)) },
            transform = { actual ->
                if (!comparisonStrategy.areEqual(actual, expected)) {
                    throwAssertionError(RaiseShouldSucceedWith.shouldSucceedWith(expected, actual))
                }
            },
        )
    }

    fun raises(expected: ERROR) {
        fold(
            block = actual,
            recover = { Assertions.assertThat(it).isEqualTo(expected) },
            transform = { failWithMessage("Expected lambda to raise a logical error but it succeeded with value '$it'") },
            catch = { ex: Throwable ->
                when (ex) {
                    is AssertionError -> throw ex
                    else -> failWithMessage("Expected lambda to raise a logical error but it throws the exception '$ex'")
                }
            },
        )
    }
}
