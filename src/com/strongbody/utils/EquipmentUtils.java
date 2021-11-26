package com.strongbody.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.strongbody.beans.Equipment;
import com.strongbody.connection.MySQLConnectionPool;

public class EquipmentUtils {

	// Find equipment by ID - used in UpdateEquipment servlet
	public static Equipment findEquipment(int id) throws SQLException{
		String query = "SELECT * FROM equipment WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
			id = rs.getInt("id");
			String name = rs.getString("name");
			String serialNo = rs.getString("serial_no");
			String category = rs.getString("category");
			String manufacturer = rs.getString("manufacturer");
			String width = rs.getString("width");
			String length = rs.getString("length");
			String heigth = rs.getString("heigth");
			String weight = rs.getString("weight");
			
			Equipment equipment = new Equipment(id,name,serialNo,category,manufacturer,width,length,heigth,weight);
			return equipment;
			}
		}
		return null;
	}
	
	// Show all equipment in database - used in EquipmentList servlet
	public static List<Equipment> equipmentList() throws SQLException{
		String query = "SELECT * FROM equipment";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		List<Equipment> list = new ArrayList<Equipment>();
		
		try(conn;stmt;rs){
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String serialNo = rs.getString("serial_no");
				String category = rs.getString("category");
				String manufacturer = rs.getString("manufacturer");
				String width = rs.getString("width");
				String length = rs.getString("length");
				String heigth = rs.getString("heigth");
				String weight = rs.getString("weight");
				Equipment equipment = new Equipment(id,name,serialNo,category,manufacturer,width,length,heigth,weight);
				list.add(equipment);
			}		
		}
		return list;
	}
	
	// Create new equipment - used in CreateEquipment servlet
	public static void createEquipment(Equipment equipment) throws SQLException{
		String query = "INSERT INTO equipment(name,serial_no,category,manufacturer,width,length,heigth,weight) VALUES (?,?,?,?,?,?,?,?)";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){
			stmt.setString(1, equipment.getName());
			stmt.setString(2, equipment.getSerialNo());
			stmt.setString(3, equipment.getCategory());
			stmt.setString(4, equipment.getManufacturer());
			stmt.setString(5, equipment.getWidth());
			stmt.setString(6, equipment.getLength());
			stmt.setString(7, equipment.getHeigth());
			stmt.setString(8, equipment.getWeight());
			stmt.executeUpdate();
		}
		
	}
	
	// Update equipment - used in UpdateEquipment servlet doPost
	public static void updateEquipment(Equipment equipment) throws SQLException{
		String query = "UPDATE equipment SET name=?,serial_no=?,category=?,manufacturer=?,width=?,length=?,heigth=?,weight=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){
			stmt.setString(1, equipment.getName());
			stmt.setString(2, equipment.getSerialNo());
			stmt.setString(3, equipment.getCategory());
			stmt.setString(4, equipment.getManufacturer());
			stmt.setString(5, equipment.getWidth());
			stmt.setString(6, equipment.getLength());
			stmt.setString(7, equipment.getHeigth());
			stmt.setString(8, equipment.getWeight());
			stmt.setInt(9, equipment.getId());
			stmt.executeUpdate();
		}
	}
	
	// Delete equipment based on ID - used in DeleteEquipment servlet
	public static void deleteEquipment(String id) throws SQLException{
		String query = "DELETE FROM equipment WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			stmt.setString(1, id);
			stmt.executeUpdate();
		}
	}
	
	// Automatically update equipment with newly created service - used in CreateScheduledService servlet
	public static void scheduledServicesUpdate(Equipment equipment) throws SQLException{
		String query = "UPDATE equipment SET scheduled_service_id=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
		
			stmt.setInt(1, equipment.getScheduledServicesID());
			stmt.setInt(2, equipment.getId());
		
			stmt.executeUpdate();
		}
	}
	
	// Automatically update equipment with newly created service - used in CreateUnplannedService servlet
	public static void unplannedServicesUpdate(Equipment equipment) throws SQLException{
		String query = "UPDATE equipment SET unplanned_service_id=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
		
			stmt.setInt(1, equipment.getUnplannedServicesID());
			stmt.setInt(2, equipment.getId());
		
			stmt.executeUpdate();
		}
	}
}
