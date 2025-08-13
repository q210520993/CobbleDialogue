package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer

class ShiftDialogueText(text: MutableList<ComponentTextUnit>) : PhaseDialogueText(text) {
    override suspend fun dialogue(dialoguer: Dialoguer) {
        var currentTextUnit: ComponentTextUnit? = null
        components.forEach { component ->

        }
    }
}
