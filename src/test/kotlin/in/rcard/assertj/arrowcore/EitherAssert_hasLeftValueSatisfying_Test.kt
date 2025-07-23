package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.right
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test


internal class EitherAssert_hasLeftValueSatisfying_Test {

    @Test
    internal fun `should fail when either is null`() {
        val actual: Either<Int, String>? = null
        Assertions.assertThatThrownBy { EitherAssert.assertThat(actual).hasLeftValueSatisfying { } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }

    @Test
    internal fun `should fail if either is right`() {
        val actual: Either<Int, String> = Either.Right("something")
        Assertions.assertThatThrownBy { EitherAssert.assertThat(actual).hasLeftValueSatisfying { } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(EitherShouldBeLeft.shouldBeLeft(actual).create())
    }

    @Test
    internal fun `should fail if either is right and null`() {
        val actual: Either<Int, String?> = null.right()
        Assertions.assertThatThrownBy { EitherAssert.assertThat(actual).hasLeftValueSatisfying { } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(EitherShouldBeLeft.shouldBeLeft(actual).create())
    }

    @Test
    internal fun `should fail if consumer fails`() {
        val actual: Either<Int, String> = Either.Left(42)
        Assertions.assertThatThrownBy { EitherAssert.assertThat(actual).hasLeftValueSatisfying { assertThat(it).isEqualTo(24) } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(("${System.lineSeparator()}expected: 24${System.lineSeparator()} but was: 42"))
    }

    @Test
    internal fun `should pass if consumer passes`() {
        val actual: Either<Int, String> = Either.Left(42)
        EitherAssert.assertThat(actual).hasLeftValueSatisfying { assertThat(it).isEqualTo(42) }
    }
}