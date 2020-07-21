package by.home.telegramBot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.home.telegramBot.service.TelegramRequestHandler;

@Configuration
@ConfigurationProperties(prefix = "bot")
public class MyTelegramBotConfig {

	private String botUserName;
	private String botToken;
	private String webHookPath;

	@Bean
	public MyTelegramBot myTelegramBot(TelegramRequestHandler telegramRequestHandler) {

		MyTelegramBot telegramBot = new MyTelegramBot(telegramRequestHandler);
		telegramBot.setBotUserName(botUserName);
		telegramBot.setBotToken(botToken);
		telegramBot.setWebHookPath(webHookPath);

		return telegramBot;

	}

	public String getBotUserName() {
		return botUserName;
	}

	public void setBotUserName(String botUserName) {
		this.botUserName = botUserName;
	}

	public String getBotToken() {
		return botToken;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

	public String getWebHookPath() {
		return webHookPath;
	}

	public void setWebHookPath(String webHookPath) {
		this.webHookPath = webHookPath;
	}

}
