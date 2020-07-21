package by.home.telegramBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

@Component
public class TelegramRequestHandler {
	
	
	private DataService dataService;
	
	public TelegramRequestHandler(DataService dataService) {
		this.dataService = dataService;
	}

	private static final Logger logger = LoggerFactory.getLogger(TelegramRequestHandler.class);

	public BotApiMethod<?> updateHandler(Update update) {

		SendMessage responseMessage = null;
		Message message = update.getMessage();

		if (message != null && message.hasText()) {
			logger.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
					message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
			
			saveUserInfo(message);

			responseMessage = inputMessageHendler(message);
		}

		return responseMessage;
	}

	private void saveUserInfo(Message message) {
		String username = message.getFrom().getUserName();
		int userId = message.getFrom().getId();
		Long chatId = message.getChatId();
		
		User user = new User(username, Integer.toString(userId), Long.toString(chatId));
		
		boolean checkUserInfo = dataService.getUserInfo(username);
		
		if(checkUserInfo == false) {
			dataService.save(user);
		}

		
	}

	public SendMessage inputMessageHendler(Message message) {

		SendMessage sendMessage = new SendMessage();
		Long chatId = message.getChatId();
		int messageId = message.getMessageId();
		String usersMessage = message.getText();

		if (usersMessage.equals("/start")) {
			sendMessage.setChatId(chatId);
			sendMessage.setReplyToMessageId(messageId);
			sendMessage
					.setText("Введите название города, например: Будапешт, Рим или Санкт-Петербург, и ожидайте ответ.");
		} else if(usersMessage.equals("/help")) {
			sendMessage.setChatId(chatId);
			sendMessage.setReplyToMessageId(messageId);
			sendMessage.setText(
					"Бот позволяет получить факты о городах. Просто введите интересующий город из перечисленных: Будапешт, Рио-де-Жанейро, Новосибирск, Санкт-Петербург, Гамбург, Рим, Екатеринбург, Сидней, Стокгольм, Лондон.");
		} else {
			sendMessage.setChatId(chatId);
			sendMessage.setReplyToMessageId(messageId);
			City myCity = dataService.get(usersMessage);
			if(myCity != null) {
				sendMessage.setText(myCity.getDescription());
			} else {
				sendMessage.setText("Информации о таком городе нет, попробуйте выбрать другой город. Информация о доступных городах: /help.");
			}
			
		}

		return sendMessage;

	}

}
