package `in`.rcard.assertj.arrowcore

import arrow.core.Either

class EitherAssert<LEFT, RIGHT>(either: Either<LEFT, RIGHT>?) :
    AbstractEitherAssert<EitherAssert<LEFT, RIGHT>, LEFT, RIGHT>(either) {
    companion object {
        fun <LEFT, RIGHT> assertThat(actual: Either<LEFT, RIGHT>?): EitherAssert<LEFT, RIGHT> {
            return EitherAssert(actual)
        }
    }
}
