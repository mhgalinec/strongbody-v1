package com.strongbody.member.servlet;

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
import com.strongbody.utils.MemberUtils;

@WebServlet(urlPatterns= {"/app/createMember"})
public class CreateMember extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreateMember.class.getName());
	
	//Show createMember form
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/memberJSP/createMember.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{

		// Get parameters from createMember.jsp POST method
		String fullName = request.getParameter("fullName");
		String dateOfBirthStr = request.getParameter("dateOfBirth");
		String sex = request.getParameter("sex");
		String contactNumber = request.getParameter("contactNumber");
		String email = request.getParameter("email");
		String diet = request.getParameter("diet");


		Date dateOfBirth = null;

		try {
			dateOfBirth = Date.valueOf(dateOfBirthStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		//Create a new member object (Auto-Incremented ID) and set the user entered values to it(membershipID and measurementID are not created now)
		Members member = new Members();
		member.setFullName(fullName);
		member.setDateOfBirth(dateOfBirth);
		member.setSex(sex);
		member.setContactNumber(contactNumber);
		member.setEmail(email);
		member.setDiet(diet);
		String error = null;

		//Create a new member 
		try {
			LOGGER.info("New member was created: " + member.getFullName());
			MemberUtils.createMember(member);
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		// Forward if there was an error, redirect if everything is ok
		if(error != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
}
