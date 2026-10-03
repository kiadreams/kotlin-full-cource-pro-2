package chapter11.users


fun main() {
    Display().show()
    Administrator(UsersRepository.getInstance("qwerty"))
        .work()
}