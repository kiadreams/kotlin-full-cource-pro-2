package chapter11.users

import chapter11.command.Command

sealed interface AdministratorCommands : Command {

    data class AddUser(
        val repository: UsersRepository,
        val user: User
    ) : AdministratorCommands {

        override fun execute() {
            repository.addUser(user)
        }
    }

    data class DeleteUser(
        val repository: UsersRepository,
        val id: Int
    ) : AdministratorCommands {

        override fun execute() {
            repository.deleteUser(id)
        }
    }

    data class SaveChanges(
        val repository: UsersRepository
    ) : AdministratorCommands {

        override fun execute() {
            repository.saveChanges()
        }
    }
}