package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_SUCCEED_WITH_MESSAGE: String =
    "%nExpecting the lambda to succeed with:%n  <%s> %nbut it actual succeed with:%n  <%s>"

/**
 * Build error message when a lambda should succeed with a value but it succeeds with a different value.
 *
 * @author Riccardo Cardin
 * @since 0.2.0
 */
internal class RaiseShouldSucceedWith private constructor(expected: Any, actual: Any) :
    BasicErrorMessageFactory(SHOULD_SUCCEED_WITH_MESSAGE, expected, actual) {

    companion object {
        internal fun shouldSucceedWith(expected: Any, actual: Any): RaiseShouldSucceedWith =
            RaiseShouldSucceedWith(expected, actual)
    }
}