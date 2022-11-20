package co.pvphub.procotollib.example

import co.pvphub.procotollib.dsl.packet
import co.pvphub.procotollib.extensions.at
import co.pvphub.procotollib.extensions.chatComponent
import co.pvphub.procotollib.extensions.field
import co.pvphub.procotollib.extensions.write
import com.comphenix.protocol.PacketType

object Examples {

    fun foo() {
        packet(PacketType.Play.Server.KICK_DISCONNECT) {
            this write chatComponent("something") at 0
        }
    }

}