package co.pvphub.procotollib

import co.pvphub.procotollib.dsl.packet
import co.pvphub.procotollib.dsl.packetReceiving
import co.pvphub.procotollib.extensions.broadcast
import com.comphenix.protocol.PacketType
import com.comphenix.protocol.wrappers.WrappedChatComponent
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class test : JavaPlugin() {
    init {

        packetReceiving(this, type = arrayOf(PacketType.Play.Client.CHAT)) {
            val message = packet.strings.read(0)
            if (isMuted(player)) {
                println("[MUTED] ${player.name}: $message")
                isCancelled = true
            }
        }

        packet(PacketType.Play.Server.KICK_DISCONNECT) {
            chatComponents.write(0, WrappedChatComponent.fromText("Kicked because naughty!"))
        }.broadcast()

    }
}

fun isMuted(player: Player): Boolean = true