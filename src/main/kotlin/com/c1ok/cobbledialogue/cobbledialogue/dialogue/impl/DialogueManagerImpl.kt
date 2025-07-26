package com.c1ok.cobbledialogue.cobbledialogue.dialogue.impl

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialogue
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueManager
import net.minecraft.server.level.ServerPlayer
import java.util.*

object DialogueManagerImpl : DialogueManager {
    override fun tryDialogue(player: ServerPlayer, dialogue: Dialogue): Boolean {
        println()
        return false
    }
    override fun exitDialogue(player: ServerPlayer) {

    }

}