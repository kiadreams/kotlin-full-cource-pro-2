package org.example.test

import java.io.File
import kotlinx.serialization.json.Json

fun main() {
//    val itemsFile = File("items.json")
//    writeItemsToFile(file)
//    val items = readItemsFromFile(file)
//    items.forEach { println(it) }

    val booksFile = File("books.json")
//    writeBooksToFile(booksFile)
    val content = booksFile.readText().trim()
    val books = Json.decodeFromString<List<Book>>(content)
    books.forEach { println(it) }

}

fun readItemsFromFile(file: File): List<Item> {
    val content = file.readText().trim()
    return Json.decodeFromString<List<Item>>(content)
}

fun readBooksFromFile(file: File): List<Book> {
    val content = file.readText().trim()
    return Json.decodeFromString<List<Book>>(content)
}

fun writeItemsToFile(file: File) {
    val items = mutableListOf<Item>()
    while (true) {
        print("Enter id or 0 to exit: ")
        val id = readln().toInt()
        if (id == 0) break
        print("Enter name: ")
        val name = readln()
        val item = Item(id, name)
        items.add(item)
    }
    val content = Json.encodeToString(items)
    file.writeText(content)
}

fun writeBooksToFile(file: File) {
    val books = mutableListOf<Book>()
    while (true) {
        print("Enter title or 0 to exit: ")
        val title = readln()
        if (title == "0") break
        print("Enter author: ")
        val author = readln()
        print("Enter year: ")
        val year = readln().toInt()
        val book = Book(title, author, year)
        books.add(book)
    }
    val content = Json.encodeToString(books)
    file.writeText(content)
}