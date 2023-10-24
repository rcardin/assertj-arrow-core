package `in`.rcard.assertj.arrowcore

import arrow.core.Either

/**
 * Assertions for [Either].
 *
 * @param LEFT  type of the value on the left contained in the [Either].
 * @param RIGHT type of the value on the right contained in the [Either].
 * @author Riccardo Cardin
 * @since 0.0.1
 */
class EitherAssert<LEFT : Any, RIGHT : Any>(either: Either<LEFT, RIGHT>?) :
    AbstractEitherAssert<EitherAssert<LEFT, RIGHT>, LEFT, RIGHT>(either) {
    companion object {
        fun <LEFT : Any, RIGHT : Any> assertThat(actual: Either<LEFT, RIGHT>?): EitherAssert<LEFT, RIGHT> =
            EitherAssert(actual)
    }
}
