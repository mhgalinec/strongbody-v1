package com.strongbody.membership.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/deleteMembership"})
public class DeleteMembership extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DeleteMembership.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws 
		ServletException,IOException{
		String error = null;
		// Get parameter from membershipList.jsp href ( ?id=${membership.id} )
		String id = request.getParameter("id");
		
		//Delete Membership
		try{
			LOGGER.info("Membership Deleted!");
			MembershipUtils.deleteMembership( id);
		} catch (SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
	
		//Forward if there was an error,redirect if everything is ok
		if(error != null) {
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/membershipList");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws 
		ServletException,IOException{
		doGet(request,response);
	}
}
