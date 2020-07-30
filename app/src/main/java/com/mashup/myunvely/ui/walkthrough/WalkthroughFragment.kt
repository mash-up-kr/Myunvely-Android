package com.mashup.myunvely.ui.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mashup.myunvely.R
import com.mashup.myunvely.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_walkthrough.*

class WalkthroughFragment : BaseFragment() {
    private val text: String by lazy { arguments?.getString(KEY_TEXT_ID) ?: "" }
    private val imageId: Int by lazy { arguments?.getInt(KEY_IMAGE_ID) ?: R.mipmap.ic_launcher }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_walkthrough, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        walkthrough_title?.text = text
        walkthrough_image?.setImageResource(imageId)
    }

    companion object {
        private const val KEY_TEXT_ID = "text_id"
        private const val KEY_IMAGE_ID = "image_id"

        fun newInstance(text: String, @DrawableRes image: Int): WalkthroughFragment =
            WalkthroughFragment().apply {
                arguments = bundleOf(
                    KEY_TEXT_ID to text,
                    KEY_IMAGE_ID to image
                )
            }
    }
}