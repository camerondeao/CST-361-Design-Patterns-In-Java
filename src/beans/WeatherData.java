package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class WeatherData 
{
	String location;
	String weatherDescription;
	int temperature;
	int humidity;
	int windSpeed;
	String day;
	List<WeatherData> data;
	
	public WeatherData()
	{
		location = "";
		weatherDescription = "";
		temperature = 0;
		humidity = 0;
		windSpeed = 0;
		day = "";
	}
	
	public WeatherData(String location, String description, int temperature, int humidity, int windSpeed, String day)
	{
		this.location = location;
		this.weatherDescription = description;
		this.temperature = temperature;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.day = day;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public String getDescription()
	{
		return weatherDescription;
	}
	
	public void setDescription(String description)
	{
		this.weatherDescription = description;
	}
	
	public int getTemperature()
	{
		return temperature;
	}
	
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	
	public int getHumidity()
	{
		return humidity;
	}
	
	public void setHumidity(int humidity)
	{
		this.humidity = humidity;
	}
	
	public int getWindSpeed()
	{
		return windSpeed;
	}
	
	public void setWindSpeed(int speed)
	{
		this.windSpeed = speed;
	}
	
	public String getDay()
	{
		return day;
	}
	
	public void setDay(String day)
	{
		this.day = day;
	}
	
	public List<WeatherData> getData()
	{
		return data;
	}
	
	public void setData(List<WeatherData> inc)
	{
		this.data = inc;
	}
}