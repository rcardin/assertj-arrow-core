package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListShouldContainOnly.Companion.shouldContainOnly
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldContainOnlyNulls_Test {

    @Test
    internal fun `should fail if non empty list contains a non-null`() {
        // GIVEN
        val list: NonEmptyList<Int?> = nonEmptyListOf(null, null, 42)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldContainOnlyNulls() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(shouldContainOnly(list, null).create())
    }

    @Test
    internal fun `should pass if empty list contains only null`() {
        // GIVEN
        val list: NonEmptyList<Int?> = nonEmptyListOf(null, null, null)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldContainOnlyNulls()
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldContainOnlyNulls() }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
