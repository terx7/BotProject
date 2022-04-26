package me.erki.testBot.Utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandExecutor {
    // always return true
    boolean execute(String[] args, MessageReceivedEvent event);

    String name();

    String description();

    String alias();
}
