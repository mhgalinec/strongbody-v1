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

import com.strongbody.utils.EquipmentUtils;

@WebServlet(urlPatterns= {"/app/deleteEquipment"})
public class DeleteEquipment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(DeleteEquipment.class.getName());

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		
		RequestDispatcher dispatcher = null;
		String error = null;
		// Get id from equipmentList.jsp href ( ?id=${equipment.id} )
		String id = request.getParameter("id");
		
		String equipmentName = request.getParameter("equipmentName");
				
		try {
			EquipmentUtils.deleteEquipment(id);
			LOGGER.info(equipmentName + " deleted");
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
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws
		ServletException,IOException{
		doGet(request,response);
	}
	
}
