package business;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import beans.User;
import data.UserDataInterface;
import data.UserDataService;
import util.ApplicationLogger;

public class UserBusinessService implements UserServiceInterface<User>
{
	@Override
	public boolean register(User user) {
		
		UserDataService UserDAO = new UserDataService();
		
		boolean result = UserDAO.create(user);
		
		return result;
		
	}

	@Override
	public boolean login(User user) 
	{
		UserDataService dao = new UserDataService();
		return dao.find(user);
	}
}
