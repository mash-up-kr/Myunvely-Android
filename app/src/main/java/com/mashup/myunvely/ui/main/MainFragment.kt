package com.mashup.myunvely.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mashup.myunvely.R
import com.mashup.myunvely.rx.observeOnMain
import com.mashup.myunvely.rx.subscribeWithErrorLogger
import com.mashup.myunvely.ui.base.BaseViewModelFragment
import io.reactivex.rxkotlin.toFlowable

class MainFragment : BaseViewModelFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)
}