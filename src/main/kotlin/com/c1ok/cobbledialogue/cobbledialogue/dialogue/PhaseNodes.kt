package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.SimpleDialogueText
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueBuilder
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import net.minecraft.network.chat.Component

val town1_mayor_start_argue = object : DialogueNode {
    override val id: String
        get() = "start_argue"
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(
            "Mayor: I don't care what you think, I'm the mayor here and I make the rules!"
        ).addText(
            "You: But Mayor, you can't just ignore the law!"
        ).addText(
            "Mayor: The law? Ha! I make the law!"
        ).addText(
            "You: This is ridiculous, we need to talk about this!"
        ).build()
    override val options: List<DialogueOption>
        get() = listOf(
            DialogueOption(
                text = Component.literal("Argue with the Mayor"),
                action = { session ->
                    // Implement the action logic here
                    // For example, you might want to change the dialogue node or update the session state
                    DialogueActionResult.Advance(
                        "mayor_argue"
                    ) // Assuming "mayor_argue" is the next node ID for the argument phase
                }
            ),
            DialogueOption(
                text = Component.literal("Leave the argument"),
                action = { session ->
                    // Implement the action logic here
                    // For example, you might want to end the dialogue or go back to a previous node
                    DialogueActionResult.Exit
                }
            )
        )
    override val result: DialogueActionResult
        get() = DialogueActionResult.Exit

}