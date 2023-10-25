package `in`.rcard.assertj.arrowcore.errors

import arrow.core.Either
import org.assertj.core.error.BasicErrorMessageFactory

/**
 * Build error message when an [Either] should be right.
 *
 * @author Riccardo Cardin
 * @since 0.0.1
 */
internal class EitherShouldBeRight private constructor(actual: Either<*, *>) :
    BasicErrorMessageFactory("%nExpecting an Either to be right but was <$actual>.") {
    companion object {
        internal fun shouldBeRight(actual: Either<*, *>): EitherShouldBeRight = EitherShouldBeRight(actual)
    }
}
