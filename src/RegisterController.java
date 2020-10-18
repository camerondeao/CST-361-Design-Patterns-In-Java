import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.*;

import beans.User;

@ManagedBean
@ViewScoped
public class RegisterController {

	public String onSubmit(User user) {
		
		StoreUser(user);
		//Forwards to view along with the user bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);

		return "registerResponse.xhtml";
	}
	
	public void StoreUser(User user)
	{
		try
		{
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sys", "root", "root");	

//			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "connection");
			String sqlStatement = "INSERT INTO users (firstname, lastname, age, state, emailaddress, username, password) VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement prep = myConn.prepareStatement(sqlStatement);
			prep.setString(1, user.getFirstName());
			prep.setString(2, user.getLastName());
			prep.setInt(3, user.getAge());
			prep.setString(4, user.getState());
			prep.setString(5, user.getEmail());
			prep.setString(6, user.getUsername());
			prep.setString(7, user.getPassword());
			
			prep.executeUpdate();
			
			myConn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
