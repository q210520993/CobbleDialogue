package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer

interface CoroutineText<T : TextUnit>: Text<T> {
    suspend fun dialogue(dialoguer: Dialoguer)
}