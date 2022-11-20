package co.pvphub.procotollib.extensions

import com.comphenix.protocol.events.NetworkMarker
import com.comphenix.protocol.events.PacketContainer
import org.bukkit.entity.Player

fun Player.sendPacket(packet: PacketContainer, marker: NetworkMarker? = null, filters: Boolean = true) {
    packet.send(this, marker, filters)
}

fun List<Player>.sendPacket(packet: PacketContainer, marker: NetworkMarker? = null, filters: Boolean = true) {
    forEach { packet.send(it, marker, filters) }
}