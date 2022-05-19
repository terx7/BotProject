package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;
import java.util.List;


@SuppressWarnings("ConstantConditions")
public class KickCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.KICK_MEMBERS);
        if(hasPermission || isOwner){
            if(args.length == 0){ //no argument
                //error message
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<User> kickList = event.getMessage().getMentionedUsers();

                for (User i : kickList) {
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
    public String name() { return "kick"; }

    @Override
    public String description() { return "Kicks a user from the server.\nUsage: ``-kick @user``"; }

    @Override
    public String alias() { return "Kick"; }
}