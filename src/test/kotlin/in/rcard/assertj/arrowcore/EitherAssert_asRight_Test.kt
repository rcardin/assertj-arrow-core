package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.EitherAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class EitherAssert_asRight_Test {

    @Test
    internal fun `should return a valid Object assert if the either contains a right-sided value`() {
        val actualRightValue: Either<Nothing, Int> = 42.right()
        assertThat(actualRightValue).asRight().isEqualTo(42)
    }

    @Test
    internal fun `should return a valid Object assert if either contains a right-sided null`() {
        val actualRightValue: Either<Nothing, Int?> = null.right()
        assertThat(actualRightValue).asRight().isEqualTo(null)
    }

    @Test
    internal fun `should fail if the either contains a left-sided value`() {
        val actualLeftValue: Either<String, Nothing> = "42".left()
        Assertions.assertThatThrownBy { assertThat(actualLeftValue).asRight() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actualLeftValue).create())
    }

    @Test
    internal fun `should fail if either contains a left-sided null`() {
        val actualLeftValue: Either<String?, Nothing> = null.left()
        Assertions.assertThatThrownBy { assertThat(actualLeftValue).asRight() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actualLeftValue).create())
    }

    @Test
    internal fun `should fail if the either itself is null`() {
        val actualRightValue: Either<Nothing, Int>? = null
        Assertions.assertThatThrownBy { assertThat(actualRightValue).asRight() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }
}