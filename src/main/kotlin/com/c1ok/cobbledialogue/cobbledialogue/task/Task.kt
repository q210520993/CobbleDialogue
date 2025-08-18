package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.world.entity.player.Player
import java.util.function.Consumer

open class Task(
    val id: String,
    val name: String,               //  这只是作为展示名称
    val goals: List<TaskGoal>,      // 任务目标
    val reward: Consumer<Player>     // 完成后触发
) {

    fun getGoal(name: String): TaskGoal? {
        return goals.firstOrNull { it.name == name }
    }

    fun isComplete(): Boolean = goals.all { it.isComplete() }

    fun progressInformation(): String = goals.joinToString("\n") { it.getProgressInformation() }
}