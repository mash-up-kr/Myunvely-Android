package com.mashup.myunvely.ui

import android.content.Intent
import android.os.Bundle
import com.mashup.myunvely.R
import com.mashup.myunvely.ui.base.BaseActivity
import com.mashup.myunvely.ui.walkthrough.WalkthroughActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            startActivity(Intent(this, WalkthroughActivity::class.java))
        }
    }
}