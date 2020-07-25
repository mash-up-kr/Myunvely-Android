package com.moim.android.base.utils

import com.moim.android.base.utils.CountFormatUtils.toActionCount
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class CountFormatUtilsTest {

    @Test
    fun getActionCount_Korean() {
        Locale.setDefault(Locale.KOREAN)
        assertEquals(0L.toActionCount(), null)
        assertEquals(1L.toActionCount(), "1")
        assertEquals(2L.toActionCount(), "2")
        assertEquals(999L.toActionCount(), "999")
        assertEquals(1000L.toActionCount(), "1,000")
        assertEquals(1001L.toActionCount(), "1,001")
        assertEquals(9999L.toActionCount(), "9,999")
        assertEquals(10000L.toActionCount(), "1만")
        assertEquals(10001L.toActionCount(), "1만")
        assertEquals(10099L.toActionCount(), "1만")
        assertEquals(10100L.toActionCount(), "1만")
        assertEquals(10101L.toActionCount(), "1만")
        assertEquals(11000L.toActionCount(), "1.1만")
        assertEquals(999999L.toActionCount(), "99.9만")
        assertEquals(1000000L.toActionCount(), "100만")
        assertEquals(1000001L.toActionCount(), "100만")
        assertEquals(1009999L.toActionCount(), "100.9만")
        assertEquals(1100000L.toActionCount(), "110만")
        assertEquals(1100001L.toActionCount(), "110만")
        assertEquals(99999999L.toActionCount(), "9999.9만")
        assertEquals(100000000L.toActionCount(), "1억")
        assertEquals(100000001L.toActionCount(), "1억")
        assertEquals(110000001L.toActionCount(), "1.1억")
        assertEquals(999999999L.toActionCount(), "9.9억")
        assertEquals(1000000000L.toActionCount(), "10억")
        assertEquals(1000000001L.toActionCount(), "10억")
        assertEquals(1099999999L.toActionCount(), "10.9억")
        assertEquals(1100000000L.toActionCount(), "11억")
        assertEquals(2147483647L.toActionCount(), "21.4억")
    }

    @Test
    fun getActionCount_Other() {
        Locale.setDefault(Locale.ENGLISH)
        assertEquals(0L.toActionCount(), null)
        assertEquals(1L.toActionCount(), "1")
        assertEquals(2L.toActionCount(), "2")
        assertEquals(999L.toActionCount(), "999")
        assertEquals(1000L.toActionCount(), "1,000")
        assertEquals(1001L.toActionCount(), "1,001")
        assertEquals(9999L.toActionCount(), "9,999")
        assertEquals(10000L.toActionCount(), "10K")
        assertEquals(10001L.toActionCount(), "10K")
        assertEquals(10099L.toActionCount(), "10K")
        assertEquals(10100L.toActionCount(), "10.1K")
        assertEquals(10101L.toActionCount(), "10.1K")
        assertEquals(11000L.toActionCount(), "11K")
        assertEquals(999999L.toActionCount(), "999.9K")
        assertEquals(1000000L.toActionCount(), "1M")
        assertEquals(1000001L.toActionCount(), "1M")
        assertEquals(1009999L.toActionCount(), "1M")
        assertEquals(1100000L.toActionCount(), "1.1M")
        assertEquals(1100001L.toActionCount(), "1.1M")
        assertEquals(99999999L.toActionCount(), "99.9M")
        assertEquals(100000000L.toActionCount(), "100M")
        assertEquals(100000001L.toActionCount(), "100M")
        assertEquals(110000001L.toActionCount(), "110M")
        assertEquals(999999999L.toActionCount(), "999.9M")
        assertEquals(1000000000L.toActionCount(), "1B")
        assertEquals(1000000001L.toActionCount(), "1B")
        assertEquals(1099999999L.toActionCount(), "1B")
        assertEquals(1100000000L.toActionCount(), "1.1B")
        assertEquals(2147483647L.toActionCount(), "2.1B")
    }
}
