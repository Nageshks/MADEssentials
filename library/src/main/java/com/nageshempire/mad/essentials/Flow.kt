package com.nageshempire.mad.essentials


import kotlinx.coroutines.flow.*

/** Credit https://proandroiddev.com/from-rxjava-to-kotlin-flow-throttling-ed1778847619#:~:text=In%20Kotlin%20Flow%20though%20there%20is%20no%20such,this:%20fun%20%3CT%3E%20Flow%3CT%3E.throttleFirstJava%20(periodMillis:%20Long):%20Flow%3CT%3E%20%7B */
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