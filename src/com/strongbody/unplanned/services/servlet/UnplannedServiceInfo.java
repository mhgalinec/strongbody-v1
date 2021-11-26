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

import com.strongbody.beans.UnplannedServices;
import com.strongbody.utils.ServicesUtils;

@WebServlet(urlPatterns= {"/app/unplannedService"})
public class UnplannedServiceInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UnplannedServiceInfo.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws	
		ServletException,IOException{
		RequestDispatcher dispatcher = null;
		
		// Get parameters from equipmentList.jsp href ( ?id=${equipment.id }&amp;equipmentName=${equipment.name} )	
		String idStr = request.getParameter("id");
		String equipmentID = request.getParameter("id");
		String equipmentName = request.getParameter("equipmentName");
		
		UnplannedServices service = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
			
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		
		try {
			service = ServicesUtils.unplannedServicesInfo(id);
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		
		// Set attributes for unplannedService.jsp
		request.setAttribute("equipmentName", equipmentName);
		request.setAttribute("unplannedService", service);
		
		// Forward to createUnplannedServices.jsp if equipment has no services or to unplannedServices.jsp if equipment already has a service
		if(service == null) {
			LOGGER.info("No unplanned service was found, redirecting to Create Unplanned Service");
			// Set equimentID attribute to createUnplannedServices.jsp and use it as a hidden field
			request.setAttribute("equipmentID", equipmentID);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/unplannedServicesJSP/createUnplannedService.jsp");
		}else {	
			LOGGER.info("Reviewing unplanned service for "+ equipmentName);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/unplannedServicesJSP/unplannedService.jsp");		
		}
		dispatcher.forward(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws	
		ServletException,IOException{
		doGet(request,response);
	}
}
