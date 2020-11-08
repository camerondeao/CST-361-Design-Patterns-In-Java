package business;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.User;
import beans.UserResponseDataModel;
import data.UserDataInterface;
import data.UserDataService;
import util.FactoryDTO;
import util.FactoryService;
import util.FactoryService.DTOType;

@RequestScoped
@Path("/user")
@Produces("application/xml, applicaiton/json")
@Consumes("application/xml, application/json")
public class UserRestService 
{
<<<<<<< HEAD
	@EJB
	UserDataInterface<User> dao;
=======
	FactoryService service = new FactoryService();
>>>>>>> LoggingService
	
	@GET
	@Path("/getuserj/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponseDataModel getuserj(@PathParam("id")int id)
	{
		User user = new User();
<<<<<<< HEAD
		
		try
		{
			user =dao.findById(id);
=======
		FactoryDTO userDTO = service.getDTO(DTOType.USER);

		try
		{
			user = (User) userDTO.findBy(id);
>>>>>>> LoggingService
			UserResponseDataModel model = new UserResponseDataModel(0, "", user);
			return model;
		}
		catch(Exception ex)
		{
			UserResponseDataModel model = new UserResponseDataModel(-1, "", user);
			return model;
		}
	}
	
	@GET
	@Path("/getuserx/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public UserResponseDataModel getuserx(@PathParam("id")int id)
	{
		User user = new User();
<<<<<<< HEAD
=======
		FactoryDTO userDTO = service.getDTO(DTOType.USER);
>>>>>>> LoggingService
		
		try
		{
			user = (User) userDTO.findBy(id);
			UserResponseDataModel model = new UserResponseDataModel(0, "", user);
			return model;
		}
		catch(Exception ex)
		{
			UserResponseDataModel model = new UserResponseDataModel(-1, "", user);
			return model;
		}
	}
}
