package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.ActiveDialogue
import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import com.c1ok.cobbledialogue.cobbledialogue.data.impl.DataManagerImpl
import net.minecraft.server.level.ServerPlayer

fun ServerPlayer.data(): PlayerData? {
    return DataManagerImpl.datas[this.uuid]
}

object DialogueManager {
    fun startDialogue(player: ServerPlayer, tree: DialogueTree) {
        val data = player.data() ?: return
        val node = tree.getRootNode(data)
        data.activeDialogue = ActiveDialogue(tree, node)
        // 向玩家显示初始对话
        displayDialogue(player, node)
    }

    private fun displayDialogue(player: ServerPlayer, node: DialogueNode) {
        //
    }

    fun selectOption(player: ServerPlayer, optionIndex: Int) {
        val data = player.data() ?: return
        val active = data.activeDialogue ?: return
        val node = active.currentNode

        if (optionIndex < 0 || optionIndex >= node.options.size) return

        when (val result = node.options[optionIndex].action.apply(data)) {
            is DialogueActionResult.Advance -> {
                // 查找并更新下一个节点
                val nextNode = findNodeById(active.tree, result.nextNodeId)
                if (nextNode != null) {
                    active.currentNode = nextNode
                    displayDialogue(player, nextNode)
                } else {
                    exitDialogue(player)
                }
            }
            is DialogueActionResult.Execute -> result.command.run()
            is DialogueActionResult.Exit -> exitDialogue(player)
        }
    }

    fun exitDialogue(player: ServerPlayer) {
        val data = player.data() ?: return
        data.activeDialogue = null
        // TODO 清理对话...
    }

    private fun findNodeById(dialogueTree: DialogueTree, nextID: String): DialogueNode? {
        return when (dialogueTree) {
            is SimpleDialogueTree -> dialogueTree.nodes.find { it.id == nextID }
            else -> null
        }
    }

}