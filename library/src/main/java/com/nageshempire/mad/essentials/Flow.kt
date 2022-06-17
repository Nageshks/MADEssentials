package com.nageshempire.mad.essentials


import kotlinx.coroutines.flow.*


fun <T> Flow<T>.throttleFirst(periodMillis: Long = 500): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}