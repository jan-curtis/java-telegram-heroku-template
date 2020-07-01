package com.rsfedotov.handler;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import spark.Request;
import spark.Response;
import spark.Route;

abstract public class BotHandlerBase implements Route {

    private static final String DEFAULT_WELCOME_MESSAGE = "ok";
    private static final String DEFAULT_RESPONSE = "ok";

    @Override
    public Object handle(Request request, Response response) {
        Update update = BotUtils.parseUpdate(request.body());
        Message message = update.message();

        if (isStartMessage(message) && onStart()) {
            return getDefaultWelcomeMessage();
        } else {
            onWebhookUpdate(update);
        }
        return DEFAULT_RESPONSE;
    }

    protected String getDefaultWelcomeMessage() {
        return DEFAULT_WELCOME_MESSAGE;
    }

    protected boolean onStart() {
        return false;
    }

    public abstract void onWebhookUpdate(Update update);

    public abstract String getToken();

    public abstract TelegramBot getBot();

    private boolean isStartMessage(Message message) {
        return message != null && message.text() != null && message.text().startsWith("/start");
    }
}
