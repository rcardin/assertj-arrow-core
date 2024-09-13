package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldNotContain.Companion.shouldNotContain
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldContain.Companion.shouldContain
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldContainOnly.Companion.shouldContainOnly
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldBeSingleElement.Companion.shouldBeSingleElement
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldHaveDuplicates.Companion.shouldHaveDuplicates
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldBeSorted.Companion.shouldBeSorted
import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AssertFactory
import org.assertj.core.api.FactoryBasedNavigableListAssert
import org.assertj.core.internal.StandardComparisonStrategy


/**
 * Assertions for [NonEmptyList].
 *
 * @param SELF the "self" type of this assertion class.
 * @param ELEMENT type of the element contained in the [NonEmptyList].
 * @param ELEMENT_ASSERT type used for assertion of element contained in the [NonEmptyList].
 * @author Hamza Faraji
 *
 * @since 1.2.0
 */
open class AbstractNonEmptyListAssert<
        SELF : AbstractNonEmptyListAssert<SELF, ELEMENT, ELEMENT_ASSERT>,
        ELEMENT : Any?,
        ELEMENT_ASSERT : AbstractAssert<ELEMENT_ASSERT, ELEMENT>
        > internal constructor(
    list: NonEmptyList<ELEMENT?>?,
    assertFactory: AssertFactory<ELEMENT, ELEMENT_ASSERT>?,
) : FactoryBasedNavigableListAssert<SELF, NonEmptyList<ELEMENT?>, ELEMENT, ELEMENT_ASSERT>(
    list,
    AbstractNonEmptyListAssert::class.java,
    assertFactory
) {
    private val comparisonStrategy: StandardComparisonStrategy = StandardComparisonStrategy.instance()

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
            throwAssertionError(shouldContain(actual, elements))
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
            throwAssertionError(shouldNotContain(actual, null))
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
        if (!actual.none { it != null }) {
            throwAssertionError(shouldContainOnly(actual, null))
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
        if (actual.distinct().size == actual.size) {
            throwAssertionError(shouldHaveDuplicates(actual))
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
            throwAssertionError(shouldBeSingleElement(actual, expectedValue))
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
        if (actual.sortedWith { first, second ->
            when {
                comparisonStrategy.areEqual(first, second) -> 0
                comparisonStrategy.isLessThanOrEqualTo(first, second) -> -1
                else -> 1
            }
        } != actual) {
            throwAssertionError(shouldBeSorted(actual))
        }

        return myself
    }

    private fun assertContains(expectedValue: ELEMENT?) {
        isNotNull
        if (!actual.contains(expectedValue)) {
            throwAssertionError(shouldContain(actual, expectedValue))
        }
    }
}