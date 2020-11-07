package util;

import java.util.List;

import beans.WeatherData;
import data.WeatherDataService;

public class WeatherDTO implements FactoryDTO<WeatherData> 
{

	@Override
	public WeatherData findBy(int t) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeatherData> findAll(String t) 
	{
		WeatherDataService service = new WeatherDataService();
		return service.findByLocation(t);
	}
}