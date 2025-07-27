package com.c1ok.cobbledialogue.cobbledialogue.dialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.Dialoguer

interface DialogueTree {

    fun getRootNode(dialoguer: Dialoguer): DialogueNode?

    fun getNodeById(id: String): DialogueNode?

}