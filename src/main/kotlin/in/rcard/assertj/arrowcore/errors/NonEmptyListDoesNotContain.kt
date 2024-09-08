package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] does not contain a specific value.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
internal class NonEmptyListDoesNotContain private constructor(message: String, actual: NonEmptyList<Any?>, expected: Any?) :
    BasicErrorMessageFactory(message, actual, expected) {
    companion object {
        private const val EXPECTING_TO_CONTAIN =
            "%nExpecting:%n  <%s>%nto contain:%n  <%s>%nbut did not."

        internal fun <ELEMENT : Any> doesNotContain(
            actual: NonEmptyList<ELEMENT?>,
            vararg expected: ELEMENT?
        ): NonEmptyListDoesNotContain = NonEmptyListDoesNotContain(
            EXPECTING_TO_CONTAIN,
            actual,
            expected
        )
    }
}