package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.EitherAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeLeft.Companion.shouldBeLeft
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContainInstanceOf.Companion.shouldContainOnLeftInstanceOf
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class EitherAssert_containsOnLeftInstanceOf_Test {
    @Test
    internal fun `should fail if either is null`() {
        val actual: Either<Any, Any>? = null
        Assertions.assertThatThrownBy {
            assertThat(actual).containsLeftInstanceOf(
                Any::class.java,
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }

    @Test
    internal fun `should fail if either is right`() {
        val actual: Either<Any, String> = "some".right()
        Assertions.assertThatThrownBy {
            assertThat(actual).containsLeftInstanceOf(
                Any::class.java,
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeLeft(actual).create())
    }

    @Test
    internal fun `should pass if either contains required type on left`() {
        val actual: Either<String, Nothing> = "something".left()
        assertThat(actual)
            .containsLeftInstanceOf(String::class.java)
    }

    @Test
    internal fun `should pass if either contains required type subclass on left`() {
        val actual = Child().left()
        assertThat(actual).containsLeftInstanceOf(Parent::class.java)
    }

    @Test
    internal fun `should fail if either contains other type on left than required`() {
        val actual: Either<String, Nothing> = "something".left()
        Assertions.assertThatThrownBy {
            assertThat(actual).containsLeftInstanceOf(Int::class.java)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldContainOnLeftInstanceOf(
                    actual,
                    Int::class.java,
                ).create(),
            )
    }

    private open class Parent

    private class Child : Parent()
}
