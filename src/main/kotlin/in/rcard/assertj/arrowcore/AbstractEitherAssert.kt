package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import org.assertj.core.api.AbstractAssert

abstract class AbstractEitherAssert<SELF : AbstractEitherAssert<SELF, LEFT, RIGHT>, LEFT, RIGHT>(either: Either<LEFT, RIGHT>?) :
    AbstractAssert<SELF, Either<LEFT, RIGHT>>(either, AbstractEitherAssert::class.java)
