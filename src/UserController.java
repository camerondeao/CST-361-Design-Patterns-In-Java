
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import beans.User;
import beans.WeatherData;
import business.GenerateWeatherData;
import business.UserBusinessService;
import business.UserServiceInterface;
import data.UserDataService;
import data.UserManagement;
import data.WeatherDataService;

@ManagedBean
@ViewScoped
public class UserController 
{
	public String onLogin(User user) throws SQLException
	{
		//INITIAL MESSAGE
		try
		{
			UserBusinessService UserBS = new UserBusinessService();
			if(UserBS.login(user))
			{
				checkWeatherData();
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				return "homePage.xhtml";
			}
			else 
			{
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
	
	private void checkWeatherData()
	{
		WeatherDataService dao = new WeatherDataService();
		GenerateWeatherData generate = new GenerateWeatherData();
		
		if(dao.checkData("Arizona"))
		{
			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
			String day = getDay();
			
			retrievedData = dao.findByLocation("Arizona");
			
			if(!Objects.equals(retrievedData.get(0), day))
			{
				WeatherData data = new WeatherData();
				data.setLocation("Arizona");
				data.setData(generate.shiftData(day, retrievedData));
				dao.update(data);
			}
		}
		else
		{
			List<WeatherData> data = new ArrayList<WeatherData>();
			
			for(int i = 0; i < 7; i++)
			{
				data = generate.generateData(data);
			}
			generate.setDays(data);
			
			WeatherData weatherData = new WeatherData();
			weatherData.setLocation(data.get(0).getLocation());
			weatherData.setData(data);
			dao.create(weatherData);
		}
	}
	
	private String getDay()
	{
		SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
		String day = simpleDate.format(new Date());
		return day;
	}
}