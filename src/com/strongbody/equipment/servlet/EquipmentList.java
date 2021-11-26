package com.strongbody.equipment.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


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

@WebServlet(urlPatterns= {"/app/equipmentList"})
public class EquipmentList extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(EquipmentList.class.getName());
	
	//Show all equipment in database
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		List<Equipment> list = null;
		
		try {
			list = EquipmentUtils.equipmentList();	
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		
		// Set list of equipments in "equipmentList" attribute - used in equipmentList.jsp to loop through the list
		request.setAttribute("equipmentList", list);
		
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/equipmentJSP/equipmentList.jsp");
		dispatcher.forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException,IOException{
		doGet(request,response);
	}
		
}
