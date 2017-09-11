package com.Wolfstorm.bot;

import com.Wolfstorm.bot.command.fun.chooseCommand;
import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import com.jagrosh.jdautilities.commandclient.examples.AboutCommand;
import com.jagrosh.jdautilities.commandclient.examples.PingCommand;
import com.jagrosh.jdautilities.commandclient.examples.ShutdownCommand;
import com.jagrosh.jdautilities.waiter.EventWaiter;
import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;


public class Bot
{
    public static void main(String[] args) throws IOException, LoginException, RateLimitedException, IllegalArgumentException {

       //Config.JSON contains two lines
       List<String> list = Files.readAllLines(Paths.get("config.json"));

       //Get BotToken from Config.JSON
       String botToken = list.get(0);

       //Get OwnerID from Config.JSON
       String ownerID = list.get(1);

       //Define EventWaiter - Add this to JDA
        EventWaiter wait4evt = new EventWaiter();

        //Command Client
        CommandClientBuilder client = new CommandClientBuilder();

        //Default Command - "Type [p]Help" (NOTE = [p] is Prefix)
        client.useDefaultGame();

        //Set OwnerID
        client.setOwnerId(ownerID);

        //Emojis to indicate Success, Warning and Failure
        client.setEmojis("\uD83C\uDF96","⚠️", "✖️");

        //set Prefix
        client.setPrefix("+");

        //add Commands
        client.addCommands(
                //About Command
                new AboutCommand(Color.RED, "WolfstormBot 0.1-dev",
                        new String[]{"Commands", "Examples", "and more..."},
                        new Permission[]{Permission.ADMINISTRATOR}),

                //Ping Command
                new PingCommand(),

                //Choose Command
                new chooseCommand(),

                //Shutdown Command
                new ShutdownCommand());

        //Start The Bot
        new JDABuilder(AccountType.BOT)
                //Set Not Token
                .setToken(botToken)

                //Set Status while Bot is Loading
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setGame(Game.of("LOADING....."))

                //Add All Listeners here
                .addEventListener(wait4evt)
                .addEventListener(client.build())

                //Start Up HYPE!
                .buildAsync();
    }
}

