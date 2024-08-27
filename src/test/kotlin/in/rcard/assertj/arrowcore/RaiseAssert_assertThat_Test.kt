package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.Dummy.aSuspendFunctionWithContext
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class RaiseAssert_assertThat_Test {
    @Test
    internal fun `should create an assertion instance when given lambda is not null`() {
        val assertion = RaiseAssert.assertThat { aFunctionWithContext(42) }
        then(assertion).isNotNull.isInstanceOf(RaiseAssert::class.java)
    }

    @Test
    internal fun `should create an assertion instance for suspending lambdas`() =
        runTest {
            val assertion = RaiseAssert.assertThat { aSuspendFunctionWithContext(42) }
            then(assertion).isNotNull.isInstanceOf(RaiseAssert::class.java)
        }
}
