package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueData
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer
import kotlinx.coroutines.delay
import net.minecraft.network.chat.Component
import java.util.ArrayList

open class PhaseDialogueText(text: MutableList<ComponentTextUnit>) : CoroutineText<TextUnit> {
    val components: MutableList<ComponentTextUnit> = text

    override suspend fun dialogue(dialoguer: Dialoguer) {

            components.forEach { a ->
                if (!dialoguer.active) return
                dialoguer.showDialogueTextUnit(a)
                delay(1000)
            }
    }
}

class PhaseDialogueBuilder {
    private val components: MutableList<ComponentTextUnit> = ArrayList()

    fun addText(text: DialogueData): PhaseDialogueBuilder {
        text.dialogues.forEach {
            addText(it)
        }
        return this
    }

    fun addText(text: String): PhaseDialogueBuilder {
        components.add(ComponentTextUnit(Component.literal(text.replace("&","§"))))
        return this
    }
    fun build(): PhaseDialogueText{
        val text = PhaseDialogueText(components)
        return text
    }
}