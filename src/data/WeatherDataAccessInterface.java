package data;

import java.util.List;

public interface WeatherDataAccessInterface <T>
{
	public List<T> findAll();
	public List<T> findByLocation(String location);
	public T create(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public boolean checkData(String location);
}
