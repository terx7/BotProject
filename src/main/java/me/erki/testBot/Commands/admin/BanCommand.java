package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;


public class BanCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        String message = event.getMessage().getContentRaw();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.BAN_MEMBERS);
        if (hasPermission || isOwner){
            if(args.length == 0){

                channel.sendMessage("no user given").queue();

                return true;
            }else{

                List<User> banList = event.getMessage().getMentionedUsers();

                String target = Arrays.stream(args).toList().get(0);
                String reason = event.getMessage().getContentRaw().replace("-ban " + target, "");

                boolean containsReason = banList.contains(reason);

                for (User i : banList) {
                    if(!containsReason){
                        event.getGuild().ban(i.getId(),0, reason).queue();
                        channel.sendMessage(i.getAsTag() + " was banned! Reason: " + reason).queue();
                    }else {
                        event.getGuild().ban(i.getId(), 0,"ban command").queue();
                        channel.sendMessage(i.getAsTag() + " was banned! Reason: reason not given").queue();
                    }
                        return true;
                }

            }

        }
        return true;
        }


    @Override
    public String name() { return "ban"; }

    @Override
    public String description() { return "Bans a user from the server.\nUsage: ``-ban @user reason``"; }

    @Override
    public String alias() { return "Ban"; }
    }