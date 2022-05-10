package me.erki.testBot.Commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.erki.testBot.LavaPlayer.GuildMusicManager;
import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@SuppressWarnings("ConstantConditions")
public class NowPlayingCommand implements CommandExecutor {

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
        final AudioTrack track = audioPlayer.getPlayingTrack();

        if (track == null) {
            channel.sendMessage("There is no track playing currently").queue();
            return true;
        }

        final AudioTrackInfo info = track.getInfo();

        channel.sendMessageFormat("Now playing `%s` by `%s` (Link: <%s>)", info.title, info.author, info.uri).queue();

        return true;
    }

    @Override
    public String name() {
        return "nowplaying";
    }

    @Override
    public String description() {
        return "Shows the currently playing track.\nUsage: ``-nowplaying``";
    }

    @Override
    public String alias() {
        return "playing";
    }
}
