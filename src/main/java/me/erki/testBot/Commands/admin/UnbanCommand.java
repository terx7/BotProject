package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;
import java.util.List;


public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.BAN_MEMBERS);
        if (hasPermission || isOwner){
            if(args.length == 0){ //no argument
                //error message
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<User> unbanList = event.getMessage().getMentionedUsers();

                for (User i : unbanList) {
                    System.out.println(i.getId());
                    event.getGuild().unban(i.getId()).queue();
                    channel.sendMessage(i.getAsTag() + " was unbanned!").queue();
                    return true;
                }

            }

        }
        return true;
    }


    @Override
    public String name() { return "Unban"; }

    @Override
    public String description() { return "Unbans a user from the server."; }

    @Override
    public String alias() { return "Unban"; }
}