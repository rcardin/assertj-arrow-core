package `in`.rcard.assertj.arrowcore

import arrow.core.Option

/**
 * Assertions for [Option].
 *
 * @param VALUE type of the value contained in the [Option].
 * @author Riccardo Cardin
 * @since 0.0.1
 */
class OptionAssert<VALUE : Any> private constructor(option: Option<VALUE>?) :
    AbstractOptionAssert<OptionAssert<VALUE>, VALUE>(option) {
    companion object {
        fun <VALUE : Any> assertThat(option: Option<VALUE>?): OptionAssert<VALUE> =
            OptionAssert(option)
    }
}
