package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceed.Companion.shouldSucceed
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions

abstract class AbstractRaiseAssert<
    SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR : Any, VALUE : Any,
    >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java) {

    fun succeedsWith(value: VALUE) {
        fold(
            block = actual,
            recover = { error: ERROR -> throwAssertionError(shouldSucceed(value, error)) },
            transform = { Assertions.assertThat(it).isEqualTo(value) },
            catch = { ex: Throwable ->
                when (ex) {
                    is AssertionError -> throw ex
                    else -> failWithMessage("Expected lambda to succeed but it throws the exception '$ex'")
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
