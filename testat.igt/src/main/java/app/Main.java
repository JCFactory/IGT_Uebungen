package app;

import org.hibernate.Transaction;

import entity.Customer;

public class Main {
	public static void main(String[] args) {
		
		EntityManager entityManager = new EntityManager();
		entityManager.setup();
		
		
		/**
		 * AUFGABE 2 - BEWEIS DASS HIBERNATE KEINE GESCHACHTELTEN TRANSAKTIONEN ZULÄSST
		 */
//		Transaction t = entityManager.getSession().beginTransaction();
//		t.commit();
		
		
		
		Customer customer = new Customer();

		entityManager.close();
	}
}