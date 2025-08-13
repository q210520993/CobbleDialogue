package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import net.minecraft.network.chat.Component
import java.util.function.Function

data class DialogueOption(
    val text: Component, //TODO INTO DialogueText
    val action: Function<DialogueSession, DialogueActionResult>
) {
    constructor(text: String, action: Function<DialogueSession, DialogueActionResult>): this(
        Component.literal(text.replace("&","§")),
        action
    )
}