package com.c1ok.cobbledialogue.cobbledialogue.task

/**
 * 表示一个任务目标的接口，用于描述任务完成的条件。
 *
 * 每个任务目标都应实现此接口，定义自己的完成逻辑和状态更新方法。
 */
interface TaskGoal {
    /**
     * 目标的名称，用于标识和展示此目标。
     */
    val name: String

    /**
     * 当前进度。表示该目标的当前完成状态。
     */
    var progress: Int

    /**
     * 完成目标所需的进度。例如，当 `progress >= goal` 时目标会被视为完成。
     */
    val goal: Int

    /**
     * 检查此目标是否已完成。
     *
     * @return 如果进度达到目标所需的值，则返回 true；否则返回 false。
     */
    fun isComplete(): Boolean

    /**
     * 根据事件数据更新目标状态。
     *
     * @param data 触发目标更新的事件数据。
     */
    fun update(data: EventData)

    /**
     * 获取此目标的进度信息文本。
     *
     * @return 描述当前目标进度的字符串。
     */
    fun getProgressInformation(): String
}