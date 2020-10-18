package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.User;

/***
 * 
 * stmt = conn.prepareStatement("INSERT INTO users(FIRSTNAME, LASTNAME, EMAIL, GENDER, AGE, STATE, USERNAME,PASSWORD) 															VALUES(?,?,?,?,?,?,?,?);");
					
					
					stmt.setString(1, user.getFirstName());
					stmt.setString(2, user.getLastName());
					stmt.setString(3, user.getEmail());
					stmt.setString(4, user.getGender());
					stmt.setInt(5, user.getAge());
					stmt.setString(6, user.getState());
					stmt.setString(7, user.getUsername());
					stmt.setString(8, user.getPassword());
 *
 */
@Stateless
@Local(UserDataInterface.class)
@LocalBean
public class UserDataService implements UserDataInterface<User>
{
	Connection myConn = null;
	String connURL = "jdbc:mysql://localhost:3306/sys?serverTimezone=GMT";
	String username = "root";
	String password = "xkdls123";
	@Override
	public List<User> findAll() {
		List<User> dbUsers = new ArrayList<User>();
		
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String sqlStatement = "SELECT * FROM users2";
			Statement state = myConn.createStatement();
			ResultSet rs = state.executeQuery(sqlStatement);
			
			while(rs.next())
			{
				User user = new User();
				user.setFirstName(user.getFirstName());
				user.setLastName(user.getLastName());
				user.setEmail(user.getEmail());
				user.setGender(user.getGender());
				user.setAge(user.getAge());
				user.setState(user.getState());
				user.setUsername(user.getUsername());
				user.setPassword(user.getPassword());
				
				
				rs.close();
				
				state.close();
				
				
				dbUsers.add(user);
				myConn.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			
		}
		return dbUsers;
	}
//	@Override
//	public User findById(int id) {
//		
//		User foundUser = new User();
//		try 
//		{
//			myConn = DriverManager.getConnection(connURL, username, password);
//			String query = "SELECT * FROM users2 WHERE ID = ?";
//			PreparedStatement statement = myConn.prepareStatement(query);
//			
//			statement.setInt(1, id);
//			
//			ResultSet rs = statement.executeQuery();
//			while(rs.next())
//			{
//				foundUser.setFirstName(rs.getString("FIRSTNAME"));
//				foundUser.setLastName(rs.getString("LASTANAME"));
//				foundUser.setEmail(rs.getString("EMAILADDRESS"));
//				foundUser.setGender(rs.getString("GENDER"));
//				foundUser.setAge(rs.getInt("AGE"));
//				foundUser.setState(rs.getString("STATE"));
//				foundUser.setUsername(rs.getString("USERNAME"));
//				foundUser.setPassword(rs.getString("PASSWORD"));
//			}
//			
//			
//	
//			rs.close();
//			
//			statement.close();
//			
//			myConn.close();
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			
//		}
//		
//		return foundUser;
//	}
	
	@Override
	public User findBy(User user) {
		
		
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
//			String query = "SELECT * FROM users2 WHERE FIRSTNAME = ? AND LASTNAME = ? AND EMAILADDRESS = ?  AND GENDER = ?  AND AGE = ? AND STATE = ? AND USERNAME = ?  AND PASSWORD = ?" ;
//			
//			PreparedStatement statement = myConn.prepareStatement(query);
//			
//			statement.setString(1, user.getFirstName());
//			statement.setString(2, user.getLastName());
//			statement.setString(3, user.getEmail());
//			statement.setString(4, user.getGender());
//			statement.setInt(5, user.getAge());
//			statement.setString(6, user.getState());
//			statement.setString(7, user.getUsername());
//			statement.setString(8, user.getPassword());
//			
//			ResultSet rs = statement.executeQuery();
			String query = " SELECT * FROM users2 WHERE USERNAME = ? " ;
			
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, user.getUsername());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAILADDRESS"));
				user.setGender(rs.getString("GENDER"));
				user.setAge(rs.getInt("AGE"));
				user.setState(rs.getString("STATE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
			}
			System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " + user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
//			if(rs.next()) {
//				System.out.println("Username :" + user.getUsername());
//			}else {
//			System.out.println("Something is wrong");
//			}
			
			rs.close();
			
			statement.close();
			
			myConn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			//throw new DatabaseException();
		}
		
		return user;
	}
	@Override
	public boolean create(User user) {
		boolean found = false;
		
		System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " + user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
		try
		{
			
			
			myConn = DriverManager.getConnection(connURL, username, password);
			String createQuery = "INSERT INTO users2 (FIRSTNAME,LASTNAME,EMAILADDRESS,GENDER,AGE,STATE,USERNAME,PASSWORD) VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement p = myConn.prepareStatement(createQuery);
			
			p.setString(1, user.getFirstName());
			p.setString(2, user.getLastName());
			p.setString(3, user.getEmail());
			p.setString(4, user.getGender());
			p.setInt(5, user.getAge());
			p.setString(6, user.getState());
			p.setString(7, user.getUsername());
			p.setString(8, user.getPassword());
			
			p.executeUpdate();
			
			
			
			myConn.close();
			
			found = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//throw new DatabaseException();
		}
		return found;
	}
	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean find(User user) {
		
		boolean found = false;
		
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
//			String query = "SELECT * FROM users2 WHERE FIRSTNAME = ? AND LASTNAME = ? AND EMAILADDRESS = ?  AND GENDER = ?  AND AGE = ? AND STATE = ? AND USERNAME = ?  AND PASSWORD = ?" ;
//			
//			PreparedStatement statement = myConn.prepareStatement(query);
//			
//			statement.setString(1, user.getFirstName());
//			statement.setString(2, user.getLastName());
//			statement.setString(3, user.getEmail());
//			statement.setString(4, user.getGender());
//			statement.setInt(5, user.getAge());
//			statement.setString(6, user.getState());
//			statement.setString(7, user.getUsername());
//			statement.setString(8, user.getPassword());
//			
//			ResultSet rs = statement.executeQuery();
			String query = " SELECT * FROM users2 WHERE USERNAME = ? " ;
			
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, user.getUsername());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				found = true;
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAILADDRESS"));
				user.setGender(rs.getString("GENDER"));
				user.setAge(rs.getInt("AGE"));
				user.setState(rs.getString("STATE"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				
				UserManagement loggedUser = UserManagement.getInstance();
				
				loggedUser.setUser(user);
				
			}
			System.out.println("Firstname: " + user.getFirstName() + " Lastname: " + user.getLastName() + " Email: " + user.getEmail()+ " Gender: " + user.getGender()+ " Age: " + user.getAge()+ " State: " + user.getState()+ " Username: " + user.getUsername()+ " Password: " + user.getPassword());
//			if(rs.next()) {
//				System.out.println("Username :" + user.getUsername());
//			}else {
//			System.out.println("Something is wrong");
//			}
			
			rs.close();
			
			statement.close();
			
			myConn.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			//throw new DatabaseException();
		}
		
		return found;
	}
	
	
}
