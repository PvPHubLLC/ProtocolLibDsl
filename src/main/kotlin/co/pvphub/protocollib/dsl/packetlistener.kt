package co.pvphub.protocollib.dsl

import co.pvphub.protocollib.DummyPacketAdapter
import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

fun packetListener(adapter: PacketAdapter) {
    ProtocolLibrary.getProtocolManager().addPacketListener(adapter)
}

fun packetReceiving(
    plugin: JavaPlugin,
    priority: ListenerPriority = ListenerPriority.HIGH,
    vararg type: PacketType,
    event: PacketEvent.() -> Unit
): PacketAdapter {
    val adapter = DummyPacketAdapter(plugin, priority, event, null, *type)
    packetListener(adapter)
    return adapter
}

fun packetSending(
    plugin: JavaPlugin,
    priority: ListenerPriority = ListenerPriority.HIGH,
    vararg type: PacketType,
    event: PacketEvent.() -> Unit
): PacketAdapter {
    val adapter = DummyPacketAdapter(plugin, priority, null, event, *type)
    packetListener(adapter)
    return adapter
}