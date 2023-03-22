package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldContain.Companion.shouldContain
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class OptionAssert_contains_Test {

    @Test
    internal fun `should fail when option is null`() {
        Assertions.assertThatThrownBy {
            val nullOption: Option<String>? = null
            assertThat(nullOption).contains("something")
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }

    @Test
    internal fun `should pass if option contains expected value`() {
        assertThat("something".some()).contains("something")
    }

    @Test
    internal fun `should fail if option does not contain expected value`() {
        val actual: Option<String> = "not-expected".some()
        val expectedValue = "something"
        Assertions.assertThatThrownBy { assertThat(actual).contains(expectedValue) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContain(actual, expectedValue).create())
    }

    @Test
    internal fun `should fail if option is empty`() {
        val expectedValue = "something"
        val emptyOption: Option<String> = None
        Assertions.assertThatThrownBy {
            assertThat(emptyOption).contains(expectedValue)
        }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContain(emptyOption, expectedValue).create())
    }
}
