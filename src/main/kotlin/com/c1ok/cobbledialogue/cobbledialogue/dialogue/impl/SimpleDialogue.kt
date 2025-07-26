package com.c1ok.cobbledialogue.cobbledialogue.dialogue.impl

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialogue
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueResult
import java.util.function.Function

class SimpleDialogue(override val id: String, override val getDialogueGroup: Function<PlayerData, DialogueResult>): Dialogue