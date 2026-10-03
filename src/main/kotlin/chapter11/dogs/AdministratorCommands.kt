package chapter11.dogs

import chapter11.command.Command

sealed interface AdministratorCommands : Command {

    data class AddDog(
        val repository: DogRepository,
        val dog: Dog
    ) : AdministratorCommands {
        override fun execute() {
            repository.addDog(dog)
        }
    }

    data class DeleteDog(
        val repository: DogRepository,
        val id: Int
    ) : AdministratorCommands {
        override fun execute() {
            repository.deleteDog(id)
        }
    }

    data class SaveChanges(
        val repository: DogRepository
    ) : AdministratorCommands {
        override fun execute() {
            repository.saveChanges()
        }
    }
}