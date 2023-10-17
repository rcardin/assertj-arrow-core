package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import org.junit.jupiter.api.Test

internal class RaiseAssert_succeedsWith_Test {

    @Test
    fun `should pass if lambda succeeds with the given value`() {
        val i: Int = 42
        val lambda: context(Raise<String>) () -> Int = { i }
        assertThat(lambda).succeedsWith(42)
    }

//    @Test
//    fun `should fail if lambda succeeds with a value different than the given one`() {
//        Assertions.assertThatThrownBy {
//            assertThat({ aFunctionWithContextThatSucceeds(42) }).succeedsWith(41)
//        }.isInstanceOf(AssertionError::class.java)
//            .hasMessage(
//                "\n" +
//                    "expected: 41\n" +
//                    " but was: 42",
//            )
//    }

    context (Raise<String>)
    fun aFunctionWithContextThatSucceeds(input: Int): Int = input

    context (Raise<String>)
    private fun aFunctionWithContextThatFails(): Int = raise("There was a logical error")
}
