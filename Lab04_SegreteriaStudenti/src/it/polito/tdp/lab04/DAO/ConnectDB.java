package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	static private final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?user=root&password=bianca97";
	//static private Connection connection = null;

	public static Connection getConnection() {

		Connection conn;
		
		try {
			
				conn = DriverManager.getConnection(jdbcUrl);
			
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
		return conn;
	}

}
