package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueBuilder
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit

val town1_mayor_start_argue = object : DialogueNode {
    override val id: String
        get() = "start_argue"
    val dialogueData = DialogueDataManager.getDialogue("town1_mayor_start_argue")
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(
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
        get() = PhaseDialogueBuilder().addText(dialogueData).build()
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
    val dialogueData = DialogueDataManager.getDialogue("player_request")
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(
            dialogueData
        ).build()
    override val options: List<DialogueOption>
        get() = listOf(
            DialogueOption(
                text = dialogueData.options[0], // 浅青色选项
                action = { session ->
                    DialogueActionResult.Advance("village_chief_response_1")
                }
            )
        )
    override val result: DialogueActionResult
        get() = DialogueActionResult.Exit
}

val village_chief_response_1 = object : DialogueNode {
    override val id: String
        get() = "village_chief_response_1"
    val dialogueData = DialogueDataManager.getDialogue("village_chief_response_1")
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(dialogueData).build()
    override val options: List<DialogueOption>
        get() = listOf(
            DialogueOption(
                text = dialogueData.options[0], // 绿色选项
                action = { session ->
                    DialogueActionResult.Advance("player_rebuttal_1")
                }
            )
        )
    override val result: DialogueActionResult
        get() = DialogueActionResult.Exit
}

val player_rebuttal_1 = object : DialogueNode {
    override val id: String
        get() = "player_rebuttal_1"
    val dialogueData = DialogueDataManager.getDialogue("village_chief_response_1")
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(dialogueData).build()
    override val options: List<DialogueOption>
        get() = listOf(
            DialogueOption(
                text = dialogueData.options[0], // 绿色提示积极态度
                action = { session ->
                    DialogueActionResult.Advance("village_chief_counter_2")
                }
            )
        )
    override val result: DialogueActionResult
        get() = DialogueActionResult.Exit
}

val village_chief_counter_2 = object : DialogueNode {
    override val id: String
        get() = "village_chief_counter_2"
    val dialogueData = DialogueDataManager.getDialogue("village_chief_counter_2")
    override val text: Text<TextUnit>
        get() = PhaseDialogueBuilder().addText(dialogueData).build()
    override val options: List<DialogueOption>
        get() = listOf(
            DialogueOption(
                text = dialogueData.options[0], // 红色显示情绪高涨
                action = { session ->
                    DialogueActionResult.Exit
                }
            )
        )
    override val result: DialogueActionResult
        get() = DialogueActionResult.Exit
}