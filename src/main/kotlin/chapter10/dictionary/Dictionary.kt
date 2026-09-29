package chapter10.dictionary

import kotlinx.serialization.json.Json
import java.io.File
import kotlin.time.measureTime


fun main() {
    val file = File("../chapter10.dictionary.json")
    val content = file.readText().trim()
    val dictionaryMap = Json.decodeFromString<Map<String, String>>(content)
    showDescription(dictionaryMap)
}

fun showDescription(dictionary: Map<String, String>) {
    while (true) {
        print("Enter the word or 0 to exit: ")
        val input = readln().lowercase()
        if (input == "0") break
        val time = measureTime {
            dictionary[input]?.let { println(it) } ?: println("Not found")
        }
        println(time)
    }
}