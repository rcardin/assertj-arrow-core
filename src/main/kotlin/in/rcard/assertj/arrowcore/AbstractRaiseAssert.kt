package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.Assertions

abstract class AbstractRaiseAssert<
    SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR, VALUE : Any,
    >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java) {

    fun succeedsWith(value: VALUE): SELF {
        fold(
            block = actual,
            recover = { error: ERROR -> failWithMessage("Expected lambda to succeed but it failed with $error") },
            transform = { Assertions.assertThat(it).isEqualTo(value) },
            catch = { ex: Throwable ->
                when (ex) {
                    is AssertionError -> throw ex
                    else -> failWithMessage("Expected lambda to succeed but it throws the exception $ex")
                }
            },
        )
        return myself
    }
}
