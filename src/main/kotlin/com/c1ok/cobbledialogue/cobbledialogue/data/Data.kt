package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueTree

data class DialogueSession(
    val dialoguer: Dialoguer,  // 对话终端
    val tree: DialogueTree,    // 对话树
    var currentNode: DialogueNode, // 当前节点
    val startTime: Long = System.currentTimeMillis()
)
