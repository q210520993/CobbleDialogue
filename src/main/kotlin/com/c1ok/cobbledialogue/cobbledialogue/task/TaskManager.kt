package com.c1ok.cobbledialogue.cobbledialogue.task

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerDataManager
import com.c1ok.cobbledialogue.cobbledialogue.task.tasks.AfterArgueCreator
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import java.util.concurrent.ConcurrentHashMap

object TaskManager {
    val playerTasks: MutableMap<Player, MutableMap<String, Task>> = ConcurrentHashMap()
    val tasks: MutableMap<String, TaskCreator> = ConcurrentHashMap<String, TaskCreator>()

    // 将所有taskcreator放到这里注册
    fun registerTasks() {
        registerTask(AfterArgueCreator)
    }

    // 获取玩家的任务列表
    fun getTasks(player: Player): List<Task> {
        return playerTasks[player]?.values?.toList() ?: emptyList()
    }

    // 注册任务
    fun registerTask(task: TaskCreator) {
        tasks[task.id] = task
    }

    fun unregisterTask(task: Task) {
        playerTasks.values.forEach { map->
            map.remove(task.id)
        }
        tasks.remove(task.id)
    }

    // 添加任务
    fun addTask(player: Player, taskCreator: TaskCreator) {
        val tasks = playerTasks.computeIfAbsent(player) { ConcurrentHashMap() }
        val task = taskCreator.createTask(player)
        if (tasks.containsKey(task.id)) {
            println("不可重复添加同一任务")
            return
        }
        tasks[task.id] = task
        PlayerDataManager.updatePlayerTask(player.uuid, task)
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
        val data = PlayerDataManager.getPlayerData(player.uuid) ?: return
        data.tasks[task.id]
    }

}