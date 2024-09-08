package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListHasNoDuplicates.Companion.hasNoDuplicate
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListIsNotSorted.Companion.isNotSorted
import org.assertj.core.api.AbstractAssert
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListContains.Companion.contains
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotContain.Companion.doesNotContain
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotContainOnly.Companion.doesNotContainOnly
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotHaveSingleElementEqual.Companion.doesNotHaveSingleElementEqual
import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [NonEmptyList].
 *
 * @param SELF the "self" type of this assertion class.
 * @param ELEMENT type of the element contained in the [NonEmptyList].
 * @param ELEMENT_ASSERT type used for navigational assertion of element contained in the [NonEmptyList].
 * @author Hamza Faraji
 *
 * @since 1.2.0
 */
abstract class AbstractNonEmptyListAssert<
        SELF : AbstractNonEmptyListAssert<SELF, ELEMENT, ELEMENT_ASSERT>,
        ELEMENT : Any?,
        ELEMENT_ASSERT : AbstractAssert<ELEMENT_ASSERT, ELEMENT>
        > internal constructor(
    list: NonEmptyList<ELEMENT?>?,
) : AbstractIterableAssert<SELF, NonEmptyList<ELEMENT?>, ELEMENT, ELEMENT_ASSERT>(list, AbstractNonEmptyListAssert::class.java) {
    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the actual [NonEmptyList] contains the expected element
     *
     * @return the assertion object
     */
    fun shouldContain(expectedValue: ELEMENT): SELF {
        isNotNull
        assertContains(expectedValue)
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] contains all the expected elements
     *
     * @return the assertion object
     */
    fun shouldContainAll(vararg elements: ELEMENT): SELF {
        isNotNull
        if (!actual.containsAll(elements.toList())) {
            throwAssertionError(doesNotContain(actual, elements))
        }
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] contains null
     *
     * @return the assertion object
     */
    fun shouldContainNull(): SELF {
        isNotNull
        assertContains(null)
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] does not contain null
     *
     * @return the assertion object
     */
    fun shouldContainNoNulls(): SELF {
        isNotNull
        if (actual.contains(null)) {
            throwAssertionError(contains(actual, null))
        }
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] contains only null
     *
     * @return the assertion object
     */
    fun shouldContainOnlyNulls(): SELF {
        isNotNull
        if (!actual.all { it == null }) {
            throwAssertionError(doesNotContainOnly(actual, null))
        }
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] contains at least one duplicate
     *
     * @return the assertion object
     */
    fun shouldHaveDuplicates(): SELF {
        isNotNull
        if (!actual.groupBy { it }.any { it.value.size > 1 }) {
            throwAssertionError(hasNoDuplicate(actual))
        }
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] has a single element which is expected element
     *
     * @return the assertion object
     */
    fun shouldBeSingleElement(expectedValue: ELEMENT): SELF {
        isNotNull
        if (actual.size != 1 || !comparisonStrategy.areEqual(actual.first(), expectedValue)) {
            throwAssertionError(doesNotHaveSingleElementEqual(actual, expectedValue))
        }
        return myself
    }

    /**
     * Verifies that the actual [NonEmptyList] is sorted
     *
     * @return the assertion object
     */
    fun shouldBeSorted(): SELF {
        isNotNull
        if (!actual.zipWithNext().all { comparisonStrategy.isLessThanOrEqualTo(it.first, it.second) }) {
            throwAssertionError(isNotSorted(actual))
        }

        return myself
    }

    private fun assertContains(expectedValue: ELEMENT?) {
        isNotNull
        if (!actual.contains(expectedValue)) {
            throwAssertionError(doesNotContain(actual, expectedValue))
        }
    }

}