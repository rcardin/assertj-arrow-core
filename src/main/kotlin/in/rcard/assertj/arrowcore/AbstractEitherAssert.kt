package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import org.assertj.core.api.AbstractObjectAssert

abstract class AbstractEitherAssert<SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>, LEFT, RIGHT>(either: Either<LEFT, RIGHT>?) :
    AbstractObjectAssert<SELF, Either<LEFT, RIGHT>>(either, AbstractEitherAssert::class.java) {
    fun isRight() {
        isNotNull
        if (!actual.isRight()) {
            failWithMessage("%nExpected Either to be right but was <%s>", actual)
        }
    }
}
