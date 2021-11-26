package com.strongbody.measurement.servlet;

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

import com.strongbody.beans.Measurements;
import com.strongbody.utils.MeasurementsUtils;

@WebServlet(urlPatterns= {"/app/updateMeasurements"})
public class UpdateMeasurements extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateMeasurements.class.getName());

	
	//Show updateMeasurements page
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		RequestDispatcher dispatcher = null;
		
		// Get parameter from measurements.jsp href( ?id=${measurements.id } )
		String idStr = request.getParameter("id");
		String memberName = request.getParameter("name");
		
		Measurements measurements = null;
		int id= 0;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Find measurement based on measurement.jsp ID parameter
		try{
			measurements = MeasurementsUtils.findMeasurements(id);
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		
		// Set measurements object in "measurements" attribute - this will show values of the found measurement object in the form(instead of an empty form)
		request.setAttribute("measurements", measurements);
		request.setAttribute("member", memberName);
		
		// Redirect if no measurement was found and stop the execution of the code below, forward if everything is ok
		if(measurements == null) {
			LOGGER.error("No measurements found for updating");
			response.sendRedirect(request.getContextPath() + "/app/memberList");
			return;
		}
		
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/measurementJSP/updateMeasurements.jsp");
		dispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException,IOException{

		RequestDispatcher dispatcher = null;
		
		// Get the parameters from updateMeasurements.jsp POST method
		String idStr = request.getParameter("id");
		String measurementDateStr = request.getParameter("measurementDate");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String bodyFat = request.getParameter("bodyFat");
		String shoulders = request.getParameter("shoulders");
		String torso = request.getParameter("torso");
		String waist = request.getParameter("waist");
		String upperArm = request.getParameter("upperArm");
		String lowerArm = request.getParameter("lowerArm");
		String upperLeg = request.getParameter("upperLeg");
		String lowerLeg = request.getParameter("lowerLeg");
		String restingHeartRate = request.getParameter("restingHeartRate");
		
		int id=0;
		Date measurementDate = null;
		String error = null;
		
		try {
			id=Integer.parseInt(idStr);
			measurementDate = Date.valueOf(measurementDateStr);
		}catch(Exception e) {
			LOGGER.error(e);
		}
		
		// Create new measurements object with user entered values
		Measurements measurements = new Measurements(id,measurementDate,height,weight,bodyFat,shoulders,torso,waist,upperArm,lowerArm,upperLeg,lowerLeg,restingHeartRate);
		
		// Update the old measurements object with the new measurements object
		try	{
			MeasurementsUtils.updateMeasurements( measurements);
			LOGGER.info("Measurements successfully updated!");
		}catch(Exception e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		//Forward if there was an error, redirect if everything is ok
		if(error != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
		
		
	}
	
}
