package co.pvphub.procotollib.extensions

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketAdapter

fun PacketAdapter.unregister() {
    ProtocolLibrary.getProtocolManager().removePacketListener(this)
}