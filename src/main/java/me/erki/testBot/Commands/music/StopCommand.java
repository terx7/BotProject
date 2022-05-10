package me.erki.testBot.Commands.music;

import me.erki.testBot.LavaPlayer.GuildMusicManager;
import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

@SuppressWarnings("ConstantConditions")
public class StopCommand implements CommandExecutor {
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
        final AudioManager audioManager = event.getGuild().getAudioManager();

        musicManager.scheduler.player.stopTrack();
        musicManager.scheduler.queue.clear();
        audioManager.closeAudioConnection();

        channel.sendMessage("The player has been stopped and the queue has been cleared.").queue();

        return true;
    }

    @Override
    public String name() {
        return "stop";
    }

    @Override
    public String description() {
        return "stop";
    }

    @Override
    public String alias() {
        return "stop";
    }
}
