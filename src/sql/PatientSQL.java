package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Patient;

/**
 * This class is responsible for handling the actions associated with the
 * Patient's SQL actions.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class PatientSQL {
	private String SELECT_ALL = "SELECT * FROM Patient";
	private String SELECT_ID = "SELECT * FROM Patient WHERE PatientID = ?";
	private String ADD = "INSERT INTO Patient(SSN,Name,DateofBirth,Gender,Status,PhoneNumber,Address)"
			+ " VALUES (?,?,?,?,?,?,?)";
	private String DELETE = "DELETE FROM Patient WHERE PatientId = ?";
	
	private Connection con;
	
	/**
	 * Constructor for this class.
	 * 
	 * @param con The connection to the database.
	 */
	public PatientSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Gather a list of all patients in the database.
	 * @return An array of all patients in the database currently.
	 */
	public ArrayList<Patient> getAllPatients() {
		ArrayList<Patient> pats = new ArrayList<Patient>();
		ResultSet rs = null;
		Patient p = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ALL) ) {
			rs = stm.executeQuery();// Execute query
			// Gather all patient information
			while(rs.next()) {
				int id = rs.getInt("PatientID");
				String name = rs.getString("Name");
				String dob = rs.getString("DateofBirth");
				int SSN = rs.getInt("SSN");
				String gender = rs.getString("Gender");
				String status = rs.getString("Status");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				p = new Patient(id, name, dob, SSN, gender, status, phoneNumber, address);// Create new patient
				pats.add( p );// Add new patient to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pats;
	}	
	
	/**
	 * Gather a list of all patients in the database.
	 * @return An array of all patients in the database currently.
	 */
	public Patient getPatientsByID(int ID) {
		ArrayList<Patient> pats = new ArrayList<Patient>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ID) ) {
			stm.setInt(1, ID);
			
			rs = stm.executeQuery();// Execute query
			// Gather all patient information
			if(rs.next()) {
				int id = rs.getInt("PatientID");
				String name = rs.getString("Name");
				String dob = rs.getString("DateofBirth");
				int SSN = rs.getInt("SSN");
				String gender = rs.getString("Gender");
				String status = rs.getString("Status");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				Patient p = new Patient(id, name, dob, SSN, gender, status, phoneNumber, address);// Create new patient
				pats.add( p );// Add new patient to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pats.get(0);
	}
	public boolean updatePatient(Patient p){
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement("UPDATE Patient SET SSN =?,"
				+ "Name=?,DateofBirth=?,Gender=?,Status=?,PhoneNumber=?,Address=? WHERE PatientID=?") ) {
			stm.setInt(1, p.getSSN());
			stm.setString(2, p.getName());
			stm.setString(3, p.getDateofBirth());
			stm.setString(4, p.getGender());
			stm.setString(5, p.getStatus());
			stm.setString(6, p.getPhoneNumber());
			stm.setString(7, p.getAddress());
			stm.setInt(8, p.getPatientID());
			
			if( stm.executeUpdate()==1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used to add a new staff member to the database.
	 * 
	 * @param s The new staff member being added to the system.
	 * @return Returns if the staff member was added or not.
	 */
	public boolean addNewPatient( Patient p ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD) ) {
			stm.setInt(1, p.getSSN());
			stm.setString(2, p.getName());
			stm.setString(3, p.getDateofBirth());
			stm.setString(4, p.getGender());
			stm.setString(5, p.getStatus());
			stm.setString(6, p.getPhoneNumber());
			stm.setString(7, p.getAddress());
			
			if( stm.executeUpdate()==1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used to delete a staff member from the table.
	 * @param s Staff member being deleted.
	 * @return Returns if the staff member was deleted or not.
	 */
	public boolean deleteRecord( int p ) {
		boolean deleted = false;
		try( PreparedStatement stm = con.prepareStatement(DELETE) ) {
			stm.setInt(1, p);
			
			if( stm.executeUpdate() == 1 )
				deleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public void clearTable() {
		String clear = "DELETE FROM Patient";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear = "ALTER TABLE Patient AUTO_INCREMENT = 1";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
