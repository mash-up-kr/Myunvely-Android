package com.mashup.android.base.rx

data class Wrapper<T : Any>(val data: T? = null) {
    val isEmpty get() = data == null
    val isNotEmpty get() = data != null
}

fun <T : Any> T?.wrap(): Wrapper<T> = Wrapper(this)
