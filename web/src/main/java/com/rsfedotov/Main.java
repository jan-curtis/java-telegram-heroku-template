package com.rsfedotov;

import com.pengrad.telegrambot.request.SetWebhook;
import com.rsfedotov.handler.BotHandlerBase;
import com.rsfedotov.handler.EchoBot;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        final String portNumber = System.getenv("PORT");
        if (portNumber != null) {
            port(Integer.parseInt(portNumber));
        }

        // current app url to set webhook
        // should be set via heroku config vars
        // https://devcenter.heroku.com/articles/config-vars
        // heroku config:set APP_URL=https://hidden-badlands-36172.herokuapp.com
        final String appUrl = System.getenv("APP_URL");

        // define list of bots
        BotHandlerBase[] bots = new BotHandlerBase[]{new EchoBot()};

        // set bot to listen https://my-app.heroku.com/BOTTOKEN
        // register this URL as Telegram Webhook
        for (BotHandlerBase bot : bots) {
            String token = bot.getToken();
            post("/" + token, bot);
            if (appUrl != null) {
                bot.getBot().execute(new SetWebhook().url(appUrl + "/" + token));
            }
        }
    }
}
