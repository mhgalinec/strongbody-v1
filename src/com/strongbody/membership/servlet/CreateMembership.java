package com.strongbody.membership.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.strongbody.beans.Members;
import com.strongbody.beans.Membership;
import com.strongbody.utils.MemberUtils;
import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/createMembership"})
public class CreateMembership extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreateMembership.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/createMembership.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{

		RequestDispatcher dispatcher = null;
		
		// Get parameters from createMmbership.jsp POST method
		String registrationDateStr = request.getParameter("registrationDate");
		String validFromStr = request.getParameter("validFrom");
		String validThroughStr = request.getParameter("validThrough");
		String serviceLevel = request.getParameter("serviceLevel");
		String membershipType = request.getParameter("membershipType");
		String paymentStatus = request.getParameter("paymentStatus");

		/* Automatic membership Insert step 5
		*  Get parameter from createMembership.jsp hidden field(POST method)
		*/
		String memberIDStr = request.getParameter("memberID");

		String memberName = request.getParameter("member");

		int memberID = 0;
		Date registrationDate = null;
		Date validFrom = null;
		Date validThrough = null;

		try {
			// If the user tries to create membership for non existent user, forward to an error page and stop the execution of code below
			if(memberIDStr.equals("")) {
				LOGGER.error("No member was found!");
				dispatcher= request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
				dispatcher.forward(request, response);
				return;
			}else {
			memberID = Integer.parseInt(memberIDStr);
			registrationDate = Date.valueOf(registrationDateStr);
			validFrom = Date.valueOf(validFromStr);
			validThrough = Date.valueOf(validThroughStr);
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}

		// ID is Auto-Incremented, setters are used to create new membership object
		Membership membership = new Membership();
		membership.setRegistrationDate(registrationDate);
		membership.setValidFrom(validFrom);
		membership.setValidThrough(validThrough);
		membership.setServiceLevel(serviceLevel);
		membership.setMembershipType(membershipType);
		membership.setPaymentStatus(paymentStatus);
		membership.setMemberID(memberID);
		String error = null;



		try {
			// Create new membership
			LOGGER.info("New membership was created for " + memberName );
			MembershipUtils.createMembership(membership);
			
			/* Automatic membership insert Step 6
			*  Update member with new membershipID automatically when its created
			*/
			Members member = new Members();
			// membershipID was returned as a generated key in MmbershipUtils.createMembership();
			member.setMembershipID(membership.getId());
			member.setId(memberID);
			
			MemberUtils.membershipUpdate(member);
			LOGGER.info(memberName + " was updated with new membership.");

		} catch (SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}

		// Forward If there is an error, redirect if everything is ok
		if(error != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}

}
