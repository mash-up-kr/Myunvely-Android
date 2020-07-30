package com.mashup.myunvely.ui.walkthrough

import android.os.Bundle
import com.mashup.myunvely.R
import com.mashup.myunvely.ui.base.BaseAppIntroActivity

class WalkthroughActivity : BaseAppIntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNextArrowColor(R.color.colorBlack)
        setColorSkipButton(R.color.colorBlack)
        setColorDoneText(R.color.colorBlack)
        setSkipText("SKIP")

        setDoneText(null)
        showSeparator(false)
        isWizardMode = true
        setImmersiveMode()
        setIndicatorColor(
            selectedIndicatorColor = getColor(R.color.colorAccent),
            unselectedIndicatorColor = getColor(R.color.colorBlack)
        )
        addSlide(WalkthroughFragment.newInstance("생활용품\n주기를 등록하고,", R.mipmap.img_testworkthrough))
        addSlide(WalkthroughFragment.newInstance("교체 시기를\n확인하고,", R.mipmap.img_testworkthrough))
        addSlide(WalkthroughFragment.newInstance("청결한 생활의\n주인이 되세요.", R.mipmap.img_testworkthrough))
    }
}