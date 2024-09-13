package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldContain.Companion.shouldContain
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldContainNull_Test {

    @Test
    internal fun `should fail if non empty list does not contain a null`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldContainNull() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContain(list, null).create())
    }

    @Test
    internal fun `should pass if empty list contains null`() {
        // GIVEN
        val list: NonEmptyList<Int?> = nonEmptyListOf(1, null, 2)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldContainNull()
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldContainNull() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
