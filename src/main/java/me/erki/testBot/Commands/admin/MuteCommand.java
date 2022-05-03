package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Duration;

import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;


public class MuteCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isModerator = event.getMember().getRoles().toString().toLowerCase().contains("moderator");
        boolean isAdmin = event.getMember().getRoles().toString().toLowerCase().contains("admin");
        boolean isOwner = event.getMember().isOwner();

        Duration muteDuration = Duration.ofMinutes(parseInt(Arrays.stream(args).toList().get(args.length - 1)));

        if(isAdmin || isOwner || isModerator){
            if(args.length == 0){ //no argument
                //error message
                channel.sendMessage("no user given").queue();
                return true;
            }else{
                List<Member> muteList = event.getMessage().getMentionedMembers();

                for (Member i : muteList) {
                    System.out.println(i.getId());
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
    public String name() { return "Mute"; }

    @Override
    public String description() { return "Times a user out/Mutes a user."; }

    @Override
    public String alias() { return "Mute"; }
}