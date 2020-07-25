package com.mashup.android.base.extension.rx

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

@Deprecated("Deprecated because of performance issue. Use TextWatcher directly.")
inline fun <T : CharSequence> Flowable<T>.twoWayBindTo(
    editText: EditText?,
    crossinline onTextChanged: (String) -> Unit
): Disposable {
    editText?.doOnTextChanged { text, _, _, _ -> onTextChanged(text?.toString() ?: "") }
    return distinctUntilChanged()
        .observeOnMain()
        .subscribeWithErrorLogger {
            if (editText?.text?.toString() != it) {
                editText?.setText(it)
                editText?.setSelection(it.length)
            }
        }
}
