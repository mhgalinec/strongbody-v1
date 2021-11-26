package com.strongbody.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.strongbody.beans.Membership;
import com.strongbody.connection.MySQLConnection;
import com.strongbody.connection.MySQLConnectionPool;

public class MembershipUtils {

	// Show all memberships in database - used in MembershipList servlet
	public static List<Membership> listMemberships()throws SQLException{

		String query ="SELECT members.full_name,membership.* FROM members INNER JOIN membership ON members.membership_id = membership.id";
		List<Membership> list = new ArrayList<Membership>();

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			
			while(rs.next()) {
				int id = rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				Date validFrom = rs.getDate("valid_from");
				Date validThrough = rs.getDate("valid_through");
				String serviceLevel = rs.getString("service_level");
				String membershipType = rs.getString("membership_type");
				String paymentStatus = rs.getString("payment_status");
				String memberName = rs.getString("full_name");

				Membership membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
				membership.setMemberName(memberName);
				membership.setPaymentStatus(paymentStatus);
				list.add(membership);
			}
		}
		return list;
				
	}
	
	// Create membership - used in CreateMembership servlet
	public static void createMembership(Membership membership) throws SQLException{
		
		String query = "INSERT INTO membership(registration_date,valid_from,valid_through,service_level,membership_type,member_id) VALUES (?,?,?,?,?,?)";
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		
		try(conn;stmt){
			stmt.setDate(1, membership.getRegistrationDate());
			stmt.setDate(2, membership.getValidFrom());
			stmt.setDate(3, membership.getValidThrough());
			stmt.setString(4, membership.getServiceLevel());
			stmt.setString(5, membership.getMembershipType());
			stmt.setInt(6, membership.getMemberID());
			
			stmt.executeUpdate();
		
			ResultSet rs = stmt.getGeneratedKeys();
			try(rs){
				if(rs.next()) {
					int id = rs.getInt(1);
					membership.setId(id);
				}
			}
		}

	}
	
	//Show membership of a member selecetd by ID
	public static Membership membershipInfo (int memberID) throws SQLException{

		//SQL Statement that selects all records from membership based on membership_id in members
		String query ="SELECT * FROM membership INNER JOIN members ON membership.id = members.membership_id WHERE members.id=?";
		Membership membership = null;
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1,memberID);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
				int id = rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				Date validFrom = rs.getDate("valid_from");
				Date validThrough = rs.getDate("valid_through");
				String serviceLevel = rs.getString("service_level");
				String membershipType = rs.getString("membership_type");
				String paymentStatus = rs.getString("payment_status");

				membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
				membership.setPaymentStatus(paymentStatus);
			
			}
		}
		return membership;
	}
	
	//Find membership based on ID - used in UpdateMembership and MembershipFee servlets
	public static Membership findMembership(int id) throws SQLException{
		String query = "SELECT id,registration_date,valid_from,valid_through,service_level,membership_type,payment_status FROM membership WHERE id=?";

		Connection conn = MySQLConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try(conn;stmt;rs){
			while(rs.next()) {
				id = rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				Date validFrom = rs.getDate("valid_from");
				Date validThrough = rs.getDate("valid_through");
				String serviceLevel = rs.getString("service_level");
				String membershipType = rs.getString("membership_type");
				String paymentStatus = rs.getString("payment_status");
			
				Membership membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
				membership.setPaymentStatus(paymentStatus);
				return membership;
			}
		}
		return null;
		
	}
	
	// Delete membership by id - used in DeleteMembership servlet
	public static void deleteMembership(String id) throws SQLException{
		
		String query = "DELETE  FROM membership where id=?";

		Connection conn = MySQLConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			stmt.setString(1, id);
		
			stmt.executeUpdate();
		}
	}
	
	// Show all active membership in database - used in ActiveMembership servlet
	public static List<Membership> activeMembership() throws SQLException{
		//String query = "SELECT * FROM membership WHERE valid_through >= CURDATE();";
		String query = "SELECT members.full_name,membership.* FROM members INNER JOIN membership ON members.membership_id = membership.id WHERE valid_through >= CURDATE()";
		List<Membership> list = new ArrayList<Membership>();

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		try(conn;stmt;rs){
			while(rs.next()) {
				int id = rs.getInt("id");
				Date registrationDate = rs.getDate("registration_date");
				Date validFrom = rs.getDate("valid_from");
				Date validThrough = rs.getDate("valid_through");
				String serviceLevel = rs.getString("service_level");
				String membershipType = rs.getString("membership_type");
				String memberName = rs.getString("full_name");
				Membership membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
				membership.setMemberName(memberName);
				list.add(membership);
			}
		}
		return list;
	}
	
	// Update membership - used in UpdateMembership servlet doPost
	public static void updateMembership(Membership membership) throws SQLException {
		String query = "UPDATE membership SET registration_date=?,valid_from=?,valid_through=?,service_level=?,membership_type=?,payment_status=? WHERE id=?";

		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			
			stmt.setDate(1, membership.getRegistrationDate());
			stmt.setDate(2, membership.getValidFrom());
			stmt.setDate(3, membership.getValidThrough());
			stmt.setString(4, membership.getServiceLevel());
			stmt.setString(5, membership.getMembershipType());
			stmt.setString(6, membership.getPaymentStatus());
			stmt.setInt(7, membership.getId());
		
			stmt.executeUpdate();
		}
				
	}
	
	// Change payment status to Payed - used in MembershipFee servlet
	public static void paymentUpdate(Membership membership) throws SQLException{
		String query="UPDATE membership SET payment_status=? WHERE id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
		
			stmt.setString(1, membership.getPaymentStatus());
			stmt.setInt(2, membership.getId());
		
			stmt.executeUpdate();
		}
	}
	
	public static int dateDifference(Membership membership) throws SQLException{
		String query="SELECT DATEDIFF(valid_through,valid_from) FROM membership WHERE id=?";
		int dateDiff=0;
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, membership.getId());
		ResultSet rs=stmt.executeQuery();
		
		try(conn;stmt;rs){
			if(rs.next()) {
				dateDiff=rs.getInt(1);
			}
		}
		return dateDiff;
		
	}
}
