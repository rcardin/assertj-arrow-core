package `in`.rcard.assertj.arrowcore.errors

import arrow.core.NonEmptyList
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when a [NonEmptyList] has no duplicates.
 *
 * @author Hamza Faraji
 * @since 1.2.0
 */
class NonEmptyListHasNoDuplicates private constructor(message: String, actual: NonEmptyList<Any?>) :
    BasicErrorMessageFactory(message, actual) {
    companion object {
        private const val EXPECTING_TO_CONTAIN_DUPLICATES =
            "%nExpecting:%n  <%s>%nto contain duplicates but did not."

        internal fun <ELEMENT : Any> hasNoDuplicate(
            actual: NonEmptyList<ELEMENT?>
        ): NonEmptyListHasNoDuplicates = NonEmptyListHasNoDuplicates(
            EXPECTING_TO_CONTAIN_DUPLICATES,
            actual
        )
    }
}