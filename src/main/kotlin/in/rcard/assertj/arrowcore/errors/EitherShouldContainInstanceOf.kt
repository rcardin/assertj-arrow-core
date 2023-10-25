package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Either
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Either] should contain a value being an instance of a specific
 * class.
 *
 * @author Riccardo Cardin
 * @since 0.0.1
 */
internal class EitherShouldContainInstanceOf private constructor(message: String) : BasicErrorMessageFactory(message) {
    companion object {
        private const val EXPECTING_TO_CONTAIN_DIFFERENT_INSTANCE =
            "%nExpecting:%n <%s>%nto contain a value that is an instance of:%n <%s>%nbut did contain an instance of:%n <%s>"

        private const val EXPECTING_TO_CONTAIN_BUT_IS_LEFT =
            "%nExpecting:%n  <%s>%nto contain on right side:%n  <%s>%nbut was left-sided."

        private const val EXPECTING_TO_CONTAIN_BUT_IS_RIGHT =
            "%nExpecting:%n  <%s>%nto contain on left side:%n  <%s>%nbut was right-sided."

        /**
         * Indicates that a value should be present in a right-sided [Either].
         *
         * @param actual Either to be checked.
         * @param expectedClass expected class of a right value
         * @return an error message factory.
         * @throws java.lang.NullPointerException if either is null.
         */
        internal fun shouldContainOnRightInstanceOf(
            actual: Either<Any, Any>,
            expectedClass: Class<*>,
        ): EitherShouldContainInstanceOf =
            actual.fold(
                {
                    EitherShouldContainInstanceOf(
                        EXPECTING_TO_CONTAIN_BUT_IS_LEFT.format(
                            actual.javaClass.simpleName,
                            expectedClass.name,
                        ),
                    )
                },
                { rightValue ->
                    EitherShouldContainInstanceOf(
                        EXPECTING_TO_CONTAIN_DIFFERENT_INSTANCE.format(
                            actual.javaClass.simpleName,
                            expectedClass.name,
                            rightValue.javaClass.name,
                        ),
                    )
                },
            )

        internal fun shouldContainOnLeftInstanceOf(
            actual: Either<Any, Any>,
            expectedClass: Class<*>,
        ): EitherShouldContainInstanceOf =
            actual.fold(
                { leftValue ->
                    EitherShouldContainInstanceOf(
                        EXPECTING_TO_CONTAIN_DIFFERENT_INSTANCE.format(
                            actual.javaClass.simpleName,
                            expectedClass.name,
                            leftValue.javaClass.name,
                        ),
                    )
                },
                {
                    EitherShouldContainInstanceOf(
                        EXPECTING_TO_CONTAIN_BUT_IS_RIGHT.format(
                            actual.javaClass.simpleName,
                            expectedClass.name,
                        ),
                    )
                },
            )
    }
}
