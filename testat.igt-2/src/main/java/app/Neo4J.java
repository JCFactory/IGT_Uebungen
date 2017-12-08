package app;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import com.mysql.jdbc.Statement;

import static org.neo4j.driver.v1.Values.parameters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class Neo4J implements AutoCloseable {


	public static void main(String... args) throws Exception {

		// Connect
		Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://localhost");

		// Querying
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("MATCH (n:User) RETURN n.name");
			while (rs.next()) {
				System.out.println(rs.getString("n.name"));
			}
		}
		con.close();
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
