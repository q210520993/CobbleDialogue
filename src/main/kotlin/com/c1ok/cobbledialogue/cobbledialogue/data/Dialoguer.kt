package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import net.minecraft.server.level.ServerPlayer
import java.util.*

interface Dialoguer {
    val id: UUID // 唯一标识符
    fun showDialogue(node: DialogueNode) // 显示对话内容
    fun closeDialogue() // 关闭对话
}

class PlayerDialoguer(val player: ServerPlayer) : Dialoguer {
    override val id: UUID = player.uuid
    override fun showDialogue(node: DialogueNode) {
    }
    override fun closeDialogue() {
    }
}

object ConsoleDialoguer : Dialoguer {
    override val id: UUID = UUID.randomUUID()
    override fun showDialogue(node: DialogueNode) {
        println(node.text)
        node.options.forEachIndexed { idx, opt ->
            println("$idx: ${opt.text}")
        }
    }
    override fun closeDialogue() {
    }
}