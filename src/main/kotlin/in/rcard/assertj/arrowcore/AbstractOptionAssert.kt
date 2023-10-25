package `in`.rcard.assertj.arrowcore

import arrow.core.Option
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBeEmpty.Companion.shouldBeEmpty
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import `in`.rcard.assertj.arrowcore.errors.OptionShouldContain.Companion.shouldContain
import `in`.rcard.assertj.arrowcore.errors.OptionShouldContainInstanceOf.Companion.shouldContainInstanceOf
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [Option].
 *
 * @param SELF  the "self" type of this assertion class.
 * @param VALUE type of the value contained in the [Option].
 * @author Riccardo Cardin
 * @author Simon Frost
 * @since 0.0.1
 */
abstract class AbstractOptionAssert<
        SELF : AbstractOptionAssert<SELF, VALUE>, VALUE : Any,
        > internal constructor(
    option: Option<VALUE>?,
) : AbstractObjectAssert<SELF, Option<VALUE>>(option, AbstractOptionAssert::class.java) {

    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the actual [Option] is empty.
     *
     * @return this assertion object.
     */
    fun isEmpty(): SELF {
        isNotNull
        if (actual.isSome()) throwAssertionError(shouldBeEmpty(actual))
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

    /**
     * Verifies that the actual [Option] contains a value that is an instance of the argument.
     *
     * @param expectedClass the expected class of the value inside the [Option].
     * @return this assertion object.
     */
    fun containsInstanceOf(expectedClass: Class<*>): SELF {
        assertValueIsPresent()
        actual.onSome { value ->
            if (!expectedClass.isInstance(value)) {
                throwAssertionError(
                    shouldContainInstanceOf(
                        actual,
                        expectedClass,
                    ),
                )
            }
        }
        return myself
    }

    /**
     * Verifies that the actual [Option] contains a value and gives this value to the given
     * consumer for further assertions. Should be used as a way of deeper asserting on the
     * containing object, as further requirement(s) for the value.
     *
     * @param requirement to further assert on the object contained inside the [Option].
     * @return this assertion object.
     * @since 0.1.0
     */
    @Deprecated(
        "hasValueSatisfying can be replaced using the method get() and chaining assertions on the returned object.",
        ReplaceWith("get().satisfies(requirement)"),
    )
    fun hasValueSatisfying(requirement: (VALUE) -> Unit): SELF {
        assertValueIsPresent()
        actual.onSome { requirement(it) }
        return myself
    }

    /**
     * Verifies that the actual [Option] is not null and not empty and returns an Object assertion that allows
     * chaining (object) assertions on the optional value.
     *
     * @since 0.2.0
     * @return a new [AbstractObjectAssert] for assertions chaining on the value of the [Option].
     */
    fun get(): AbstractObjectAssert<*, VALUE> {
        isDefined()
        return Assertions.assertThat(actual.getOrNull())
    }

    /**
     * Verifies that there is a value present in the actual [Option].
     *
     * @return this assertion object.
     */
    fun isDefined(): SELF {
        assertValueIsPresent()
        return myself
    }

    private fun assertValueIsPresent() {
        isNotNull
        if (actual.isNone()) throwAssertionError(shouldBePresent())
    }
}
