package com.c1ok.cobbledialogue.cobbledialogue.task.goals

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueSession
import com.c1ok.cobbledialogue.cobbledialogue.task.EventData

data class DialogueEvent(
    val session: DialogueSession
) : EventData