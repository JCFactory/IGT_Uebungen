package app;

import entity.Customer;

public class Main {
	public static void main(String[] args) {
		
		EntityManager entityManager = new EntityManager(Database.CASSANDRA);
		
		/**
		 * AUFGABE 2 - BEWEISEN DASS HIBERNATE KEINE GESCHACHTELTEN TRANSAKTIONEN ZULÄSST
		 */
//		Transaction t = entityManager.getSession().beginTransaction();
//		t.commit();
		
		
		
		Customer customer = new Customer();
		entityManager.getSession().save(customer);

		entityManager.close();
	}
}
