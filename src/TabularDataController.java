import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.WeatherData;
import business.GenerateWeatherData;
import data.WeatherDataAccessInterface;
import data.WeatherDataService;

import java.util.Date;
import java.text.SimpleDateFormat;

@ManagedBean
@ViewScoped
public class TabularDataController 
{
	@EJB
	WeatherDataAccessInterface<WeatherData> dao;
	
	public String onSubmit()
	{
//	 	List<WeatherData> data = new ArrayList<WeatherData>();
//        GenerateWeatherData generate = new GenerateWeatherData();
//        for(int i = 0; i < 7; i++)
//        {
//        	data = generate.generateData(data);
//        }
//        generate.setDays(data);
//        
//        WeatherData newStuff = new WeatherData();
//        newStuff.setData(data);
//        newStuff.setLocation(data.get(0).getLocation());
//        
//        for(int i = 0; i < data.size(); i++)
//        {
//        	System.out.println("Location: " + data.get(i).getLocation() + " - Description: " + data.get(i).getDescription() + " - Temperature: " + data.get(i).getTemperature()
//          + " - Humidity: " + data.get(i).getHumidity() + " - Wind Speed: " + data.get(i).getWindSpeed() + " - Weekday: " + data.get(i).getDay());
//        }
		
        List<WeatherData> testing = new ArrayList<WeatherData>();
        
        testing = dao.findByLocation("Arizona");
        
        for(int i = 0; i < testing.size(); i++)
        {
        	System.out.println("Location: " + testing.get(i).getLocation() + " - Description: " + testing.get(i).getDescription() + " - Temperature: " + testing.get(i).getTemperature()
        	          + " - Humidity: " + testing.get(i).getHumidity() + " - Wind Speed: " + testing.get(i).getWindSpeed() + " - Weekday: " + testing.get(i).getDay());
        }
        WeatherData dataTest = new WeatherData();
        dataTest.setData(testing);
        dataTest.setLocation(testing.get(0).getLocation());
        
//        WeatherDataService dao = new WeatherDataService();
//        //dao.create(newStuff);
//        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
//        String date = sdf2.format(new Date());
//        System.out.println("Today is: " + date);
//        generate.shiftArray(date);
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", dataTest);
           
		return "tabularData.xhtml";
	}	
}
