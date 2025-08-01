package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ComponentTextUnit
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import java.util.*

interface Dialoguer {
    val id: UUID // 唯一标识符
    fun showDialogue(node: DialogueNode) // 显示对话内容
    fun showDialogueTextUnit(text:TextUnit)
    fun closeDialogue() // 关闭对话
}

class PlayerDialoguer(val player: ServerPlayer) : Dialoguer {
    override val id: UUID = player.uuid
    override fun showDialogue(node: DialogueNode) {
        Thread {
            node.text.dialogue(this)
            node.options.forEachIndexed { idx, opt ->
                showDialogueTextUnit(ComponentTextUnit(Component.literal("$idx: ${opt.text.string}")))
            }
        }.start()
    }

    override fun showDialogueTextUnit(text: TextUnit) {
        if(text is ComponentTextUnit){
            player.displayClientMessage(text.component,false)
            return
        }
        player.displayClientMessage(Component.literal(text.getString()),false)
    }

    override fun closeDialogue() {
    }
}

object ConsoleDialoguer : Dialoguer {
    override val id: UUID = UUID.randomUUID()
    override fun showDialogue(node: DialogueNode) {
        Thread {
            node.text.dialogue(this)
            node.options.forEachIndexed { idx, opt ->
                println("$idx: ${opt.text.string}")
            }
        }.start()
    }

    override fun showDialogueTextUnit(text: TextUnit) {
        print(text.getString()+"\n")
    }

    override fun closeDialogue() {
    }
}