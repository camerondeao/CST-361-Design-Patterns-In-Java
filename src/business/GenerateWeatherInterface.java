package business;

import java.util.List;

import beans.WeatherData;

public interface GenerateWeatherInterface 
{
	List<WeatherData> generateData(List<WeatherData> data);
	void setDays(List<WeatherData> data);
	List<WeatherData> shiftData(String day, List<WeatherData> oldData);
}
