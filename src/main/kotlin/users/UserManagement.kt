package users


fun main() {
    Display().show()
    Display().show()
    Administrator(UsersRepository.getInstance("qwerty"))
        .work()
}