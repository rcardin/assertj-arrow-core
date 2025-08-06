package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.EitherAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class EitherAssert_asLeft_Test {

    @Test
    internal fun `should return a valid Object assert if the either contains a left-sided value`() {
        val actualLeftValue: Either<Int, Nothing> = 42.left()
        assertThat(actualLeftValue).asLeft().isEqualTo(42)
    }

    @Test
    internal fun `should return a valid Object assert if the either contains a left-sided null`() {
        val actualLeftValue: Either<Int?, Nothing> = null.left()
        assertThat(actualLeftValue).asLeft().isEqualTo(null)
    }

    @Test
    internal fun `should fail if the either contains a right-sided value`() {
        val actualRightValue: Either<Nothing, String> = "42".right()
        Assertions.assertThatThrownBy { assertThat(actualRightValue).asLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(actualRightValue).create())
    }

    @Test
    internal fun `should fail if the either contains a right-sided null`() {
        val actualRightValue: Either<Nothing, String?> = null.right()
        Assertions.assertThatThrownBy { assertThat(actualRightValue).asLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(actualRightValue).create())
    }

    @Test
    internal fun `should fail if the either itself is null`() {
        val actualLeftValue: Either<Int, Nothing>? = null
        Assertions.assertThatThrownBy { assertThat(actualLeftValue).asLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }
}