package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Either
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Either] should contain a specific value.
 *
 * @author Riccardo Cardin
 */
internal class EitherShouldContain(message: String, actual: Either<Any, Any>, expected: Any) :
    BasicErrorMessageFactory(message, actual, expected) {

    companion object {

        private const val EXPECTING_TO_CONTAIN_ON_RIGHT =
            "%nExpecting:%n  <%s>%nto contain:%n  <%s> on the [RIGHT]%nbut did not."
        private const val EXPECTING_TO_CONTAIN_ON_LEFT =
            "%nExpecting:%n  <%s>%nto contain:%n  <%s> on the [LEFT]%nbut did not."

        /**
         * Indicates that the provided [Either] does not contain the provided argument as right value.
         *
         * @param actual        the [Either] which contains a value.
         * @param expectedValue the value we expect to be in the provided [Either] on the right side.
         * @param LEFT          the type of the value contained in the [Either] on the left side.
         * @param RIGHT         the type of the value contained in the [Either] on the right side.
         * @return an error message factory
         */
        internal fun <LEFT : Any, RIGHT : Any> shouldContainOnRight(
            actual: Either<LEFT, RIGHT>,
            expectedValue: RIGHT,
        ): EitherShouldContain = EitherShouldContain(
            EXPECTING_TO_CONTAIN_ON_RIGHT,
            actual,
            expectedValue,
        )

        /**
         * Indicates that the provided [Either] does not contain the provided argument as right value.
         *
         * @param actual        the [Either] which contains a value.
         * @param expectedValue the value we expect to be in the provided [Either] on the left side.
         * @param LEFT        the type of the value contained in the [Either] on the left side.
         * @param RIGHT       the type of the value contained in the [Either] on the right side.
         * @return an error message factory
         */
        internal fun <LEFT : Any, RIGHT : Any> shouldContainOnLeft(
            actual: Either<LEFT, RIGHT>,
            expectedValue: RIGHT,
        ): EitherShouldContain = EitherShouldContain(
            EXPECTING_TO_CONTAIN_ON_LEFT,
            actual,
            expectedValue,
        )
    }
}
