package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;
import java.util.List;


public class UnmuteCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.KICK_MEMBERS);

        if (hasPermission || isOwner){
            if(args.length == 0){ //no argument
                //error message
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<Member> muteList = event.getMessage().getMentionedMembers();

                for (Member i : muteList) {
                    System.out.println(i.getId());
                    {
                        event.getGuild().removeTimeout(i).queue();
                        channel.sendMessage(i.getUser().getAsMention() + " was unmuted!").queue();
                    }
                    return true;
                }

            }

        }
        return true;
    }


    @Override
    public String name() { return "Unmute"; }

    @Override
    public String description() { return "Takes a user out of timeout/Unmutes a user."; }

    @Override
    public String alias() { return "Unmute"; }
}