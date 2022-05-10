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
            Member user = event.getMember();
            if(args.length == 0) { //no argument
                //error message
                channel.sendMessage("no nickname change given").queue();
                return true;
            } else {
                String newNick = event.getMessage().getContentRaw().replace("-nick ", "");
                System.out.println(newNick);
                user.modifyNickname(newNick).queue();
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