package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes.TownOneArgue
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes.TownOneZhang
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ComponentTextUnit
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object DialogueManager {

    val trees = ConcurrentHashMap<String, DialogueTree>()

    val activeSessions = ConcurrentHashMap<UUID, DialogueSession>()

    fun startSession(dialoguer: Dialoguer, tree: DialogueTree) {
        val rootNode = tree.getRootNode(dialoguer) ?: return
        if (activeSessions.containsKey(dialoguer.id)) {
            return
        }
        activeSessions[dialoguer.id] = DialogueSession(dialoguer, tree, rootNode)
        dialoguer.showDialogue(rootNode) // 触发终端渲染
    }

    fun registerTree(id: String, tree: DialogueTree) {
        trees[id] = tree
    }

    fun getTree(id: String): DialogueTree? {
        return trees[id]
    }

    fun registerAll() {
        registerTree("zhang", TownOneZhang.tree)
        registerTree("argue", TownOneArgue.tree)
    }

    fun selectOption(dialoguerId: UUID, optionIndex: String) {
        val session = activeSessions[dialoguerId] ?: return
        val node = session.currentNode
        val option = node.getOption(optionIndex) ?: return
        val result = option.action.apply(session)

        when (result) {
            is DialogueActionResult.Advance -> {
                // 查找并更新下一个节点
                val nextNode = session.tree.getNodeById(result.nextNodeId)
                if (nextNode != null) {
                    session.currentNode = nextNode
                    session.dialoguer.showDialogue(session.currentNode)
                } else {
                    exitDialogue(session.dialoguer)
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