package com.strongbody.scheduled.services.servlet;

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

import com.strongbody.beans.ScheduledServices;
import com.strongbody.utils.ServicesUtils;

@WebServlet(urlPatterns= {"/app/scheduledService"})
public class ScheduledServiceInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(ScheduledServiceInfo.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws	
		ServletException,IOException{
		RequestDispatcher dispatcher = null;
		
		// Get parameters from equipmentList.jsp href ( ?id=${equipment.id }&amp;equipmentName=${equipment.name} )	
		String idStr = request.getParameter("id");
		String equipmentID = request.getParameter("id");
		String equipmentName = request.getParameter("equipmentName");
		
		ScheduledServices service = null;
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
			
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		
		try {
			service = ServicesUtils.servicesInfo(id);
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		
		// Set attributes for scheduledServices.jsp
		request.setAttribute("equipmentName", equipmentName);
		request.setAttribute("scheduledService", service);
		
		// Forward to createScheduledServices.jsp if equipment has no services or to scheduledServices.jsp if equipment already has a service
		if(service == null) {
			LOGGER.info("No scheduled service was found, redirecting to Create Scheduled Service");
			// Set equimentID attribute to createScheduledServices.jsp and use it as a hidden field
			request.setAttribute("equipmentID", equipmentID);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/scheduledServicesJSP/createScheduledService.jsp");
		}else {	
			LOGGER.info("Reviewing scheduled service for "+ equipmentName);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/scheduledServicesJSP/scheduledService.jsp");		
		}
		dispatcher.forward(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws	
		ServletException,IOException{
		doGet(request,response);
	}
}
