package dao;

import java.util.List;

public interface IDao<T> {
	
	public T save(T obj);
	public T delete(T obj);
	public T edit(T obj);
	public T find(Long objId);
	public List<T> findAll();
}
