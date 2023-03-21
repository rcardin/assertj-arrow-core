package `in`.rcard.assertj.arrowcore

import arrow.core.Option
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBeEmpty.Companion.shouldBeEmpty
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import org.assertj.core.api.AbstractObjectAssert

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
}
