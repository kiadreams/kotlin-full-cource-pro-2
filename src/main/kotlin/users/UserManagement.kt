package users


fun main() {
    val display = Display()
    display.show()
    UsersRepository.getInstance("qwerty").users.take(3).forEach(::println)
}