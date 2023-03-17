package `in`.rcard.assertj.arrowcore

import arrow.core.Option

class OptionAssert<VALUE : Any>(option: Option<VALUE>?) :
    AbstractOptionAssert<OptionAssert<VALUE>, VALUE>(option) {
    companion object {
        fun <VALUE : Any> assertThat(option: Option<VALUE>?): OptionAssert<VALUE> =
            OptionAssert(option)
    }
}
