package co.pvphub.protocollib.example

import co.pvphub.protocollib.dsl.packet
import co.pvphub.protocollib.extensions.*
import com.comphenix.protocol.PacketType
import com.comphenix.protocol.PacketType.Play
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ExamplePlugin : JavaPlugin(), CommandExecutor {

    override fun onEnable() {
        Bukkit.getPluginCommand("packetdsl")?.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        packet(Play.Server.CHAT) {
            chatComponents + "This is a packet based chat component!".component() field 0
        }.send(sender)

        return false
    }

}