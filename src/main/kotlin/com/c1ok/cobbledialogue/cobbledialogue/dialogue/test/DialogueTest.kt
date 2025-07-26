package com.c1ok.cobbledialogue.cobbledialogue.dialogue.test

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialogue
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueResult
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap

object DialogueTest: Dialogue {
    override val id: String = "Test"
    override val getDialogueGroup: Function<PlayerData, DialogueResult> = Function<PlayerData, DialogueResult> {
        return@Function DialogueResult.Ok(DialogueGroupA())
    }
}

fun main() {
    val result = DialogueTest.getDialogueGroup.apply(PlayerData(UUID.randomUUID(), HashMap()))
    if (result is DialogueResult.Ok) {
        result.nextDialogueGroup.trigger(0)
    }
}