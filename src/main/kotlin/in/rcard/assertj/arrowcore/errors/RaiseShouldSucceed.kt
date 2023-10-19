package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_SUCCEED_MESSAGE: String =
    "%nExpecting the lambda to succeed with:%n  <%s> %nbut it failed with:%n  <%s>"

internal class RaiseShouldSucceed(expected: Any, actualRaisedError: Any) :
    BasicErrorMessageFactory(SHOULD_SUCCEED_MESSAGE, expected, actualRaisedError) {

    companion object {
        internal fun shouldSucceed(expected: Any, actualRaisedError: Any): RaiseShouldSucceed =
            RaiseShouldSucceed(expected, actualRaisedError)
    }
}
