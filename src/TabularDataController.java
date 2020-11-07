import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import beans.WeatherData;
import business.GenerateWeatherData;
import business.LoggingInterceptor;
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
	private static final long serialVersionUID = 1L;

	public String onSubmit()
	{
		
        List<WeatherData> data = new ArrayList<WeatherData>();
        WeatherDataService dao = new WeatherDataService();
        
        data = dao.findByLocation("Frisco");

        WeatherData newData = new WeatherData();
        newData.setData(data);
        newData.setLocation(data.get(0).getLocation());
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", newData);
	        
		return "tabularData.xhtml";
	}	
}