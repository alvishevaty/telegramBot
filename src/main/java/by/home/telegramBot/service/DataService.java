package by.home.telegramBot.service;

import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

public interface DataService {

	void save(User user);
	
	boolean getUserInfo(String username);

	City get(String city);

}
