package `in`.rcard.assertj.arrowcore.errors

import org.assertj.core.error.BasicErrorMessageFactory

private const val SHOULD_SUCCEED_BUT_FAILED_MESSAGE: String =
    "%nExpecting the lambda to succeed but it failed with:%n  <%s>"

/**
 * Build error message when a lambda should succeed, but it fails with a logic error.
 *
 * @author Riccardo Cardin
 * @since 1.0.0
 */
internal class RaiseShouldSucceedButFailed private constructor(
    actualRaisedError: Any,
) : BasicErrorMessageFactory(SHOULD_SUCCEED_BUT_FAILED_MESSAGE, actualRaisedError) {
    companion object {
        internal fun shouldSucceedButFailed(actualRaisedError: Any): RaiseShouldSucceedButFailed =
            RaiseShouldSucceedButFailed(actualRaisedError)
    }
}
