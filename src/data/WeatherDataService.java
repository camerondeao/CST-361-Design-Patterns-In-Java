package data;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;

import beans.WeatherData;

public class WeatherDataService implements WeatherDataAccessInterface<WeatherData>
{

	Connection myConn = null;
	String connURL = "jdbc:mysql://localhost:3306/sys";
	String username = "root";
	String password = "connection";
	
	@Override
	public List<WeatherData> findAll() 
	{
		return null;
	}

	@Override
	public WeatherData create(WeatherData t) 
	{
		try
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String insertQuery = "INSERT INTO weather (LOCATION) VALUES (?)";
			
			PreparedStatement prep = myConn.prepareStatement(insertQuery);
			prep.setString(1, t.getLocation());
			
			prep.executeUpdate();
			
			String idQuery = "SELECT LAST_INSERT_ID() AS LAST_ID FROM weather";
			Statement statement = myConn.createStatement();
			ResultSet rs = statement.executeQuery(idQuery);
			rs.next();
			int locationID = rs.getInt("LAST_ID");
			rs.close();
			statement.close();
			
			for(WeatherData data: t.getData())
			{
				String insert = "INSERT INTO weatherdaily (LOCATION_ID, DESCRIPTION, TEMPERATURE, HUMIDITY, WINDSPEED, DAY) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement dataPrep = myConn.prepareStatement(insert);
				dataPrep.setInt(1, locationID);
				dataPrep.setString(2, data.getDescription());
				dataPrep.setInt(3, data.getTemperature());
				dataPrep.setInt(4, data.getHumidity());
				dataPrep.setInt(5, data.getWindSpeed());
				dataPrep.setString(6, data.getDay());
				dataPrep.executeUpdate();
			}
			
			myConn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public boolean update(WeatherData t) 
	{
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = "SELECT * FROM weather WHERE LOCATION = ?"; 
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, t.getLocation());
			
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while(rs.next())
			{
				id = rs.getInt("ID");
			}
			
			String test = "SELECT * FROM weatherdaily WHERE LOCATION_ID = ?";
			PreparedStatement testStatement = myConn.prepareStatement(test);
			
			testStatement.setInt(1, id);
			
			ResultSet testRS = testStatement.executeQuery();
			int newID = 0;
			int index = 0;
			while(testRS.next())
			{
				newID = testRS.getInt("ID");
				System.out.println("TESTING ID RETRIEVAL: " + newID);
				String dataQuery = "UPDATE weatherdaily SET DESCRIPTION = ?, TEMPERATURE = ?, HUMIDITY = ?, WINDSPEED = ?, DAY = ? WHERE ID = " + newID; 
				PreparedStatement dataPrep = myConn.prepareStatement(dataQuery);
				dataPrep.setString(1, t.getData().get(index).getDescription());
				dataPrep.setInt(2, t.getData().get(index).getTemperature());
				dataPrep.setInt(3, t.getData().get(index).getHumidity());
				dataPrep.setInt(4, t.getData().get(index).getWindSpeed());
				dataPrep.setString(5, t.getData().get(index).getDay());
				dataPrep.executeUpdate();
				index++;
			}	
			myConn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(WeatherData t) 
	{
		return false;
	}

	@Override
	public List<WeatherData> findByLocation(String location) 
	{
		WeatherData data = new WeatherData();
		List<WeatherData> dayData = new ArrayList<WeatherData>();
		try 
		{
			myConn = DriverManager.getConnection(connURL, username, password);
			String query = "SELECT * FROM weather WHERE LOCATION = ?";
			
			PreparedStatement statement = myConn.prepareStatement(query);
			
			statement.setString(1, location);
			
			ResultSet rs = statement.executeQuery();
			int id = 0;
			while(rs.next())
			{
				data.setLocation(rs.getString("LOCATION"));
				id = rs.getInt("ID");
			}
			
			String dayQuery = "SELECT * FROM weatherdaily WHERE LOCATION_ID = " + 2;
			Statement dayStatement = myConn.createStatement();
			ResultSet dayRS = dayStatement.executeQuery(dayQuery);
			
			while(dayRS.next())
			{
				dayData.add(new WeatherData(location, dayRS.getString("DESCRIPTION"), dayRS.getInt("TEMPERATURE"), dayRS.getInt("HUMIDITY"), dayRS.getInt("WINDSPEED"), dayRS.getString("DAY")));
			}
			data.setData(dayData);
			
			rs.close();
			dayRS.close();
			statement.close();
			dayStatement.close();
			myConn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return dayData;
	}
}