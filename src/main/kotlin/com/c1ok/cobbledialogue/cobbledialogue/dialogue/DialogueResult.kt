package com.c1ok.cobbledialogue.cobbledialogue.dialogue

/**
 *
 * @property Ok 如果对话为Ok，哪么将会接着下一个dialogueGroup
 * @property Cancelled 如果对话是Cancelled，哪么将会结束对话
 *
 */
sealed class DialogueResult {
    class Ok(val nextDialogueGroup: DialogueGroup) : DialogueResult()
    data object Cancelled: DialogueResult()
}