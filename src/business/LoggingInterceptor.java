package business;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import util.ApplicationLogger;

public class LoggingInterceptor implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	@EJB
	ApplicationLogger logger;
	
	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception
	{
		System.out.println("************** Intercepting call to method: " + ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()");
		logger.logInfo("******* Intercepting call to method: " + ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()");
		return ctx.proceed();
	}
}