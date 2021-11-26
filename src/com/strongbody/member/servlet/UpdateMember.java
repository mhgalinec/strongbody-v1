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

@WebServlet(urlPatterns= {"/app/updateMember"})

public class UpdateMember extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateMember.class.getName());
	//Show edit member page
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		//Get parameter from memberList href ( ?id=${member.id} )
		String idStr = request.getParameter("id"); 
		
		Members member = null;
		String error = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Find member
		try {
			member = MemberUtils.findMember(id);
			
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		//Redirect if the member does not exist and stop the code execution below
		if(member == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/memberJSP/memberList.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("error", error);
		// Set member object in "member" attribute - this will show values of the found member object in the form(instead of an empty form)
		request.setAttribute("member", member);
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/memberJSP/updateMember.jsp");
		dispatcher.forward(request, response);
			
	}
	
	//After modification and submiting this method will be called
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		String error = null;
		//Get parameters from updateMember.jsp POST method
		String idStr = request.getParameter("id");
		String fullName = request.getParameter("fullName");
		String dateOfBirthStr =  request.getParameter("dateOfBirth");
		String sex =  request.getParameter("sex");
		String contactNumber =  request.getParameter("contactNumber");
		String email =  request.getParameter("email");
		String diet =  request.getParameter("diet");

		
		int id = 0;
		Date dateOfBirth = null;
		
		try {
			id = Integer.parseInt(idStr);
			dateOfBirth =  Date.valueOf(dateOfBirthStr);

		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Create new member object and set the user entered values to it
		Members member = new Members();
		member.setId(id);
		member.setFullName(fullName);
		member.setDateOfBirth(dateOfBirth);
		member.setSex(sex);
		member.setContactNumber(contactNumber);
		member.setEmail(email);
		member.setDiet(diet);

		//Update the old member object with the new member object
		try {
			LOGGER.info("Member " + member.getFullName() + " was updated!");
			MemberUtils.updateMember(member);
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		//Forward if there was an error,redirect if everything is ok
		if(error != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
}
