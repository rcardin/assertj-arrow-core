package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_FAIL_BUT_SUCCEEDED_MESSAGE: String =
    "%nExpecting the lambda to fail with:%n  <%s> %nbut it succeeded with:%n  <%s>"

internal class RaiseShouldFailButSucceeded private constructor(expectedError: Any, actualSucceededValue: Any) :
    BasicErrorMessageFactory(SHOULD_FAIL_BUT_SUCCEEDED_MESSAGE, expectedError, actualSucceededValue) {

    companion object {
        internal fun shouldFailButSucceeded(
            expectedError: Any,
            actualSucceededValue: Any
        ): RaiseShouldFailButSucceeded =
            RaiseShouldFailButSucceeded(expectedError, actualSucceededValue)
    }
}