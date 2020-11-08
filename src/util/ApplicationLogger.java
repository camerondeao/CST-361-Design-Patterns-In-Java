package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ApplicationLogger 
{
	Logger logger;
	
	@PostConstruct
	public void init()
	{
		logger = Logger.getLogger(ApplicationLogger.class);
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Logger acquired");
	}
	
	public void logInfo(String message)
	{
		logger.info(message);
	}
	
	public void logDebug(String message)
	{
		logger.debug(message);
	}
	
	public void logWarn(String message)
	{
		logger.warn(message);
	}
	
	public void logError(String message)
	{
		logger.error(message);
	}
	
	public void logFatal(String message)
	{
		logger.fatal(message);
	}
//	public void logError(String message)
//	{
//		logger.severe(message);
//	}
}
