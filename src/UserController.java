
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

//import javax.faces.bean.ViewScoped;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.User;
import beans.WeatherData;
import business.GenerateWeatherData;
import business.GenerateWeatherInterface;
import business.LoggingInterceptor;
import business.UserBusinessService;
import business.UserServiceInterface;
import data.UserDataInterface;
import data.UserDataService;
import data.UserManagement;
import data.WeatherDataAccessInterface;
import data.WeatherDataService;
import util.ApplicationLogger;
import javax.inject.Named;

//@ManagedBean
//@ViewScoped
//@Stateless
//@Local(UserController.class)
//@LocalBean
@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class UserController implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	ApplicationLogger logger;
	
	@EJB
	GenerateWeatherInterface weather;
	
	@EJB
	UserDataInterface dao;
	
	@EJB
	WeatherDataAccessInterface weatherDAO;
	
	public String onLogin(User user) throws SQLException
	{
		//logger.logInfo("TESTING");
		//logger.logDebug("DEBUG TEST");
		
		try
		{
			if(dao.find(user)) 
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
			dao.create(user);
			
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
		
		if(weatherDAO.checkData("Arizona"))
		{
			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
			String day = getDay();
			
			retrievedData = dao.findByLocation("Arizona");
			
			if(!Objects.equals(retrievedData.get(0), day))
			{
				WeatherData data = new WeatherData();
				data.setLocation("Arizona");
				data.setData(generate.shiftData(day, retrievedData));
				weatherDAO.update(data);
			}
		}
		else
		{
			List<WeatherData> data = new ArrayList<WeatherData>();
			
			for(int i = 0; i < 7; i++)
			{
				data = weather.generateData(data);
			}
			generate.setDays(data);
			
			WeatherData weatherData = new WeatherData();
			weatherData.setLocation(data.get(0).getLocation());
			weatherData.setData(data);
			weatherDAO.create(weatherData);
		}
	}
	
	private String getDay()
	{
		SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
		String day = simpleDate.format(new Date());
		return day;
	}
}