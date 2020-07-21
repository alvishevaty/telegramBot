package by.home.telegramBot.dao;

import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

public interface DataDAO {
	
	void save(User user);
	void save(City city);
	
	boolean getUserInfo(int userId);
	
	City get(String city);

}
