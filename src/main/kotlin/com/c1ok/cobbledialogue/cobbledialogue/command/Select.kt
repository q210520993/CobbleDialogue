package com.c1ok.cobbledialogue.cobbledialogue.command

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueManager
import com.c1ok.cobbledialogue.commands.Subcommand
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.tree.CommandNode
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component

object Select: Subcommand("§9Usage:\n§3- /battlepass addxp <player> <amount>") {

    override fun build(): CommandNode<CommandSourceStack> {
        return Commands.literal("select")
            .requires { source: CommandSourceStack -> source.hasPermission(4) } // Requires operator permission level
            .then(
                Commands.argument("string", StringArgumentType.string())
                    .executes { context: CommandContext<CommandSourceStack> ->
                        this.run(
                            context
                        )
                    }
            )
            .build();
    }

    override fun run(context: CommandContext<CommandSourceStack>): Int {
        try {
            val player = context.source.player ?: return 0
            DialogueManager.selectOption(player.uuid, StringArgumentType.getString(context, "string"))
            return 1
        }catch (e: Exception) {
            context.source.sendSystemMessage(Component.literal("§cError: " + e.message));
            return 0;
        }
    }

}