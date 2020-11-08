package business;

import java.sql.SQLException;

import javax.ejb.EJB;
<<<<<<< HEAD
=======
import javax.inject.Inject;
>>>>>>> LoggingService
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

<<<<<<< HEAD
import beans.User;
import data.UserDataInterface;

@Stateless
@Local(UserServiceInterface.class)
@LocalBean
public class UserBusinessService implements UserServiceInterface{
	@EJB
	UserDataInterface<User> UserDAO;
	
	public UserBusinessService() {
		//
	}
	
=======
import org.apache.log4j.Logger;

import beans.User;
import data.UserDataInterface;
import data.UserDataService;
import util.ApplicationLogger;

public class UserBusinessService implements UserServiceInterface<User>
{
>>>>>>> LoggingService
	@Override
	public boolean register(User user) {		
		boolean result = UserDAO.create(user);
		return result;
	}

	@Override
	public boolean login(User user) 
	{
		return UserDAO.find(user);
	}
}
