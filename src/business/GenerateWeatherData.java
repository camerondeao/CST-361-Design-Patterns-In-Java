package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import data.UserManagement;
import beans.WeatherData;

@Stateless
@Local(GenerateWeatherInterface.class)
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class GenerateWeatherData implements GenerateWeatherInterface 
{		
	String[] weatherDescriptions = {"Sunny", "Cloudy", "Rainy", "Overcast", "Foggy"};
	String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	Random random = new Random();
	
	
	@Override
	@Interceptors(LoggingInterceptor.class)
	public List<WeatherData> generateData(List<WeatherData> data) 
	{	
		String location = UserManagement.getInstance().getUser().getState();
		WeatherData newData = new WeatherData();
		newData.setLocation(location);
		newData.setTemperature(getRandomTemperature());
		newData.setHumidity(getRandomHumidity());
		newData.setWindSpeed(getRandomWindSpeed());
		setDescription(newData);
		checkWind(newData);
		checkHumidity(newData);
		data.add(newData);
		return data;
	}
	
	public void setDays(List<WeatherData> data)
	{
		for(int i = 0; i < data.size(); i++)
		{
			data.get(i).setDay(days[i]);
		}
	}
	
	private int getRandomTemperature()
	{
		return random.nextInt(105 - 40) + 40;
	}
	
	private int getRandomHumidity()
	{
		return random.nextInt(100 - 0) + 0;
	}
	
	private int getRandomWindSpeed()
	{
		return random.nextInt(40 - 0) + 0;
	}
	
	private void setDescription(WeatherData data)
	{	
		int index = random.nextInt(weatherDescriptions.length);
		String temp = weatherDescriptions[index];
		data.setDescription(temp);
	}
	
	private void checkWind(WeatherData data)
	{
		String temp = data.getDescription();
		if(data.getWindSpeed() >= 25)
		{
			temp = temp + ", Windy"; 
			data.setDescription(temp);
		}
		else if(data.getWindSpeed() >= 15)
		{
			temp = temp + ", Breezy";
			data.setDescription(temp);
		}
		else
		{
			return;
		}
	}
	
	private void checkHumidity(WeatherData data)
	{
		String temp = data.getDescription();
		if(data.getHumidity() <= 40)
		{
			temp = temp + ", Dry";
		}
		else
		{
			return;
		}
	}
	
	public List<WeatherData> shiftData(String day, List<WeatherData> oldData)
	{
		int n = 0;
		List<WeatherData> weatherData = new ArrayList<WeatherData>();
		
		switch(day)
		{
		case "Sunday":
			n = 0;
			break;
		case "Monday":
			n = 1;
			break;
		case "Tuesday":
			n = 2;
			break;
		case "Wednesday":
			n = 3;
			break;
		case "Thursday":
			n = 4;
			break;
		case "Friday":
			n = 5;
			break;
		case "Saturday":
			n = 6;
			break;
		}
		
		for(int i = days.length - 1; i >= n; i--)
		{
			weatherData.add(oldData.get(i));
		}
		
//		System.out.println("Pulled data:");
//		for(int i = 0; i < oldData.size(); i++)
//		{
//			System.out.print("Day: " + oldData.get(i).getDay() + " ");
//		}
		
		Collections.reverse(weatherData);
//		System.out.println();
//		System.out.println("PASSED LIST REVERSE DATA");
//		for(int i = 0; i < weatherData.size(); i++)
//		{
//			System.out.print("Day: " + weatherData.get(i).getDay() + " ");
//		}
		
		List<WeatherData> newData = new ArrayList<WeatherData>();
		for(int i = 0; i < n; i++)
		{
			newData = generateData(newData);
		}
		
		for(int i = 0; i < newData.size(); i++)
		{
			newData.get(i).setDay(days[i]);
			weatherData.add(newData.get(i));
		}
		
		
//		System.out.println("FINAL DATA LIST: ");
//		for(int i = 0; i < weatherData.size(); i++)
//		{
//			System.out.println("Location: " + weatherData.get(i).getLocation() + " - Description: " + weatherData.get(i).getDescription() + " - Temperature: " + weatherData.get(i).getTemperature()
//			          + " - Humidity: " + weatherData.get(i).getHumidity() + " - Wind Speed: " + weatherData.get(i).getWindSpeed() + " - Weekday: " + weatherData.get(i).getDay());
//		}
		return weatherData;
	}
}