package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import net.kyori.adventure.text.Component
import java.util.function.Function

data class DialogueOption(
    val text: Component,
    val action: Function<PlayerData, DialogueActionResult>
)