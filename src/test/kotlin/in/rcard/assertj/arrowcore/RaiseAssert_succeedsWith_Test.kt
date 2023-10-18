package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_succeedsWith_Test {

    @Test
    fun `should pass if lambda succeeds with the given value`() {
        assertThat { Dummy.aFunctionWithContext(42) }.succeedsWith(42)
    }

    @Test
    fun `should fail if lambda succeeds with a value different than the given one`() {
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
    fun `should fail if lambda raises an error instead of succeeding`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatRaisesAnError() }.succeedsWith(42)
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                "Expected lambda to succeed but it failed with 'LOGICAL ERROR'",
            )
    }

    @Test
    fun `should fail if lambda throws an exception`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatThrowsAnException() }.succeedsWith(42)
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                "Expected lambda to succeed but it throws the exception 'java.lang.RuntimeException: AN EXCEPTION'",
            )
    }
}

private object Dummy {

    context (Raise<String>)
    fun aFunctionWithContext(input: Int): Int = input

    context (Raise<String>)
    fun aFunctionThatRaisesAnError(): Int = raise("LOGICAL ERROR")

    context (Raise<String>)
    fun aFunctionThatThrowsAnException(): Int = throw RuntimeException("AN EXCEPTION")
}
