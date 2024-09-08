package `in`.rcard.assertj.arrowcore

import arrow.core.NonEmptyList
import arrow.core.nonEmptyListOf
import `in`.rcard.assertj.arrowcore.errors.NonEmptyListDoesNotHaveSingleElementEqual.Companion.doesNotHaveSingleElementEqual
import org.assertj.core.api.Assertions
import org.assertj.core.util.FailureMessages.actualIsNull
import org.junit.jupiter.api.Test

internal class NonEmptyListAssert_shouldBeSingleElement_Test {

    @Test
    internal fun `should fail if non empty list has more than one element`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(1, 2)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldBeSingleElement(1) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(doesNotHaveSingleElementEqual(list, 1).create())
    }

    @Test
    internal fun `should fail if non empty list has one element but it's not equal to expected value`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(41)
        // WHEN/THEN
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat(list).shouldBeSingleElement(42) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(doesNotHaveSingleElementEqual(list, 42).create())
    }

    @Test
    internal fun `should pass if empty list contains element`() {
        // GIVEN
        val list: NonEmptyList<Int> = nonEmptyListOf(42)
        // WHEN/THEN
        NonEmptyListAssert.assertThat(list).shouldBeSingleElement(42)
    }

    @Test
    internal fun `should fail if list is null`() {
        Assertions.assertThatThrownBy { NonEmptyListAssert.assertThat<Int>(null).shouldBeSingleElement(1) }
            .isInstanceOf(AssertionError::class.java)
            .hasMessage(actualIsNull())
    }
}
