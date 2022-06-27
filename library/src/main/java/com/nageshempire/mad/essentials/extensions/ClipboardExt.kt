@file:JvmName("Extensions")
@file:JvmMultifileClass

package com.nageshempire.mad.essentials.extensions

import android.content.ClipData
import android.content.Context

/**
 * copy text to clipboard
 *
 * @param[text] to copy
 */
fun Context.copyText(text: CharSequence) {
    clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text))
}

/**
 * get text of clipboard list
 *
 * @return first object of primaryClip list
 */
fun Context.getTextFromClipboard(): String {
    val clip = clipboardManager.primaryClip
    return if (clip != null && clip.itemCount > 0) clip.getItemAt(0).coerceToText(this).toString() else ""
}