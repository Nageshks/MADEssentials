package com.nageshempire.mad.essentials.util

import java.util.concurrent.atomic.AtomicInteger

object UID {
    private val id = AtomicInteger()
    fun getUID(): Int = id.getAndIncrement()
}