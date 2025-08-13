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

object TestDialogue : SimpleDialogueTree(TestS, listOf(
    town1_mayor_start_argue,
    town1_village_chief_opening,
    player_request,
    village_chief_response_1,
    player_rebuttal_1,
    village_chief_counter_2
))