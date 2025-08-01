package com.c1ok.cobbledialogue.cobbledialogue.dialogue

class DialogueStorage {
    private val dialogues: MutableMap<String, DialogueNode> = mutableMapOf()

    fun addDialogue(node: DialogueNode):DialogueStorage {
        dialogues[node.id] = node
        return this;
    }

    fun getDialogue(id: String): DialogueNode? {
        return dialogues[id]
    }

    fun removeDialogue(id: String) {
        dialogues.remove(id)
    }

    fun clear() {
        dialogues.clear()
    }
}