package com.mashup.myunvely.ui.base

import androidx.annotation.CallSuper
import com.github.appintro.AppIntro
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BaseAppIntroActivity : AppIntro() {

    private val disposables by lazy { CompositeDisposable() }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    protected fun Disposable.addToDisposables(): Disposable = addTo(disposables)
}