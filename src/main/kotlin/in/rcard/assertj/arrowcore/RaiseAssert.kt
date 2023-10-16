package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise

class RaiseAssert<ERROR, VALUE : Any>(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractRaiseAssert<RaiseAssert<ERROR, VALUE>, ERROR, VALUE>(lambda) {
    companion object {
        fun <ERROR, VALUE : Any> assertThat(lambda: context(Raise<ERROR>) () -> VALUE): RaiseAssert<ERROR, VALUE> =
            RaiseAssert(lambda)
    }
}
