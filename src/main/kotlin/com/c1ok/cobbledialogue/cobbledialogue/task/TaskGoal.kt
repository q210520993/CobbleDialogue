package com.c1ok.cobbledialogue.cobbledialogue.task

interface TaskGoal {
    val name: String
    var progress: Int
    val goal: Int
    fun isComplete(): Boolean // 判断目标是否完成
    fun update(data: EventData) // 根据事件更新目标状态
    fun getProgressInformation(): String
}