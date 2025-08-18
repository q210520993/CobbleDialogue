package com.c1ok.cobbledialogue.cobbledialogue.task

import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerDataManager
import com.c1ok.cobbledialogue.cobbledialogue.task.tasks.AfterArgueCreator
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Player
import java.util.concurrent.ConcurrentHashMap

/**
 * 任务管理器，用于管理玩家的任务及其状态
 */
object TaskManager {

    // 存储每个玩家的任务列表（以玩家为键，任务映射为值）
    val playerTasks: MutableMap<Player, MutableMap<String, Task>> = ConcurrentHashMap()

    // 存储系统中所有注册的任务类型（TaskCreator）
    val tasks: MutableMap<String, TaskCreator> = ConcurrentHashMap()

    /**
     * 注册所有任务类型（任务创建器）
     * 每个任务类型都需要通过此方法注册到 TaskManager 中
     */
    fun registerTasks() {
        registerTask(AfterArgueCreator) // 示例任务注册
    }

    /**
     * 获取玩家当前所有的任务列表
     *
     * @param player 要查询的玩家
     * @return 玩家当前的任务列表
     */
    fun getTasks(player: Player): List<Task> {
        return playerTasks[player]?.values?.toList() ?: emptyList() // 返回玩家的任务列表或空列表
    }

    /**
     * 注册任务类型（任务创建器）
     *
     * @param task 任务创建器
     */
    fun registerTask(task: TaskCreator) {
        tasks[task.id] = task
    }

    /**
     * 注销任务
     * 从系统中移除指定的任务，同时移除所有相关任务实例
     *
     * @param task 要注销的任务
     */
    fun unregisterTask(task: Task) {
        // 从每个玩家的任务列表中移除该任务
        playerTasks.values.forEach { map ->
            map.remove(task.id)
        }
        // 从全局任务创建器中移除
        tasks.remove(task.id)
    }

    /**
     * 为玩家添加一个新的任务
     *
     * @param player 玩家
     * @param taskCreator 任务创建器，用于生成新的任务实例
     */
    fun addTask(player: Player, taskCreator: TaskCreator) {
        // 获取玩家的任务映射，若不存在则创建新映射
        val tasks = playerTasks.computeIfAbsent(player) { ConcurrentHashMap() }
        val task = taskCreator.createTask(player) // 使用创建器生成任务实例

        // 避免重复添加相同任务
        if (tasks.containsKey(task.id)) {
            println("不可重复添加同一任务")
            return
        }

        // 添加任务到玩家任务列表
        tasks[task.id] = task
        PlayerDataManager.updatePlayerTask(player.uuid, task) // 更新玩家任务数据
    }

    /**
     * 更新任务状态：根据指定事件更新任务目标，若任务完成则触发完成逻辑
     *
     * @param player 玩家
     * @param event 触发更新的事件数据
     * @param eventID 任务对应的事件标识
     */
    fun updateTask(player: Player, event: EventData, eventID: String) {
        val tasks = playerTasks[player] ?: return // 获取玩家的任务列表，若不存在则直接返回
        val task = tasks[eventID] ?: return // 获取指定任务，若不存在则直接返回

        // 遍历任务目标并尝试更新每个目标
        task.goals.forEach {
            it.update(event)
        }

        // 如果任务已完成，触发完成任务逻辑
        if (task.isComplete()) {
            completeTask(player, task)
        }
    }

    /**
     * 完成任务：处理任务奖励并从玩家任务列表中移除
     *
     * @param player 玩家
     * @param task 要完成的任务
     */
    private fun completeTask(player: Player, task: Task) {
        // 显示任务完成信息
        player.displayClientMessage(Component.literal("${task.name} 已完成！"), false)

        // 分发任务奖励
        task.reward.accept(player)

        // 从玩家任务列表中移除任务
        playerTasks[player]?.remove(task.id)

        // 更新玩家的任务数据
        val data = PlayerDataManager.getPlayerData(player.uuid) ?: return
        data.tasks[task.id] // NOTE: 此处逻辑仅更新引用，但具体用法未明确
    }
}