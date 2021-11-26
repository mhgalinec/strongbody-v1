package com.strongbody.unplanned.services.servlet;

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

import com.strongbody.beans.UnplannedServices;
import com.strongbody.utils.ServicesUtils;

@WebServlet(urlPatterns= {"/app/updateUnplannedService"})
public class UpdateUnplannedService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateUnplannedService.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		
		// Get parameter from unplannedServices.jsp href( ?id=${unplannedService.id } )
		String idStr = request.getParameter("id");
		String equipmentName = request.getParameter("equipment");
		
		int id = 0;
		@SuppressWarnings("unused")
		String error = null;
		UnplannedServices service = null;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Find service
		try {
			service = ServicesUtils.findUnplannedService(id);
		}catch(SQLException e) {
			e.printStackTrace();
			LOGGER.error(e);
		}
		
		// Set service object in "unplannedService" attribute - this will show values of the found service object in the form(instead of an empty form)
		request.setAttribute("unplannedService", service);
		request.setAttribute("equipmentName", equipmentName);
		
		// Redirect if no service was found and stop the execution of the code below, forward if everything is ok
		if(service == null) {
			LOGGER.info("No service was found");
			response.sendRedirect(request.getContextPath() + "/app/equipmentList");
			return;
		}
			
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/unplannedServicesJSP/updateUnplannedService.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		
		//Get parameters from updateUnplannedService.jsp POST method
		String idStr = request.getParameter("id");
		String faultType = request.getParameter("faultType");
		String dateOfFaultStr = request.getParameter("dateOfFault");
		String warranty = request.getParameter("warranty");
		String priceStr = request.getParameter("price");
		String serviceCompany = request.getParameter("serviceCompany");
		
		RequestDispatcher dispatcher = null;
		String error = null;
		Date dateOfFault = null;
		int id = 0;
		float price = 0;
		
		try {
			id = Integer.parseInt(idStr);
			price = Float.parseFloat(priceStr);
			dateOfFault = Date.valueOf(dateOfFaultStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		// Create a new service object with user entered values
		UnplannedServices service = new UnplannedServices(id,faultType,dateOfFault,warranty,price,serviceCompany);
		
		try {
			LOGGER.info("Unplanned service was successfully updated!");
			ServicesUtils.updateUnplannedService(service);
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		//Forward if there was an error, redirect if everything is ok
		if(error!= null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/equipmentList");
		}
		
		
	}
}
