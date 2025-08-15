package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.Utils
import com.c1ok.cobbledialogue.cobbledialogue.serializer.LocalDateTimeTypeAdapter
import com.c1ok.cobbledialogue.cobbledialogue.serializer.UUIDTypeAdapter
import com.c1ok.cobbledialogue.cobbledialogue.task.Task
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.minecraft.world.entity.player.Player
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap

const val DATA_FILE = "data.json"


object PlayerDataManager {
    val datas: MutableMap<UUID, PlayerData> = ConcurrentHashMap<UUID, PlayerData>()
    val gson by lazy {
        GsonBuilder().
            setPrettyPrinting().
            registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter()).
            registerTypeAdapter(UUID::class.java, UUIDTypeAdapter()).
        create()
    }

    fun loadDatas() {
        val str = Utils.readFileSync(CONFIG_FILE_PATH, DATA_FILE)
        val type = object : TypeToken<ConcurrentHashMap<UUID, PlayerData>>() {}.type
        val deserializedData: ConcurrentHashMap<UUID, PlayerData> = gson.fromJson(str, type)
        deserializedData.entries.forEach {
            datas[it.key] = it.value
        }
        datas.values.forEach { value ->
            value.tasks.forEach { task ->
            }
        }
    }

    fun addPlayerData(player: Player) {
        datas.putIfAbsent(player.uuid, PlayerData(player.uuid, ConcurrentHashMap(), 0, LocalDateTime.now()))
    }

    fun getPlayerData(uuid: UUID): PlayerData? {
        return datas[uuid]
    }

    fun updatePlayerTask(uuid: UUID, task: Task) {
        getPlayerData(uuid)?.let { playerData ->
            playerData.tasks[task.id]?.executionCount
        }
    }

    fun saveDatas() {
        val jsonString = gson.toJson(datas)
        Utils.writeFileSync(CONFIG_FILE_PATH, DATA_FILE, jsonString)
    }

}

data class PlayerData(
    val playerId: UUID,                      // 玩家唯一标识
    val tasks: MutableMap<String, TaskData>,         // 任务信息（键为任务 ID）
    val totalTasksExecuted: Int,              // 所有任务执行次数总和
    val lastUpdated: LocalDateTime            // 数据最后更新时间
)

data class TaskData(
    val id: String,                         // 任务名称
    var executionCount: Int,                  // 任务执行次数
    var successCount: Int = 0,                // 任务成功次数（默认值为 0）
    var failureCount: Int = 0,                // 任务失败次数（默认值为 0）
    var lastCompleted: LocalDateTime? = null,
    var taskDoingData: TaskDoingData
)

data class TaskDoingData(
    val id: String,                         // 任务名称
    val goals: MutableList<GoalData>
)

data class GoalData(
    val name: String,       // 目标id
    val progress: Int       // 进度条
)

//
//
//fun main() {
//
//    // 创建示例数据
//    val task1 = TaskData(
//        name = "Collect 10 Apples",
//        executionCount = 3,
//        successCount = 2,
//        failureCount = 1,
//        lastCompleted = LocalDateTime.now()
//    )
//
//    val task2 = TaskData(
//        name = "Test 1",
//        executionCount = 1,
//        successCount = 1,
//        failureCount = 0,
//        lastCompleted = LocalDateTime.now()
//    )
//
//    val playerDataMap: ConcurrentHashMap<UUID, PlayerData> = ConcurrentHashMap()
//    playerDataMap[UUID.randomUUID()] = PlayerData(
//        playerId = UUID.randomUUID(),
//        tasks = mapOf("task_01" to task1, "task_02" to task2),
//        totalTasksExecuted = 4,
//        lastUpdated = LocalDateTime.now()
//    )
//    // 序列化为 JSON
//    val jsonString = PlayerDataManager.gson.toJson(playerDataMap)
//    println("Serialized JSON:\n$jsonString")
//
//    // 反序列化为 ConcurrentHashMap<UUID, PlayerData>
//    val type = object : TypeToken<ConcurrentHashMap<UUID, PlayerData>>() {}.type
//    val deserializedData: ConcurrentHashMap<UUID, PlayerData> = PlayerDataManager.gson.fromJson(jsonString, type)
//
//    println(deserializedData)
//}