package `in`.rcard.assertj.arrowcore

import arrow.core.Option
import org.assertj.core.api.AbstractObjectAssert

/**
 * Assertions for [Option].
 *
 * @param SELF  the "self" type of this assertion class.
 * @param VALUE type of the value contained in the [Option].
 * @author Riccardo Cardin
 */
abstract class AbstractOptionAssert<
    SELF : AbstractOptionAssert<SELF, VALUE>, VALUE : Any,
    >(
    option: Option<VALUE>?,
) : AbstractObjectAssert<SELF, Option<VALUE>>(option, AbstractOptionAssert::class.java)
