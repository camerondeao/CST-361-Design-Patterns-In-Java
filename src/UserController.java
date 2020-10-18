
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import beans.User;
import business.UserBusinessService;
import business.UserServiceInterface;
import data.UserDataService;
import data.UserManagement;

@ManagedBean
@ViewScoped
public class UserController {
	
	
	//UserDataService service;
	
	
	
	public String onLogin(User user) throws SQLException
	{
		try
		{
			UserBusinessService UserBS = new UserBusinessService();
			//user = UserBS.login(user);
			if(UserBS.login(user))
				{
				
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				
				return "homePage.xhtml";
				
				}else {
					return "loginFail.xhtml";
				}
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred");
			e.printStackTrace();
		}
		
		
		return "loginFail.xhtml";
		
		


	}
	public String onRegister(User user) throws SQLException
	{
		
		try 
		{
			UserBusinessService UserBS = new UserBusinessService();

			UserBS.register(user);
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception: occurred");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		return "registerResponse.xhtml";
		
		
		
	}
}

