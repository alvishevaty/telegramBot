package by.home.telegramBot.dao;

import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

public interface DataDAO {
	
	void save(User user);
	
	boolean getUserInfo(String username);
	
	City get(String city);

}
