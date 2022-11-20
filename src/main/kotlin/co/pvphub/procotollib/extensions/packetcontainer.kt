package co.pvphub.procotollib.extensions

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.InternalStructure
import com.comphenix.protocol.events.NetworkMarker
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.reflect.EquivalentConverter
import com.comphenix.protocol.reflect.StructureModifier
import com.comphenix.protocol.wrappers.WrappedChatComponent
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

inline fun <reified T> PacketContainer.write(value: T, field: Int) {
    structures.withType<T>(T::class.java)?.write(field, value)
}
infix fun <T> PacketContainer.write(value: T) : Pair<PacketContainer, T> {
    return Pair(this, value)
}

inline infix fun <reified T> Pair<PacketContainer, T>.at(value: Int) = field(value)
inline infix fun <reified T> Pair<PacketContainer, T>.atField(value: Int) = field(value)
inline infix fun <reified T> Pair<PacketContainer, T>.field(value: Int) : T {
    first.structures.withType<T>(T::class.java)?.write(value, second)
    return second
}

infix fun Any.chatComponent(string: String) : WrappedChatComponent {
    return WrappedChatComponent.fromText(string)
}