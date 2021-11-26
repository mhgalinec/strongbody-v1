package com.strongbody.scheduled.services.servlet;

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

import com.strongbody.beans.Equipment;
import com.strongbody.beans.ScheduledServices;
import com.strongbody.utils.EquipmentUtils;
import com.strongbody.utils.ServicesUtils;

@WebServlet(urlPatterns= {"/app/createScheduledService"})
public class CreateScheduledService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreateScheduledService.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/scheduledServicesJSP/createScheduledService.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws 
		ServletException,IOException{
		
		// Get parameters from createScheduledServices.jsp POST method
		String serviceType = request.getParameter("serviceType");
		String serviceDateStr = request.getParameter("serviceDate");
		String warranty = request.getParameter("warranty");
		String priceStr = request.getParameter("price");
		String serviceCompany = request.getParameter("serviceCompany");
		
		//Get parameter from createScheduledServices.jsp hidden field(POST method)
		String equipmentIDStr = request.getParameter("equipmentID");
		
		String equipmentName = request.getParameter("equipmentName");
		
		Date serviceDate = null;
		float price = 0;
		String error = null;
		RequestDispatcher dispatcher = null;
		int equipmentID = 0;
		
		try {
			serviceDate = Date.valueOf(serviceDateStr);
			price = Float.parseFloat(priceStr);
			equipmentID = Integer.parseInt(equipmentIDStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// ID is Auto-Incremented, setters are used to create new service object
		ScheduledServices service = new ScheduledServices();
		service.setServiceType(serviceType);
		service.setServiceDate(serviceDate);
		service.setWarranty(warranty);
		service.setPrice(price);
		service.setServiceCompany(serviceCompany);
		service.setEquipmentID(equipmentID);
		
		try {
			// Create new ScheduledService object
			ServicesUtils.createScheduledService(service);
			LOGGER.info("New scheduled service was created for " + equipmentName );
				
			//Update equipment with new serviceID automatically when its created
			Equipment equipment = new Equipment();
			
			// ScheduledServicesID was returned as a generated key in ServicesUtils.createScheduledServices();
			equipment.setScheduledServicesID(service.getId());
			equipment.setId(equipmentID);
			
			EquipmentUtils.scheduledServicesUpdate(equipment);
			LOGGER.info(equipmentName + " was updated with new scheduled service");
			
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		// Forward If there is an error, redirect if everything is ok
		if(error != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/equipmentList");
		}
	}
}
