package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_FAIL_WITH_BUT_SUCCEEDS_WITH_MESSAGE: String =
    "%nExpecting the lambda to fail with:%n  <%s> %nbut it succeeded with:%n  <%s>"

private const val SHOULD_FAIL_BUT_SUCCEEDS_WITH_MESSAGE: String =
    "%nExpecting the lambda to fail<%s>, but it succeeded with:%n  <%s>"

/**
 * Build error message when a lambda should fail with a logic error but it succeeded with a value.
 *
 * @author Riccardo Cardin
 * @since 0.2.0
 */
class RaiseShouldFailButSucceeds private constructor(
    expectedError: Any,
    actualSucceededValue: Any,
    message: String,
) : BasicErrorMessageFactory(message, expectedError, actualSucceededValue) {
    companion object {
        internal fun shouldFailWithButSucceedsWith(
            expectedError: Any,
            actualSucceededValue: Any,
        ): RaiseShouldFailButSucceeds =
            RaiseShouldFailButSucceeds(expectedError, actualSucceededValue, SHOULD_FAIL_WITH_BUT_SUCCEEDS_WITH_MESSAGE)

        fun shouldFailButSucceedsWith(actualSucceededValue: Any): RaiseShouldFailButSucceeds =
            RaiseShouldFailButSucceeds("", actualSucceededValue, SHOULD_FAIL_BUT_SUCCEEDS_WITH_MESSAGE)
    }
}
