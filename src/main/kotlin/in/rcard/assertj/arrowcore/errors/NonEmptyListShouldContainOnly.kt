package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] does not contain only a specific value.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
class NonEmptyListShouldContainOnly private constructor(message: String, actual: NonEmptyList<Any?>, expected: Any?) :
    BasicErrorMessageFactory(message, actual, expected) {

    companion object {
        private const val EXPECTING_TO_CONTAIN_ONLY =
            "%nExpecting:%n  <%s>%nto contain only:%n  <%s>%nbut did not."

        internal fun <ELEMENT : Any> shouldContainOnly(
            actual: NonEmptyList<ELEMENT?>,
            expected: ELEMENT?
        ): NonEmptyListShouldContainOnly = NonEmptyListShouldContainOnly(
            EXPECTING_TO_CONTAIN_ONLY,
            actual,
            expected
        )
    }
}