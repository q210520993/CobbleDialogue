package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit

interface DialogueNode {
    val id: String
    // 主文本
    val text: Text<TextUnit>
    // 选项
    val options: List<DialogueOption>
    //结束触发
    val result:DialogueActionResult

    fun getOption(id: String): DialogueOption? {
        return options.firstOrNull { it.id == id }
    }

}
