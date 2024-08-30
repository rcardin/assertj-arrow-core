package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionThatThrowsAnException
import `in`.rcard.assertj.arrowcore.Dummy.aFunctionWithContext
import `in`.rcard.assertj.arrowcore.Dummy.aSuspendFunctionThatRaisesAnError
import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThatRaisedBy
import `in`.rcard.assertj.arrowcore.errors.RaiseShouldFailButSucceeds.Companion.shouldFailButSucceedsWith
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RaiseAssert_assertThatRaisedBy_Test {
    @Test
    internal fun `should return a valid Object Assert if the Raise raises an error`() {
        assertThatRaisedBy { aFunctionThatRaisesAnError() }.isEqualTo("LOGICAL ERROR")
    }

    @Test
    fun `should work with suspended functions`() =
        runTest {
            assertThatRaisedBy { aSuspendFunctionThatRaisesAnError() }.isEqualTo("LOGICAL ERROR")
        }

    @Test
    internal fun `should fails if the Raise returns a value`() {
        Assertions
            .assertThatThrownBy { assertThatRaisedBy { aFunctionWithContext(42) } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldFailButSucceedsWith(42).create())
    }

    @Test
    internal fun `should rethrow the inner exception`() {
        Assertions
            .assertThatThrownBy { assertThatRaisedBy { aFunctionThatThrowsAnException() } }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("AN EXCEPTION")
    }
}
