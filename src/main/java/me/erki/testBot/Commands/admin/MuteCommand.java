package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Duration;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static java.lang.Integer.parseInt;


public class MuteCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.KICK_MEMBERS);

        Duration muteDuration = Duration.ofMinutes(parseInt(Arrays.stream(args).toList().get(args.length - 1)));

        if(hasPermission || isOwner){
            if(args.length == 0){
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<Member> muteList = event.getMessage().getMentionedMembers();

                for (Member i : muteList) {
                    {
                        event.getGuild().timeoutFor( i, muteDuration).queue();
                        channel.sendMessage(i.getUser().getAsMention() + " was muted for " + muteDuration.toMinutes() + " minutes!").queue();
                    }
                    return true;
                }

            }

        }
        return true;
    }


    @Override
    public String name() { return "mute"; }

    @Override
    public String description() { return "Times a user out/Mutes a user.\nUsage: ``-mute @user duration(minutes)``"; }

    @Override
    public String alias() { return "Mute"; }
}