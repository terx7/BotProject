package me.erki.testBot.Commands.fun;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.swing.plaf.metal.MetalMenuBarUI;
import java.util.List;

public class AvatarCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();

        if(args.length > 0){
            List<Member> userList = event.getMessage().getMentionedMembers();
            String avatar = userList.get(0).getUser().getAvatarUrl();
            channel.sendMessage(avatar).queue();
            return true;
        }

        if (args.length == 0){
            String avatar = event.getAuthor().getAvatarUrl();
            channel.sendMessage(avatar).queue();
            return true;
        }
        channel.sendMessage("No avatar available.").queue();
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
