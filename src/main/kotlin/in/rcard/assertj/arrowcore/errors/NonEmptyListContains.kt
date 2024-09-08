package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] contains a specific value.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
class NonEmptyListContains(message: String, actual: NonEmptyList<Any?>, expected: Any?) :
    BasicErrorMessageFactory(message, actual, expected)  {
    companion object {
        private const val EXPECTING_NOT_TO_CONTAIN =
            "%nExpecting:%n  <%s>%nto not contain any:%n  <%s>%nbut it did."

        internal fun <ELEMENT : Any?> contains(
            actual: NonEmptyList<ELEMENT>,
            expected: ELEMENT
        ): NonEmptyListContains = NonEmptyListContains(
            EXPECTING_NOT_TO_CONTAIN,
            actual,
            expected
        )
    }
}