package business;
 
import java.sql.SQLException;

import beans.User;


public interface UserServiceInterface {

	// method to register
		public boolean register(User user) throws SQLException;

		// method to login
		public boolean login(User user) throws SQLException;
		
//		User register(User user);
//
//		User login(User user);
	
}
