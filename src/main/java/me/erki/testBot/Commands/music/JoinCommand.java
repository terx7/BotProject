package me.erki.testBot.Commands.music;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        System.out.println();
        final MessageChannel channel = event.getChannel();
//        final SelfUser self = event.getGuild().getVo;
//        final VoiceChannel connectedChannel
        return true;
    }

    @Override
    public String name() {
        return "join";
    }

    @Override
    public String description() {
        return "joins your voice chat";
    }

    @Override
    public String alias() {
        return "join";
    }
}
