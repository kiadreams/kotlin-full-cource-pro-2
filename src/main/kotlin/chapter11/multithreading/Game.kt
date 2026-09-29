package chapter11.multithreading

import kotlin.concurrent.thread
import kotlin.random.Random


fun main() {
    print("Enter number from 1 to 1_000_000_000: ")
    val number = readln().toInt().takeIf { it in 1..1_000_000_000 } ?: return
    var guessed = false
    var count = 0L
    thread {
        var seconds = 0
        while (!guessed) {
            println(++seconds)
            Thread.sleep(1000)
        }
    }
    while (!guessed) {
        Random.nextInt(1_000_000_001)
            .also { count++ }
            .takeIf { it == number }
            ?.let {
                println("Number is: $it; Attempts: $count")
                guessed = true
            }
    }
}