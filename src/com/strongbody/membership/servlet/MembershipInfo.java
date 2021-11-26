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

import com.strongbody.beans.Membership;
import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/membership"})

public class MembershipInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MembershipInfo.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{

		RequestDispatcher dispatcher = null;
		
		// Get parameters from memberList.jsp href ( ?id=${member.id }&amp;member=${member.fullName} )	
		String name = request.getParameter("member");
		
		/* Automatic membership Insert Step 1
		*  Create membership when member has none
		*/
		String memberID = request.getParameter("id");
		String idStr = request.getParameter("id");
		
		@SuppressWarnings("unused")
		String error = null;
		Membership membership = null;
		int ID = 0;
		
		try {
			ID = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
	
		try{
			membership = MembershipUtils.membershipInfo(ID);
		} catch (SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		
		request.setAttribute("member", name);
		request.setAttribute("membership", membership);
		
		
		/* Automatic membership Insert Step 2
		*  Redirect to Create Membership jsp if member has no membership
		*/
		if(membership == null) {
			/* Automatic membership Insert step 3
			*  Forward memberID to createMembership.jsp
			*/
			LOGGER.info("No membership found for " + name + ", redirecting to Create Membership.");
			request.setAttribute("memberID",memberID);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/createMembership.jsp");
			
		}else {	
				
			LOGGER.info("Reviewing membership for " + name);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/membership.jsp");
		}
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		doGet(request,response);
	}

}
