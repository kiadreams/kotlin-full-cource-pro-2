package users

import observer.Observer
import kotlinx.serialization.json.Json
import observer.Observable
import java.io.File

class UsersRepository private constructor() : Observable<List<User>> {

    init {
        println("Creating repository")
    }

    private val file = File("users.json")

    private val _observers = mutableListOf<Observer<List<User>>>()
    override val observers: List<Observer<List<User>>>
        get() = _observers.toList()

    private val _users = loadUser()
    override val currentValue: List<User>
        get() = _users.toList()

    private fun loadUser(): MutableList<User> {
        if (!file.exists() || file.readText().isBlank()) return mutableListOf()
        return Json.decodeFromString<MutableList<User>>(file.readText().trim())
    }

    fun saveChanges() {
        Json.encodeToString(_users).let { file.writeText(it) }
    }

    override fun registerObserver(observer: Observer<List<User>>) {
        _observers.add(observer)
        observer.onChange(currentValue)
    }

    fun addOnUsersChangeListener(observer: Observer<List<User>>) {
        registerObserver(observer)
    }

    override fun unregisterObserver(observer: Observer<List<User>>) {
        _observers.remove(observer)
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

}