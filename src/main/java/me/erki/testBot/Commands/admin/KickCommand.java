package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;


public class KickCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isModerator = event.getMember().getRoles().toString().toLowerCase().contains("moderator");
        boolean isAdmin = event.getMember().getRoles().toString().toLowerCase().contains("admin");
        boolean isOwner = event.getMember().isOwner();
        if(isAdmin || isOwner || isModerator){
            if(args.length == 0){ //no argument
                //error message
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<User> kickList = event.getMessage().getMentionedUsers();

                for (User i : kickList) {
                    System.out.println(i.getId());
                    {
                        event.getGuild().kick(i.getId()).queue();
                        channel.sendMessage(i.getAsTag() + " was kicked!").queue();
                    }
                    return true;
                }

            }

        }
        return true;
    }


    @Override
    public String name() { return "Kick"; }

    @Override
    public String description() { return "Kicks a user from the server."; }

    @Override
    public String alias() { return "Kick"; }
}