package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:class-naming")
internal class RaiseAssert_suceeded_Test {
    @Test
    internal fun `should pass if lambda succeeds`() {
        assertThat { aFunctionWithContext(42) }.succeeds()
    }

    @Test
    internal fun `should fail if lambda raises an error instead of succeeding`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionThatRaisesAnError() }.succeeds()
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldSucceedButFailed("LOGICAL ERROR").create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionThatThrowsAnException() }.succeedsWith(42)
            }.isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
