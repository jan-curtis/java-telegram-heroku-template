package com.rsfedotov.handler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.rsfedotov.service.EchoService;

public class EchoBot extends BotHandlerBase {

    private static final String TOKEN = "1240357126:AAEijDaLiuRx-DeH4MZkmWpB6ysSrnLjf9E";
    private final TelegramBot bot = new TelegramBot(TOKEN);

    @Override
    public void onWebhookUpdate(Update update) {
        Long chatId = update.message().chat().id();
        bot.execute(new SendMessage(chatId,
                String.format("Processing request '%s' ...", update.message().text())));
        try {
            EchoService service = new EchoService();
            String result = service.process(update.message().text());
            bot.execute(new SendMessage(chatId, result).parseMode(ParseMode.HTML));
        } catch (Throwable e) {
            bot.execute(new SendMessage(chatId, "Server Error"));
        }
    }

    @Override
    public String getToken() {
        return TOKEN;
    }

    @Override
    public TelegramBot getBot() {
        return bot;
    }
}
