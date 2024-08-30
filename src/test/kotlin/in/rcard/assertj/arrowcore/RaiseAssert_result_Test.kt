package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_result_Test {
    @Test
    internal fun `should return a valid Object Assert if the Raise returns a result`() {
        assertThat { aFunctionWithContext(42) }.result().isEqualTo(42)
    }

    @Test
    internal fun `should fails if the Raise raised an error`() {
        Assertions
            .assertThatThrownBy { assertThat { aFunctionThatRaisesAnError() }.result() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldSucceedButFailed("LOGICAL ERROR").create())
    }

    @Test
    internal fun `should rethrow the inner exception`() {
        Assertions
            .assertThatThrownBy { assertThat { aFunctionThatThrowsAnException() }.result() }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
