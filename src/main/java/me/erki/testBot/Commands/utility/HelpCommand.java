package me.erki.testBot.Commands.utility;

import me.erki.testBot.Main;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Objects;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {

        HashMap<String, CommandExecutor> commands = Main.COMMANDS;

        if (args.length == 0) {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("Help");
            help.setDescription("Write ``-help (type)`` for information on commands from particular command types.\nFor information on a particular command write ``-help (command)``");
            help.addField("Types", ":cop: admin - Administration commands\n:soccer: fun - Fun commands\n:headphones: music - Music commands\n:wrench: utility - Utility commands", false);
            help.setColor(00000);
            event.getChannel().sendMessageEmbeds(help.build()).queue();

            help.clear();
        } else if (Objects.equals(args[0], "admin")) {
            EmbedBuilder admin = new EmbedBuilder();
            admin.setTitle("Administration commands");
            admin.setDescription("Commands that help you moderate your server.");
            admin.addField("Commands", "``ban``\n``unban``\n``kick``\n``mute``\n``unmute``\n``createrole``\n``addrole``\n``removerole``", false);
            admin.setColor(00000);
            event.getChannel().sendMessageEmbeds(admin.build()).queue();

            admin.clear();
        } else if (Objects.equals(args[0], "fun")) {
            EmbedBuilder fun = new EmbedBuilder();
            fun.setTitle("Fun commands");
            fun.setDescription("Commands for entertainment.");
            fun.addField("Commands", "``hello``\n``roll``\n``8ball``\n``avatar``", false);
            fun.setColor(00000);
            event.getChannel().sendMessageEmbeds(fun.build()).queue();

            fun.clear();
        } else if (Objects.equals(args[0], "music")) {
            EmbedBuilder music = new EmbedBuilder();
            music.setTitle("Music commands");
            music.setDescription("Commands for enjoying your favorite music. You must be in the same voice channel as the bot for those to work (the bot automatically joins upon using the play command unless it is already in another voice channel).");
            music.addField("Commands", "``play``\n``nowplaying``\n``skip``\n``stop``\n``repeat``\n``queue``\n``shuffle``", false);
            music.setColor(00000);
            event.getChannel().sendMessageEmbeds(music.build()).queue();

            music.clear();
        } else if (Objects.equals(args[0], "utility")) {
            EmbedBuilder utility = new EmbedBuilder();
            utility.setTitle("Utility commands");
            utility.setDescription("Commands that help you with the bot and server.");
            utility.addField("Commands", "``help``\n``info``\n``nick``\n``nickremove``\n``ping``", false);
            utility.setColor(00000);
            event.getChannel().sendMessageEmbeds(utility.build()).queue();

            utility.clear();
        }else if (commands.containsKey(args[0])){
            System.out.println(commands.get(args[0]));
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(commands.get(args[0]).name() + " usages");
            embed.setDescription(commands.get(args[0]).description());
            embed.setColor(00000);
            event.getChannel().sendMessageEmbeds(embed.build()).queue();

            embed.clear();
        }
        return true;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Displays commands and info on specific commands.\nUsage: ``-help (type or command)``";
    }

    @Override
    public String alias() {
        return "help";
    }
}
