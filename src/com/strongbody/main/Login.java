package com.strongbody.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.strongbody.connection.MySQLConnectionPool;


public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());

	public Login() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		//Show the main login page
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/login.html");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		String query ="SELECT * FROM info WHERE uname=? AND pass=?";
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		try {
			Connection conn = MySQLConnectionPool.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, uname);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
		
			try(conn;stmt;rs){

				if(rs.next()) {
					// Set logged user to session attribute
					HttpSession session = request.getSession();
					session.setAttribute("user", uname);
					LOGGER.info(uname + " logged in successfully!");

					response.sendRedirect(request.getContextPath() + "/app/menu");
					
				}else {
					dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/loginError.jsp");
					dispatcher.forward(request,response);
				}				
			}
		
		}catch(SQLException e) {
			LOGGER.error(e);
		}
	}
	
}
