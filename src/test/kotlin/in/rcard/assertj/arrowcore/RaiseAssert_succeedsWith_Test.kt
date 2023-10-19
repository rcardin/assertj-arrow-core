package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedButFailed.Companion.shouldSucceedButFailed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_succeedsWith_Test {

    @Test
    internal fun `should pass if lambda succeeds with the given value`() {
        assertThat { Dummy.aFunctionWithContext(42) }.succeedsWith(42)
    }

    @Test
    internal fun `should fail if lambda succeeds with a value different than the given one`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionWithContext(42) }.succeedsWith(41)
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                "\n" +
                    "expected: 41\n" +
                    " but was: 42",
            )
    }

    @Test
    internal fun `should fail if lambda raises an error instead of succeeding`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatRaisesAnError() }.succeedsWith(42)
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldSucceedButFailed(42, "LOGICAL ERROR").create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatThrowsAnException() }.succeedsWith(42)
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                "Expected lambda to succeed but it throws the exception 'java.lang.RuntimeException: AN EXCEPTION'",
            )
    }
}
