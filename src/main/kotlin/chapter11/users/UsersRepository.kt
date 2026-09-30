package chapter11.users

import chapter11.observer.Observer
import kotlinx.serialization.json.Json
import java.io.File

class UsersRepository private constructor() {

    init {
        println("Creating repository")
    }

    private val file = File("users.json")

    private val observers = mutableListOf<Observer<List<User>>>()

    private val _users = loadUser()
    val users: List<User>
        get() = _users.toList()

    private fun loadUser(): MutableList<User> {
        if (!file.exists() || file.readText().isBlank()) return mutableListOf()
        return Json.decodeFromString<MutableList<User>>(file.readText().trim())
    }

    fun addOnUsersChangeListener(observer: Observer<List<User>>) {
        observers.add(observer)
        observer.onChange(users)
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

    fun saveChanges() {
        Json.encodeToString(_users).let { file.writeText(it) }
    }

    fun addUser(user: User) {
        _users.maxOfOrNull { it.id }
            ?.let { _users.add(user.copy(id = it + 1)) }
            ?: _users.add(user.copy(id = 1))
        notifyObservers()
    }

    fun deleteUser(id: Int) {
        _users.removeIf { it.id == id }
            .also { if (it) notifyObservers() }
    }

    private fun notifyObservers() {
        observers.forEach { it.onChange(users) }
    }
}