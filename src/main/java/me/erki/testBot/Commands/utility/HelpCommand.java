package me.erki.testBot.Commands.utility;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("Help");
            help.setDescription("Write -help (type) for information on particular commands.");
            help.addField("Types", " :cop: admin - Administration commands \n :soccer: fun - Fun commands \n :headphones: music - Music commands \n :wrench: utility - Utility commands", false);
            help.setColor(00000);
            event.getChannel().sendMessageEmbeds(help.build()).queue();

            help.clear();
        } else if (Objects.equals(args[0], "admin")) {
            EmbedBuilder admin = new EmbedBuilder();
            admin.setTitle("Administration commands");
            admin.setDescription("Commands that help you moderate your server.");
            admin.addField("Commands", " ``ban`` Bans the chosen member. ``-ban @user reason`` \n ``kick`` Kicks the chosen member. ``-kick @user`` \n ``mute`` Mutes/Times out the chosen member. ``-mute @user duration(minutes)`` \n ``unmute`` Unmutes/Untimes out the chosen member. ``-unmute @user`` \n ``createrole`` Creates a role in your server. ``-createrole rolename color[hex] hoisted[true or false]`` \n ``addrole`` Adds a role to a chosen member. ``-addrole @user rolename`` \n ``removerole`` Removes a role from a chosen member. ``-removerole @user rolename``", false);
            admin.setColor(00000);
            event.getChannel().sendMessageEmbeds(admin.build()).queue();

            admin.clear();
        } else if (Objects.equals(args[0], "fun")) {
            EmbedBuilder fun = new EmbedBuilder();
            fun.setTitle("Fun commands");
            fun.setDescription("Commands for entertainment.");
            fun.addField("Commands", " hello - The bot greets you. (-hello) \n roll - The bot rolls a random number from 1-100. (-roll) \n 8ball - The bot answers your question with a yes, no, or maybe answer. (-8ball question) \n avatar - The bot posts your avatar as a larger image, if you mention another user in the command, it posts theirs instead. (-avatar @user)", false);
            fun.setColor(00000);
            event.getChannel().sendMessageEmbeds(fun.build()).queue();

            fun.clear();
        } else if (Objects.equals(args[0], "music")) {
            EmbedBuilder music = new EmbedBuilder();
            music.setTitle("Music commands");
            music.setDescription("Commands for enjoying your favorite music. You must be in the same voice channel as the bot for those to work (the bot automatically joins upon using the play command unless it is already in another voice channel).");
            music.addField("Commands", " play - The bot plays your chosen song. (-play URL) \n nowplaying - The bot displays the current song that is playing. (-nowplaying) \n skip - The bot skips the current song. (-skip) \n stop - The bot stops the music and clears the queue. (-stop) \n queue - The bot displays the current queued songs. (-queue)", false);
            music.setColor(00000);
            event.getChannel().sendMessageEmbeds(music.build()).queue();

            music.clear();
        } else if (Objects.equals(args[0], "utility")) {
            EmbedBuilder utility = new EmbedBuilder();
            utility.setTitle("Utility commands");
            utility.setDescription("Commands that help you with the bot and server.");
            utility.addField("Commands", " help - The bot displays different commands that you can use. (-help type) \n info - The bot displays some information about itself. (-info) \n nick - The bot changes your nickname. (-nick nickname) \n nickremove - The bot removes your nickname, reverting it to your default name. (-nickremove) \n ping - The bot displays its latency to the server. (-ping)", false);
            utility.setColor(00000);
            event.getChannel().sendMessageEmbeds(utility.build()).queue();

            utility.clear();
        }
        return true;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Displays usable commands";
    }

    @Override
    public String alias() {
        return "help";
    }
}
