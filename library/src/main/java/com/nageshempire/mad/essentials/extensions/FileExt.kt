@file:JvmName("Extensions")
@file:JvmMultifileClass
package com.nageshempire.mad.essentials.extensions

import android.content.Context
import android.webkit.MimeTypeMap
import java.io.File


fun Context.getMimeType(file: File): String {
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(
            file.name.getExtension()
                .substring(1)
    ) ?: "application/octet-stream"
}

private fun String?.getExtension(): String {
    if (this == null) return ""
    val dot = this.lastIndexOf(".")
    return if (dot >= 0) {
        this.substring(dot)
    } else {
        ""
    }
}

fun String.getFilenameFromPath() = substring(lastIndexOf("/") + 1)
