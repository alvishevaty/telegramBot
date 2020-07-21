package by.home.telegramBot.service;

import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

public interface DataService {

	void save(User user);
	void save(City city);
	
	boolean getUserInfo(int userId);

	City get(String city);
	
	

}
