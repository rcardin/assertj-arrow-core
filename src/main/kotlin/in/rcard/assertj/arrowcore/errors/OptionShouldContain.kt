package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Option
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Option] should contain a specific value.
 *
 * @author Riccardo Cardin
 * @since 0.0.1
 */
internal class OptionShouldContain private constructor(message: String, vararg objs: Any) :
    BasicErrorMessageFactory(message, *objs) {

    companion object {
        private const val EXPECTING_TO_CONTAIN =
            "%nExpecting:%n  <%s>%nto contain:%n  <%s>%nbut did not."
        private const val EXPECTING_TO_CONTAIN_BUT_WAS_EMPTY =
            "%nExpecting Option to contain:%n  <%s>%nbut was empty."

        /**
         * Indicates that the provided [Option] does not contain the provided argument.
         *
         * @param VALUE      the type of the value contained in the [Option].
         * @param option        the [Option] which contains a value.
         * @param expectedValue the value we expect to be in the provided [Option].
         * @return an error message factory
         */
        internal fun <VALUE : Any> shouldContain(
            option: Option<VALUE>,
            expectedValue: VALUE,
        ): OptionShouldContain =
            option.fold(
                { OptionShouldContain(EXPECTING_TO_CONTAIN_BUT_WAS_EMPTY, expectedValue) },
                { _ ->
                    OptionShouldContain(
                        EXPECTING_TO_CONTAIN,
                        option,
                        expectedValue,
                    )
                },
            )
    }
}
