package com.c1ok.cobbledialogue.cobbledialogue.dialogue.test

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.ConsoleDialoguer
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.*
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ComponentTextUnit
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.SimpleDialogueText
import net.minecraft.network.chat.Component
import java.util.*

val startNode = object : DialogueNode {
    override val id = "start"
    override val text = PhaseDialogueText(Arrays.asList(
        ComponentTextUnit(Component.literal("你好！需要什么帮助吗？")),
        ComponentTextUnit(Component.literal("not really?")),
        ComponentTextUnit(Component.literal("no way!"))
    ))
    override val options = listOf(
        DialogueOption(Component.literal("关于任务...")) {
            DialogueActionResult.Advance("quest_info")
        },
        DialogueOption(Component.literal("交易")) {
            DialogueActionResult.Execute { /*TODO OpenTrade*/ }
        },
        DialogueOption(Component.literal("再见")) {
            DialogueActionResult.Exit
        }
    )
    override val result: DialogueActionResult
        get() = TODO("Not yet implemented")
}

val questNode = object : DialogueNode {
    override val id = "quest_info"
    override val text = SimpleDialogueText(ComponentTextUnit(Component.literal("北方洞穴里有邪恶的宝可梦...")))
    override val options = listOf(
        DialogueOption(Component.literal("接受任务")) {
            DialogueActionResult.Execute {
                // TODO Accept Quest
                DialogueActionResult.Exit
            }
        },
        DialogueOption(Component.literal("返回")) {
            DialogueActionResult.Advance("start")
        }
    )
    override val result: DialogueActionResult
        get() = TODO("Not yet implemented")
}

fun main() {

    val simpleSelector = DialogueRootSelector { playerData->
        // 做一些判断，判断玩家该进行哪一段对话，这里为例子直接返回"start"了
        return@DialogueRootSelector "start"
    }

    val testTree = SimpleDialogueTree(
        simpleSelector,
        listOf(startNode, questNode)
    )

    DialogueManager.startSession(ConsoleDialoguer, testTree)
    val scanner = Scanner(System.`in`)
    //TODO 用for的无限循环 获取scanner的值
    while (scanner.hasNextLine()) {
        val input = scanner.nextLine()
        when (input) {
            "0" -> DialogueManager.selectOption(ConsoleDialoguer.id, 0)
            "1" -> DialogueManager.selectOption(ConsoleDialoguer.id, 1)
            "2" -> DialogueManager.selectOption(ConsoleDialoguer.id, 2)
            else -> println("无效的选项，请输入 0, 1 或 2")
        }
    }
//    DialogueManager.selectOption(ConsoleDialoguer.id, 0)
//    DialogueManager.selectOption(ConsoleDialoguer.id, 1)

}