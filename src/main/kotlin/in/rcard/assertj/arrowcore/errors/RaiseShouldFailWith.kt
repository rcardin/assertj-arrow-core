package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_FAIL_WITH_MESSAGE: String =
    "%nExpecting the lambda to fail with:%n  <%s> %nbut it actual failed with:%n  <%s>"

internal class RaiseShouldFailWith private constructor(expectedError: Any, actualError: Any) :
    BasicErrorMessageFactory(SHOULD_FAIL_WITH_MESSAGE, expectedError, actualError) {

    companion object {
        internal fun shouldFailWith(expectedError: Any, actualError: Any): RaiseShouldFailWith =
            RaiseShouldFailWith(expectedError, actualError)
    }
}