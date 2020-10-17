package data;

import beans.User;

public class UserManagement
{
	private static UserManagement _instance;
	public User user = new User();
	
	public static UserManagement getInstance()
	{
		if(_instance == null)
		{
			_instance = new UserManagement();
		}
		return _instance;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
}
