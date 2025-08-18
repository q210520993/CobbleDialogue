package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ComponentTextUnit
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object DialogueManager {
    val startNode = object : DialogueNode {
        override val id = "start"
        override val text: Text<TextUnit> = PhaseDialogueText(Arrays.asList(
            ComponentTextUnit(Component.literal("你好！需要什么帮助吗？")),
            ComponentTextUnit(Component.literal("not really?")),
            ComponentTextUnit(Component.literal("no way!"))
        ))
        override val options = listOf(
            DialogueOption("option1", "关于任务...") {
                DialogueActionResult.Advance("quest_info")
            },
            DialogueOption("option2", "交易") {
                DialogueActionResult.Execute { /*TODO OpenTrade*/ }
            },
            DialogueOption("option3","再见") {
                DialogueActionResult.Exit
            }
        )
        override val result: DialogueActionResult
            get() = TODO("Not yet implemented")
    }

    val activeSessions = ConcurrentHashMap<UUID, DialogueSession>()
    val dialogueStorage = DialogueStorage.NodeStorage.addDialogue(startNode)

    fun startSession(dialoguer: Dialoguer, tree: DialogueTree) {
        val rootNode = tree.getRootNode(dialoguer) ?: return
        activeSessions[dialoguer.id] = DialogueSession(dialoguer, tree, rootNode)
        dialoguer.showDialogue(rootNode) // 触发终端渲染
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