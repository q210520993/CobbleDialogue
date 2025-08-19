package com.c1ok.cobbledialogue.cobbledialogue

import com.c1ok.cobbledialogue.cobbledialogue.command.Select
import com.c1ok.cobbledialogue.cobbledialogue.data.CONFIG_FILE_PATH
import com.c1ok.cobbledialogue.cobbledialogue.data.DATA_FILE
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager
import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerDataManager
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueManager
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskManager
import com.c1ok.cobbledialogue.commands.DialogueCommand
import com.c1ok.cobbledialogue.commands.TestCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import java.nio.file.Path


class Cobbledialogue : ModInitializer {

    override fun onInitialize() {

        val configFile = Path.of(CONFIG_FILE_PATH).toFile()
        if (!configFile.exists()) {
            DialogueDataManager.saveDialogue()
        }
        val data = Path.of(DATA_FILE).toFile()
        if (!data.exists()) {
            PlayerDataManager.saveDatas()
        }

        DialogueDataManager.loadDialogue()
        PlayerDataManager.loadDatas()
        TaskManager.registerTasks()

        ServerLifecycleEvents.SERVER_STOPPING.register(ServerLifecycleEvents.ServerStopping {
            DialogueDataManager.saveDialogue()
            PlayerDataManager.saveDatas()
        })

        ServerPlayerEvents.JOIN.register {
            PlayerDataManager.loadPlayerData(it)
        }

        CommandRegistrationCallback.EVENT.register{ dispatcher, registryAccess, environment ->
            DialogueCommand(
                listOf("dl"),
                listOf(Select, TestCommand())).register(dispatcher)
        }
        DialogueManager.registerAll()
    }

}
