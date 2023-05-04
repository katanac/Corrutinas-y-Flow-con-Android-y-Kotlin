package com.example.corrutinas

import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.random.Random

private const val SEPARATOR = "=============="

fun main() {


    //  lambda()
    // threads()
    // threadsLambda()
    //threadVsCoroutines()
    sequences()
}

fun sequences() {
    newTopic("Sequences")
    getDataBySequence().forEach {
        println(" grados : $it ")
    }
}

fun getDataBySequence(): Sequence<Float> {
    return sequence {
        (1..5).forEach {
            println("procesando data")
            Thread.sleep(someTime())
            yield(20 + it + Random.nextFloat())

        }
    }
}

fun threadVsCoroutines() {
    newTopic("Threads Vs Coroutines")

    /*
     newTopic("Break thread")
    (1..1_000_000).forEach {
         thread {
             Thread.sleep(someTime())
             println("*")
         }
     }*/

    runBlocking {
        (1..1_000_000).forEach {
            launch {
                delay(someTime())
                print("*")
            }
        }
    }
}


fun threadsLambda() {
    newTopic("Threads + Lambda")
    multiThreadLamda(2, 3) {
        println("Thread + Lambda $it")
    }

}

fun multiThreadLamda(x: Int, y: Int, callback: (result: Int) -> Unit) {
    var result = 0

    thread {
        Thread.sleep(someTime())
        result = x * y
        callback(result)
    }


}

fun threads() {
    newTopic("Threads")
    println("thread ${multiThread(3, 2)}")
}

fun multiThread(x: Int, y: Int): Int {
    var result = 0

    thread {
        Thread.sleep(someTime())
        result = x * y
    }
    Thread.sleep(2100)
    return result
}

fun someTime(): Long = Random.nextLong(500, 2000)

fun lambda() {
    newTopic("Lambdas")

    println(multi(2, 3))
    multiLambda(3, 2) { result -> println(result) }
}

fun multiLambda(x: Int, y: Int, callback: (result: Int) -> Unit) {
    callback(x * y)
}

fun multi(x: Int, y: Int): Int {
    return x * y
}

fun newTopic(topic: String) {
    println("\n$SEPARATOR $topic $SEPARATOR \n")
}

