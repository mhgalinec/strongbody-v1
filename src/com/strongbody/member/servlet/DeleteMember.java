package com.strongbody.member.servlet;

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

import com.strongbody.utils.MemberUtils;

@WebServlet(urlPatterns= {"/app/deleteMember"})
public class DeleteMember extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DeleteMember.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		String error = null;	
		// Get parameter from memberList.jsp href ( ?id=${member.id} )
		String id = request.getParameter("id");
		
		String memberName = request.getParameter("member");
		
		// Delete member
		try {
			MemberUtils.deleteMember( id);
			LOGGER.info(memberName + " deleted!");
		}catch(SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		//Forward if there was an error,redirect if everything is ok
		if(error != null) {
			request.setAttribute("error", error);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		doGet(request,response);
	}
}
