package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnLeft
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.*
import org.junit.jupiter.api.Test

class EitherAssert_containsOnLeft_Test {
    @Test
    fun `should fail when either is null`() {
        val leftValue: Either<String, Nothing>? = null
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(leftValue).containsOnLeft(
                "something"
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }

    @Test
    fun `should pass if either contains expected value on left side`() {
        val leftValue = "something".left()
        EitherAssert.assertThat(leftValue).containsOnLeft("something")
    }

    @Test
    fun `should fail if either does not contain expected value on left side`() {
        val actual: Either<String, Nothing> = "something".left()
        val expectedValue = "nothing"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnLeft(
                expectedValue
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContainOnLeft(actual, expectedValue).create())
    }

    @Test
    fun `should fail if either is right`() {
        val actual: Either<String, String> = "nothing".right()
        val expectedValue = "something"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnLeft(
                expectedValue
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(actual).create())
    }
}
