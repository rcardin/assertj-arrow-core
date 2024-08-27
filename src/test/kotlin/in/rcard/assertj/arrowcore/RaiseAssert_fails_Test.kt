package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:class-naming")
internal class RaiseAssert_fails_Test {
    @Test
    internal fun `should pass if lambda raises a logical error`() {
        assertThat { aFunctionThatRaisesAnError() }.fails()
    }

    @Test
    internal fun `should fail if lambda succeeds with a value instead of failing`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionWithContext(42) }.fails()
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldFailButSucceedsWith(42).create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions
            .assertThatThrownBy {
                assertThat { aFunctionThatThrowsAnException() }.fails()
            }.isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
