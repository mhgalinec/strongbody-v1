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
import com.strongbody.beans.Members;
import com.strongbody.utils.MeasurementsUtils;
import com.strongbody.utils.MemberUtils;

@WebServlet(urlPatterns= {"/app/createMeasurements"})
public class CreateMeasurements extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreateMeasurements.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/measurementJSP/createMeasurements.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{

		RequestDispatcher dispatcher = null;

		// Get parameters from createMeasurement.jsp POST method
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
		
		// Get parameter from createMeasurement.jsp hidden field(POST method)
		String memberIDStr = request.getParameter("memberID");
		
		String memberName = request.getParameter("memberName");

		int memberID = 0;
		Date measurementDate = null;
		String error = null;
		
		try {
			// If the user tries to create measurements for non existent user, forward to an error page and stop the execution of code below
			if(memberIDStr.equals("")) {
				LOGGER.info("No member was found");
				dispatcher= request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
				dispatcher.forward(request, response);
				return;
			}else {
			memberID = Integer.parseInt(memberIDStr);
			measurementDate = Date.valueOf(measurementDateStr);
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}
		
		// ID is Auto-Incremented, setters are used to create new measurements object
		Measurements measurements = new Measurements();
		measurements.setMeasurementDate(measurementDate);
		measurements.setHeight(height);
		measurements.setWeight(weight);
		measurements.setBodyFat(bodyFat);
		measurements.setShoulders(shoulders);
		measurements.setTorso(torso);
		measurements.setWaist(waist);
		measurements.setUpperArm(upperArm);
		measurements.setLowerArm(lowerArm);
		measurements.setUpperLeg(upperLeg);
		measurements.setLowerLeg(lowerLeg);
		measurements.setRestingHeartRate(restingHeartRate);
		measurements.setMemberID(memberID);
		
		
		try{
			//Create new measurements
			MeasurementsUtils.createMeasurements(measurements);
			LOGGER.info("New measurements were created for " + memberName);

			Members members = new Members();
			// measurementID was returned as a generated key in MeasurementUtils.createMeasurements();
			members.setMeasurementID(measurements.getId());
			members.setId(memberID);
			
			//Automatically update member with new measurements AFTER those were created
			MemberUtils.measurementsUpdate(members);
			LOGGER.info(memberName + " was updated with new measurements");
			
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}

		// Forward If there is an error, redirect if everything is ok
		if(error != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
}
