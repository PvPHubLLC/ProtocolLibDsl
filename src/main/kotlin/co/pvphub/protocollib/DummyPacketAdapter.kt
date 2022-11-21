package co.pvphub.protocollib

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

class DummyPacketAdapter(
    plugin: JavaPlugin,
    priority: ListenerPriority,
    val receive: (PacketEvent.() -> Unit)? = null,
    val sending: (PacketEvent.() -> Unit)? = null,
    vararg type: PacketType,
) : PacketAdapter(plugin, priority, *type) {

    override fun onPacketReceiving(event: PacketEvent) {
        receive?.invoke(event)
    }

    override fun onPacketSending(event: PacketEvent) {
        sending?.invoke(event)
    }

}