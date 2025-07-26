package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import java.util.function.Function

/**
 * @property id String 唯一ID，将被存入DialogueManager
 * @property inners 有序对话集
 */
interface Dialogue {

    // 表示这个对话的唯一ID（将存入DialogueManager）
    val id: String

    // 通过传入的PlayerData来选择一开始的DialogueResult
    val getDialogueGroup: Function<PlayerData, DialogueResult>

}