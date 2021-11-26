package com.strongbody.equipment.servlet;

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

import com.strongbody.beans.Equipment;
import com.strongbody.utils.EquipmentUtils;

@WebServlet(urlPatterns= {"/app/createEquipment"})
public class CreateEquipment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(CreateEquipment.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/equipmentJSP/createEquipment.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		// Get parameters from createEquipment.jsp POST method
		String name = request.getParameter("name");
		String serialNo = request.getParameter("serialNo");
		String category = request.getParameter("category");
		String manufacturer = request.getParameter("manufacturer");
		String width = request.getParameter("width");
		String length = request.getParameter("length");
		String heigth = request.getParameter("heigth");
		String weight = request.getParameter("weight");
		
		// ID is Auto-Incremented,setters are used to create a new Equipment object
		Equipment equipment = new Equipment();
		equipment.setName(name);
		equipment.setSerialNo(serialNo);
		equipment.setCategory(category);
		equipment.setManufacturer(manufacturer);
		equipment.setWidth(width);
		equipment.setLength(length);
		equipment.setHeigth(heigth);
		equipment.setWeight(weight);
		
		String error = null;
		RequestDispatcher dispatcher = null;
		
		
		try {
			EquipmentUtils.createEquipment(equipment);
			LOGGER.info("New Equipment Created");
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
