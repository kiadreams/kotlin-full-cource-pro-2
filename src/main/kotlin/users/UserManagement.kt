package users


fun main() {
    val displayUser = DisplayUser()
    displayUser.show()
    UsersRepository.getInstance("qwerty").users.take(3).forEach(::println)
}