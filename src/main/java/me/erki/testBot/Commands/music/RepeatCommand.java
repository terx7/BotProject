package me.erki.testBot.Commands.music;

import me.erki.testBot.LavaPlayer.GuildMusicManager;
import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@SuppressWarnings("ConstantConditions")
public class RepeatCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        final MessageChannel channel = event.getChannel();
        final Member self = event.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return true;
        }

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final boolean newRepeating = !musicManager.scheduler.repeating;

        musicManager.scheduler.repeating = newRepeating;

        channel.sendMessageFormat("The player has been set to **%s**", newRepeating ? "repeating" : "not repeating").queue();

        return true;
    }

    @Override
    public String name() {
        return "repeat";
    }

    @Override
    public String description() {
        return "Repeats the currently playing song after it ends.\nThe same command can be used to stop repeating.\nUsage: ``-repeat``";
    }

    @Override
    public String alias() {
        return "repeat";
    }
}
