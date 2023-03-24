package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import `in`.rcard.assertj.arrowcore.errors.OptionShouldContainInstanceOf.Companion.shouldContainInstanceOf
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class OptionAssert_containsInstanceOf_Test {

    @Test
    internal fun `should fail if option is empty`() {
        val actual: Option<Any> = None
        Assertions.assertThatThrownBy {
            assertThat(actual).containsInstanceOf(
                Any::class.java,
            )
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBePresent().create())
    }

    @Test
    internal fun `should pass if option contains required type`() {
        val optionValue = "something".some()
        assertThat(optionValue).containsInstanceOf(String::class.java)
    }

    @Test
    internal fun `should pass if option contains required type subclass`() {
        val optionValue = Child().some()
        assertThat(optionValue).containsInstanceOf(
            Parent::class.java,
        )
    }

    @Test
    internal fun `should fail if option contains other type than required`() {
        val actual = 42.some()
        Assertions.assertThatThrownBy {
            assertThat(actual).containsInstanceOf(String::class.java)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldContainInstanceOf(
                    actual,
                    String::class.java,
                ).create(),
            )
    }

    private open class Parent

    private class Child : Parent()
}
