package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import business.GenerateWeatherData;
import data.WeatherDataService;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;

@ManagedBean
@ViewScoped
public class LineChartReport
{
	private LineChartModel lineModel;
	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	List<WeatherData> data = new ArrayList<WeatherData>();
	
	@PostConstruct
	public void init()
	{
//		GenerateWeatherData generate = new GenerateWeatherData();
//		for(int i = 0; i < 7; i++)
//		{
//			data = generate.generateData(data);
//		}
//		generate.setDays(data);
		
		WeatherDataService dao = new WeatherDataService();
		data = dao.findByLocation("Dallas");
		
		lineModel = new LineChartModel();
		createTemperatureSeries();
		createHumiditySeries();
		createWindSeries();
		
		lineModel.setTitle(data.get(0).getLocation() + " 7 Day Forecast");
		lineModel.setShowPointLabels(true);
		lineModel.setLegendPosition("ne");
		
		Axis xAxis = new CategoryAxis("Weekdays");
		lineModel.getAxes().put(AxisType.X, xAxis);
		
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setMax(120);
		yAxis.setMin(0);
		yAxis.setTickInterval("15");
		
	}
	
	public LineChartModel getLineModel()
	{
		return lineModel;
	}
	
	private void createTemperatureSeries()
	{
		LineChartSeries temps = new LineChartSeries();
		temps.setLabel("Temperature");
		temps.set(data.get(0).getDay(), data.get(0).getTemperature());
		temps.set(data.get(1).getDay(), data.get(1).getTemperature());
		temps.set(data.get(2).getDay(), data.get(2).getTemperature());
		temps.set(data.get(3).getDay(), data.get(3).getTemperature());
		temps.set(data.get(4).getDay(), data.get(4).getTemperature());
		temps.set(data.get(5).getDay(), data.get(5).getTemperature());
		temps.set(data.get(6).getDay(), data.get(6).getTemperature());
		lineModel.addSeries(temps);
	}
	
	private void createHumiditySeries()
	{
		LineChartSeries humidity = new LineChartSeries();
		humidity.setLabel("Humidity");
		humidity.set(data.get(0).getDay(), data.get(0).getHumidity());
		humidity.set(data.get(1).getDay(), data.get(1).getHumidity());
		humidity.set(data.get(2).getDay(), data.get(2).getHumidity());
		humidity.set(data.get(3).getDay(), data.get(3).getHumidity());
		humidity.set(data.get(4).getDay(), data.get(4).getHumidity());
		humidity.set(data.get(5).getDay(), data.get(5).getHumidity());
		humidity.set(data.get(6).getDay(), data.get(6).getHumidity());
		lineModel.addSeries(humidity);
	}
	
	private void createWindSeries()
	{
		LineChartSeries windSpeed = new LineChartSeries();
		windSpeed.setLabel("Wind Speed");
		windSpeed.set(data.get(0).getDay(), data.get(0).getWindSpeed());
		windSpeed.set(data.get(1).getDay(), data.get(1).getWindSpeed());
		windSpeed.set(data.get(2).getDay(), data.get(2).getWindSpeed());
		windSpeed.set(data.get(3).getDay(), data.get(3).getWindSpeed());
		windSpeed.set(data.get(4).getDay(), data.get(4).getWindSpeed());
		windSpeed.set(data.get(5).getDay(), data.get(5).getWindSpeed());
		windSpeed.set(data.get(6).getDay(), data.get(6).getWindSpeed());
		lineModel.addSeries(windSpeed);
	}
}
