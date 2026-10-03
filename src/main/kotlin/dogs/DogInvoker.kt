package dogs

import command.Command
import command.Invoker
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

object DogInvoker : Invoker<AdministratorCommands> {

    private val commands = LinkedBlockingQueue<Command>()

    init {
        thread {
            while (true) {
                println("Waiting for commands...")
                val command = commands.take()
                println("Executing command: $command...")
                command.execute()
                println("Done command: $command!")
            }
        }
    }

    override fun addCommand(command: AdministratorCommands) {
        println("New command: $command")
        commands.add(command)
    }
}