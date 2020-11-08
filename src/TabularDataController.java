import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import beans.WeatherData;
import business.GenerateWeatherData;
<<<<<<< HEAD
import data.WeatherDataAccessInterface;
=======
import business.LoggingInterceptor;
>>>>>>> LoggingService
import data.WeatherDataService;

import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;

//@ManagedBean
//@ViewScoped
@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class TabularDataController implements Serializable
{
<<<<<<< HEAD
	@EJB
	WeatherDataAccessInterface<WeatherData> dao;
	
=======
	private static final long serialVersionUID = 1L;

>>>>>>> LoggingService
	public String onSubmit()
	{
		
<<<<<<< HEAD
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
           
=======
        List<WeatherData> data = new ArrayList<WeatherData>();
        WeatherDataService dao = new WeatherDataService();
        
        data = dao.findByLocation("Frisco");

        WeatherData newData = new WeatherData();
        newData.setData(data);
        newData.setLocation(data.get(0).getLocation());
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", newData);
	        
>>>>>>> LoggingService
		return "tabularData.xhtml";
	}	
}