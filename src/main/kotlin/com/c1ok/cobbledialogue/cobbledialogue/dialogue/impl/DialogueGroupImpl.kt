package com.c1ok.cobbledialogue.cobbledialogue.dialogue.impl

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueGroup
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueResult
import java.util.concurrent.CopyOnWriteArrayList

open class DialogueGroupImpl: DialogueGroup {

    override val innerDialogue: MutableList<DialogueGroup.InnerDialogue> = CopyOnWriteArrayList()

    override fun trigger(index: Int): DialogueResult {
        val innerDialogue = innerDialogue[index]
        return innerDialogue.onTrigger.apply(innerDialogue)
    }

}