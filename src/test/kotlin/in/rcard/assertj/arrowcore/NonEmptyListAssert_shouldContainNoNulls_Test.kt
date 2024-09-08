package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListContains.Companion.contains
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldContainNoNulls_Test {

    @Test
    internal fun `should fail if non empty list contains a null`() {
        // GIVEN
        val list: NonEmptyList<Int?> = nonEmptyListOf(1, 2, null)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldContainNoNulls() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(contains(list, null).create())
    }

    @Test
    internal fun `should pass if empty list contain no nulls`() {
        // GIVEN
        val list: NonEmptyList<Int?> = nonEmptyListOf(1, 2, 3)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldContainNoNulls()
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldContainNoNulls() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
