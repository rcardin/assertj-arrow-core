@file:OptIn(ExperimentalTypeInference::class)

package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import arrow.core.raise.fold
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldThrowAnException.Companion.shouldThrowAnException
import org.assertj.core.api.AbstractThrowableAssert
import org.assertj.core.api.Assertions
import org.assertj.core.internal.Failures
import kotlin.experimental.ExperimentalTypeInference

class RaiseAssert<ERROR : Any, VALUE : Any> private constructor(lambda: Raise<ERROR>.() -> VALUE) :
    AbstractRaiseAssert<RaiseAssert<ERROR, VALUE>, ERROR, VALUE>(lambda) {
    companion object {
        fun <ERROR : Any, VALUE : Any> assertThat(@BuilderInference lambda: Raise<ERROR>.() -> VALUE): RaiseAssert<ERROR, VALUE> =
            RaiseAssert(lambda)

        fun <ERROR : Any, VALUE : Any> assertThatThrownBy(@BuilderInference shouldRaiseThrowable: Raise<ERROR>.() -> VALUE): AbstractThrowableAssert<*, out Throwable> {
            val throwable: Throwable? = fold(block = shouldRaiseThrowable,
                recover = { null },
                transform = { null },
                catch = { exception -> exception })

            return throwable?.let { return Assertions.assertThat(throwable) } ?: throw Failures.instance()
                .failure(Assertions.assertThat(throwable).writableAssertionInfo, shouldThrowAnException())
        }
    }
}
