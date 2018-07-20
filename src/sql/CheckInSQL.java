package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.CheckIn;
import objects.Patient;

/**
 * This class is responsible for handling the sql actions in the SignIn table.
 * 
 * Table Structure:
 * VARCHAR(100) UserName, VARCHAR(100) PassWord, VARCHAR(50) Role
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CheckInSQL {
	private final String SELECT_ALL = "SELECT * FROM CheckIn";
	private final String SELECT_ID = "SELECT * FROM CheckIn WHERE CheckInID=?";
	private final String SELECT_SOME = "SELECT * FROM CheckIn WHERE StartDate > ? AND EndDate < ?";
	private final String ADD_ENTRY = "INSERT INTO CheckIn(PatientID,WardNumber,StartDate,RegistrationFee)"
			+ " VALUES (?,?,?,?)";
	private final String UPDATE_ENTRY = "UPDATE CheckIn SET EndDate = ?"
			+ "WHERE CheckInID = ?";
	private final String DELETE_ENTRY = "DELETE FROM CheckIn WHERE CheckInID = ?";
	
	private Connection con;
	
	public CheckInSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Method used to return all the entries in the SignIn table.
	 * @return A list of all the entries currently in the SignIn table.
	 */
	public ArrayList<CheckIn> getAllCheckIn() {
		ArrayList<CheckIn> checkIns = new ArrayList<CheckIn>();// array for all sign ins
		ResultSet rs = null;// result set to hold the results of the query
		
		try( PreparedStatement stm = con.prepareStatement( SELECT_ALL ) ) {
			rs = stm.executeQuery();// executes the select all
			CheckIn c = null;
			while( rs.next() ) {
				int pID = rs.getInt("PatientID");// grab user name
				int wid = rs.getInt("WardNumber");// grab user password
				String bdate = rs.getString("StartDate");// grab user role
				String edate = rs.getString("EndDate");// grab user name
				int fee = rs.getInt("RegistrationFee");// grab user password
				c = new CheckIn(pID, wid, bdate, edate, fee);// Create sign in object
				checkIns.add(c);// Add new sign in to list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIns;
	}
	
	/**
	 * Gather a list of all patients in the database.
	 * @return An array of all patients in the database currently.
	 */
	public CheckIn getCheckInByID(int ID) {
		ArrayList<CheckIn> checkIns = new ArrayList<CheckIn>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ID) ) {
			stm.setInt(1, ID);
			
			rs = stm.executeQuery();// Execute query
			// Gather all patient information
			if(rs.next()) {
				int cID = rs.getInt("CheckInID");
				int pID = rs.getInt("PatientID");// grab user name
				int wid = rs.getInt("WardNumber");// grab user password
				String bdate = rs.getString("StartDate");// grab user role
				String edate = rs.getString("EndDate");// grab user name
				int fee = rs.getInt("RegistrationFee");// grab user password
				CheckIn c = new CheckIn(cID, pID, wid, bdate, edate, fee);// Create sign in object
				checkIns.add(c);// Add new sign in to list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIns.get(0);
	}
	
	/**
	 * Method used to select only specific user names from the entries in the
	 * database.  Useful for comparing user names to passwords.
	 * @param Username The user name that was entered into the system.
	 * @return Returns an array of all users matching that user name.
	 */
	public ArrayList<CheckIn> getCheckInFrom( String start, String end ) {
		ArrayList<CheckIn> checkIns = new ArrayList<CheckIn>();// array for all sign ins
		ResultSet rs = null;// result set to hold the results of the query
		try( PreparedStatement stm = con.prepareStatement( SELECT_SOME ) ) {
			stm.setString( 1, start );
			stm.setString( 2, end );
			rs = stm.executeQuery();
			CheckIn c = null;
			while( rs.next() ) {
				int pID = rs.getInt("PatientID");// grab user name
				int wid = rs.getInt("WardNumber");// grab user password
				String bdate = rs.getString("StartDate");// grab user role
				String edate = rs.getString("EndDate");// grab user name
				int fee = rs.getInt("RegistrationFee");// grab user password
				c = new CheckIn(pID, wid, bdate, edate, fee);// Create sign in object
				checkIns.add(c);// Add new sign in to list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkIns;
	}
	
	/**
	 * Method used to add a new SignIn object into the existing database.
	 * 
	 * @param s The new SignIn object to be entered into the system.
	 * @return Return true or false depending on if the entry was added or not.
	 */
	public boolean addCheckIn( CheckIn c ) {
		boolean added = false;
		// Checks to make sure it is a SignIn object
		if( !(c instanceof CheckIn) )
			return added;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setInt( 1, c.getPatientID() );// Sets user name
			stm.setInt( 2, c.getWardID() );// Sets user name
			stm.setString( 3, c.getStartDate() );// Sets password
			stm.setInt( 4, c.getRegFee() );// Sets user name
			// Executes Update and checks to see if successful
			if( stm.executeUpdate() == 1 ) 
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used to update a entry in the sign in table.
	 * @param s The SignIn object that is being updated in the database.
	 * @return Returns if the update was successful or not.
	 */
	public boolean updateCheckIn( CheckIn c ) {
		boolean updated = false;
		if( !(c instanceof CheckIn) )
			return updated;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setString( 1, c.getEndDate() );// Sets role
			stm.setInt( 2, c.getCheckInID() );// Sets user name
			
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	/**
	 * Method used to delete an entry from the database where the user name
	 * matches the one passed in.
	 * @param s The SignIn object being removed from the table.
	 * @return Returns if the operation was successful or not.
	 */
	public boolean deleteCheckIn( CheckIn s ) {
		boolean deleted = false;
		if( !(s instanceof CheckIn ) )
			return deleted;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setInt( 1, s.getCheckInID() );
			
			stm.execute();
			
			deleted = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public void clearTable() {
		String clear = "DELETE FROM CheckIn";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
