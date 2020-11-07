package util;

public class FactoryService 
{
	public static enum DTOType{WEATHER,USER};
	
	public FactoryDTO<?> getDTO(DTOType type)
	{
		switch(type)
		{
			case WEATHER:
				return new WeatherDTO();
			case USER:
				return new UserDTO();
				default:
					return null;
		}
	}
}
