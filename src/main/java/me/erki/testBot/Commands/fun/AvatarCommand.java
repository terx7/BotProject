package me.erki.testBot.Commands.fun;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class AvatarCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        List<Member> userList = event.getMessage().getMentionedMembers();
        String avatar = userList.get(1).getAvatarUrl();
        channel.sendMessage(avatar).queue();
        return true;
    }

    @Override
    public String name() {
        return "Avatar";
    }

    @Override
    public String description() {
        return "Get avatar of mentioned user";
    }

    @Override
    public String alias() {
        return "Avatar";
    }
}
