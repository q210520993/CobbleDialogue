package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer

class SimpleDialogueText(text: ComponentTextUnit) :
    DialogueText<TextUnit> {
    val component:ComponentTextUnit = text

    override fun dialogue(dialoguer: Dialoguer) {
        // Implement the dialogue logic here
        // For example, you might want to send the text to the dialoguer
        dialoguer.showDialogueTextUnit(component)
    }
}