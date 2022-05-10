package me.erki.testBot.Commands.music;

import me.erki.testBot.LavaPlayer.PlayerManager;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;


public class PlayCommand implements CommandExecutor {
    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {

        final MessageChannel channel = event.getChannel();
        final Member self = event.getGuild().getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();


        if (args.length == 0){
            channel.sendMessage("Correct usage is `-play <youtube link>`").queue();
            return true;
        }

        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();


//        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
//            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
//            return true;
//        }

        if(selfVoiceState.inAudioChannel() && !memberVoiceState.getChannel().equals(selfVoiceState.getChannel())){
            channel.sendMessage("I'm already in a voice channel.").queue();
            return true;
        }

        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work.").queue();
            return true;
        }

        final AudioManager audioManager = event.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);


        String link = String.join(" ", args);

        if(!isUrl(link)){
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance()
                .loadAndPlay(event, channel, link);

        return true;
    }

    @Override
    public String name() {
        return "play";
    }

    @Override
    public String description() {
        return "Plays the YouTube audio linked in the URL.\nUsage: ``-play URL``";
    }

    @Override
    public String alias() {
        return "play";
    }

    private boolean isUrl(String url){
        try {
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }
}
