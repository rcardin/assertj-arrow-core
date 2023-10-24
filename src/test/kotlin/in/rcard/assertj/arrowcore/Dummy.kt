package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise

internal object Dummy {

    context (Raise<String>)
    fun aFunctionWithContext(input: Int): Int = input

    context (Raise<String>)
    fun aFunctionThatRaisesAnError(): Int = raise("LOGICAL ERROR")

    context (Raise<String>)
    fun aFunctionThatThrowsAnException(): Int = throw RuntimeException("AN EXCEPTION")
}
