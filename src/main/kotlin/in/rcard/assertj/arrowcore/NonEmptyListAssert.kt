package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import org.assertj.core.api.AssertFactory
import org.assertj.core.api.ObjectAssert

/**
 * Assertions for [NonEmptyList].
 *
 * @param ELEMENT type of the element contained in the [NonEmptyList].
 * @author Hamza Faraji
 *
 * @since 1.2.0
 */
class NonEmptyListAssert<ELEMENT : Any?> private constructor(nel: NonEmptyList<ELEMENT>?) :
    AbstractNonEmptyListAssert<NonEmptyListAssert<ELEMENT>, ELEMENT, ObjectAssert<ELEMENT>>(
        nel,
        AssertFactory { actual: ELEMENT -> ObjectAssert(actual) }
    ) {
        companion object {
            fun <VALUE : Any?> assertThat(list: NonEmptyList<VALUE>?): NonEmptyListAssert<VALUE> =
                NonEmptyListAssert(list)
        }
}
