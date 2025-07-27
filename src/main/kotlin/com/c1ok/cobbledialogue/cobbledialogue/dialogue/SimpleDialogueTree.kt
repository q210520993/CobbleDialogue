package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData


fun interface DialogueRootSelector {
    fun select(playerData: PlayerData): String
}

open class SimpleDialogueTree(val rootSelector: DialogueRootSelector, val nodes: List<DialogueNode>) : DialogueTree {
    override fun getRootNode(playerData: PlayerData): DialogueNode {
        val rootId = rootSelector.select(playerData)
        return nodes.find { it.id == rootId } ?: throw IllegalStateException("root node not found")
    }
}