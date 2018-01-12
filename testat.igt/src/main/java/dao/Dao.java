package dao;

import java.util.List;

import javax.persistence.Query;

import app.EntityManager;

public class Dao<T> implements IDao<T>{
	
	private Class<T> type;
	private EntityManager entityManager;
	
	public Dao(EntityManager entityManager, Class<T> type) {
		this.type = type;
		this.entityManager = entityManager;
	}

	public T save(T obj) {
		try {
			this.entityManager.getSession().persist(obj);
			return obj;
		} catch(Exception e) {
			return null;
		}
	}

	public T delete(T obj) {
		try {
			this.entityManager.getSession().delete(obj);
			return obj;
		} catch(Exception e) {
			return null;
		}
	}

	public T edit(T obj) {
		try {
			this.entityManager.getSession().update(obj);
			return obj;
		} catch(Exception e) {
			return null;
		}
	}

	public T find(Long objId) {
		try {
			return (T)this.entityManager.getSession().get(this.type, objId);
		} catch(Exception e) {
			return null;
		}
	}

	public List<T> findAll() {
		try {
			return (List<T>) entityManager.getSession().createQuery("from" + type.toString()).list(); 
		} catch(Exception e) {
			return null;
		}
	}


	
}
