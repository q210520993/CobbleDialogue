package com.c1ok.cobbledialogue.cobbledialogue.task

interface TaskGoal {
    fun isComplete(): Boolean // 判断目标是否完成
    fun progress(): String    // 当前进度描述
    fun update(data: EventData) // 根据事件更新目标状态
}