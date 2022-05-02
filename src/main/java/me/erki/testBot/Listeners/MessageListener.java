package me.erki.testBot.Listeners;

import me.erki.testBot.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        if(event.getAuthor().isBot() || event.isWebhookMessage()){
            return;
        }
        if(event.getChannelType().equals(ChannelType.TEXT)){

            if(event.getMessage().getContentRaw().startsWith(Main.prefix)){
                try{
                    Main.executeCommand(Main.prefix, event);
                } catch (IOException e) {
                    event.getChannel().sendMessage("An error occurred when trying to execute this command").queue();
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println(event.getJDA().getSelfUser().getId() + " " + event.getMessage());
        String selfID = event.getJDA().getSelfUser().getAsMention();
        String[] splitMessage = event.getMessage().getContentRaw().split(" ");
        for(String i : splitMessage){
            System.out.println(selfID);
            System.out.println(i);
            if(selfID.equals(i)){
                String user = event.getAuthor().getAsMention();
                MessageChannel channel = event.getChannel();
                channel.sendMessage("Hello " + user).queue();
            }
        }
    }
}
