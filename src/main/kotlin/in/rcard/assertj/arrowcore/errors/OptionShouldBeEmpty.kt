package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Option
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Option] should be empty.
 *
 * @author Riccardo Cardin
 */
internal class OptionShouldBeEmpty(expected: Option<*>) :
    BasicErrorMessageFactory("%nExpecting an Option to be empty but was <%s>.", expected.orNull()) {
    companion object {
        internal fun shouldBeEmpty(actual: Option<*>): OptionShouldBeEmpty =
            OptionShouldBeEmpty(actual)
    }
}
