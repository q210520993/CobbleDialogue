package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import net.kyori.adventure.text.Component

interface DialogueNode {
    val id: String
    // 主文本
    val text: Component
    // 选项
    val options: List<DialogueOption>
}
