package com.nageshempire.mad.essentials.extensions

import android.widget.EditText

fun EditText.getString(): String? = if (text != null) text.toString() else null