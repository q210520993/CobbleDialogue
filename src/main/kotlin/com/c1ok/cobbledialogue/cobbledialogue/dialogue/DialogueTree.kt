package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData

interface DialogueTree {
    fun getRootNode(playerData: PlayerData): DialogueNode
}