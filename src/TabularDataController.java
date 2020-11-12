import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import beans.WeatherData;
import data.UserManagement;
import data.WeatherDataAccessInterface;
import business.LoggingInterceptor;
import data.WeatherDataService;
import java.io.Serializable;

@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class TabularDataController implements Serializable
{
	@EJB
	WeatherDataAccessInterface<WeatherData> dao;
	
	private static final long serialVersionUID = 1L;

	public String onSubmit()
	{
		String location = UserManagement.getInstance().getUser().getState();
        List<WeatherData> testing = new ArrayList<WeatherData>();
        
        testing = dao.findByLocation(location);
        
        WeatherData dataTest = new WeatherData();
        dataTest.setData(testing);
        dataTest.setLocation(testing.get(0).getLocation());
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", dataTest);
           
        List<WeatherData> data = new ArrayList<WeatherData>();
        WeatherDataService dao = new WeatherDataService();
        
        data = dao.findByLocation(location);

        WeatherData newData = new WeatherData();
        newData.setData(data);
        newData.setLocation(data.get(0).getLocation());
        
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("weatherData", newData);
	        
		return "tabularData.xhtml";
	}	
}