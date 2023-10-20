package `in`.rcard.assertj.arrowcore

import arrow.core.Either
import `in`.rcard.assertj.arrowcore.errors.EitherShouldBeRight.Companion.shouldBeRight
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test


internal class EitherAssert_hasRightValueSatisfying_Test {

    @Test
    internal fun `should fail when either is null`() {
        val actual: Either<Int, String>? = null
        assertThatThrownBy { EitherAssert.assertThat(actual).hasRightValueSatisfying { } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }

    @Test
    internal fun `should fail if either is left`() {
        val actual: Either<Int, String> = Either.Left(42)
        assertThatThrownBy { EitherAssert.assertThat(actual).hasRightValueSatisfying { } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeRight(actual).create())
    }

    @Test
    internal fun `should fail if consumer fails`() {
        val actual: Either<Int, String> = Either.Right("something")
        assertThatThrownBy { EitherAssert.assertThat(actual).hasRightValueSatisfying { assertThat(it).isEqualTo("something else") } }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(("\nexpected: \"something else\"\n but was: \"something\""))
    }

    @Test
    internal fun `should pass if consumer passes`() {
        val actual: Either<Int, String> = Either.Right("something")
        EitherAssert.assertThat(actual).hasRightValueSatisfying { assertThat(it).isEqualTo("something") }
    }
}