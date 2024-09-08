package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotContain.Companion.doesNotContain
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldContainAll_Test {

    @Test
    internal fun `should fail if non empty list does not contain all elements`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldContainAll(4, 5, 6) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(doesNotContain(list, arrayOf(4, 5, 6)).create())
    }

    @Test
    internal fun `should pass if empty list contains element`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldContainAll(1, 2, 3)
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldContainAll(1) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
