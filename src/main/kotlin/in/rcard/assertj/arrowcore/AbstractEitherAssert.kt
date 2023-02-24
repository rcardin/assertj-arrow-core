package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnRight
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.internal.ComparisonStrategy
import org.assertj.core.internal.StandardComparisonStrategy

/**
 * Assertions for [Either].
 *
 * @param SELF  the "self" type of this assertion class.
 * @param LEFT  type of the left value contained in the [Either].
 * @param RIGHT type of the right value contained in the [Either].
 * @author Riccardo Cardin
 */
abstract class AbstractEitherAssert<SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>, LEFT : Any, RIGHT : Any>(either: Either<LEFT, RIGHT>?) :
    AbstractObjectAssert<SELF, Either<LEFT, RIGHT>>(either, AbstractEitherAssert::class.java) {

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

    private fun assertIsLeft() {
        isNotNull
        if (!actual.isLeft()) {
            throwAssertionError(shouldBeLeft(actual))
        }
    }

    fun containsOnRight(expectedValue: RIGHT): SELF {
        assertIsRight()
        actual.onRight { right ->
            if (!comparisonStrategy.areEqual(right, expectedValue)) throwAssertionError(
                shouldContainOnRight(actual, expectedValue)
            )
        }
        return myself
    }

    private fun assertIsRight() {
        isNotNull
        if (!actual.isRight()) {
            throwAssertionError(shouldBeRight(actual))
        }
    }
}
