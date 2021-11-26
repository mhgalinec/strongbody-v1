package com.strongbody.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.strongbody.beans.Members;
import com.strongbody.connection.MySQLConnection;
import com.strongbody.connection.MySQLConnectionPool;

public class MemberUtils {

	// Find member based on ID - used in EditMember servlet
	public static Members findMember(int id) throws SQLException{
		
		String query = "SELECT id,full_name,date_of_birth,sex,contact_number,email,diet,membership_id,measurement_id FROM members WHERE id = ?";

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		try(conn;stmt;rs){
	
			while(rs.next()) {
				id= rs.getInt("id");
				String fullName = rs.getString("full_name");
				Date dateOfBirth = rs.getDate("date_of_birth");
				String sex = rs.getString("sex");
				String contactNumber = rs.getString("contact_number");
				String email = rs.getString("email");
				String diet = rs.getString("diet");
				int membershipID = rs.getInt("membership_id");
				int measurementID = rs.getInt("measurement_id");
			
				Members member = new Members(id,fullName,dateOfBirth,sex,contactNumber,email,diet,membershipID,measurementID);
				return member;
			}
		}
		return null;
		
	}
	
	//Show all members in database - used in MemberList servlet
	public static List<Members> listMembers() throws SQLException{
		
		String query ="SELECT * FROM members";

		List<Members> list = new ArrayList<Members>();
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
		
			while(rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				Date dateOfBirth = rs.getDate("date_of_birth");
				String sex = rs.getString("sex");
				String contactNumber = rs.getString("contact_number");
				String email = rs.getString("email");
				String diet = rs.getString("diet");
				int membershipID = rs.getInt("membership_id");
				int measurementID = rs.getInt("measurement_id");
			
				Members member = new Members(id,fullName,dateOfBirth,sex,contactNumber,email,diet,membershipID,measurementID);
				list.add(member);
			}
		}
		return list;
	}
	
	//Update member - method used in EditMember servlet doPost
	public static void updateMember(Members member) throws SQLException{
		String query = "UPDATE members SET full_name=?, date_of_birth=?, sex=?, contact_number=?, email=?, diet=? WHERE id=?";
	
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){

			//Because the update query is searching full_name first stmt.setString(1,member.getFullName()); needs to be the first parameter
			//And because id is not in the update query,its not updated and its set to parameter 8
			stmt.setString(1, member.getFullName());
			stmt.setDate(2, member.getDateOfBirth());
			stmt.setString(3, member.getSex());
			stmt.setString(4, member.getContactNumber());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getDiet());
			stmt.setInt(7, member.getId());
			
			stmt.executeUpdate();
		}
	}
	
	// Create a new member -  method used in CreateMember servlet
	public static void createMember(Members member) throws SQLException{
		String query ="INSERT INTO members(full_name,date_of_birth,sex,contact_number,email,diet) VALUES (?,?,?,?,?,?)";

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		try(conn;stmt){
			stmt.setString(1, member.getFullName());
			stmt.setDate(2, member.getDateOfBirth());
			stmt.setString(3, member.getSex());
			stmt.setString(4, member.getContactNumber());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getDiet());
		
			stmt.executeUpdate();
		}

	}
	
	
	// Delete member - method used in DeleteMember servlet
	public static void deleteMember(String id) throws SQLException{
		String query = "DELETE FROM members WHERE id=?";
	
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
		
			stmt.setString(1,id);
		
			stmt.executeUpdate();
		}
	}
	
	// Automatically update a member with newly created membership - used in CreateMembership servlet
	public static void membershipUpdate(Members member) throws SQLException{
		String query ="UPDATE members SET membership_id =? WHERE id=?";

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
		
			stmt.setInt(1, member.getMembershipID());
			stmt.setInt(2, member.getId());
		
			stmt.executeUpdate();
		}
	}
	
	// Automatically update a member with newly created measurement - used in CreateMeasurements servlet
	public static void measurementsUpdate(Members members) throws SQLException{
		String query = "UPDATE members SET measurement_id=? WHERE id=?";
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(query);
		
		try(conn;stmt){
			stmt.setInt(1, members.getMeasurementID());
			stmt.setInt(2, members.getId());;

			stmt.executeUpdate();
		}
	}
	


}
