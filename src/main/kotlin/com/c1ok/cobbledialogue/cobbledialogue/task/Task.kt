package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.world.entity.player.Player
import java.util.function.Consumer

/**
 * 表示一个任务的核心类。
 *
 * @property id 任务的唯一标识符。
 * @property name 任务的名称，用于向玩家展示。
 * @property goals 任务的目标列表，每个目标是一个具体的 `TaskGoal` 实例。
 * @property reward 任务完成后触发的奖励逻辑，使用 `Consumer<Player>` 表示。
 */
open class Task(
    val id: String,
    val name: String,
    val goals: List<TaskGoal>,
    val reward: Consumer<Player>
) {

    /**
     * 根据目标名称获取任务目标。
     *
     * @param name 目标的名称。
     * @return 返回匹配名称的目标，若不存在则返回 null。
     */
    fun getGoal(name: String): TaskGoal? {
        return goals.firstOrNull { it.name == name }
    }

    /**
     * 检测此任务是否已完成。
     *
     * @return 如果所有目标均已完成，则返回 true；否则返回 false。
     */
    fun isComplete(): Boolean = goals.all { it.isComplete() }

    /**
     * 获取任务的进度信息。
     *
     * @return 每个目标的进度信息，由换行符分隔。
     */
    fun progressInformation(): String = goals.joinToString("\n") { it.getProgressInformation() }
}