package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer

interface DialogueText<T:TextUnit>: Text<T> {
    fun dialogue(dialoguer: Dialoguer)
}