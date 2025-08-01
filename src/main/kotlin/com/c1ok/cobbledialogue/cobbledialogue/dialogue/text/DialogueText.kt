package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer

interface DialogueText<T:TextUnit> {
    fun dialogue(dialoguer: Dialoguer)
}