package co.pvphub.protocollib.extensions

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.NetworkMarker
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.reflect.FieldAccessException
import com.comphenix.protocol.reflect.StructureModifier
import com.comphenix.protocol.wrappers.*
import com.comphenix.protocol.wrappers.nbt.NbtBase
import org.bukkit.*
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MerchantRecipe
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import kotlin.Pair

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

infix operator fun <T> StructureModifier<T>.plusAssign(value: Pair<T, Int>) {
    write(value.second, value.first)
}

infix operator fun <T> StructureModifier<T>.plus(value: T) : Pair<StructureModifier<T>, T> {
    return Pair(this, value)
}

infix fun <T> Pair<StructureModifier<T>, T>.field(index: Int) : T {
    first.write(index, second)
    return second
}

infix fun <T> Pair<StructureModifier<T>, T>.at(index: Int) = field(index)
infix fun <T> Pair<StructureModifier<T>, T>.fieldOf(index: Int) = field(index)
infix fun <T> Pair<StructureModifier<T>, T>.atField(index: Int) = field(index)

//inline infix fun <reified T> Pair<PacketContainer, T>.at(value: Int) = field(value)
//inline infix fun <reified T> Pair<PacketContainer, T>.atField(value: Int) = field(value)
//inline infix fun <reified T> Pair<PacketContainer, T>.field(value: Int): T {
//    println("before error")
//    try {
//        first.getSpecificModifier(T::class.java)?.writeSafely(value, second)
//    } catch (e: FieldAccessException) {
//        when (T::class.java) {
//            ItemStack::class.java -> first.itemModifier
//            Array<ItemStack>::class.java -> first.itemArrayModifier
//            WorldType::class.java -> first.worldTypeModifier
//            WrappedDataWatcher::class.java -> first.dataWatcherModifier
//            EntityType::class.java -> first.entityTypeModifier
//            ChunkPosition::class.java -> first.positionModifier
//            BlockPosition::class.java -> first.blockPositionModifier
//            ChunkCoordIntPair::class.java -> first.chunkCoordIntPairs
//            Vector::class.java -> first.vectors
//            Material::class.java -> first.blocks
//            WrappedGameProfile::class.java -> first.gameProfiles
//            WrappedBlockData::class.java -> first.blockData
//            Array<WrappedBlockData>::class.java -> first.blockDataArrays
//            Array<MultiBlockChangeInfo>::class.java -> first.multiBlockChangeInfoArrays
//            WrappedChatComponent::class.java -> first.chatComponents
//            Array<WrappedChatComponent>::class.java -> first.chatComponentArrays
//            WrappedServerPing::class.java -> first.serverPings
//            PacketType.Protocol::class.java -> first.protocols
//            EnumWrappers.ClientCommand::class.java -> first.clientCommands
//            EnumWrappers.ChatVisibility::class.java -> first.chatVisibilities
//            EnumWrappers.Difficulty::class.java -> first.difficulties
//            EnumWrappers.EntityUseAction::class.java -> first.entityUseActions
//            WrappedEnumEntityUseAction::class.java -> first.enumEntityUseActions
//            EnumWrappers.NativeGameMode::class.java -> first.gameModes
//            EnumWrappers.ResourcePackStatus::class.java -> first.resourcePackStatus
//            EnumWrappers.PlayerInfoAction::class.java -> first.playerInfoAction
//            EnumWrappers.TitleAction::class.java -> first.titleActions
//            EnumWrappers.WorldBorderAction::class.java -> first.worldBorderActions
//            EnumWrappers.CombatEventType::class.java -> first.combatEvents
//            EnumWrappers.PlayerDigType::class.java -> first.playerDigTypes
//            EnumWrappers.PlayerAction::class.java -> first.playerActions
//            EnumWrappers.ScoreboardAction::class.java -> first.scoreboardActions
//            EnumWrappers.Particle::class.java -> first.particles
//            PotionEffectType::class.java -> first.effectTypes
//            EnumWrappers.SoundCategory::class.java -> first.soundCategories
//            Sound::class.java -> first.soundEffects
//            EnumWrappers.ItemSlot::class.java -> first.itemSlots
//            EnumWrappers.Hand::class.java -> first.hands
//            EnumWrappers.Direction::class.java -> first.directions
//            EnumWrappers.ChatType::class.java -> first.chatTypes
//            MinecraftKey::class.java -> first.minecraftKeys
//            World::class.java -> first.dimensionTypes
//            MovingObjectPositionBlock::class.java -> first.movingBlockPositions
//            BlockPosition::class.java -> first.blockPositionModifier
//        }
//    }
//    println("no error")
//    return second
//}

fun String.component(): WrappedChatComponent {
    return WrappedChatComponent.fromText(this)
}