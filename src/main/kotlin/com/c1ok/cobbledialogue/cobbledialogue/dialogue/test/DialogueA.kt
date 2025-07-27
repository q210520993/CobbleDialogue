package com.c1ok.cobbledialogue.cobbledialogue.dialogue.test

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.*
import net.kyori.adventure.text.Component

val startNode = object : DialogueNode {
    override val id = "start"
    override val text = Component.text("你好！需要什么帮助吗？")
    override val options = listOf(
        DialogueOption(Component.text("关于任务...")) {
            DialogueActionResult.Advance("quest_info")
        },
        DialogueOption(Component.text("交易")) {
            DialogueActionResult.Execute { /*TODO OpenTrade*/ }
        },
        DialogueOption(Component.text("再见")) {
            DialogueActionResult.Exit
        }
    )
}

val questNode = object : DialogueNode {
    override val id = "quest_info"
    override val text = Component.text("北方洞穴里有邪恶的宝可梦...")
    override val options = listOf(
        DialogueOption(Component.text("接受任务")) {
            DialogueActionResult.Execute {
                // TODO Accept Quest
                DialogueActionResult.Exit
            }
        },
        DialogueOption(Component.text("返回")) {
            DialogueActionResult.Advance("start")
        }
    )
}


fun initDialogueSystem() {

    val simpleSelector = DialogueRootSelector { playerData->
        // 做一些判断，判断玩家该进行哪一段对话，这里为例子直接返回"start"了
        return@DialogueRootSelector "start"
    }

    val testTree = SimpleDialogueTree(
        rootSelector = simpleSelector,
        nodes = listOf(startNode, questNode)
    )

}

