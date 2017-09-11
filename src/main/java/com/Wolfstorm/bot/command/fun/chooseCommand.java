package com.Wolfstorm.bot.command.fun;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

public class chooseCommand extends Command {

    public chooseCommand()
    {
        this.name = "choose";
        this.help = "Get the bot to make a decision for you!";
        this.arguments = "<choice 1> <choice 2> ...";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent evt){
        if(evt.getArgs().isEmpty()){
            evt.replyWarning("You didnt give me any choices! :crying_cat_face: ");

        }
        else{
            String[] items = evt.getArgs().split("\\s+");

            if(items.length==1){
                evt.replyWarning("You only gave me one option, so here is your outcome, `"+items[0]+"`");

            }
            else{
                evt.replySuccess("Here is your outcome: `"+items[(int)(Math.random()*items.length)]+"`");
            }
        }
    }
}
