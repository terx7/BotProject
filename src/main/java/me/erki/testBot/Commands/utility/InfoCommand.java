package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.lang.reflect.Member;

public class InfoCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        String avatarUrl = event.getJDA().getSelfUser().getAvatarUrl();
        EmbedBuilder info = new EmbedBuilder();
            info.setThumbnail(avatarUrl);
            info.setTitle("Information");
            info.setDescription("notBot is a multi purpose bot made for server admin and casual users. Type ``-help`` to get a list of commands.");
            info.setColor(Color.decode("#ff8000"));
            event.getChannel().sendMessageEmbeds(info.build()).queue();

            info.clear();

        return true;
    }

    @Override
    public String name() {
        return "Info";
    }

    @Override
    public String description() {
        return "Displays bot info.\nUsage: ``-info``";
    }

    @Override
    public String alias() {
        return "info";
    }
}
