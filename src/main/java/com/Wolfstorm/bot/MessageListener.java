package com.Wolfstorm.bot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent evt){
        if (evt.getAuthor().isBot()) return;
        Message msg = evt.getMessage();
        String content = msg.getRawContent();

        if (content.equals("+ping")){
            MessageChannel chn = evt.getChannel();
            chn.sendMessage("Pong!").queue();
        }
    }
}
