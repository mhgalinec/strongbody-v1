package com.strongbody.unplanned.services.servlet;

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

import com.strongbody.utils.ServicesUtils;

@WebServlet(urlPatterns= {"/app/deleteUnplannedService"})
public class DeleteUnplannedService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DeleteUnplannedService.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		// Get parameter from unplannedServices.jsp href( ?id=${unplannedService.id } )
		String id = request.getParameter("id");
		String error = null;
		
		// Delete service
		try {
			ServicesUtils.deleteUnplannedService(id);
			LOGGER.info("Unplanned service deleted");
		}catch(SQLException e) {
			e.printStackTrace();
			error = e.getMessage();
		}
		
		// Forward if there was an error,redirect if everything is ok
		if(error != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/equipmentList");
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
	ServletException,IOException{
		doGet(request,response);
	}
}
