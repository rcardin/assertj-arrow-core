package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class OptionAssert_isDefined_Test {
    @Test
    internal fun `should pass when Option is present`() {
        assertThat("present".some()).isDefined()
    }

    @Test
    internal fun `should fail when Option is empty`() {
        Assertions.assertThatThrownBy { assertThat(None).isDefined() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBePresent().create())
    }

    @Test
    internal fun `should fail when Option is null`() {
        val nullOption: Option<Nothing>? = null
        Assertions.assertThatThrownBy { assertThat(nullOption).isDefined() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
