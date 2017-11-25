package app;

import entity.Customer;

public class Main {
	public static void main(String[] args) {
		
		EntityManager entityManager = new EntityManager();
		entityManager.setup();
		
		entityManager.getSession().save(new Customer("Christopher" , "Hansen", "MHP"));
		entityManager.close();
	}
}