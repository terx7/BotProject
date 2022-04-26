package me.erki.testBot.Listeners;

import me.erki.testBot.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println(event.getAuthor().isBot());
        if(event.getAuthor().isBot() || event.isWebhookMessage()){
            return;
        }
        if(event.getChannelType().equals(ChannelType.TEXT)){
            if(event.getMessage().getContentRaw().startsWith(Main.prefix)){
                try{
                    Main.executeCommand(Main.prefix, event);
                } catch (Exception exception) {
                    event.getChannel().sendMessage("An error occurred when trying to execute this command.").queue();
                    throw new RuntimeException(exception);
                }
            }
        }
    }
}
