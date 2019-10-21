package com.cyp.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	private String url = "jdbc:mysql://localhost:3306/movies?serverTimezone=UTC" +
            "&user=root&password=root";
	public DataBase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
		
		}
	
	}
	public Connection getCoonection() throws SQLException {
		return DriverManager.getConnection(url);
	}
	
	public void closeCoonection(Connection con) throws SQLException {
		con.close();
	}

}
