package com.mashup.android.base.extension

import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.doOnScrolled(
    crossinline action: (recyclerView: RecyclerView, dx: Int, dy: Int) -> Unit
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            action(recyclerView, dx, dy)
        }
    })
}
