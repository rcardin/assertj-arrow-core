package `in`.rcard.assertj.arrowcore

import `in`.rcard.assertj.arrowcore.RaiseAssert.Companion.assertThat
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RaiseAssert_raises_Test {

    @Test
    fun `should pass if lambda raises a logical error`() {
        assertThat { Dummy.aFunctionThatRaisesAnError() }.raises("LOGICAL ERROR")
    }

    @Test
    fun `should fail if lambda raises a logical error different from the expected`() {
        Assertions.assertThatThrownBy {
            assertThat { Dummy.aFunctionThatRaisesAnError() }.raises("ANOTHER LOGICAL ERROR")
        }.isInstanceOf(AssertionError::class.java)
            .hasMessage(
                "\n" +
                    "expected: \"ANOTHER LOGICAL ERROR\"\n" +
                    " but was: \"LOGICAL ERROR\"",
            )
    }
}
