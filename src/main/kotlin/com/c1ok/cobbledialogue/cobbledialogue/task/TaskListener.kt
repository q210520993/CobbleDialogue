package com.c1ok.cobbledialogue.cobbledialogue.task

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents

object TaskListener {
    fun regsiter() {
        PlayerBlockBreakEvents.BEFORE.register { world, player, blockPos, blockState, blockentity ->
            TaskManager.updateTask(player, BlockEventData(world,player,blockPos,blockState,blockentity), "dig")
            true
        }
    }
}