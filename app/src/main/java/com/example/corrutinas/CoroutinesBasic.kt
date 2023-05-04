package com.example.corrutinas

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() {
    // globalScope()
    //   suspendFuntion()
    contructores()
    readLine()
}

fun contructores() {
    newTopic("Constructores con corrutines")
    // cRunBloaking()
    //cLaunch()
    //cAsyn()
    //job()
    //  deferred()
    produce()
}

fun produce() = runBlocking {
    newTopic("produce")
    val names = produceNames()
    names.consumeEach { println(it) }
}

fun CoroutineScope.produceNames(): ReceiveChannel<String> = produce {
    (1..5).forEach { send("name$it") }
}

fun deferred() {
    runBlocking {
        newTopic("job")
        val deferred = async {
            startMsg()
            delay(2_100)
            println("-deferred..")
            endMsg()
            multi(5, 3)
        }

        println("Deferred: $deferred")
        println("Deferred.await: ${deferred.await()}")
    }

}

fun job() {
    runBlocking {
        newTopic("job")
        val job = launch {
            startMsg()
            delay(2_100)
            println("Job..")
            endMsg()
        }

        println("job: $job")
        println("stateActive: ${job.isActive}")
        println("stateCancelled: ${job.isCancelled}")
        println("stateCompleted: ${job.isCompleted}")

        println("Job cancel")
        job.cancel()

        println("stateActive: ${job.isActive}")
        println("stateCancelled: ${job.isCancelled}")
        println("stateCompleted: ${job.isCompleted}")
    }


}

fun cAsyn() {
    runBlocking {
        newTopic("Async")
        val result = async {
            startMsg()
            delay(someTime())
            println("Async..")
            endMsg()

            1
        }

        println(result.await())
    }

}

fun cLaunch() {

    runBlocking {
        newTopic("Launch")
        launch {
            startMsg()
            delay(someTime())
            println("Launch..")
            endMsg()
        }

    }
}

fun cRunBloaking() {
    newTopic("RunBloaking")
    runBlocking {
        startMsg()
        delay(someTime())
        println("run bloaking corrutine")
        endMsg()
    }
}

fun suspendFuntion() {
    newTopic("SUSPEND")
    Thread.sleep(someTime())
    //delay(someTime())
    GlobalScope.launch { delay(someTime()) }
}

fun globalScope() {
    newTopic("Global Scope")
    GlobalScope.launch {
        startMsg()
        delay(someTime())
        println(" my firts coroutine")
        endMsg()
    }
}

fun startMsg() {
    println("Comenzando corrutina -${Thread.currentThread().name}-")
}

fun endMsg() {
    println("Finalizando corrutina -${Thread.currentThread().name}-")
}
