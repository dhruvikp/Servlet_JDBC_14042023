package com.simplilearn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private Connection connection;
	
	public DbUtil(String dbUrl, String username, String password) throws ClassNotFoundException, SQLException {
		// STEP 1: Register Driver
		Class.forName("com.mysql.jdbc.Driver");
		
		// STEP 2: Get Connection Object
		this.connection = DriverManager.getConnection(dbUrl, username, password);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() throws SQLException {
		if(this.connection!=null) {
			this.connection.close();
		}
	}
}
