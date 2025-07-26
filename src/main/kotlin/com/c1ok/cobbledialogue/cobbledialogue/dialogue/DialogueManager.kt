package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import net.minecraft.server.level.ServerPlayer
import java.util.*

interface DialogueManager {

    fun tryDialogue(dialoguer: Dialoguer, dialogue: Dialogue): Boolean

    fun exitDialogue(player: ServerPlayer)

}