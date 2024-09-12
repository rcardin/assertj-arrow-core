package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] contains a specific value.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
class NonEmptyListShouldNotContain(message: String, actual: NonEmptyList<Any?>, expected: Any?) :
    BasicErrorMessageFactory(message, actual, expected)  {
    companion object {
        private const val EXPECTING_NOT_TO_CONTAIN =
            "%nExpecting:%n  <%s>%nnot to contain any:%n  <%s>%nbut it did."

        internal fun <ELEMENT : Any?> shouldNotContain(
            actual: NonEmptyList<ELEMENT>,
            expected: ELEMENT
        ): NonEmptyListShouldNotContain = NonEmptyListShouldNotContain(
            EXPECTING_NOT_TO_CONTAIN,
            actual,
            expected
        )
    }
}