package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer

class PhaseDialogueText(text: List<ComponentTextUnit>) : DialogueText<TextUnit>{
    val components:List<ComponentTextUnit> = text

    override fun dialogue(dialoguer: Dialoguer) {
        // Implement the dialogue logic here
        // For example, you might want to send the text to the dialoguer
            components.forEach { a ->
                dialoguer.showDialogueTextUnit(a)
                Thread.sleep(1000L)
            }
    }
}