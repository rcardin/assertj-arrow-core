package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_SUCCEED_BUT_FAILED_MESSAGE: String =
    "%nExpecting the lambda to succeed with:%n  <%s> %nbut it failed with:%n  <%s>"

internal class RaiseShouldSucceedButFailed private constructor(expected: Any, actualRaisedError: Any) :
    BasicErrorMessageFactory(SHOULD_SUCCEED_BUT_FAILED_MESSAGE, expected, actualRaisedError) {

    companion object {
        internal fun shouldSucceedButFailed(expected: Any, actualRaisedError: Any): RaiseShouldSucceedButFailed =
            RaiseShouldSucceedButFailed(expected, actualRaisedError)
    }
}
