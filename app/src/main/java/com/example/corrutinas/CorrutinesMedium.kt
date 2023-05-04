package com.example.corrutinas

import kotlinx.coroutines.*

fun main() {
    //   dispachers()
    // nested()
    changeWithContext()
}

fun changeWithContext() = runBlocking {
    startMsg()
    println("with context")
    withContext(newSingleThreadContext("curso and")){
        startMsg()
        delay(someTime())
        println("with context curso and")
        endMsg()
    }


    withContext(Dispatchers.IO){
        startMsg()
        delay(someTime())
        println("peticion al sever")
        endMsg()
    }

    endMsg()
}

fun nested() = runBlocking {
    newTopic("anidar corroutines")

    val job = launch {
        startMsg()
        launch {
            startMsg()
            delay(someTime())
            println("otra tarea")
            endMsg()
        }

        val job2 = launch(Dispatchers.IO) {
            startMsg()

            launch(newSingleThreadContext("curso and anidado")) {
                startMsg()

                println("personalizado")
                endMsg()
            }

            delay(someTime())
            println("otra tarea IO server")
            endMsg()
        }

        delay(someTime() / 3)
        job2.cancel()

        println("job 2 cancel..  status: ${job2.isCancelled}")

        var sum = 0
        (1..100).forEach {
            sum += it
            delay(someTime() / 100)
        }

        println("suma = $sum")

        endMsg()
    }

    delay(someTime() / 2)

    // job.cancel()

    // println("job cancelaso...  status: ${job.isCancelled}")
}

fun dispachers() = runBlocking {
    newTopic("dispachers")

    launch {
        startMsg()
        println("None")
        endMsg()
    }

    launch(Dispatchers.IO) {
        startMsg()
        println("IO")
        endMsg()
    }


    launch(Dispatchers.Unconfined) {
        startMsg()
        println("Unconfined")
        endMsg()
    }

//solo para android
    /*  launch(Dispatchers.Main) {
          startMsg()
          println("Main")
          endMsg()
      }

  */
    launch(Dispatchers.Default) {
        startMsg()
        println("Default")
        endMsg()
    }

    launch(newSingleThreadContext("curso Android")) {
        startMsg()
        println("Mi corroutina Personalizada con un dispacher")
        endMsg()
    }


    newSingleThreadContext("curso de flow y corrutines").use { contex ->
        launch(contex) {

            startMsg()
            println("Mi corroutina Personalizada con un dispacher 2")
            endMsg()
        }
    }

}

