package `in`.rcard.assertj.arrowcore

import arrow.core.raise.Raise
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class RaiseAssert_assertThat_Test {
    @Test
    internal fun `should create an assertion instance when given lambda is not null`() {
        // GIVEN
        val aLambdaWithContext: context(Raise<String>) () -> Int = this::aFunctionWithContext
        // WHEN
        val assertion = RaiseAssert.assertThat(aLambdaWithContext)
        // THEN
        then(assertion).isNotNull.isInstanceOf(RaiseAssert::class.java)
    }

    context (Raise<String>)
    private fun aFunctionWithContext(): Int = 42
}
