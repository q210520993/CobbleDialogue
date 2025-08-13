package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.Utils
import com.cobblemon.mod.common.api.dialogue.Dialogue
import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import java.util.concurrent.ConcurrentHashMap

data class DialogueData(val id: String, val dialogues: List<String>, val options: List<String>)

const val CONFIG_FILE_PATH = "config/cobbledialogue"
const val CONFIG_FILE = "dialogue.json"


val gson by lazy {
    GsonBuilder().setPrettyPrinting().create()
}

object DialogueDataManager {

    val dialogues: MutableMap<String, DialogueData> = ConcurrentHashMap()
    val originDialogues: MutableMap<String, DialogueData> = ConcurrentHashMap()
    // 但是反序列化出来是这样的，Map<String,String>
    fun loadDialogue() {
        OriginDialogueData.register()
        println(Utils.readFileSync(CONFIG_FILE_PATH, CONFIG_FILE))
    }

    fun getDialogue(id: String): DialogueData {
        return dialogues[id] ?: originDialogues[id]!!
    }

    fun addDialogueData(dialogueData: DialogueData) {
        dialogues[dialogueData.id] = dialogueData
    }

    fun addOriginDialogueData(dialogueData: DialogueData) {
        originDialogues[dialogueData.id] = dialogueData
    }

    fun saveDialogue() {
        val str = gson.toJson(dialogues)
        println(str)
        println(Utils.writeFileSync(CONFIG_FILE_PATH, CONFIG_FILE, str))
    }

}