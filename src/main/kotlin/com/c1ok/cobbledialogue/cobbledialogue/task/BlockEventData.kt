package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

data class BlockEventData(
    val level: Level,
    val player: Player,
    val blockPos: BlockPos,
    val blockState: BlockState,
    val blockEntity: BlockEntity?
): EventData