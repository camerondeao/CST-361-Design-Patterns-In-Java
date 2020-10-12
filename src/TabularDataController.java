import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.WeatherData;
import business.GenerateWeatherData;

@ManagedBean
@ViewScoped
public class TabularDataController 
{
	public String onSubmit()
	{
	 	List<WeatherData> data = new ArrayList<WeatherData>();
        GenerateWeatherData generate = new GenerateWeatherData();
        for(int i = 0; i < 7; i++)
        {
        	data = generate.generateData(data);
        }
        generate.setDays(data);
        
        WeatherData newStuff = new WeatherData();
        newStuff.setData(data);
        
        for(int i = 0; i < data.size(); i++)
        {
        	System.out.println("Location: " + data.get(i).getLocation() + " - Description: " + data.get(i).getDescription() + " - Temperature: " + data.get(i).getTemperature()
          + " - Humidity: " + data.get(i).getHumidity() + " - Wind Speed: " + data.get(i).getWindSpeed() + " - Weekday: " + data.get(i).getDay());
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", newStuff);
	        
		return "tabularData.xhtml";
	}	
}
