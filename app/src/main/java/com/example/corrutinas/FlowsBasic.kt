package com.example.corrutinas

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    newTopic("Flows")
    basicFlows()
}

fun basicFlows() = runBlocking {

    launch {
        getDataByFlow().collect {
            println("grados flow : $it")
        }
    }

    launch {
        (1..50).forEach{
            delay(someTime()/10)
            println("tarea 2")
        }
    }
}

fun getDataByFlow(): Flow<Float> {
    return flow {
        (1..5).forEach {
            println("procesando data")
            delay(someTime())
            emit(20 + it + Random.nextFloat())

        }
    }
}