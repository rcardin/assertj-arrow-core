package `in`.rcard.assertj.arrowcore

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class RaiseAssert_assertThat_Test {
    @Test
    internal fun `should create an assertion instance when given lambda is not null`() {
        val assertion = RaiseAssert.assertThat { Dummy.aFunctionWithContext(42) }
        then(assertion).isNotNull.isInstanceOf(RaiseAssert::class.java)
    }
}
