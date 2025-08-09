package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer
import kotlinx.coroutines.delay
import net.minecraft.network.chat.Component
import java.util.ArrayList

class PhaseDialogueText(text: MutableList<ComponentTextUnit>) : CoroutineText<TextUnit> {
    val components: MutableList<ComponentTextUnit> = text

    override suspend fun dialogue(dialoguer: Dialoguer) {
        // Implement the dialogue logic here
        // For example, you might want to send the text to the dialoguer
            components.forEach { a ->
                if (!dialoguer.active) return
                dialoguer.showDialogueTextUnit(a)
                delay(1000)
            }
    }
}

class PhaseDialogueBuilder{
    private val components: MutableList<ComponentTextUnit> = ArrayList()
    fun addText(text: String): PhaseDialogueBuilder {
        components.add(ComponentTextUnit(Component.literal(text)))
        return this
    }
    fun build():PhaseDialogueText{
        val text = PhaseDialogueText(components)
        return text
    }
}