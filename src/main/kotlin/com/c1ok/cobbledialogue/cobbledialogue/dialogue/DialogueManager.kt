package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueSession
import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object DialogueManager {

    val activeSessions = ConcurrentHashMap<UUID, DialogueSession>()

    fun startSession(dialoguer: Dialoguer, tree: DialogueTree) {
        val rootNode = tree.getRootNode(dialoguer) ?: return
        activeSessions[dialoguer.id] = DialogueSession(dialoguer, tree, rootNode)
        dialoguer.showDialogue(rootNode) // 触发终端渲染
    }

    fun selectOption(dialoguerId: UUID, optionIndex: Int) {
        val session = activeSessions[dialoguerId] ?: return
        val node = session.currentNode
        if (optionIndex < 0 || optionIndex >= node.options.size) return
        val result = node.options[optionIndex].action.apply(session)

        when (result) {
            is DialogueActionResult.Advance -> {
                // 查找并更新下一个节点
                val nextNode = session.tree.getNodeById(result.nextNodeId)
                if (nextNode != null) {
                    session.currentNode = nextNode
                    session.dialoguer.showDialogue(session.currentNode)
                } else {
                    session.dialoguer.closeDialogue()
                }
            }
            is DialogueActionResult.Execute -> result.command.run()
            is DialogueActionResult.Exit -> exitDialogue(session.dialoguer)
        }
    }

    fun exitDialogue(dialoguer: Dialoguer) {
        activeSessions.remove(dialoguer.id)
        dialoguer.closeDialogue()
    }


}