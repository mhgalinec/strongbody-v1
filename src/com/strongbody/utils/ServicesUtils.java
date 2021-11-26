package com.strongbody.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.strongbody.beans.ScheduledServices;
import com.strongbody.beans.UnplannedServices;
import com.strongbody.connection.MySQLConnectionPool;

public class ServicesUtils {

	/* 	
	 * 
	 * SCHEDULED SERVICES
	 * 
	 */
	
	// Find services by ID - method used in EditScheduledService servlet
	public static ScheduledServices findScheduledService(int id) throws SQLException{
		String query = "SELECT * FROM scheduled_services WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
			id = rs.getInt("id");
			String serviceType = rs.getString("service_type");
			Date serviceDate = rs.getDate("service_date");
			String warranty = rs.getString("warranty");
			float price = rs.getFloat("price");
			String serviceCompany = rs.getString("service_company");
			
			ScheduledServices services = new ScheduledServices(id,serviceType,serviceDate,warranty,price,serviceCompany);
			return services;
			}
		}
		return null;
	}
	
	// Create new scheduled service - method used in CreateScheduledService servlet
	public static void createScheduledService(ScheduledServices services) throws SQLException{
		String query = "INSERT INTO scheduled_services(service_type,service_date,warranty,price,service_company,equipment_id) VALUES (?,?,?,?,?,?)";;
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		try(conn;stmt){
			stmt.setString(1, services.getServiceType());
			stmt.setDate(2, services.getServiceDate());
			stmt.setString(3, services.getWarranty());
			stmt.setFloat(4, services.getPrice());
			stmt.setString(5, services.getServiceCompany());
			stmt.setInt(6, services.getEquipmentID());
			
			stmt.executeUpdate();	
			
			ResultSet rs = stmt.getGeneratedKeys();
			try(rs){
				if(rs.next()) {
					int id = rs.getInt(1);
					services.setId(id);
				}
			}
		}
			
	}
	
	// Update ScheduledService - method used in UpdateScheduledService servlet doPost
	public static void updateScheduledService(ScheduledServices services) throws SQLException{
		String query = "UPDATE scheduled_services SET service_type=?,service_date=?,warranty=?,price=?,service_company=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			stmt.setString(1, services.getServiceType());
			stmt.setDate(2, services.getServiceDate());
			stmt.setString(3, services.getWarranty());
			stmt.setFloat(4, services.getPrice());
			stmt.setString(5, services.getServiceCompany());
			stmt.setInt(6, services.getId());
			
			stmt.executeUpdate();
		}
		
	}
	
	// Show Scheduled service of equipment selected by ID - used in ScheduledServiceInfo servlet
	public static ScheduledServices servicesInfo (int equipmentID) throws SQLException{

		//SQL Statement that selects all records from scheduled services based on foreign key in equipment
		String query ="SELECT * FROM scheduled_services INNER JOIN equipment ON scheduled_services.id = equipment.scheduled_service_id WHERE equipment.id=?";
		ScheduledServices services = null;
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1,equipmentID);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
				int id = rs.getInt("id");
				String serviceType = rs.getString("service_type");
				Date serviceDate = rs.getDate("service_date");
				String warranty = rs.getString("warranty");
				float price = rs.getFloat("price");
				String serviceCompany = rs.getString("service_company");

				services = new ScheduledServices(id,serviceType,serviceDate,warranty,price,serviceCompany);
					
			}
		}
		return services;
	}
	
	// Delete scheduled service - used in DeleteScheduledService servlet
	public static void deleteScheduledService (String id) throws SQLException{
		String query = "DELETE FROM scheduled_services WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){
			stmt.setString(1,id);
			
			stmt.executeUpdate();
		}
		
	}
	
	/*
	 * 
	 * UNPLANNED SERVICES
	 * 
	 * 
	 */
	
	// Find services by ID - method used in EditUnplannedService servlet
	public static UnplannedServices findUnplannedService(int id) throws SQLException{
		String query = "SELECT * FROM unplanned_services WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
			id = rs.getInt("id");
			String faultType = rs.getString("fault_type");
			Date dateOfFault = rs.getDate("date_of_fault");
			String warranty = rs.getString("warranty");
			float price = rs.getFloat("price");
			String serviceCompany = rs.getString("service_company");
			
			UnplannedServices services = new UnplannedServices(id,faultType,dateOfFault,warranty,price,serviceCompany);
			return services;
			}
		}
		return null;
	}
	
	// Create new unplanned service - method used in CreateUnplannedService servlet
	public static void createUnplannedService(UnplannedServices services) throws SQLException{
		String query = "INSERT INTO unplanned_services(fault_type,date_of_fault,warranty,price,service_company,equipments_id) VALUES (?,?,?,?,?,?)";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		try(conn;stmt){
			stmt.setString(1, services.getFaultType());
			stmt.setDate(2, services.getDateOfFault());
			stmt.setString(3, services.getWarranty());
			stmt.setFloat(4, services.getPrice());
			stmt.setString(5, services.getServiceCompany());
			stmt.setInt(6, services.getEquipmentID());
			
			stmt.executeUpdate();	
			
			ResultSet rs = stmt.getGeneratedKeys();
			try(rs){
				if(rs.next()) {
					int id = rs.getInt(1);
					services.setId(id);
				}
			}
		}
			
	}
	
	// Update UnplannedService - method used in UpdateUnplannedService servlet doPost
	public static void updateUnplannedService(UnplannedServices services) throws SQLException{
		String query = "UPDATE unplanned_services SET fault_type=?,date_of_fault=?,warranty=?,price=?,service_company=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			stmt.setString(1, services.getFaultType());
			stmt.setDate(2, services.getDateOfFault());
			stmt.setString(3, services.getWarranty());
			stmt.setFloat(4, services.getPrice());
			stmt.setString(5, services.getServiceCompany());
			stmt.setInt(6, services.getId());
			
			stmt.executeUpdate();
		}
		
	}
	
	// Show Unplanned service of equipment selected by ID - used in UnplannedServiceInfo servlet
	public static UnplannedServices unplannedServicesInfo (int equipmentID) throws SQLException{

		//SQL Statement that selects all records from scheduled services based on foreign key in equipment
		String query ="SELECT * FROM unplanned_services INNER JOIN equipment ON unplanned_services.id = equipment.unplanned_service_id WHERE equipment.id=?";
		UnplannedServices services = null;
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1,equipmentID);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
				int id = rs.getInt("id");
				String faultType = rs.getString("fault_type");
				Date dateOfFault = rs.getDate("date_of_fault");
				String warranty = rs.getString("warranty");
				float price = rs.getFloat("price");
				String serviceCompany = rs.getString("service_company");

				services = new UnplannedServices(id,faultType,dateOfFault,warranty,price,serviceCompany);
					
			}
		}
		return services;
	}
	
	// Delete unplanned service - used in DeleteUnplannedService servlet
	public static void deleteUnplannedService (String id) throws SQLException{
		String query = "DELETE FROM unplanned_services WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){
			stmt.setString(1,id);
			
			stmt.executeUpdate();
		}
		
	}
	
}
