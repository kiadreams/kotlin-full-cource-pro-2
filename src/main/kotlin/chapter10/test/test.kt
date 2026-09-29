package chapter10.test

import chapter10.extensions.myApply

fun main() {
    mutableListOf<Int>().apply {
        while (true) {
            print("Enter number or 0 to exit: ")
            val number = readln().toInt().takeIf { it != 0 } ?: break
            add(number)
        }
        println("Max: ${max()}")
        println("Min: ${min()}")
    }.forEach { println(it) }
}

fun exampleApply() {
    mutableListOf<Int>().myApply {
        while (true) {
            print("Enter number or 0 to exit: ")
            val number = readln().toInt().takeIf { it != 0 } ?: break
            add(number)
        }
    }.forEach { println(it) }
}