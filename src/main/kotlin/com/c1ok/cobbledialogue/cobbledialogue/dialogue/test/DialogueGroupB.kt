package com.c1ok.cobbledialogue.cobbledialogue.dialogue.test

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueGroup
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueResult
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.impl.DialogueGroupImpl
import net.kyori.adventure.text.Component

class DialogueGroupB: DialogueGroupImpl() {
    init {
        innerDialogue.add(DialogueGroup.InnerDialogue(Component.text("你好，这是对话B，任务结束")) {
            return@InnerDialogue DialogueResult.Cancelled
        })
    }
}