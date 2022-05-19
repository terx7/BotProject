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
            if(args.length == 0){
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                String member = args[0];
                try{
                    event.getGuild().unban(member).queue();
                    channel.sendMessage("User was successfully unbanned").queue();
                }catch (Exception e){
                    channel.sendMessage("Something went wrong.").queue();
                }
            }

        }
        return true;
    }


    @Override
    public String name() { return "unban"; }

    @Override
    public String description() { return "Unbans a user from the server.\nUsage: ``-unban userid``"; }

    @Override
    public String alias() { return "Unban"; }
}