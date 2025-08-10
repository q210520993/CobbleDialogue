package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block

class BlockBreakGoal(
    private val blockType: Block,          // 目标方块
    private val targetPos: BlockPos,       // 目标位置
    private val targetAmount: Int          // 挖掘数量
) : TaskGoal {
    private var currentAmount = 0          // 当前进度

    override fun isComplete(): Boolean = currentAmount >= targetAmount

    override fun progress(): String = "挖掘目标: $currentAmount/$targetAmount (${blockType.name})"

    override fun update(data: EventData) {
        if (data !is BlockEventData) {
            return
        }
        if (data.blockPos != targetPos) {
            return
        }
        currentAmount ++
    }

}