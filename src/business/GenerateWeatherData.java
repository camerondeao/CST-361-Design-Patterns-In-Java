package business;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;

import beans.WeatherData;

@Stateless
public class GenerateWeatherData implements GenerateWeatherInterface 
{
	String[] weatherDescriptions = {"Sunny", "Cloudy", "Rainy", "Overcast", "Foggy"};
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	Random random = new Random();
	
	@Override
	public List<WeatherData> generateData(List<WeatherData> data) 
	{	
		//Testing
		WeatherData newData = new WeatherData();
		newData.setLocation("Frisco, Texas");
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
}