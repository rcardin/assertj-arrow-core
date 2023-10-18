@file:OptIn(ExperimentalTypeInference::class)

package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import kotlin.experimental.ExperimentalTypeInference

class RaiseAssert<ERROR, VALUE : Any>(lambda: Raise<ERROR>.() -> VALUE) :
    AbstractRaiseAssert<RaiseAssert<ERROR, VALUE>, ERROR, VALUE>(lambda) {
    companion object {
        fun <ERROR, VALUE : Any> assertThat(@BuilderInference lambda: Raise<ERROR>.() -> VALUE): RaiseAssert<ERROR, VALUE> =
            RaiseAssert(lambda)
    }
}