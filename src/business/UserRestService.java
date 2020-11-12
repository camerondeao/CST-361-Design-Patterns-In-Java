package business;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.User;
import beans.UserResponseDataModel;
import util.FactoryDTO;
import util.FactoryService;
import util.FactoryService.DTOType;

@RequestScoped
@Path("/user")
@Produces("application/xml, applicaiton/json")
@Consumes("application/xml, application/json")
public class UserRestService 
{	
	FactoryService service = new FactoryService();
	
	@GET
	@Path("/getuserj/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponseDataModel getuserj(@PathParam("id")int id)
	{
		User user = new User();		
		FactoryDTO userDTO = service.getDTO(DTOType.USER);
		
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
	
	@GET
	@Path("/getuserx/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public UserResponseDataModel getuserx(@PathParam("id")int id)
	{
		User user = new User();
		FactoryDTO userDTO = service.getDTO(DTOType.USER);
		
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
