package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import net.kyori.adventure.text.Component
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier

/**
 * Dialogue -> DialogueGroup -> Inner -> trigger -> DialogueGroup -> ... -> Cancelled
 *
 */

interface DialogueGroup {

    val innerDialogue: MutableList<InnerDialogue>

    data class InnerDialogue(val text: Component, val onTrigger: Function<InnerDialogue, DialogueResult>)

    fun trigger(index: Int): DialogueResult

}