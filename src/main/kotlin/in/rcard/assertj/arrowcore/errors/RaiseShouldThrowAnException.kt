package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val EXPECTING_CODE_TO_THROW = "%nExpecting code to throw a throwable."

class RaiseShouldThrowAnException : BasicErrorMessageFactory(EXPECTING_CODE_TO_THROW) {

    companion object {
        fun shouldThrowAnException(): RaiseShouldThrowAnException =
            RaiseShouldThrowAnException()
    }
}