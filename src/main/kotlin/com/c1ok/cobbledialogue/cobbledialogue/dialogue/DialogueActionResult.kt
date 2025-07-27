package com.c1ok.cobbledialogue.cobbledialogue.dialogue

/**
 *
 * @property Ok 如果对话为Ok，哪么将会接着下一个dialogueGroup
 * @property Cancelled 如果对话是Cancelled，哪么将会结束对话
 *
 */
sealed class DialogueActionResult {
    data class Advance(val nextNodeId: String) : DialogueActionResult()
    data class Execute(val command: Runnable) : DialogueActionResult()
    data object Exit : DialogueActionResult()
}