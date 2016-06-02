package ro.oho.rest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelperClass {
	private static Connection connection;
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "ciprian";
	private static final String PASSWORD = "parola";

	private ConnectionHelperClass() {
	}

	public static Connection getOracleConnection() {

		if (connection == null) {
			try {
				System.out.println("LOADING DRIVER...");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("DRIVER LOADED...");
				System.out.println("INITIALIZING CONNECTION...");
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("CONNECTION INITIALIZED!");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Cannot find the driver in the classpath!", e);
			} catch (SQLException e) {
				throw new IllegalStateException("Cannot connect the database!", e);
			}
		}

		return connection;
	}

}
