package util;

import java.util.List;

import beans.User; 
import data.UserDataService;

public class UserDTO implements FactoryDTO<User> 
{

	@Override
	public User findBy(int t) 
	{
		UserDataService service = new UserDataService();
		return service.findById(t);
	}

	@Override
	public List<User> findAll(String t) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
