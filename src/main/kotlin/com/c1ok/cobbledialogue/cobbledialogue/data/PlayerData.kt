package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueTree
import java.util.*

data class PlayerData(val uuid: UUID, var activeDialogue: ActiveDialogue? = null)

data class ActiveDialogue(
    val tree: DialogueTree,
    var currentNode: DialogueNode,
    val startTime: Long = System.currentTimeMillis()
)