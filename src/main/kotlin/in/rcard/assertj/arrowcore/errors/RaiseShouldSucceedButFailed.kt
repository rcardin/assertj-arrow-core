package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_SUCCEED_BUT_FAILED_MESSAGE: String =
    "%nExpecting the lambda to succeed with:%n  <%s> %nbut it failed with:%n  <%s>"

/**
 * Build error message when a lambda should succeed but it fails with a logic error.
 *
 * @author Riccardo Cardin
 * @since 0.2.0
 */
internal class RaiseShouldSucceedButFailed private constructor(expected: Any, actualRaisedError: Any) :
    BasicErrorMessageFactory(SHOULD_SUCCEED_BUT_FAILED_MESSAGE, expected, actualRaisedError) {

    companion object {
        internal fun shouldSucceedButFailed(expected: Any, actualRaisedError: Any): RaiseShouldSucceedButFailed =
            RaiseShouldSucceedButFailed(expected, actualRaisedError)
    }
}
