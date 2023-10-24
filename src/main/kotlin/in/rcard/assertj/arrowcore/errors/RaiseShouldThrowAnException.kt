package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_THROW_AN_EXCEPTION_MESSAGE = "%nExpecting code to throw a throwable."

/**
 * Build error message when a lambda should throw an exception but it doesn't.
 *
 * @author Riccardo Cardin
 * @since 0.2.0
 */
internal class RaiseShouldThrowAnException private constructor() :
    BasicErrorMessageFactory(SHOULD_THROW_AN_EXCEPTION_MESSAGE) {

    companion object {
        internal fun shouldThrowAnException(): RaiseShouldThrowAnException =
            RaiseShouldThrowAnException()
    }
}