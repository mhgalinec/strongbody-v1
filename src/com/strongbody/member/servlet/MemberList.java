package com.strongbody.member.servlet;

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

import com.strongbody.beans.Members;
import com.strongbody.utils.MemberUtils;

@WebServlet(urlPatterns= {"/app/memberList"})

public class MemberList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MemberList.class.getName());
	
	// Show all members in database
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
		ServletException,IOException{		
	
		RequestDispatcher dispatcher = null;
		
		String error = null;
		List<Members> list = null;
		
		try {
			list = MemberUtils.listMembers();
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		
		request.setAttribute("error", error);
		// Set list of members in "memberList" attribute - used in memberList.jsp to loop through the list 
		request.setAttribute("memberList", list);
		
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/memberJSP/memberList.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		doGet(request,response);
	}
}
