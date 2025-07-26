package com.c1ok.cobbledialogue.cobbledialogue.dialogue.test

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueGroup
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueResult
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.impl.DialogueGroupImpl
import net.kyori.adventure.text.Component

class DialogueGroupA: DialogueGroupImpl() {

    init {
        // 0
        innerDialogue.add(DialogueGroup.InnerDialogue(Component.text("你好，这是对话A，选择它将不会发生任何事")) {
            println("对话A")
            return@InnerDialogue DialogueResult.Cancelled
        })
        // 1
        innerDialogue.add(DialogueGroup.InnerDialogue(Component.text("选项B，选择它将会触发下一段对话")) {
            return@InnerDialogue DialogueResult.Ok(DialogueGroupB())
        })
    }

}