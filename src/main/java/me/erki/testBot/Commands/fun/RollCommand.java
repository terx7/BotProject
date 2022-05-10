package me.erki.testBot.Commands.fun;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RollCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        byte roll = (byte) (Math.random() * 100);
        String user = event.getAuthor().getAsMention();
        MessageChannel channel = event.getChannel();
        channel.sendMessage(user + " rolled " + roll).queue();
        return true;
    }

    @Override
    public String name() {
        return "roll";
    }

    @Override
    public String description() {
        return "The bot rolls a number from 1-100\nUsage: ``-roll``";
    }

    @Override
    public String alias() {
        return "Roll";
    }
}
