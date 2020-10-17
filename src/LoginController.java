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
public class LoginController {

	public String onSubmit(Login login) {
		
		GenerateWeatherData generate = null;
		SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
		String date = simpleDate.format(new Date());
		System.out.println("CURRENT DAY IS: " + date);
		
		List<WeatherData> retrievedData = new ArrayList<WeatherData>();
		WeatherDataService dao = new WeatherDataService();
		
		retrievedData = dao.findByLocation("Frisco");
		
		if(!Objects.equals(retrievedData.get(0).getDay(), date))
		{
			WeatherData data = new WeatherData();
			generate = new GenerateWeatherData();
			
			data.setData(generate.shiftData(date, retrievedData));
			dao.update(data);
			
		}
		
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("login", login);

		UserBusinessService userService = new UserBusinessService();
		if(userService.login(login.getUsername(), login.getPassword())) 
		{
			UserManagement loggedUser = UserManagement.getInstance();
			User testUser = new User();
			testUser.setFirstName("TESTING");
			testUser.setLastName("TEST");
			testUser.setEmail("HELLO");
			testUser.setGender("Male");
			testUser.setAge(44);
			testUser.setState("TEXAS");
			testUser.setUsername(login.getUsername());
			testUser.setPassword(login.getPassword());
			
			loggedUser.setUser(testUser);
			
			return "homePage.xhtml";
		}
		
		
		return "loginFail.xhtml";
	}	
}