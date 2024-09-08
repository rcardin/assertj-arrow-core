package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.toNonEmptyListOrNull
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
    AbstractNonEmptyListAssert<NonEmptyListAssert<ELEMENT>, ELEMENT, ObjectAssert<ELEMENT>>(nel) {
        companion object {
            fun <VALUE : Any?> assertThat(list: NonEmptyList<VALUE>?): NonEmptyListAssert<VALUE> =
                NonEmptyListAssert(list)
        }

    override fun toAssert(value: ELEMENT, description: String?): ObjectAssert<ELEMENT> =
        ObjectAssert(value).`as`(description)

    override fun newAbstractIterableAssert(iterable: MutableIterable<ELEMENT>?): NonEmptyListAssert<ELEMENT> =
        NonEmptyListAssert(iterable?.toNonEmptyListOrNull())
}
