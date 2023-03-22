package `in`.rcard.assertj.arrowcore

import arrow.core.Option
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBeEmpty.Companion.shouldBeEmpty
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import `in`.rcard.assertj.arrowcore.errors.OptionShouldContain.Companion.shouldContain
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [Option].
 *
 * @param SELF  the "self" type of this assertion class.
 * @param VALUE type of the value contained in the [Option].
 * @author Riccardo Cardin
 */
abstract class AbstractOptionAssert<
    SELF : AbstractOptionAssert<SELF, VALUE>, VALUE : Any,
    >(
    option: Option<VALUE>?,
) : AbstractObjectAssert<SELF, Option<VALUE>>(option, AbstractOptionAssert::class.java) {

    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that there is a value present in the actual [Option].
     *
     * @return this assertion object.
     */
    fun isDefined(): SELF {
        isNotNull
        if (actual.isEmpty()) throwAssertionError(shouldBePresent())
        return myself
    }

    /**
     * Verifies that the actual [Option] is empty.
     *
     * @return this assertion object.
     */
    fun isEmpty(): SELF {
        isNotNull
        if (actual.isDefined()) throwAssertionError(shouldBeEmpty(actual))
        return myself
    }

    /**
     * Verifies that the actual [Option] contains the given value.
     *
     * @param expectedValue the expected value inside the [Option].
     * @return this assertion object.
     */
    fun contains(expectedValue: VALUE): SELF {
        isNotNull
        actual.fold(
            { throwAssertionError(shouldContain(actual, expectedValue)) },
            { actualValue ->
                if (!comparisonStrategy.areEqual(actualValue, expectedValue)) {
                    throwAssertionError(shouldContain(actual, expectedValue))
                }
            },
        )
        return myself
    }
}
