package by.home.telegramBot.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.home.telegramBot.dao.DataDAO;
import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;
import by.home.telegramBot.service.DataService;

@Service
public class DataServiceImpl implements DataService{
	
	private DataDAO dataDAO;
	
	@Autowired
	public DataServiceImpl(DataDAO dataDAO) {
		this.dataDAO = dataDAO;
	}
	
	@Override
	@Transactional
	public void save(User user) {
		
		dataDAO.save(user);
		
	}

	@Override
	@Transactional
	public City get(String cityName) {
		City city = dataDAO.get(cityName);
		return city;
	}

	@Override
	public boolean getUserInfo(int userId) {
		boolean chechUser = dataDAO.getUserInfo(userId);
		return chechUser;
	}

	@Override
	public void save(City city) {
		dataDAO.save(city);
		
	}

}
