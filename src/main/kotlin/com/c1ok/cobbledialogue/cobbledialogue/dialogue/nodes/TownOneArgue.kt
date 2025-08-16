package com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueActionResult
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueOption
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.PlayerDialoguer
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueBuilder
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ShiftDialogueBuilder
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskManager
import com.c1ok.cobbledialogue.cobbledialogue.task.tasks.AfterArgue

object TownOneArgue {
    val town1_mayor_start_argue = object : DialogueNode {
        override val id: String
            get() = "start_argue"
        val dialogueData = DialogueDataManager.getDialogue("town1_mayor_start_argue")
        override val text: Text<TextUnit>
            get() = ShiftDialogueBuilder().addText(
                dialogueData
            ).build()
        override val options: List<DialogueOption>
            get() = listOf(
                DialogueOption(
                    text = dialogueData.options[0],
                    action = { session ->
                        DialogueActionResult.Advance("village_chief_opening")
                    }
                ),
                DialogueOption(
                    text = dialogueData.options[1], // 灰色选项，中立退出
                    action = { session ->
                        DialogueActionResult.Exit
                    }
                )
            )
        override val result: DialogueActionResult
            get() = DialogueActionResult.Exit
    }

    val town1_village_chief_opening = object : DialogueNode {
        override val id: String
            get() = "village_chief_opening"
        val dialogueData = DialogueDataManager.getDialogue("town1_village_chief_opening")
        override val text: Text<TextUnit>
            get() = ShiftDialogueBuilder().addText(dialogueData).build()
        override val options: List<DialogueOption>
            get() = listOf(
                DialogueOption(
                    text = dialogueData.options[0], // 绿色选项
                    action = { session ->
                        DialogueActionResult.Advance("player_request")
                    }
                )
            )
        override val result: DialogueActionResult
            get() = DialogueActionResult.Exit
    }

    val player_request = object : DialogueNode {
        override val id: String
            get() = "player_request"
        val dialogueData = DialogueDataManager.getDialogue("village_chief_intro")
        override val text: Text<TextUnit>
            get() = ShiftDialogueBuilder().addText(
                dialogueData
            ).build()
        override val options: List<DialogueOption>
            get() = listOf(
                DialogueOption(
                    text = dialogueData.options[0], // 浅青色选项
                    action = { session ->
                        DialogueActionResult.Advance("village_chief_argument")
                    }
                ),
                DialogueOption(
                    text = dialogueData.options[1],
                    action = {
                        DialogueActionResult.Exit
                    }
                )
            )
        override val result: DialogueActionResult
            get() = DialogueActionResult.Exit
    }

    val village_chief_argument = object : DialogueNode {
        override val id: String
            get() = "village_chief_argument"
        val dialogueData = DialogueDataManager.getDialogue("village_chief_argument")
        override val text: Text<TextUnit>
            get() = PhaseDialogueBuilder().addText(
                dialogueData
            ).build()
        override val options: List<DialogueOption>
            get() = listOf(
                DialogueOption(
                    text = dialogueData.options[0]
                ) // 浅青色选项
                { session ->
                    val player = (session.dialoguer as PlayerDialoguer).player
                    TaskManager.addTask(player, AfterArgue { })
                    DialogueActionResult.Exit
                }
            )
        override val result: DialogueActionResult
            get() = DialogueActionResult.Exit
    }

}