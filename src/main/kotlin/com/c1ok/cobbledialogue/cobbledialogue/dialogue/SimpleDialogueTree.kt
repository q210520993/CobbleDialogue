package com.c1ok.cobbledialogue.cobbledialogue.dialogue

fun interface DialogueRootSelector {
    fun select(dialoguer: Dialoguer): String
}

open class SimpleDialogueTree(private val rootid: DialogueRootSelector, val nodes: List<DialogueNode>) : DialogueTree {
    override fun getRootNode(dialoguer: Dialoguer): DialogueNode? {
        val id = rootid.select(dialoguer)
        return nodes.first { it.id == id }
    }

    override fun getNodeById(id: String): DialogueNode? {
        return kotlin.runCatching {
            return@runCatching nodes.first { it.id == id }
        }.getOrNull()
    }
}

object TestS : DialogueRootSelector {
    override fun select(dialoguer: Dialoguer): String {
        return "start_argue"
    }
}
