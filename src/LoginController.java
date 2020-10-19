import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

//import beans.Login;
import beans.User;
import beans.WeatherData;
import business.GenerateWeatherData;
import business.UserServiceInterface;
import data.UserDataService;
import data.WeatherDataService;

//import beans.Login;
//import beans.User;
//import beans.WeatherData;
//import business.GenerateWeatherData;
//import business.UserBusinessService;
//import business.UserServiceInterface;
//import data.UserDataInterface;
//import data.WeatherDataService;
//import util.UserNotFoundException;

@ManagedBean
@ViewScoped
public class LoginController 
{
	WeatherDataService dao = new WeatherDataService();
	GenerateWeatherData generate = new GenerateWeatherData();
	
	
	
//	public String onSubmit(Login login) 
//	{
//		//Forwards to view along with the user bean
//		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("login", login);
//
//		UserBusinessService userService = new UserBusinessService();
//		if(userService.login(login.getUsername(), login.getPassword())) 
//		{
//
//			return "homePage.xhtml";
//		}
//		
//		
//		return "loginFail.xhtml";
//	}
		
	
//	public void logUser(Login login)
//	{
//		
//	}
	
	
	public void checkWeatherData()
	{
		if(dao.checkData("California"))
		{
			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
			String day = getDay();
			
			retrievedData = dao.findByLocation("California");
			
			if(!Objects.equals(retrievedData.get(0).getDay(), "Friday")) 
			{
				WeatherData data = new WeatherData();
				data.setLocation("California");
				data.setData(generate.shiftData("Wednesday", retrievedData));
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
			
			String day = getDay();
			WeatherData weatherData = new WeatherData();
			weatherData.setLocation(data.get(0).getLocation());
			//THIS CALL WILL BE UTILIZED LATER
			//weatherData.setData(generate.shiftData(day, data));
			weatherData.setData(data);
			dao.create(weatherData);
			
			System.out.println("Inserted new data");
		}
	}
	
	private String getDay()
	{
		SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
		String day = simpleDate.format(new Date());
		return day;
	}
}