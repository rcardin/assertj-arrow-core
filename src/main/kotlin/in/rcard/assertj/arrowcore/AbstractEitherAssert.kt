package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import org.assertj.core.api.AbstractObjectAssert

/**
 * Assertions for [Either].
 *
 * @param <SELF>  the "self" type of this assertion class.
 * @param <LEFT>  type of the left value contained in the [Either].
 * @param <RIGHT> type of the right value contained in the [Either].
 * @author Riccardo Cardin
 */
abstract class AbstractEitherAssert<SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>, LEFT, RIGHT>(either: Either<LEFT, RIGHT>?) :
    AbstractObjectAssert<SELF, Either<LEFT, RIGHT>>(either, AbstractEitherAssert::class.java) {

    /**
     * Verifies that the actual [Either] is right.
     *
     * @return this assertion object.
     */
    fun isRight(): SELF {
        isNotNull
        if (!actual.isRight()) {
            throwAssertionError(shouldBeRight(actual))
        }
        return myself
    }

    /**
     * Verifies that the actual [Either] is left.
     *
     * @return this assertion object.
     */
    fun isLeft(): SELF {
        isNotNull
        if (!actual.isLeft()) {
            throwAssertionError(shouldBeLeft(actual))
        }
        return myself
    }
}
