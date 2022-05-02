
package me.erki.testBot.Commands;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class EightBallCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        String[] answers = {
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes – definitely.",
                "You may rely on it.",
                "As I see it, yes.",
                "Most likely.",
                "Outlook good.",
                "Yes.",
                "Signs point to yes.",
                "Reply hazy, try again.",
                "Ask again later.",
                "Better not tell you now.",
                "Cannot predict now.",
                "Concentrate and ask again.",
                "Don't count on it.",
                "My reply is no.",
                "My sources say no.",
                "Outlook not so good.",
                "Very doubtful.",
        };

        int ballAnswers = new Random().nextInt(20);

        String ballQuestion = "";
        for (String a : args) {
            ballQuestion += a + " ";
        }

        MessageChannel channel = event.getChannel();
        if (ballQuestion.isBlank()) {
            channel.sendMessage("Please ask a question!").queue();
        } else channel.sendMessage(
                "Question: " + ballQuestion + "\n" +
                "Answer: " + answers[ballAnswers]
        ).queue();
        return true;
    }

    @Override
    public String name() {
        return "8ball";
    }

    @Override
    public String description() {
        return "Provides an answer to all your questions";
    }

    @Override
    public String alias() {
        return "Magic 8 ball";
    }
}
