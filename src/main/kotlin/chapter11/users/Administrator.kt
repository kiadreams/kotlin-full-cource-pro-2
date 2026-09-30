package chapter11.users

import kotlin.system.exitProcess

class Administrator(private val repository: UsersRepository) {

    fun work() {
        while (true) {
            println("Enter an operation:")
            OperationType.entries
                .joinToString(",") { " ${it.ordinal} - ${it.title}" }
                .let { print(it) }
                .also { print(": ") }
            val operationType = readln().trim()
                .toIntOrNull()
                ?.takeIf { it in OperationType.entries.indices }
                ?.let { OperationType.entries[it] }
                ?: continue
            when (operationType) {
                OperationType.EXIT -> exitApp()
                OperationType.ADD_USER -> addUser()
                OperationType.DELETE_USER -> deleteUser()
            }
        }
    }

    private fun exitApp() {
        repository.saveChanges()
        println("Bye!")
        exitProcess(0)
    }

    private fun addUser() {
        print("Enter firstname: ")
        val name = readln().trim()
        print("Enter lastname: ")
        val lastName = readln().trim()
        print("Enter age: ")
        val age = readln().trim().toIntOrNull() ?: 23
        repository.addUser(User(-1, name, lastName, age))
    }

    private fun deleteUser() {
        print("Enter id: ")
        val id  = readln().trim().toIntOrNull()
            ?.let { repository.deleteUser(it) }
    }
}