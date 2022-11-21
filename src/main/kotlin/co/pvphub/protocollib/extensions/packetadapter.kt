package co.pvphub.protocollib.extensions

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketAdapter

fun PacketAdapter.unregister() {
    ProtocolLibrary.getProtocolManager().removePacketListener(this)
}