import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Login;
import beans.User;
import beans.WeatherData;
import business.GenerateWeatherData;
import business.UserBusinessService;
import data.UserManagement;
import data.WeatherDataService;

@ManagedBean
@ViewScoped
public class LoginController 
{
	WeatherDataService dao = new WeatherDataService();
	GenerateWeatherData generate = new GenerateWeatherData();
	
	public String onSubmit(Login login) 
	{
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("login", login);

		UserBusinessService userService = new UserBusinessService();
		if(userService.login(login.getUsername(), login.getPassword())) 
		{
//			UserManagement loggedUser = UserManagement.getInstance();
//			User testUser = new User();
//			testUser.setFirstName("TESTING");
//			testUser.setLastName("TEST");
//			testUser.setEmail("HELLO");
//			testUser.setGender("Male");
//			testUser.setAge(44);
//			testUser.setState("TEXAS");
//			testUser.setUsername(login.getUsername());
//			testUser.setPassword(login.getPassword());
//			
//			loggedUser.setUser(testUser);
//			
//			GenerateWeatherData generate = null;
//			SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
//			String date = simpleDate.format(new Date());
//			System.out.println("CURRENT DAY IS: " + date);
//			
//			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
//			
//			
//			retrievedData = dao.findByLocation("Frisco");
//			
//			if(!Objects.equals(retrievedData.get(0).getDay(), date))
//			{
//				WeatherData data = new WeatherData();
//				generate = new GenerateWeatherData();
//				
//				data.setData(generate.shiftData(date, retrievedData));
//				dao.update(data);
//				
//			}
			
			checkWeatherData();
			return "homePage.xhtml";
		}
		
		
		return "loginFail.xhtml";
	}
	
	public void logUser(Login login)
	{
		
	}
	
	public void checkWeatherData()
	{
		if(dao.checkData("California"))
		{
			List<WeatherData> retrievedData = new ArrayList<WeatherData>();
			String day = getDay();
			
			retrievedData = dao.findByLocation("California");
			
			if(!Objects.equals(retrievedData.get(0).getDay(), "Friday")) 
			{
				System.out.println("HELLO WORLD");
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