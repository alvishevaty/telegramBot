package by.home.telegramBot.entity;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.home.telegramBot.service.TelegramRequestHandler;

public class MyTelegramBot extends TelegramWebhookBot {
	
	private TelegramRequestHandler telegramRequestHandler;
	
	public MyTelegramBot(TelegramRequestHandler telegramRequestHandler) {
		this.telegramRequestHandler = telegramRequestHandler;
	}

	private String botUserName;
	private String webHookPath;
	private String botToken;
	
	@Override
	public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

		System.out.println("onWebhookUpdateReceived");

		BotApiMethod<?> responseMessage = telegramRequestHandler.updateHandler(update);

		return responseMessage;
	}

	@Override
	public String getBotUsername() {
		return botUserName;
	}

	@Override
	public String getBotPath() {
		return webHookPath;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	public void setBotUserName(String botUserName) {
		this.botUserName = botUserName;
	}

	public void setWebHookPath(String webHookPath) {
		this.webHookPath = webHookPath;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

}
