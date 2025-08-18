package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import java.util.function.Function

data class DialogueOption(
    val id: String,
    val text: Component, //TODO INTO DialogueText
    val action: Function<DialogueSession, DialogueActionResult>
) {
    constructor(id: String, text: String, action: Function<DialogueSession, DialogueActionResult>): this(
        id,
        Component.literal(text.replace("&","§")).withStyle {
            it.withClickEvent(ClickEvent(ClickEvent.Action.RUN_COMMAND, "/cobbledialogue select $id"))
        },
        action
    )
}