package users

import kotlinx.serialization.json.Json
import java.io.File

class UsersRepository private constructor() {

    init {
        println("Creating repository")
    }

    private val file = File("Profiles.json")

    private val _users = loadUser()
    val users: List<User>
        get() = _users.toList()

    private fun loadUser(): MutableList<User> = Json.decodeFromString(file.readText().trim())

    companion object {
        private var instance: UsersRepository? = null

        fun getInstance(password: String): UsersRepository {
            val correctPassword = File("password_users.txt").readText().trim()
            if (password != correctPassword) throw IllegalArgumentException("Wrong password")
            if (instance == null) {
                instance = UsersRepository()
            }
            return instance!!
        }
    }

}