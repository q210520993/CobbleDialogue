package com.c1ok.cobbledialogue.cobbledialogue.dialogue

class DialogueStorage {
    object NodeStorage {
        private val dialogues: MutableMap<String, DialogueNode> = mutableMapOf()

        fun addDialogue(node: DialogueNode): NodeStorage {
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

    object TreeStorage {
        private val dialogues: MutableMap<String, DialogueTree> = mutableMapOf()

        fun addDialogue(name:String,tree: DialogueTree): TreeStorage {
            dialogues[name] = tree;
            return this;
        }

        fun getDialogue(id: String): DialogueTree? {
            return dialogues[id]
        }

        fun removeDialogue(id: String) {
            dialogues.remove(id)
        }

        fun clear() {
            dialogues.clear()
        }
    }
}