package com.c1ok.cobbledialogue.cobbledialogue

import com.c1ok.cobbledialogue.cobbledialogue.data.CONFIG_FILE_PATH
import com.c1ok.cobbledialogue.cobbledialogue.data.DATA_FILE
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager
import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerDataManager
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskManager
import com.c1ok.cobbledialogue.commands.DialogueCommand
import com.c1ok.cobbledialogue.commands.Subcommand
import com.c1ok.cobbledialogue.commands.TestCommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.world.entity.player.Player
import java.nio.file.Path
import java.util.*


class Cobbledialogue : ModInitializer {
    companion object {
        const val MOD_ID = "cobbledialogue"
    }

    override fun onInitialize() {

        DialogueDataManager.loadDialogue()
        TaskManager.registerTasks()
        PlayerDataManager.loadDatas()

        val configFile = Path.of(CONFIG_FILE_PATH).toFile()
        if (!configFile.exists()) {
            DialogueDataManager.saveDialogue()
        }
        val data = Path.of(DATA_FILE).toFile()
        if (!data.exists()) {
            PlayerDataManager.saveDatas()
        }

        ServerLifecycleEvents.SERVER_STOPPING.register(ServerLifecycleEvents.ServerStopping {
            DialogueDataManager.saveDialogue()
            PlayerDataManager.saveDatas()
        })

        ServerPlayerEvents.JOIN.register {
            PlayerDataManager.loadPlayerData(it)
        }

        CommandRegistrationCallback.EVENT.register{ dispatcher, registryAccess, environment ->
            // 在这里注册命令
            // 例如：dispatcher.register(CommandManager.literal("example").executes { context ->
            //     context.source.sendFeedback(Text.of("Hello, world!"), false)
            //     1
            // })
            // 注意：需要导入相关的命令管理类和文本类
            DialogueCommand(
                Arrays.asList("dl"),
                Arrays.asList(TestCommand()) as List<Subcommand>?
            ).register(dispatcher)
        }
    }

}
