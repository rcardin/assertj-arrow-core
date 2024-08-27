package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.Dummy.aSuspendFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThatThrownBy
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldThrowAnException.Companion.shouldThrowAnException
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RaiseAssert_assertThatThrownBy_Test {
    @Test
    internal fun `should succeed if the lambda throws an exception`() {
        assertThatThrownBy { aFunctionThatThrowsAnException() }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }

    @Test
    internal fun `should succeed if the suspending lambda throws an exception`() =
        runTest {
            assertThatThrownBy { aSuspendFunctionThatThrowsAnException() }
                .isInstanceOf(RuntimeException::class.java)
                .hasMessage("AN EXCEPTION")
        }

    @Test
    internal fun `should fail if the lambda succeeds with a value`() {
        Assertions
            .assertThatThrownBy {
                assertThatThrownBy { aFunctionWithContext(42) }
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldThrowAnException().create(),
            )
    }

    @Test
    internal fun `should fail if the lambda raises an error`() {
        Assertions
            .assertThatThrownBy {
                assertThatThrownBy { aFunctionThatRaisesAnError() }
            }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                shouldThrowAnException().create(),
            )
    }
}
