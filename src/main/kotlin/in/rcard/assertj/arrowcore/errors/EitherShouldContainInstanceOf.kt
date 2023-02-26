package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Either
import org.assertj.core.error.BasicErrorMessageFactory

class EitherShouldContainInstanceOf(message: String) : BasicErrorMessageFactory(message) {
  companion object {
    private const val EXPECTING_TO_CONTAIN_DIFFERENT_INSTANCE =
        "%nExpecting:%n <%s>%nto contain a value that is an instance of:%n <%s>%nbut did contain an instance of:%n <%s>"

    /**
     * Indicates that a value should be present in a right-sided [Either].
     *
     * @param actual Either to be checked.
     * @param expectedClass expected class of a right value
     * @return an error message factory.
     * @throws java.lang.NullPointerException if either is null.
     */
    fun shouldContainOnRightInstanceOf(
        actual: Either<*, *>,
        expectedClass: Class<*>
    ): EitherShouldContainInstanceOf? {
        actual.onRight { rightValue ->
            return EitherShouldContainInstanceOf(
                java.lang.String.format(
                    EXPECTING_TO_CONTAIN_DIFFERENT_INSTANCE,
                    actual.javaClass.simpleName,
                    expectedClass.name,
                    rightValue.javaClass.name
                ))
        }.onLeft {
            TODO()
        }
    }
  }
}
