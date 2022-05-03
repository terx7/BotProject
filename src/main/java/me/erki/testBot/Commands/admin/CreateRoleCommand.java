package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class CreateRoleCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.ADMINISTRATOR);
            if (hasPermission || isOwner) {
//            if(args.length == 0 ) { //no argument
//                //error message
//                channel.sendMessage("No arguments given").queue();
//                return true;
            event.getGuild().createRole()
                    .setName("Tolgus")
                    .setColor(Color.red)
                    .setHoisted(true)
                    .setMentionable(false)
                    .queue(role -> {
                        System.out.println("Created role");
                    });

            }
        return true;
        }


    @Override
    public String name () { return "AddRole"; }

    @Override
    public String description () { return "Creates a role."; }

    @Override
    public String alias () { return "AddRole"; }
}
