package com.c1ok.cobbledialogue.cobbledialogue

import com.c1ok.cobbledialogue.commands.DialogueCommand
import com.c1ok.cobbledialogue.commands.TestCommand
import com.c1ok.cobbledialogue.commands.Subcommand
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import java.util.Arrays


class Cobbledialogue : ModInitializer {
    companion object {
        const val MOD_ID = "cobbledialogue"
    }

    override fun onInitialize() {
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
