package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_FAIL_BUT_SUCCEEDED_MESSAGE: String =
    "%nExpecting the lambda to fail with:%n  <%s> %nbut it succeeded with:%n  <%s>"

/**
 * Build error message when a lambda should fail with a logic error but it succeeded with a value.
 *
 * @author Riccardo Cardin
 * @since 0.2.0
 */
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