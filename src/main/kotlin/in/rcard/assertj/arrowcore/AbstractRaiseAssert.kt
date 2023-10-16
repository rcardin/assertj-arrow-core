package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import org.assertj.core.api.AbstractAssert

abstract class AbstractRaiseAssert<
    SELF : AbstractRaiseAssert<SELF, ERROR, VALUE>, ERROR, VALUE : Any,
    >(lambda: context(Raise<ERROR>) () -> VALUE) :
    AbstractAssert<SELF, context(Raise<ERROR>) () -> VALUE>(lambda, AbstractRaiseAssert::class.java)
