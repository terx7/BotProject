package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class InfoCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
            EmbedBuilder info = new EmbedBuilder();
               info.setTitle("Information");
               info.setDescription("The best bot in this server");
               info.addField("Creator", "tictakid", false);
               info.setColor(00000);
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
