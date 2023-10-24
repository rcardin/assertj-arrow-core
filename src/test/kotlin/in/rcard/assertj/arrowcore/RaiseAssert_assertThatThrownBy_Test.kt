package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThatThrownBy
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldThrowAnException.Companion.shouldThrowAnException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_assertThatThrownBy_Test {

    @Test
    internal fun `should succeed if the lambda throws an exception`() {
        assertThatThrownBy { Dummy.aFunctionThatThrowsAnException() }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }

    @Test
    internal fun `should fail if the lambda succeeds with a value`() {
        Assertions.assertThatThrownBy {
            assertThatThrownBy { Dummy.aFunctionWithContext(42) }
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldThrowAnException().create(),
            )
    }

    @Test
    internal fun `should fail if the lambda raises an error`() {
        Assertions.assertThatThrownBy {
            assertThatThrownBy { Dummy.aFunctionThatRaisesAnError() }
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldThrowAnException().create(),
            )
    }
}