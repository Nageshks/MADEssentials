package com.nageshempire.mad.essentials.reactive

import androidx.lifecycle.Observer

class UIEventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<UIEvent<T>> {
    override fun onChanged(event: UIEvent<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}