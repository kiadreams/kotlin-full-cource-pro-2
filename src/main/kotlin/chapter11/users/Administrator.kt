package chapter11.users

import kotlin.system.exitProcess

class Administrator(private val repository: UsersRepository) {

    fun work() {
        while (true) {
            print("Enter an operation:")
            OperationType.entries
                .withIndex()
                .joinToString(",") { " ${it.index} - ${it.value}" }
                .let { print(it) }
                .also { print(": ") }
            val indexOfOperation = readln()
                .trim()
                .toIntOrNull()
                ?.takeIf { it in OperationType.entries.indices }
                ?: continue
            when (OperationType.entries[indexOfOperation]) {
                OperationType.EXIT -> {
                    repository.saveUsers()
                    println("Bye!")
                    exitProcess(0)
                }

                OperationType.ADD_USER -> addUser()
                OperationType.DELETE_USER -> deleteUser()
            }
        }
    }

    fun addUser() {
        print("Enter firstname: ")
        val name = readln().trim()
        print("Enter lastname: ")
        val lastName = readln().trim()
        print("Enter age: ")
        val age = readln().trim().toInt()
        repository.addUser(User(-1, name, lastName, age))
    }

    fun deleteUser() {
        print("Enter id: ")
        val id  = readln().trim().toInt()
        repository.deleteUser(id)
    }
}