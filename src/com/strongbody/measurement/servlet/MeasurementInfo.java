package com.strongbody.measurement.servlet;

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

import com.strongbody.beans.Measurements;
import com.strongbody.utils.MeasurementsUtils;

@WebServlet(urlPatterns= {"/app/measurements"})
public class MeasurementInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MeasurementInfo.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws 
		ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		
		// Get parameters from memberList.jsp href ( ?id=${member.id }&amp;member=${member.fullName} )
		String idStr = request.getParameter("id");
		String name = request.getParameter("member");
		String memberID = request.getParameter("id");
		
		int ID = 0;
		Measurements measurements = null;
		@SuppressWarnings("unused")
		String error = null;
		
		try {
			ID = Integer.parseInt(idStr);
		}catch(NumberFormatException e){
			LOGGER.error(e);
		}
		
		
		try{	
			measurements = MeasurementsUtils.memberMeasurements(ID);
			
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		// Set attributes for measurements.jsp
		request.setAttribute("measurements", measurements);
		request.setAttribute("member", name);
		

		// Forward to createMeasurement.jsp if member has no measurements or to measruements.jsp if member already has an measurement
		if(measurements == null) {
			// Set memberID attribute to createMeasurement.jsp and use it as a hidden field
			request.setAttribute("memberID", memberID);
			LOGGER.info("No measurements found for selected member, redirecting to Create Measurements");
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/measurementJSP/createMeasurements.jsp");			
		}else {
			LOGGER.info("Reviewing measurements of " + name);
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/measurementJSP/measurements.jsp");
		}
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws 
		ServletException,IOException{
		doGet(request,response);
	}
}
