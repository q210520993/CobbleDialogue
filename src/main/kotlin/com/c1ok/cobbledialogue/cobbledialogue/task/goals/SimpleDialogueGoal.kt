package com.c1ok.cobbledialogue.cobbledialogue.task.goals

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.task.EventData
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskGoal

class SimpleDialogueGoal(val node: DialogueNode): TaskGoal {
    private var isComplete = false
    override val name: String
        get() = TODO("Not yet implemented")
    override var progress: Int = 0

    override val goal: Int = 1

    override fun isComplete(): Boolean {
        return isComplete
    }

    override fun update(data: EventData) {
        val asData = data as? DialogueEvent ?: return
        if (asData.session.currentNode == node) isComplete = true
    }

    override fun getProgressInformation(): String {
        return "对话: 0/1"
    }

}