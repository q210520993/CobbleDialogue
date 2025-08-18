package com.c1ok.cobbledialogue.cobbledialogue.task.tasks

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes.TownOneZhang
import com.c1ok.cobbledialogue.cobbledialogue.task.Task
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskCreator
import com.c1ok.cobbledialogue.cobbledialogue.task.goals.SimpleDialogueGoal
import net.minecraft.world.entity.player.Player
import java.util.function.Consumer

class AfterArgue(consumer: Consumer<Player>): Task (
    "findZhang", "findZhang",
    goals = listOf(SimpleDialogueGoal(TownOneZhang.town1_zhang_intro_1)), consumer
)

object AfterArgueCreator: TaskCreator {
    override val id: String = "findZhang"

    override fun createTask(player: Player): Task {
        return AfterArgue {}
    }

}