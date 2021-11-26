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

@WebServlet(urlPatterns= {"/app/updateEquipment"})
public class UpdateEquipment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(UpdateEquipment.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
	
		
		Equipment equipment = null;
		RequestDispatcher dispatcher = null;
		
		// Get id from equipmentList.jsp href ( ?id=${equipment.id} )
		String idStr = request.getParameter("id");
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		try {
			equipment = EquipmentUtils.findEquipment(id);
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		
		// Set equipment object in "equipment" attribute - this will show values of the found equipment object in the form(instead of an empty form)
		request.setAttribute("equipment", equipment);
		
		// Forward to updateEquipment.jsp if the coresponding equipment object was found, rediredt if there was an error
		if(equipment != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/equipmentJSP/updateEquipment.jsp");
			dispatcher.forward(request,response);
		}else {
			response.sendRedirect(request.getContextPath() + "/error");
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		String error = null;
		
		// Get parameters from updateEquipment.jsp POST method
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String serialNo = request.getParameter("serialNo");
		String category = request.getParameter("category");
		String manufacturer = request.getParameter("manufacturer");
		String width = request.getParameter("width");
		String length = request.getParameter("length");
		String heigth = request.getParameter("heigth");
		String weight = request.getParameter("weight");
		
		int id=0;
		
		try {
			id = Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Set user entered values in a new equipment object
		Equipment equipment = new Equipment();
		equipment.setId(id);
		equipment.setName(name);
		equipment.setSerialNo(serialNo);
		equipment.setCategory(category);
		equipment.setManufacturer(manufacturer);
		equipment.setWidth(width);
		equipment.setLength(length);
		equipment.setHeigth(heigth);
		equipment.setWeight(weight);
		
		// Update the old equipment object with the new equipment object
		try {
			EquipmentUtils.updateEquipment(equipment);
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}
		
		//request.setAttribute("equipment", equipment);
		
		// Forward if there was an error, redirect if everything is ok
		if(error != null) {
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/app/equipmentList");
		}
		
	}
	
}
