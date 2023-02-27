package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import `in`.rcard.assertj.arrowcore.EitherAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import `in`.rcard.assertj.arrowcore.errors.EitherShouldContainInstanceOf.Companion.shouldContainOnRightInstanceOf
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

class EitherAssert_containsOnRightInstanceOf_Test {

    @Test
    fun `should fail if either is null`() {
        val actual: Either<Nothing, Nothing>? = null
        Assertions.assertThatThrownBy {
            assertThat(actual).containsRightInstanceOf(Any::class.java)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }

    @Test
    fun `should fail if either is left`() {
        val actual: Either<String, Nothing> = "some".left()
        Assertions.assertThatThrownBy {
            assertThat(actual).containsRightInstanceOf(Any::class.java)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actual).create())
    }

    @Test
    fun should_fail_if_either_contains_other_type_on_right_than_required() {
        val actual: Either<Any, Int> = 42.right()
        Assertions.assertThatThrownBy {
            assertThat(actual).containsRightInstanceOf(String::class.java)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldContainOnRightInstanceOf(
                    actual,
                    String::class.java,
                )
                    .create(),
            )
    }

    @Test
    fun `should pass if either contains required type subclass on right`() {
        val actual = Child().right()
        assertThat(actual).containsRightInstanceOf(Parent::class.java)
    }

    @Test
    fun `should pass if either contains required type on right`() {
        val actual: Either<Nothing, String> = "something".right()
        assertThat(actual).containsRightInstanceOf(String::class.java)
    }

    private open class Parent

    private class Child : Parent()
}
