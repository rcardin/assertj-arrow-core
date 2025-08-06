package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContainInstanceOf.Companion.shouldContainOnLeftInstanceOf
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContainInstanceOf.Companion.shouldContainOnRightInstanceOf
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [Either].
 *
 * @param SELF the "self" type of this assertion class.
 * @param LEFT type of the left value contained in the [Either].
 * @param RIGHT type of the right value contained in the [Either].
 * @author Riccardo Cardin
 * @author Simon Frost
 *
 * @since 0.0.1
 */
abstract class AbstractEitherAssert<
    SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>,
    LEFT : Any?,
    RIGHT : Any?,
> internal constructor(
    either: Either<LEFT, RIGHT>?,
) : AbstractObjectAssert<SELF, Either<LEFT, RIGHT>>(either, AbstractEitherAssert::class.java) {
    private val comparisonStrategy: ComparisonStrategy = StandardComparisonStrategy.instance()

    /**
     * Verifies that the actual [Either] is right.
     *
     * @return this assertion object.
     */
    fun isRight(): SELF {
        isNotNull
        assertIsRight()
        return myself
    }

    /**
     * Verifies that the actual [Either] is left.
     *
     * @return this assertion object.
     */
    fun isLeft(): SELF {
        assertIsLeft()
        return myself
    }

    /**
     * Verifies that the actual [Either] is [Either.Right] and contains the given value.
     *
     * @param expectedValue the expected value inside the [Either].
     * @return this assertion object.
     */
    fun containsOnRight(expectedValue: RIGHT): SELF {
        assertIsRight()
        actual.onRight { right ->
            if (!comparisonStrategy.areEqual(right, expectedValue)) {
                throwAssertionError(shouldContainOnRight(actual, expectedValue))
            }
        }
        return myself
    }

    /**
     * Verifies that the actual right-sided [Either] contains a value that is an
     * instance of the argument.
     *
     * @param expectedClass the expected class of the value inside the right-sided [Either].
     * @return this assertion object.
     */
    fun containsRightInstanceOf(expectedClass: Class<*>): SELF {
        assertIsRight()
        actual.onRight { right ->
            if (!expectedClass.isInstance(right)) {
                throwAssertionError(shouldContainOnRightInstanceOf(actual, expectedClass))
            }
        }
        return myself
    }

    /**
     * Verifies that the actual [Either] contains a right-sided value and gives this value to the given
     * consumer for further assertions. Should be used as a way of deeper asserting on the
     * containing object, as further requirement(s) for the value.
     *
     * @param requirement the consumer that will accept the right-sided value for deep asserting.
     * @return this assertion object.
     *
     * @since 0.1.0
     */
    @Deprecated(
        "hasRightValueSatisfying can be replaced using the method asRight() and chaining assertions on the returned object.",
        ReplaceWith("asRight().satisfies(requirement)"),
    )
    fun hasRightValueSatisfying(requirement: (RIGHT) -> Unit): SELF {
        assertIsRight()
        actual.onRight { requirement(it) }
        return myself
    }

    /**
     * Verifies that the actual [Either] is not null and contains a right-sided value and returns an Object assertion
     * that allows chaining (object) assertions on the value.
     *
     * @since 0.2.0
     * @return a new [AbstractObjectAssert] for assertions chaining on the right-sided value of the [Either].
     */
    fun asRight(): AbstractObjectAssert<*, RIGHT> {
        assertIsRight()
        return Assertions.assertThat(actual.getOrNull())
    }

    private fun assertIsRight() {
        isNotNull
        if (!actual.isRight()) {
            throwAssertionError(shouldBeRight(actual))
        }
    }

    /**
     * Verifies that the actual [Either] is [Either.Left] and contains the given value.
     *
     * @param expectedValue the expected value inside the [Either].
     * @return this assertion object.
     */
    fun containsOnLeft(expectedValue: LEFT): SELF {
        assertIsLeft()
        actual.onLeft { left ->
            if (!comparisonStrategy.areEqual(left, expectedValue)) {
                throwAssertionError(shouldContainOnLeft(actual, expectedValue))
            }
        }
        return myself
    }

    /**
     * Verifies that the actual left-sided [Either] contains a value that is an
     * instance of the argument.
     *
     * @param expectedClass the expected class of the value inside the left-sided [Either].
     * @return this assertion object.
     */
    fun containsLeftInstanceOf(expectedClass: Class<*>): SELF {
        assertIsLeft()
        actual.onLeft { left ->
            if (!expectedClass.isInstance(left)) {
                throwAssertionError(shouldContainOnLeftInstanceOf(actual, expectedClass))
            }
        }
        return myself
    }

    /**
     * Verifies that the actual [Either] contains a left-sided value and gives this value to the given
     * consumer for further assertions. Should be used as a way of deeper asserting on the
     * containing object, as further requirement(s) for the value.
     *
     * @param requirement the consumer that will accept the left-sided value for deep asserting.
     * @return this assertion object.
     * @since 0.1.0
     */
    @Deprecated(
        "hasLeftValueSatisfying can be replaced using the method asLeft() and chaining assertions on the returned object.",
        ReplaceWith("asLeft().satisfies(requirement)"),
    )
    fun hasLeftValueSatisfying(requirement: (LEFT) -> Unit): SELF {
        assertIsLeft()
        actual.onLeft { requirement(it) }
        return myself
    }

    /**
     * Verifies that the actual [Either] is not null and contains a left-sided value and returns an Object assertion
     * that allows chaining (object) assertions on the value.
     *
     * @since 0.2.0
     * @return a new [AbstractObjectAssert] for assertions chaining on the left-sided value of the [Either].
     */
    fun asLeft(): AbstractObjectAssert<*, LEFT> {
        assertIsLeft()
        return Assertions.assertThat(actual.leftOrNull())
    }

    private fun assertIsLeft() {
        isNotNull
        if (!actual.isLeft()) {
            throwAssertionError(shouldBeLeft(actual))
        }
    }
}
