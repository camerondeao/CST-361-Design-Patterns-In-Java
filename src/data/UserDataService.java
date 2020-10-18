package data;

import java.sql.*;

public class UserDataService 
{
	public boolean login(String username, String password) 
	{
		
		System.out.println("Username: " + username + " Password: " + password);
		if(checkLogin(username, password))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean checkLogin(String username, String password)
	{
		try
		{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sys", "root", "root");	

//			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "connection");	
			PreparedStatement statement = myConn.prepareStatement("SELECT * from users WHERE username = ?");
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{			
				if(rs.getString("username").equals(username) && rs.getString("password").equals(password))
				{
					return true;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
