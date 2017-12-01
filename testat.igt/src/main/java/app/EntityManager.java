package app;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Customer;
import entity.District;
import entity.Item;
import entity.ItemOrder;
import entity.Warehouse;

public class EntityManager {

	protected Configuration configuration;
	protected Properties properties;
	protected SessionFactory sessionFactory;
	protected Session session;
	protected Transaction transaction;

	public void setup() {
		this.properties = new Properties();	
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:1521/benchmark");
		properties.setProperty("hibernate.connection.username", "java");
		properties.setProperty("hibernate.connection.password", "qq11ww22!!!");
		properties.setProperty("hibernate.flushMode", "auto");
		properties.setProperty("log4j.logger.org.hibernate.type=trace ", "trace");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		this.configuration = new Configuration();
		configuration.addProperties(properties);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(District.class);
		configuration.addAnnotatedClass(Item.class);
		configuration.addAnnotatedClass(ItemOrder.class);
		configuration.addAnnotatedClass(Warehouse.class);

		this.sessionFactory = configuration.buildSessionFactory();
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
	}
	
	public Session getSession() {
		return this.session;
	}

	public void close() {
		this.transaction.commit();
		this.session.close();
		this.sessionFactory.close();
	}

}
