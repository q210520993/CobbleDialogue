package com.c1ok.cobbledialogue.cobbledialogue.task

import net.minecraft.world.entity.player.Player

interface TaskCreator {
    val id: String

    fun createTask(player: Player): Task
}