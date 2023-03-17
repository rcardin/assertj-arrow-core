package `in`.rcard.assertj.arrowcore

import arrow.core.Option

/**
 * Assertions for [Option].
 *
 * @param VALUE type of the value contained in the [Option].
 * @author Riccardo Cardin
 */
class OptionAssert<VALUE : Any>(option: Option<VALUE>?) :
    AbstractOptionAssert<OptionAssert<VALUE>, VALUE>(option) {
    companion object {
        fun <VALUE : Any> assertThat(option: Option<VALUE>?): OptionAssert<VALUE> =
            OptionAssert(option)
    }
}
