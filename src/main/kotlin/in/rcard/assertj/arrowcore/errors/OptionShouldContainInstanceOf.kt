package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Option
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build an error message when a value should be instance of a specific class.
 *
 * @author Riccardo Cardin
 */
internal class OptionShouldContainInstanceOf(message: String) : BasicErrorMessageFactory(message) {

    companion object {
        /**
         * Indicates that a value of a specific class should be present in an empty [Option].
         *
         * @param option Option to be checked.
         * @param clazz expected class of a value
         * @return an error message factory.
         * @throws java.lang.NullPointerException if option is null.
         */
        internal fun <VALUE : Any> shouldContainInstanceOf(
            option: Option<VALUE>,
            clazz: Class<*>,
        ): OptionShouldContainInstanceOf = option.fold(
            {
                OptionShouldContainInstanceOf(
                    "%nExpecting:%n <%s>%nto contain a value that is an instance of:%n <%s>%nbut was empty".format(
                        option,
                        clazz.name,
                    ),
                )
            },
            { value ->
                OptionShouldContainInstanceOf(
                    "%nExpecting:%n <%s>%nto contain a value that is an instance of:%n <%s>%nbut did contain an instance of:%n <%s>".format(
                        option,
                        clazz.name,
                        value.javaClass.name,
                    ),
                )
            },
        )
    }
}
