package co.pvphub.procotollib.extensions

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.NetworkMarker
import com.comphenix.protocol.events.PacketContainer
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

fun PacketContainer.send(player: Player, marker: NetworkMarker? = null, filters: Boolean = true) {
    if (marker != null) {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, this, marker, filters)
    } else {
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, this, filters)
    }
}

fun PacketContainer.broadcast() {
    ProtocolLibrary.getProtocolManager().broadcastServerPacket(this)
}

fun PacketContainer.broadcast(entity: Entity, includeTracker: Boolean = true) {
    ProtocolLibrary.getProtocolManager().broadcastServerPacket(this, entity, includeTracker)
}

fun PacketContainer.broadcast(location: Location, maxObserverDistance: Int) {
    ProtocolLibrary.getProtocolManager().broadcastServerPacket(this, location, maxObserverDistance)
}
