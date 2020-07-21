package by.home.telegramBot.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.home.telegramBot.dao.DataDAO;
import by.home.telegramBot.entity.City;
import by.home.telegramBot.entity.User;

@Repository
public class DataDAOImpl implements DataDAO {

	private static final String SELECT_CITY_INFO = "FROM City WHERE city = :city";
	private static final String CITY_ATTR = "city";
	private static final String SELECT_USER_INFO = "FROM User WHERE username = :username";
	private static final String USERNAME_ATTR = "username";

	SessionFactory sessionFactory;

	@Autowired
	public DataDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(User user) {

		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.save(user);

	}

	@Override
	public City get(String cityName) {

		Session currentSession = sessionFactory.getCurrentSession();

		List<City> cityList = currentSession.createQuery(SELECT_CITY_INFO, City.class).setParameter(CITY_ATTR, cityName).getResultList();
		
		if(cityList.isEmpty()) {
			return null;
		} else {
			City myCity = cityList.get(0); 
			return myCity;
		}
		
		
	}

	@Override
	public boolean getUserInfo(String username) {

		Session currentSession = sessionFactory.getCurrentSession();

		List<User> user = currentSession.createQuery(SELECT_USER_INFO, User.class).setParameter(USERNAME_ATTR, username).getResultList();

		if (user.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
