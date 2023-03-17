package `in`.rcard.assertj.arrowcore

import arrow.core.Option
import arrow.core.some
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class OptionAssert_assertThat_Test {

    @Test
    fun `should create an assertion instance when given object is not null`() {
        // GIVEN
        val someValue = 42.some()
        // WHEN
        val assertion = OptionAssert.assertThat<Any>(someValue)
        // THEN
        then(assertion).isNotNull.isInstanceOf(OptionAssert::class.java)
    }

    @Test
    fun `should create an assertion instance when given object is null`() {
        // GIVEN
        val someNullValue: Option<Nothing>? = null
        // WHEN
        val assertion = OptionAssert.assertThat(someNullValue)
        // THEN
        then(assertion).isNotNull.isInstanceOf(OptionAssert::class.java)
    }
}
