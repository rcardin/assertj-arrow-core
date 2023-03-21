package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBeEmpty.Companion.shouldBeEmpty
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class OptionAssert_isEmpty_Test {

    @Test
    internal fun `should pass if Option is empty`() {
        assertThat(None).isEmpty()
    }

    @Test
    internal fun `should fail when Option is null`() {
        val nullOption: Option<Nothing>? = null
        Assertions.assertThatThrownBy { assertThat(nullOption).isEmpty() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }

    @Test
    internal fun `should fail if Option is present`() {
        val actual: Option<String> = "not-empty".some()
        Assertions.assertThatThrownBy { assertThat(actual).isEmpty() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeEmpty(actual).create())
    }
}
