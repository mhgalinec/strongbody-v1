package com.strongbody.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;

public class ConnUtils {

	public static final String ATT_NAME_CONNECTION ="ATTRIBUTE_FOR_CONNECTION";
	@SuppressWarnings("unused")
	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORED_USER_NAME_IN_COOKIE";
	
	public static void storeConnection(ServletRequest request,Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
}
