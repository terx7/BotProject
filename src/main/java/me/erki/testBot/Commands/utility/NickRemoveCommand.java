package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;


public class NickRemoveCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.NICKNAME_CHANGE);

        if(hasPermission){
            Member user = event.getMember();
                //error message
                user.modifyNickname("").queue();
                return true;
        }
        return true;
    }

    @Override
    public String name() { return "nickremove"; }

    @Override
    public String description() { return "Removes your nickname.\nUsage: ``-removenick``"; }

    @Override
    public String alias() { return "nickremove"; }
}