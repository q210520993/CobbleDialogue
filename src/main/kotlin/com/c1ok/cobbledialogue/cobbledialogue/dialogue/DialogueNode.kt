package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.DialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import net.minecraft.network.chat.Component

interface DialogueNode {
    val id: String
    // 主文本
    val text: DialogueText<TextUnit>
    // 选项
    val options: List<DialogueOption>
}
