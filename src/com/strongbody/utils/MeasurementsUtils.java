package com.strongbody.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.strongbody.beans.Measurements;
import com.strongbody.connection.MySQLConnection;
import com.strongbody.connection.MySQLConnectionPool;

public class MeasurementsUtils {

	// Show the measurements of a member selected by ID
	public static Measurements memberMeasurements(int memberID) throws SQLException{
		String query ="SELECT * FROM measurements INNER JOIN members ON measurements.id = members.measurement_id WHERE members.id=?";
		
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(query);
		stmt.setInt(1, memberID);
		ResultSet rs = stmt.executeQuery();
		
		Measurements measurements = null;
		try(conn;stmt;rs){
			while(rs.next()) {
				int id= rs.getInt("id");
				Date measurementDate = rs.getDate("measurement_date");
				String height = rs.getString("height");
				String weight = rs.getString("weight");
				String bodyFat = rs.getString("body_fat");
				String shoulders = rs.getString("shoulders");
				String torso = rs.getString("torso");
				String waist = rs.getString("waist");
				String upperArm = rs.getString("upper_arm");
				String lowerArm = rs.getString("lower_arm");
				String upperLeg = rs.getString("upper_leg");
				String lowerLeg = rs.getString("lower_leg");
				String restingHeartRate = rs.getString("resting_heart_rate");

				measurements = new Measurements(id,measurementDate,height,weight,bodyFat,shoulders,torso,waist,upperArm,lowerArm,upperLeg,lowerLeg,restingHeartRate);

			}
			return measurements;
		}
	}
	
	// Create new measurement - used in CreateMeasurement servlet
	public static void createMeasurements(Measurements measurements) throws SQLException {
		String query = "INSERT INTO measurements(measurement_date,height,weight,body_fat,shoulders,torso,waist,upper_arm,lower_arm,upper_leg,lower_leg,resting_heart_rate,members_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

		try(conn;stmt){
			stmt.setDate(1, measurements.getMeasurementDate());
			stmt.setString(2, measurements.getHeight());
			stmt.setString(3, measurements.getWeight());
			stmt.setString(4, measurements.getBodyFat());
			stmt.setString(5, measurements.getShoulders());
			stmt.setString(6, measurements.getTorso());
			stmt.setString(7, measurements.getWaist());
			stmt.setString(8, measurements.getUpperArm());
			stmt.setString(9, measurements.getLowerArm());
			stmt.setString(10, measurements.getUpperLeg());
			stmt.setString(11, measurements.getLowerLeg());
			stmt.setString(12, measurements.getRestingHeartRate());
			stmt.setInt(13,measurements.getMemberID());

			stmt.executeUpdate();

			// After creating new measurement set the Auto-Incremented ID to measurements
			ResultSet rs = stmt.getGeneratedKeys();
			try(rs){
				if(rs.next()) {
					int id = rs.getInt(1);
					measurements.setId(id);
				}
			}
		}

	}
	
	// Update measurements- used in UpdateMeasuerments servlet doPost
	public static void updateMeasurements(Measurements measurements) throws SQLException{
		String query = "UPDATE measurements SET measurement_date=?, height=?, weight=?, body_fat=?, shoulders=?, torso=?, waist=?, upper_arm=?, lower_arm=?, upper_leg=?, lower_leg=?, resting_heart_rate=? WHERE id=?";
		Connection conn = MySQLConnectionPool.getConnection();
		PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(query);
		try(conn;stmt){
			stmt.setDate(1, measurements.getMeasurementDate());
			stmt.setString(2, measurements.getHeight());
			stmt.setString(3, measurements.getWeight());
			stmt.setString(4, measurements.getBodyFat());
			stmt.setString(5, measurements.getShoulders());
			stmt.setString(6, measurements.getTorso());
			stmt.setString(7, measurements.getWaist());
			stmt.setString(8, measurements.getUpperArm());
			stmt.setString(9, measurements.getLowerArm());
			stmt.setString(10, measurements.getUpperLeg());
			stmt.setString(11, measurements.getLowerLeg());
			stmt.setString(12, measurements.getRestingHeartRate());
			stmt.setInt(13, measurements.getId());

			stmt.executeUpdate();
		}
	}
	
	// Find measurement based on ID - used in UpdateMeasurement servlet
	public static Measurements findMeasurements(int id) throws SQLException{
		String query ="SELECT id,measurement_date,height,weight,body_fat,shoulders,torso,waist,upper_arm,lower_arm,upper_leg,lower_leg,resting_heart_rate FROM measurements WHERE id=?";
		PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement(query);
		Connection conn = MySQLConnectionPool.getConnection();
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		try(conn;stmt;rs){
			while(rs.next()) {
				id = rs.getInt("id");
				Date measurementDate = rs.getDate("measurement_date");
				String height = rs.getString("height");
				String weight = rs.getString("weight");
				String bodyFat = rs.getString("body_fat");
				String shoulders = rs.getString("shoulders");
				String torso = rs.getString("torso");
				String waist = rs.getString("waist");
				String upperArm = rs.getString("upper_arm");
				String lowerArm = rs.getString("lower_arm");
				String upperLeg = rs.getString("upper_leg");
				String lowerLeg = rs.getString("lower_leg");
				String restingHeartRate = rs.getString("resting_heart_rate");

				Measurements measurements = new Measurements(id,measurementDate,height,weight,bodyFat,shoulders,torso,waist,upperArm,lowerArm,upperLeg,lowerLeg,restingHeartRate);
				return measurements;
			}
		}
		return null;
	}
	
	// Delete measurement by id - used in DeleteMeasurement servlet
	public static void deleteMeasurement(String id) throws SQLException{
		
		String query = "DELETE FROM measurements where id=?";

		Connection conn = MySQLConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		try(conn;stmt){
			stmt.setString(1, id);
		
			stmt.executeUpdate();
		}
	}
}
