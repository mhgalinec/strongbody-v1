package com.strongbody.membership.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.strongbody.beans.Membership;
import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/activeMembership"})
public class ActiveMembership extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ActiveMembership.class.getName());

	//Show only active memberships in the database
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{

		RequestDispatcher dispatcher = null;
		String error = null;
		List<Membership> list = null;
		
		
		try {
			LOGGER.info("Reviewing list of active memberships");
			list = MembershipUtils.activeMembership();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		request.setAttribute("error", error);

		// Set list of active memberships in "activeMembership" attribute - used in activeMembership.jsp to loop through the list 
		request.setAttribute("activeMembership", list);

		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/activeMembership.jsp");
		dispatcher.forward(request, response);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		doGet(request,response);
	}
}
