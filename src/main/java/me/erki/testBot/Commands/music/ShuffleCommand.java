package me.erki.testBot.Commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.erki.testBot.LavaPlayer.GuildMusicManager;
import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.Queue;


public class ShuffleCommand implements CommandExecutor {
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        final MessageChannel channel = event.getChannel();
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        Queue<AudioTrack> queue = musicManager.scheduler.queue;
        musicManager.scheduler.shuffle();
        return true;
    }

    @Override
    public String name() {
        return "shuffle";
    }

    @Override
    public String description() {
        return "shuffle";
    }

    @Override
    public String alias() {
        return "shuffle";
    }
}
