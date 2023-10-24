package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeded.Companion.shouldFailButSucceeded
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailWith.Companion.shouldFailWith
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RaiseAssert_raises_Test {

    @Test
    fun `should pass if lambda raises a logical error`() {
        assertThat { Dummy.aFunctionThatRaisesAnError() }.raises("LOGICAL ERROR")
    }

    @Test
    fun `should fail if lambda raises a logical error different from the expected`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatRaisesAnError() }.raises("ANOTHER LOGICAL ERROR")
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldFailWith("ANOTHER LOGICAL ERROR", "LOGICAL ERROR").create())
    }

    @Test
    fun `should fail if lambda succeeds with a value instead of failing`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionWithContext(42) }.raises("LOGICAL ERROR")
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldFailButSucceeded("LOGICAL ERROR", 42).create(),
            )
    }

    @Test
    internal fun `should fail if lambda throws an exception`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatThrowsAnException() }.raises("LOGICAL ERROR")
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
