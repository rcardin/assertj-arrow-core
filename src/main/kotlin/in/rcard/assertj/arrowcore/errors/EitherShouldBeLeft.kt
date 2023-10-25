package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Either
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Either] should be left.
 *
 * @author Riccardo Cardin
 * @since 0.0.1
 */
internal class EitherShouldBeLeft private constructor(actual: Either<*, *>) :
    BasicErrorMessageFactory("%nExpecting an Either to be left but was <$actual>.") {
    companion object {
        internal fun shouldBeLeft(actual: Either<*, *>): EitherShouldBeLeft = EitherShouldBeLeft(actual)
    }
}
