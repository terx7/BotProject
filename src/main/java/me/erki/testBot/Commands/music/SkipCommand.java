package me.erki.testBot.Commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import me.erki.testBot.LavaPlayer.GuildMusicManager;
import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@SuppressWarnings("ConstantConditions")
public class SkipCommand implements CommandExecutor {
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
        final AudioPlayer audioPlayer = musicManager.audioPlayer;

        if (audioPlayer.getPlayingTrack() == null) {
            channel.sendMessage("There is no track playing currently.").queue();
            return true;
        }

        musicManager.scheduler.nextTrack();

        return true;
    }

    @Override
    public String name() {
        return "skip";
    }

    @Override
    public String description() {
        return "Skips the currently playing track.\nUsage: ``-skip``";
    }

    @Override
    public String alias() {
        return "skip";
    }
}
