package me.erki.testBot;


import me.erki.testBot.Listeners.MessageListener;
import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class Main {

    private static String bot_token = "OTY1NjUzMzIyNTE2Mjk1Nzcw.Yl2UvQ.cyw1QoNcqkkX1A7_3nxkgR6D0bM";

    private static HashMap<String, CommandExecutor> COMMANDS = new HashMap<>();
    public static String prefix = "-";

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(bot_token);

        builder.setActivity(Activity.watching("Loading..."));
        builder.addEventListeners(new MessageListener());
        builder.addEventListeners(new ListenerAdapter() {
           @Override
           public void onReady(@NotNull ReadyEvent event){
               event.getJDA().getPresence().setActivity(Activity.watching("Loading..."));

               event.getJDA().getPresence().setActivity(Activity.watching("over " + event.getGuildTotalCount() + " servers."));
               System.out.println("Bot loaded successfully!");
           }
        });

        builder.build();


    }

    public static void executeCommand(String prefix, MessageReceivedEvent event) throws IOException {
        String chatMessage = event.getMessage().getContentRaw();
        String[] splitCommand = chatMessage.split(" ");
        String command = splitCommand[0].replaceFirst("[" + prefix + "]", "");
        String commandArgs = event.getMessage().getContentRaw().replace(prefix + command, "");
        commandArgs = commandArgs.replaceFirst(" ", "");
        String[] args = commandArgs.split(" ");

        if (args.length == 1) {
            if (args[0].isEmpty() || args[0].equals(" ") || args[0].equals(prefix + command) || args[0].equals(prefix)) {
                args = new String[0];
            }
        }
        System.out.println(command);
        System.out.println(COMMANDS);
        if(COMMANDS.get(command.toLowerCase()) != null){
            System.out.println("???");
            COMMANDS.get(command.toLowerCase()).execute(args, event);
            if(!COMMANDS.get(command.toLowerCase()).execute(args, event)){
                event.getChannel().sendMessage("This command is currently disabled!").queue();
            }

        }
    }

}
