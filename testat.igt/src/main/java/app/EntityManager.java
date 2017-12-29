package app;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.ogm.cfg.OgmConfiguration;

import entity.Customer;
import entity.District;
import entity.Item;
import entity.ItemOrder;
import entity.Warehouse;

public class EntityManager {

	private Configuration configuration;
	private Properties properties;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public EntityManager(Database database) {
		switch (database) {
		case MYSQL:
			this.configuration = new Configuration();
			this.properties = getMySQLProperties();
			break;

		case MONGODB:
			this.configuration = new OgmConfiguration();
			this.properties = getMongoDBProperties();
			break;

		case ORIENTDB:
			this.configuration = new OgmConfiguration();
			this.properties = getOrientProperties();
			break;

		case CASSANDRA:
			this.configuration = new OgmConfiguration();
			this.properties = getCassandraProperties();
			break;

		case REDIS:
			this.configuration = new OgmConfiguration();
			this.properties = getRedisProperties();
			break;

		default:
			System.out.println("ERROR CONFIGURATING DATABASE");
			break;
		}

		this.configuration.addProperties(properties).addAnnotatedClass(Customer.class).addAnnotatedClass(District.class)
				.addAnnotatedClass(Item.class).addAnnotatedClass(ItemOrder.class).addAnnotatedClass(Warehouse.class);
	}

	
	public void setup() {
		this.sessionFactory = configuration.buildSessionFactory();
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();

	}

	private Properties getMySQLProperties() {
		Properties properties = new Properties();

		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:1521/benchmark");
		properties.setProperty("hibernate.connection.username", "java");
		properties.setProperty("hibernate.connection.password", "qq11ww22!!!");
		properties.setProperty("hibernate.flushMode", "auto");
		properties.setProperty("log4j.logger.org.hibernate.type=trace ", "trace");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		return properties;
	}

	private Properties getMongoDBProperties() {
		Properties properties = new Properties();

		properties.setProperty("hibernate.ogm.datastore.provider", "mongodb");
		properties.setProperty("hibernate.ogm.datastore.host", "127.0.0.1");
		properties.setProperty("hibernate.ogm.datastore.port", "27017");
		properties.setProperty("hibernate.ogm.datastore.database", "benchmark");
		properties.setProperty("hibernate.ogm.datastore.create_database", "true");

		return properties;
	}

	private Properties getOrientProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.ogm.datastore.provider", "orientdb_neo4j");
		properties.setProperty("hibernate.connection.url", "192.168.178.20");
		properties.setProperty("hibernate.ogm.datastore.port", "2481");
		properties.setProperty("hibernate.connection.username", "root");
		properties.setProperty("hibernate.connection.password", "root");
		properties.setProperty("hibernate.ogm.datastore.database", "benchmark");
		properties.setProperty("hibernate.ogm.datastore.create_database", "true");
		
	


		return properties;
	}

	private Properties getCassandraProperties() {
		Properties properties = new Properties();	
		properties.setProperty("hibernate.ogm.datastore.provider", "cassandra_experimental");
		properties.setProperty("hibernate.ogm.datastore.host", "127.0.0.1");
		properties.setProperty("hibernate.ogm.datastore.port", "9042");
		properties.setProperty("hibernate.ogm.datastore.database", "benchmark");
		properties.setProperty("hibernate.ogm.datastore.create_database", "true");

		return properties;
	}

	private Properties getRedisProperties() {
		Properties properties = new Properties();

		properties.setProperty("hibernate.ogm.datastore.provider", "redis_experimental");
		properties.setProperty("hibernate.ogm.datastore.host", "127.0.0.1");
		properties.setProperty("hibernate.ogm.datastore.port", "6379");
		properties.setProperty("hibernate.ogm.datastore.database", "0");
		properties.setProperty("hibernate.ogm.datastore.create_database", "true");

		return properties;
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
