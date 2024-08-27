package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWith
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldSucceedWithButFailed.Companion.shouldSucceedWithButFailed
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:class-naming")
internal class RaiseAssert_succeedsWith_Test {
    @Test
    internal fun `should pass if lambda succeeds with the given value`() {
        assertThat { aFunctionWithContext(42) }.succeedsWith(42)
    }

    @Test
    internal fun `should fail if lambda succeeds with a value different than the given one`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionWithContext(42) }.succeedsWith(41)
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(RaiseShouldSucceedWith.shouldSucceedWith(41, 42).create())
    }

    @Test
    internal fun `should fail if lambda raises an error instead of succeeding`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionThatRaisesAnError() }.succeedsWith(42)
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldSucceedWithButFailed(42, "LOGICAL ERROR").create(),
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
