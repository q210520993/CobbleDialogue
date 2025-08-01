package com.c1ok.cobbledialogue.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all commands in the battle pass system
 */
public abstract class BaseCommand {
    // The command name
    private String commandString;
    // Command aliases
    private ArrayList<String> aliases;
    // Subcommands
    private ArrayList<Subcommand> subcommands;

    /**
     * Constructor for base command
     * @param commandString The primary command string
     * @param aliases List of command aliases
     * @param subcommands List of subcommands
     */
    public BaseCommand(String commandString, List<String> aliases,
                       List<Subcommand> subcommands) {
        this.commandString = commandString;
        this.aliases = new ArrayList<>(aliases);
        this.subcommands = new ArrayList<>(subcommands);
    }

    /**
     * Register the command and its subcommands
     */
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Create the base command
        LiteralCommandNode<CommandSourceStack> root = Commands
                .literal(commandString)
                .requires(ctx -> {
//                    if (CobblePass.config.isEnablePermissionNodes()) {
//                        if (ctx.isPlayer()) {
//                            return CobblePass.permissions.hasPermission(ctx.getPlayer(), permission.toString());
//                        } else {
//                            return true;
//                        }
//                    }
                    return true;
                })
                .executes(this::run)
                .build();

        // Register the command
        dispatcher.getRoot().addChild(root);

        // Register aliases
        for (String alias : aliases) {
            dispatcher.register(Commands.literal(alias).redirect(root).executes(this::run));
        }

        // Register subcommands
        for (Subcommand subcommand : subcommands) {
            root.addChild(subcommand.build());
        }
    }

    /**
     * Execute the command
     * @param context Command context
     * @return Command result
     */
    public abstract int run(CommandContext<CommandSourceStack> context);
}
