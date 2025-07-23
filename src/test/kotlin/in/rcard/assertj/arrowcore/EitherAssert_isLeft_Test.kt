package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class EitherAssert_isLeft_Test {

    @Test
    internal fun `should fail if either is null`() {
        // GIVEN
        val leftValue: Either<Nothing, Nothing>? = null
        // WHEN/THEN
        Assertions.assertThatThrownBy { EitherAssert.assertThat(leftValue).isLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }

    @Test
    internal fun `should pass if either is left`() {
        // GIVEN
        val leftValue = "Error".left()
        // WHEN/THEN
        EitherAssert.assertThat(leftValue).isLeft()
    }

    @Test
    internal fun `should pass if either is left-sided null`() {
        // GIVEN
        val leftValue = null.left()
        // WHEN/THEN
        EitherAssert.assertThat(leftValue).isLeft()
    }

    @Test
    internal fun `should fail if either is right`() {
        // GIVEN
        val rightValue = 42.right()
        // WHEN/THEN
        Assertions.assertThatThrownBy { EitherAssert.assertThat(rightValue).isLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(rightValue).create())
    }

    @Test
    internal fun `should fail if either is right and null`() {
        // GIVEN
        val rightValue = null.right()
        // WHEN/THEN
        Assertions.assertThatThrownBy { EitherAssert.assertThat(rightValue).isLeft() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(rightValue).create())
    }
}
