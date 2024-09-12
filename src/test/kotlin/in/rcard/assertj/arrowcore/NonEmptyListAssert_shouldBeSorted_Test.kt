package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldBeSorted.Companion.shouldBeSorted
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldBeSorted_Test {

    @Test
    internal fun `should fail if non empty list is not sorted`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 3, 4, 2)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldBeSorted() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldBeSorted(list).create())
    }

    @Test
    internal fun `should pass if non empty list is sorted`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2, 3, 4)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldBeSorted()
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldBeSorted() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
