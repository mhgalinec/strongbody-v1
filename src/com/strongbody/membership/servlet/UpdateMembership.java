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

import com.strongbody.beans.Membership;
import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/updateMembership"})
public class UpdateMembership  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateMembership.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		RequestDispatcher dispatcher = null;
		// Get parameter from membership.jsp href( ?id=${membership.id } )
		String idStr = request.getParameter("id");
		
		String memberName = request.getParameter("member");
		
		
		Membership membership = null;
		@SuppressWarnings("unused")
		String error = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Find membership
		try{
			membership = MembershipUtils.findMembership(id);
		}catch(SQLException e ) {
			LOGGER.error(e);
			
		}
		// Set membership object in "membership" attribute - this will show values of the found membership object in the form(instead of an empty form)
		request.setAttribute("membership", membership);
		request.setAttribute("member",memberName);
		
		// Redirect if no membership was found and stop the execution of the code below, forward if everything is ok
		if(membership == null) {
			LOGGER.info("No membership found...");
			response.sendRedirect(request.getServletContext() + "/app/memberList");
			return;
		}
			
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/updateMembership.jsp");
		dispatcher.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		
		//Get parameters from updateMembership.jsp POST method 
		String idStr = request.getParameter("id");
		String registrationDateStr = request.getParameter("registrationDate");
		String validFromStr = request.getParameter("validFrom");
		String validThroughStr = request.getParameter("validThrough");
		String serviceLevel = request.getParameter("serviceLevel");
		String membershipType = request.getParameter("membershipType");
		
		String membershipTypeGet=request.getParameter("membershipTypeGet");
		
		System.out.println(serviceLevel + " " + membershipType);

		RequestDispatcher dispatcher = null;
		int id = 0;
		Date registrationDate = null;
		Date validFrom = null;
		Date validThrough = null;
		String error = null;
	
		
		try {
			id = Integer.parseInt(idStr);
			registrationDate = Date.valueOf(registrationDateStr);
			validFrom = Date.valueOf(validFromStr);
			validThrough = Date.valueOf(validThroughStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}

		//Create new membership object with user entered values
		Membership membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
		//Tests if the membership type has been changed(if it did,the membership is set as "Awaiting Payment")
		if(!membershipTypeGet.equals(membershipType)){
			membership.setPaymentStatus("Pending");
		}else {
			membership.setPaymentStatus("Payed");
		}
		
		// Update the old membership object with the new membership object
		try{
			LOGGER.info("Membership successfully updated!");
			MembershipUtils.updateMembership(membership);
		} catch (SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		

		
		//Forward if there was an error, redirect if everything is ok
		if(error!= null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request,response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
	
	
	
}
