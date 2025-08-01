package com.c1ok.cobbledialogue.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

import java.util.List;

public class DialogueCommand extends BaseCommand{
    /**
     * Constructor for base command
     *
     * @param aliases       List of command aliases
     * @param subcommands   List of subcommands
     */
    public DialogueCommand(List<String> aliases, List<Subcommand> subcommands) {
        super("dialogue", aliases, subcommands);
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        return 0;
    }
}
