package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherResponseDataModel extends WeatherResponseModel
{

	private WeatherData data;
	
	public WeatherResponseDataModel() 
	{
		super(0, "");
		this.data = null;
	}
	
	public WeatherResponseDataModel(int status, String message, WeatherData data)
	{
		super(status, message);
		this.data = data;
	}
	
	public WeatherData getData()
	{
		return data;
	}
	
	public void setData(WeatherData data)
	{
		this.data = data;
	}
}
