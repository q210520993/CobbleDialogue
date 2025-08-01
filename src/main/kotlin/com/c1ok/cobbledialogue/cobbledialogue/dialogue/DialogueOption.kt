package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueSession
import net.minecraft.network.chat.Component
import java.util.function.Function

data class DialogueOption(
    val text: Component, //TODO INTO DialogueText
    val action: Function<DialogueSession, DialogueActionResult>
)