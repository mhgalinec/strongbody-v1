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

import com.strongbody.utils.MeasurementsUtils;

@WebServlet(urlPatterns= {"/app/deleteMeasurements"})
public class DeleteMeasurements extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DeleteMeasurements.class.getName());
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		// Get parameter from measurements.jsp href( ?id=${measurements.id } )
		String id = request.getParameter("id");
		String error = null;
		
		// Delete measurements
		try {
			MeasurementsUtils.deleteMeasurement(id);
			LOGGER.info("Measurements Deleted");
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		// Forward if there was an error,redirect if everything is ok
		if(error != null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}
	}
}
