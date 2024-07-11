package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:class-naming")
internal class RaiseAssert_fails_Test {
    @Test
    internal fun `should pass if lambda raises a logical error`() {
        assertThat { Dummy.aFunctionThatRaisesAnError() }.fails()
    }

    @Test
    internal fun `should fail if lambda succeeds with a value instead of failing`() {
        Assertions
            .assertThatThrownBy {
                assertThat { Dummy.aFunctionWithContext(42) }.fails()
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldFailButSucceedsWith(42).create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions
            .assertThatThrownBy {
                assertThat { Dummy.aFunctionThatThrowsAnException() }.fails()
            }.isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
