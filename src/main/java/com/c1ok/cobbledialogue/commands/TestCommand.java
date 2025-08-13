package com.c1ok.cobbledialogue.commands;

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.Dialoguer;
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.PlayerDialoguer;
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class TestCommand extends Subcommand {

    public TestCommand() {
        super("§9Usage:\n§3- /battlepass addxp <player> <amount>");
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("start")
                .requires(source -> source.hasPermission(4)) // Requires operator permission level
                .then(Commands.argument("player", EntityArgument.player())
                    .then(Commands.argument("name", StringArgumentType.string())
                        .executes(this::run)))
                .build();
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer target = EntityArgument.getPlayer(context, "player");
            String name = StringArgumentType.getString(context, "name");

            DialogueNode node = DialogueManager.INSTANCE.getDialogueStorage().getDialogue(name);

            DialogueManager.INSTANCE.startSession(new PlayerDialoguer(target), TestDialogue.INSTANCE);

            return 1;
        } catch (Exception e) {
            context.getSource().sendSystemMessage(Component.literal("§cError: " + e.getMessage()));
            return 0;
        }
    }
}
