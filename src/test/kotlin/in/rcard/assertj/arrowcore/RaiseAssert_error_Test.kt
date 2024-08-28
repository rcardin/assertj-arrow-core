package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_error_Test {
    @Test
    internal fun `should return a valid Object Assert if the Raise raises an error`() {
        assertThat { aFunctionThatRaisesAnError() }.error().isEqualTo("LOGICAL ERROR")
    }

    @Test
    internal fun `should fails if the Raise returns a value`() {
        Assertions
            .assertThatThrownBy { assertThat { aFunctionWithContext(42) }.error() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldFailButSucceedsWith(42).create())
    }

    @Test
    internal fun `should rethrow the inner exception`() {
        Assertions
            .assertThatThrownBy { assertThat { aFunctionThatThrowsAnException() }.error() }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
