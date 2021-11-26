package com.strongbody.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MySQLConnectionPool {
	private static DataSource ds;
	private static final Logger LOGGER = LogManager.getLogger(MySQLConnectionPool.class.getName());

	public static void init() {
	try {
		//Create a JNDI initial context to be able to lookup the DataSource
		InitialContext ctx = new InitialContext();
		//Lookup the DataSource,which will be backed by a pool that the application server provides
		ds = (DataSource)ctx.lookup("java:comp/env/strongbody-v1.0");
		LOGGER.info("Initializing Connection Pool");
		if(ds==null) {
			LOGGER.error("Unknown DataSource");
			throw new ServletException("Unknown DataSource");
		}
	}catch(NamingException | ServletException ex) {
		LOGGER.error(ex);
		}
	}
	
	
	public static Connection getConnection() throws SQLException {
		
		if(ds == null) init();
		return ds.getConnection();
	}
	
}
