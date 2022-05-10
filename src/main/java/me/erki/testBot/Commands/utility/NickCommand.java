package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;


public class NickCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.NICKNAME_CHANGE);

        if(hasPermission){
            String user = event.getAuthor().getAsMention();
            if(args.length == 0) { //no argument
                //error message
                channel.sendMessage("no nickname change given").queue();
                return true;
            } else if (args.length == 1) {
                event.getGuild().getMember(User.fromId(user)).modifyNickname(args[0]);
                return true;
            } else {
                String newNick = String.valueOf(event.getMessage()).replace("-nick ", "");
                event.getGuild().getMember(User.fromId(user)).modifyNickname(newNick);
            }

        }
        return true;
    }

    @Override
    public String name() { return "Nick"; }

    @Override
    public String description() { return "Changes your nickname."; }

    @Override
    public String alias() { return "Nick"; }
}