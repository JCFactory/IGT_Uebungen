package app;


import javax.ws.rs.core.Application;

import org.springframework.boot.SpringApplication;

import dao.Dao;
import entity.Customer;

public class Main {
	public static void main(String[] args) {
		
		
		//SpringApplication.run(Application.class, args);
		
		EntityManager entityManager = new EntityManager(Database.MYSQL);
		entityManager.setup();
		
		
		/**
		 * AUFGABE 2 - BEWEISEN DASS HIBERNATE KEINE GESCHACHTELTEN TRANSAKTIONEN ZUL�SST
		 */
//		Transaction t = entityManager.getSession().beginTransaction();
//		entityManager.getSession().save(new Item());
//		t.commit();
		
			
		Dao<Customer> customerDao = new Dao<Customer>(entityManager, Customer.class);
		
		
		// CREATE NEW CUSTOMER
		Customer c = new Customer();
		c.setC_company("SAP");
		c.setC_forename("Peter");
		c.setC_surname("Klaus");

		// SAVE CUSTOMER TO DATABASE
		customerDao.save(c);
		
		// FIND AND EDIT CUSTOMER
		customerDao.edit(customerDao.find(c.getCustomer_id()));
		
		// FIND AND DELETE CUSTOMER
		customerDao.delete(customerDao.find(c.getCustomer_id()));
		
		entityManager.close();
	}
}
