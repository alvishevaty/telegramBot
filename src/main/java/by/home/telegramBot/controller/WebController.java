package by.home.telegramBot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.home.telegramBot.entity.MyTelegramBot;

@RestController
public class WebController {

	private final MyTelegramBot telegramBot;

	public WebController(MyTelegramBot telegramBot) {
		this.telegramBot = telegramBot;
	}

	@PostMapping(value = "/")
	public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
		return telegramBot.onWebhookUpdateReceived(update);
	}


}
