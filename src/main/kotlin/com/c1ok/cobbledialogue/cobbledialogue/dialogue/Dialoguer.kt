package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ComponentTextUnit
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.CoroutineText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.DialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.*

interface Dialoguer {
    val id: UUID // 唯一标识符
    var active: Boolean
    fun showDialogue(node: DialogueNode) // 显示对话内容
    fun showDialogueTextUnit(text:TextUnit)
    fun closeDialogue() // 关闭对话
}

class PlayerDialoguer(val player: Player) : Dialoguer {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val origin = this
    private var currentNode: DialogueNode? = null
    override val id: UUID = player.uuid
    override var active: Boolean = false
    override fun showDialogue(node: DialogueNode) {
        active = true
        scope.launch {
            currentNode = node
            when(val text = node.text) {
                is CoroutineText -> {
                    text.dialogue(origin)
                }

                is DialogueText -> {
                    text.dialogue(origin)
                }
            }
            // 再次判断，如果对话被取消，则直接任务取消
            if (!active) return@launch
            node.options.forEachIndexed { idx, opt ->
                showDialogueTextUnit(ComponentTextUnit(Component.literal("$idx: ${opt.text.string}")))
            }
        }.invokeOnCompletion {
            closeDialogue()
            currentNode = null
        }
    }

    override fun showDialogueTextUnit(text: TextUnit) {
        if(text is ComponentTextUnit){
            player.displayClientMessage(text.component,false)
            return
        }
        player.displayClientMessage(Component.literal(text.getString()),false)
    }

    override fun closeDialogue() {
        active = false
    }
}

object ConsoleDialoguer : Dialoguer {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val origin = this
    override val id: UUID = UUID.randomUUID()
    override var active: Boolean = false
    override fun showDialogue(node: DialogueNode) {
        active = true
        scope.launch {
            when(val text = node.text) {
                is CoroutineText -> {
                    text.dialogue(origin)
                }

                is DialogueText -> {
                    text.dialogue(origin)
                }

            }
            node.options.forEachIndexed { idx, opt ->
                println("$idx: ${opt.text.string}")
            }
        }.invokeOnCompletion {
            closeDialogue()
        }
    }

    override fun showDialogueTextUnit(text: TextUnit) {
        print(text.getString()+"\n")
    }

    override fun closeDialogue() {
        active = false
    }
}