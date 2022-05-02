package me.erki.testBot.Commands.admin;

import me.erki.testBot.Utils.CommandExecutor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.Command;


import java.time.temporal.ChronoUnit;


public class BanCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
            if(args.length==1){ //no argument
                //error message
            }
//            else if(event.getMentionedMembers().isEmpty()){//no mentioned members, try to use argument as ID
//                event.getGuild().ban(args[1],0,"ban command").queue();
//            }else{//mentioned members
//                event.getGuild().ban(event.getAsMention().get(0),0,"ban command").queue();
//            }
        System.out.println(args);
        return true;
        }


    @Override
    public String name() { return "Ban"; }

    @Override
    public String description() { return "Bans a user from the server."; }

    @Override
    public String alias() { return "Ban"; }
    }