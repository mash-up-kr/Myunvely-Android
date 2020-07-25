package com.mashup.android.base.extension

import android.app.Activity
import com.mashup.android.base.extension.startFileBrowserIntent
import com.mashup.android.base.extension.startFileViewerIntent
import com.mashup.android.base.extension.startGalleryIntent

fun Activity.startGalleryIntent(
    tag: String,
    requestCode: Int,
    forceChooser: Boolean = false,
    chooserTitle: String? = null
) {
    startGalleryIntent(tag, requestCode, forceChooser, chooserTitle, ::startActivityForResult)
}

fun Activity.startFileBrowserIntent(
    tag: String,
    requestCode: Int,
    forceChooser: Boolean = false,
    title: String? = null
) {
    startFileBrowserIntent(tag, requestCode, forceChooser, title, ::startActivityForResult)
}

fun Activity.startFileViewerIntent(
    tag: String,
    url: String,
    mimeType: String,
    forceChooser: Boolean = false,
    title: String? = null
) {
    startFileViewerIntent(tag, url, mimeType, forceChooser, title, ::startActivity)
}
