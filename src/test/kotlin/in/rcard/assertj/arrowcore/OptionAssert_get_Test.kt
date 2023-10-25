package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages
import org.junit.jupiter.api.Test

internal class OptionAssert_get_Test {

    @Test
    internal fun `should return a valid Object assert if the Option is not empty`() {
        val some: Option<String> = "42".some()
        assertThat(some).get().isEqualTo("42")
    }

    @Test
    internal fun `should fail if the Option is empty`() {
        val none: Option<String> = None
        Assertions.assertThatThrownBy { assertThat(none).get() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBePresent().create())
    }

    @Test
    internal fun `should fail if the Option is null`() {
        val nullOption: Option<String>? = null
        Assertions.assertThatThrownBy { assertThat(nullOption).get() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(FailureMessages.actualIsNull())
    }
}