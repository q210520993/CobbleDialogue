package com.c1ok.cobbledialogue.cobbledialogue.dialogue

interface DialogueTree {

    fun getRootNode(dialoguer: Dialoguer): DialogueNode?

    fun getNodeById(id: String): DialogueNode?

}