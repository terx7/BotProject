package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EnumSet;
import java.util.List;

public class AddRoleCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        boolean isOwner = event.getMember().isOwner();
        EnumSet<Permission> isAdmin2 = event.getMember().getPermissions();
        boolean hasPermission = isAdmin2.contains(Permission.MANAGE_ROLES);
        if (hasPermission || isOwner) {
            if (args.length == 0) {
                //no argument
                //error message
                channel.sendMessage("No arguments given").queue();
                return true;
            } else {
                Role role = event.getGuild().getRolesByName("Suspended", false).get(0);
                List<User> mentionedUsers = event.getMessage().getMentionedUsers();
                for (User mentionedUser : mentionedUsers) {
                    event.getGuild().addRoleToMember((Member) mentionedUser, role).queue();
                }
            }
        }
        return true;
    }


    @Override
    public String name () { return "CreateRole"; }

    @Override
    public String description () { return "Creates a role."; }

    @Override
    public String alias () { return "CreateRole"; }
}