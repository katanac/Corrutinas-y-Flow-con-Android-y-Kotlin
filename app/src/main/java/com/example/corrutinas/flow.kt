package com.example.corrutinas

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

fun main() {
    //  coldFlow()
    // cancelFlow()
    //  flowOperators()
    terminalFlowOperators()
}

fun terminalFlowOperators() = runBlocking {
    newTopic("Operadores terminales")
    newTopic("Operador List")
    val dataList = getDataByFlow()//.toList()
    println("list= $dataList")

    newTopic("Operador Single")
    val dataSingle = getDataByFlow()//.take(1).single()
    println("list= $dataSingle")

    newTopic("Operador first")
    val dataFirst = getDataByFlow()//.first()
    // println("list= $dataFirst")

    newTopic("Operador last")
    val dataLast = getDataByFlow()//.last()
    // println("list= $dataLast")


    newTopic("Reduce")
    val saving = getDataByFlow().reduce { accumulator, value ->
        println("Acumulador: $accumulator")
        println("value: $value")
        println("dataReduce: ${accumulator + value}")
        accumulator + value
    }
    println("list= $saving")

    newTopic("fold")

    val lastsaving = saving
    val totalSaving = getDataByFlow().fold(lastsaving) { accumulator, value ->
        println("Acumulador: $accumulator")
        println("value: $value")
        println("dataReduce: ${accumulator + value}")
        accumulator + value
    }

    println("total $totalSaving")

}

fun flowOperators() = runBlocking {
    newTopic("intermediarios")
    newTopic("Map")

    getDataByFlow().map {
        setFormat(it)
        setFormat(converCalsToFarhr(it), "F")
    }//.collect { println(it) }

    newTopic("Filter")

    getDataByFlow().filter {
        it < 23
    }.map {
        setFormat(it)
    }
    //.collect { println(it) }


    newTopic("transform")

    getDataByFlow().transform {
        emit(setFormat(it))
        emit(setFormat(converCalsToFarhr(it), "F"))
    }
    //.collect { println(it) }


    newTopic("take")

    getDataByFlow().take(3).map {
        setFormat(it)
    }.collect { println(it) }

}

fun converCalsToFarhr(temp: Float): Float = ((temp * 9) / 5) + 32

fun setFormat(temp: Float, degree: String = "C"): String =
    String.format(Locale.getDefault(), "%.1f° $degree", temp)

fun coldFlow() = runBlocking {
    newTopic("Flows are cold")

    val data = getDataByFlow()
    println("esperando")
    delay(someTime())
    data.collect { println("data:$it") }
}


fun cancelFlow() = runBlocking {
    newTopic("cancel flow")

    val job = launch { getDataByFlow().collect { println("data:$it") } }
    delay(someTime())
    job.cancel()
}