package com.strongbody.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(urlPatterns= {"/logout"})
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(Logout.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		HttpSession session = request.getSession(false);
		String uname = (String) session.getAttribute("user");
		if(session != null) {
			LOGGER.info(uname + " logged out");
			session.removeAttribute("user");
			session.invalidate();
			
		}
		
		response.sendRedirect(request.getContextPath() + "/login");
	}
}

