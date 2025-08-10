package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import java.util.concurrent.ConcurrentHashMap

object TaskManager {
    private val playerTasks: MutableMap<Player, MutableMap<String, Task>> = ConcurrentHashMap()

    // 获取玩家的任务列表
    fun getTasks(player: Player): List<Task> {
        return playerTasks[player]?.values?.toList() ?: emptyList()
    }

    // 添加任务
    fun addTask(player: Player, task: Task) {
        val tasks = playerTasks.computeIfAbsent(player) { ConcurrentHashMap() }
        tasks[task.id] = task
    }

    // 更新任务状态
    fun updateTask(player: Player, event: EventData, eventID: String) {
        val tasks = playerTasks[player] ?: return
        val task = tasks[eventID] ?: return
        task.goals.forEach {
            it.update(event)
        }
        if (task.isComplete()) {
            completeTask(player, task)
        }
    }

    // 完成任务
    private fun completeTask(player: Player, task: Task) {
        player.displayClientMessage(Component.literal("${task.name} 已完成！"), false)
        task.reward.accept(player)
        playerTasks[player]?.remove(task.id)
    }

}