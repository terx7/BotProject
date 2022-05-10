package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.temporal.ChronoUnit;


public class PingCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        long ping = event.getMessage().getTimeCreated().until(event.getMessage().getTimeCreated(), ChronoUnit.MILLIS);
        channel.sendMessage ("Ping: " + ping  + "ms | Websocket: " + event.getJDA().getGatewayPing() + "ms").queue();
        return true;
    }


    @Override
    public String name() {
        return "Ping";
    }

    @Override
    public String description() {
        return "Checks the bots latency.\nUsage: ``-ping``";
    }

    @Override
    public String alias() {
        return "Pong";
    }
}