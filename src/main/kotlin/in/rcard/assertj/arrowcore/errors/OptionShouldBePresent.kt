package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Option
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a value should be present in an [Option].
 *
 * @author Riccardo Cardin
 * @since 0.0.1
 */
internal class OptionShouldBePresent private constructor() :
    BasicErrorMessageFactory("%nExpecting Option to contain a value but it didn't.".format()) {
    companion object {

        /**
         * Indicates that a value should be present in an empty [Option].
         *
         * @return a error message factory.
         */
        internal fun shouldBePresent(): OptionShouldBePresent = OptionShouldBePresent()
    }
}
