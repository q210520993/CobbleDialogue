package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer

fun interface DialogueRootSelector {
    fun select(dialoguer: Dialoguer): String
}

open class SimpleDialogueTree(private val rootid: DialogueRootSelector, val nodes: List<DialogueNode>) : DialogueTree {
    override fun getRootNode(dialoguer: Dialoguer): DialogueNode? {
        val id = rootid.select(dialoguer)
        return nodes.first { it.id == id }
    }

    override fun getNodeById(id: String): DialogueNode? {
        return nodes.first { it.id == id }
    }
}