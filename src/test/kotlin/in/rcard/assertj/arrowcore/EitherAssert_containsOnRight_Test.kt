package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContain.Companion.shouldContainOnRight
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class EitherAssert_containsOnRight_Test {
    @Test
    internal fun `should fail when either itself is null`() {
        val rightValue: Either<Nothing, String>? = null
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(rightValue).containsOnRight(
                "something",
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }

    @Test
    internal fun `should pass if either contains expected value on right side`() {
        val rightValue = "something".right()
        EitherAssert.assertThat(rightValue).containsOnRight("something")
    }

    @Test
    internal fun `should pass if either contains a null on right side`() {
        val rightValue = null.right()
        EitherAssert.assertThat(rightValue).containsOnRight(null)
    }

    @Test
    internal fun should_fail_if_either_is_left() {
        val actual: Either<String, String> = "nothing".left()
        val expectedValue = "something"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnRight(expectedValue)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actual).create())
    }

    @Test
    internal fun `should fail if either contains a left sided null`() {
        val actual: Either<String?, String> = null.left()
        val expectedValue = "something"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnRight(expectedValue)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actual).create())
    }

    @Test
    internal fun `should fail if either does not contain expected value on right side`() {
        val actual: Either<Nothing, String> = "something".right()
        val expectedValue = "nothing"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnRight(expectedValue)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContainOnRight(actual, expectedValue).create())
    }

    @Test
    internal fun `should fail with informative error message if either contains null on right but a value was expected`() {
        val actual: Either<Nothing, String?> = null.right()
        val expectedValue = "nothing"
        Assertions.assertThatThrownBy {
            EitherAssert.assertThat(actual).containsOnRight(expectedValue)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContainOnRight(actual, expectedValue).create())
    }
}
