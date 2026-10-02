package dogs

import kotlin.system.exitProcess


class Administrator(private val repository: DogRepository) {

    fun work() {
        while (true) {
            println("Enter type operation:")
            OperationType.entries
                .joinToString(",") { " ${it.ordinal} - ${it.title}" }
                .let(::print)
                .also { print(": ") }
            val operationType = readln().trim()
                .toIntOrNull()
                ?.takeIf { it in OperationType.entries.indices }
                ?.let { OperationType.entries[it] }
                ?: continue
            when (operationType) {
                OperationType.EXIT -> exitApp()
                OperationType.ADD_DOG -> addDog()
                OperationType.DELETE_DOG -> deleteDog()
            }

        }
    }

    private fun exitApp() {
        repository.saveChanges()
        println("Bye!")
        exitProcess(0)
    }

    private fun addDog() {
        print("Enter dog's name: ")
        val name = readln().trim()
        print("Enter dog's weight: ")
        val weight = readln().trim().toDoubleOrNull() ?: 10.0
        print("Enter dog's breed:")
        Breed.entries.joinToString(",") { " ${it.ordinal} - ${it.name}" }
            .let(::print)
            .also { print(": ") }
        val bread = readln().trim().toIntOrNull()
            ?.takeIf { it in Breed.entries.indices }
            ?.let { Breed.entries[it] }
            ?: Breed.BULLDOG
        DogsInvoker.addCommand {
            repository.addDog(Dog(-1, name, bread, weight))
        }
    }

    private fun deleteDog() {
        print("Enter id of dog: ")
        readln().trim().toIntOrNull()
            ?.let {
                DogsInvoker.addCommand { repository.deleteDog(it) }
            }
    }
}