package app;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Customer;

public class EntityManager {

	protected Configuration configuration;
	protected Properties properties;
	protected SessionFactory sessionFactory;
	protected Session session;

	public void setup() {
		this.properties = new Properties();	
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/customers");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "");
		properties.setProperty("hibernate.connection.autocommit", "true");
		properties.setProperty("hibernate.flushMode", "auto");
		properties.setProperty("log4j.logger.org.hibernate.type=trace ", "trace");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		this.configuration = new Configuration();
		configuration.addProperties(properties);
		configuration.addAnnotatedClass(Customer.class);

		this.sessionFactory = configuration.buildSessionFactory();
		this.session = sessionFactory.openSession();

	}
	
	public Session getSession() {
		return this.session;
	}

	public void close() {
		this.session.close();
		this.sessionFactory.close();
	}

}
