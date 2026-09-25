package org.example.profile

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val file = File("profiles.json")
    val people = loadProfiles(file)
    people.forEach { println(it) }

}

fun loadProfiles(file: File): List<Person> {
    val content = file.readText()
    return Json.decodeFromString(content)
}