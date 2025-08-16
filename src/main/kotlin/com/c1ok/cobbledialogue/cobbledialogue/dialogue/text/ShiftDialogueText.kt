package com.c1ok.cobbledialogue.cobbledialogue.dialogue.text

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueData
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer
import net.minecraft.network.chat.Component
import java.util.ArrayList

open class ShiftDialogueText(text: MutableList<ComponentTextUnit>) : PhaseDialogueText(text) {
    private var currentTextUnit: ComponentTextUnit? = null
    var crouched: Boolean = false;
    override suspend fun dialogue(dialoguer: Dialoguer) {
        components.forEach { a->
            dialoguer.showDialogueTextUnit(a)
            crouched = false
            while(!crouched){ }
        }
    }
}

class ShiftDialogueBuilder {
    private val components: MutableList<ComponentTextUnit> = ArrayList()

    fun addText(text: DialogueData): ShiftDialogueBuilder {
        text.dialogues.forEach {
            addText(it)
        }
        return this
    }

    fun addText(text: String): ShiftDialogueBuilder {
        components.add(ComponentTextUnit(Component.literal(text.replace("&","§"))))
        return this
    }
    fun build(): ShiftDialogueText{
        val text = ShiftDialogueText(components)
        return text
    }
}
