package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.world.entity.player.Player

/**
 * 表示任务的创建器接口，用于生成任务实例。
 *
 * 实现此接口以定义自定义任务创建逻辑。
 */
interface TaskCreator {
    /**
     * 任务创建器的唯一标识符。
     */
    val id: String

    /**
     * 创建一个任务实例。
     *
     * @param player 接收任务的玩家。
     * @return 返回为此玩家创建的任务实例。
     */
    fun createTask(player: Player): Task
}