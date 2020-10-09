package business;

import java.util.List;

import beans.WeatherData;

public interface GenerateWeatherInterface 
{
	List<WeatherData> generateData(List<WeatherData> data);
}
