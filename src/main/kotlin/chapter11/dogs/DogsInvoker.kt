package chapter11.dogs

import chapter11.command.Command
import chapter11.command.Invoker
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

object DogsInvoker : Invoker<AdministratorCommands> {

    private val commands = LinkedBlockingQueue<Command>()

    init {
        thread {
            while (true) {
                println("Waiting for commands...")
                val command = commands.take()
                println("Executing chapter11.command: $command...")
                command.execute()
                println("Done chapter11.command: $command!")
            }
        }
    }

    override fun addCommand(command: AdministratorCommands) {
        commands.add(command)
    }
}