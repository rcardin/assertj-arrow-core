package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import arrow.core.right
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class EitherAssert_assertThat_Test {
    @Test
    internal fun `should create an assertion instance when given object is not null`() {
        // GIVEN
        val rightValue = 42.right()
        // WHEN
        val assertion = EitherAssert.assertThat(rightValue)
        // THEN
        then(assertion).isNotNull.isInstanceOf(EitherAssert::class.java)
    }

    @Test
    internal fun `should create an assertion instance when given object is null`() {
        // GIVEN
        val rightValue: Either<Nothing, Nothing>? = null
        // WHEN
        val assertion = EitherAssert.assertThat(rightValue)
        // THEN
        then(assertion).isNotNull.isInstanceOf(EitherAssert::class.java)
    }
}
