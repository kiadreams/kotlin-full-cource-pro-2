package chapter11.users

import kotlinx.serialization.json.Json
import java.io.File

class UsersRepository private constructor() {

    init {
        println("Creating repository")
    }

    private val file = File("users.json")

    private val _users = loadUser()
    val users: List<User>
        get() = _users.toList()

    private fun loadUser(): MutableList<User> {
        if (!file.exists() || file.readText().isBlank()) return mutableListOf()
        return Json.decodeFromString<MutableList<User>>(file.readText().trim())
    }


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

    fun saveUsers() {
        Json.encodeToString(_users).let { file.writeText(it) }
    }

    fun addUser(user: User) {
        _users.maxOfOrNull { it.id + 1 }
            ?.let { user.copy(id = it) }
            ?.also { _users.add(it) }
            ?: _users.add(user.copy(id = 1))
    }

    fun deleteUser(id: Int) {
        _users.removeIf { it.id == id }
    }

}