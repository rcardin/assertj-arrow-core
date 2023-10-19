package `in`.rcard.assertj.arrowcore

import arrow.core.None
import arrow.core.Option
import `in`.rcard.assertj.arrowcore.OptionAssert.Companion.assertThat
import `in`.rcard.assertj.arrowcore.errors.OptionShouldBePresent.Companion.shouldBePresent
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test


internal class OptionAssert_hasValueSatisfying_Test {

    @Test
    internal fun `should fail when option is null`() {
        assertThatThrownBy {
            val nullOption: Option<String>? = null
            assertThat(nullOption).hasValueSatisfying { }
        }
        .isInstanceOf(AssertionError::class.java)
        .hasMessage(actualIsNull())
    }

    @Test
    internal fun `should fail when option is none`() {
        assertThatThrownBy {
            assertThat(None).hasValueSatisfying { }
        }
        .isInstanceOf(AssertionError::class.java)
        .hasMessage(shouldBePresent().create())
    }


    @Test
    internal fun `should pass when consumer passes`() {
        assertThat(Option("something")).hasValueSatisfying {
            assertThat(it).isEqualTo("something")
                .startsWith("some")
                .endsWith("thing")
        }
        assertThat(Option(10)).hasValueSatisfying {
            assertThat(it).isGreaterThan(9)
        }
    }

    @Test
    internal fun `should fail from consumer`() {
        assertThatThrownBy {
            assertThat(Option("something"))
                .hasValueSatisfying { assertThat(it).isEqualTo("something else") }
        }
        .isInstanceOf(AssertionError::class.java)
        .hasMessage("\nexpected: \"something else\"\n but was: \"something\"")
    }
}
