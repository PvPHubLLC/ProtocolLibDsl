package co.pvphub.protocollib.dsl

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer

inline fun packet(type: PacketType, forceDefaults: Boolean = false, packet: PacketContainer.() -> Unit) : PacketContainer {
    val p = ProtocolLibrary.getProtocolManager().createPacket(type, forceDefaults)
    packet(p)
    return p
}