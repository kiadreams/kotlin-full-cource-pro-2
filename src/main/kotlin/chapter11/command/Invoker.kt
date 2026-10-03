package chapter11.command

interface Invoker<T : Command> {

    fun addCommand(command: T)
}