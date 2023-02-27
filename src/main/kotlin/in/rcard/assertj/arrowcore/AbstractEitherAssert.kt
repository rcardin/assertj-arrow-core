package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContainInstanceOf.Companion.shouldContainOnRightInstanceOf
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [Either].
 *
 * @param SELF the "self" type of this assertion class.
 * @param LEFT type of the left value contained in the [Either].
 * @param RIGHT type of the right value contained in the [Either].
 * @author Riccardo Cardin
 */
abstract class AbstractEitherAssert<
    SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>, LEFT : Any, RIGHT : Any,>(
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

    private fun assertIsLeft() {
        isNotNull
        if (!actual.isLeft()) {
            throwAssertionError(shouldBeLeft(actual))
        }
    }
}
