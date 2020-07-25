package com.moim.android.base.utils

import org.junit.Test
import java.text.SimpleDateFormat

class DateTimeUtilsTest {

    @Test
    fun random() {
        val random = (DateTimeUtils.random() * 1000).toLong()
        println(SimpleDateFormat.getDateTimeInstance().format(random))
    }
}
