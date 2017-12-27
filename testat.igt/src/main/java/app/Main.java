package app;

import entity.Customer;

public class Main {
	public static void main(String[] args) {
		
		EntityManager entityManager = new EntityManager(Database.NEO4J);
		
		/**
		 * AUFGABE 2 - BEWEISEN DASS HIBERNATE KEINE GESCHACHTELTEN TRANSAKTIONEN ZUL�SST
		 */
//		Transaction t = entityManager.getSession().beginTransaction();
//		t.commit();
		
		
		
		Customer customer = new Customer();
		customer.setC_company("SAP");
		customer.setC_forename("HAMID");
		customer.setC_surname("Ahmetovic");
		
		entityManager.setup();
		entityManager.getSession().save(customer);

		entityManager.close();
	}
}
