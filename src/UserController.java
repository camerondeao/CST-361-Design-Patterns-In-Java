
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
	UserServiceInterface UserBS;
	
	@EJB
	GenerateWeatherInterface generate;
	
	@EJB
	WeatherDataAccessInterface<WeatherData> dao;
	
	@EJB
	ApplicationLogger logger;
	
	@EJB
	GenerateWeatherInterface weather;
	
	@EJB
	UserDataInterface userDAO;
	
	@EJB
	WeatherDataAccessInterface weatherDAO;
	
	public String onLogin(User user) throws SQLException
	{	
		try
		{
			if(userDAO.find(user)) 
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

			UserBS.register(user);

			UserBusinessService UserBS = new UserBusinessService();
			userDAO.create(user);

			
		}
		catch(Exception e)
		{
			System.out.println("Exception: occurred");
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		return "registerResponse.xhtml";
	}
	
	public String onLogoff() {
		//redirect to protected page to get login page
		return "homePage.xhtml?faces-redirect=true";
	}
	
	private void checkWeatherData()
	{		
		WeatherDataService dao = new WeatherDataService();
		GenerateWeatherData generate = new GenerateWeatherData();
		
		if(weatherDAO.checkData("Dallas"))
		{
			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
			String day = getDay();
			
			retrievedData = dao.findByLocation("Dallas");
			
			if(!Objects.equals(retrievedData.get(0), day))
			{
				WeatherData data = new WeatherData();
				data.setLocation("Dallas");
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