package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise

internal object Dummy {
    fun Raise<String>.aFunctionWithContext(input: Int): Int = input

    fun Raise<String>.aFunctionThatRaisesAnError(): Int = raise("LOGICAL ERROR")

    fun Raise<String>.aFunctionThatThrowsAnException(): Int = throw RuntimeException("AN EXCEPTION")

    suspend fun Raise<String>.aSuspendFunctionWithContext(input: Int): Int = input

    suspend fun Raise<String>.aSuspendFunctionThatThrowsAnException(): Int = throw RuntimeException("AN EXCEPTION")

    suspend fun Raise<String>.aSuspendFunctionThatRaisesAnError(): Int = raise("LOGICAL ERROR")
}
