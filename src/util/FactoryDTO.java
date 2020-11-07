package util;

import java.util.List;

public interface FactoryDTO <T>
{
	public T findBy(int t);
	public List<T> findAll(String t);
}