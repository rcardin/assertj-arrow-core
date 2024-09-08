package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotContain.Companion.doesNotContain
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldContain_Test {

    @Test
    internal fun `should fail if non empty list does not contain element`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 4)
        // WHEN/THEN
       Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldContain(3) }
           .isInstanceOf(AssertionError::class.java)
            .hasMessage(doesNotContain(list, 3).create())
    }

    @Test
    internal fun `should pass if empty list contains element`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldContain(3)
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldContain(1) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
