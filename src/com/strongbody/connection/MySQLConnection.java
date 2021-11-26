package com.strongbody.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySQLConnection {
	
	private static String servername ="localhost";
	private static String dbname = "strongbody-v1.0";
	private static String username = "root";
	private static String password = "metallica";
	private static Integer portnumber = 3306;
	
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		
		MysqlDataSource datasource = new MysqlDataSource();
		
		datasource.setServerName(servername);
		datasource.setPort(portnumber);
		datasource.setUser(username);
		datasource.setPassword(password);
		datasource.setDatabaseName(dbname);
		
		try {
			conn = datasource.getConnection();
		}catch(SQLException ex) {
			System.out.println("Error " + ex);
		}
		return conn;
		
	}

}
