package business;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beans.User;
import data.UserDataInterface;
import org.apache.log4j.Logger;
import beans.User;
import data.UserDataInterface;
import data.UserDataService;
import util.ApplicationLogger;

@Stateless
@Local(UserServiceInterface.class)
@LocalBean
public class UserBusinessService implements UserServiceInterface
{
	@EJB
	UserDataInterface<User> UserDAO;
	
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
