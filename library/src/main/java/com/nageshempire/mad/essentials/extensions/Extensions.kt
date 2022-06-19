package com.nageshempire.mad.essentials.extensions

import android.net.Uri

fun Int.toResourceUri(packageName: String): Uri {
    return Uri.parse("android.resource://$packageName/$this")
}