package com.c1ok.cobbledialogue.cobbledialogue.task.goals

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueNode
import com.c1ok.cobbledialogue.cobbledialogue.task.EventData
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskGoal

class SimpleDialogueGoal(val node: DialogueNode): TaskGoal {
    var isComplete = false
        private set

    override fun isComplete(): Boolean {
        return isComplete
    }

    override fun progress(): String {
        return "对话目标: $isComplete (${node.id})"
    }

    override fun update(data: EventData) {
        val asData = data as? DialogueEvent ?: return
        if (asData.session.currentNode == node) isComplete = true
    }

}