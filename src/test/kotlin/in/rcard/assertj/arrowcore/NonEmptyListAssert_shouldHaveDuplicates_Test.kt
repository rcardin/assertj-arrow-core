package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListHasNoDuplicates.Companion.hasNoDuplicate
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldHaveDuplicates_Test {

    @Test
    internal fun `should fail if non empty list has no duplicates`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3, 4)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldHaveDuplicates() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(hasNoDuplicate(list).create())
    }

    @Test
    internal fun `should pass if non empty list has at least one duplicate`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 2, 3)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldHaveDuplicates()
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldHaveDuplicates() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
