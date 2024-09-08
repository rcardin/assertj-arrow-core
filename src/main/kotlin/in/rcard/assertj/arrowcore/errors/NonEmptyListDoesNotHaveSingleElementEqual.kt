package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] does not have a single element equal a specific value.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
class NonEmptyListDoesNotHaveSingleElementEqual private constructor(message: String, actual: NonEmptyList<Any?>, expected: Any?) :
    BasicErrorMessageFactory(message, actual, expected) {
    companion object {
        private const val EXPECTING_TO_HAVE_SINGLE_ELEMENT_EQUAL =
            "%nExpecting:%n  <%s>%nto have single element:%n  <%s>%nbut did not."

        internal fun <ELEMENT : Any> doesNotHaveSingleElementEqual(
            actual: NonEmptyList<ELEMENT?>,
            expected: ELEMENT?
        ): NonEmptyListDoesNotHaveSingleElementEqual = NonEmptyListDoesNotHaveSingleElementEqual(
            EXPECTING_TO_HAVE_SINGLE_ELEMENT_EQUAL,
            actual,
            expected
        )
    }
}