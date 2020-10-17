package business;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.WeatherData;
import beans.WeatherResponseDataModel;
import data.WeatherDataService;

@RequestScoped
@Path("/weather")
@Produces("application/xml, applicaiton/json")
@Consumes("application/xml, application/json")
public class WeatherRestService 
{
	@GET
	@Path("/getweatherj/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public WeatherResponseDataModel getWeatherj(@PathParam("location")String location)
	{
		WeatherData data = new WeatherData();
		WeatherDataService dao = new WeatherDataService();
		
		try
		{
			data.setLocation(location);
			data.setData(dao.findByLocation(location));
			
			WeatherResponseDataModel model = new WeatherResponseDataModel(0, "", data);
			return model;
		}
		catch(Exception ex)
		{
			WeatherResponseDataModel model = new WeatherResponseDataModel(-1, "Exception occurred.", data);
			return model;
		}
	}
	
	@GET
	@Path("/getweatherx/{location}")
	@Produces(MediaType.APPLICATION_XML)
	public WeatherResponseDataModel getWeatherx(@PathParam("location")String location)
	{
		WeatherData data = new WeatherData();
		WeatherDataService dao = new WeatherDataService();
		
		try
		{
			data.setLocation(location);
			data.setData(dao.findByLocation(location));
			
			WeatherResponseDataModel model = new WeatherResponseDataModel(0, "", data);
			return model;
		}
		catch(Exception ex)
		{
			WeatherResponseDataModel model = new WeatherResponseDataModel(-1, "Exception occurred.", data);
			return model;
		}
	}
}
