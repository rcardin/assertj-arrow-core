package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:class-naming")
internal class RaiseAssert_suceeded_Test {
    @Test
    internal fun `should pass if lambda succeeds`() {
        assertThat { Dummy.aFunctionWithContext(42) }.succeeded()
    }

    @Test
    internal fun `should fail if lambda raises an error instead of succeeding`() {
        Assertions
            .assertThatThrownBy {
                assertThat { Dummy.aFunctionThatRaisesAnError() }.succeeded()
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldSucceedButFailed("LOGICAL ERROR").create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions
            .assertThatThrownBy {
                assertThat { Dummy.aFunctionThatThrowsAnException() }.succeedsWith(42)
            }.isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
