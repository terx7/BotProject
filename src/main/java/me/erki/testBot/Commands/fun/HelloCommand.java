package me.erki.testBot.Commands.fun;

import me.erki.testBot.Utils.CommandExecutor;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelloCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        String user = event.getAuthor().getAsMention();
        MessageChannel channel = event.getChannel();

        channel.sendMessage("Hello " + user + "!").queue();

        return true;
    }

    @Override
    public String name() {
        return "hello";
    }

    @Override
    public String description() {
        return "The bot greets you.\nUsage: ``-hello``";
    }

    @Override
    public String alias() {
        return null;
    }
}
