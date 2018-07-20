package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Ward;

/**
 * Class responsible for handling the SQL actions for ward objects.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class WardSQL {
	private final String SELECT_ALL = "SELECT * FROM Wards";
	private final String SELECT_NURSE = "SELECT * FROM Wards WHERE ResponsibleNurse = ?";
	private final String SELECT_ID = "SELECT * FROM Wards WHERE WardID = ?";
	private final String SELECT_PERCENTAGE = "select (a/b) * 100 from "
			+ "(select (select count(*) from CheckIn where WardNumber=?) as a, "
			+ "(select count(*) from CheckIn) as b)as c;";
	private final String ADD_NEW_ENTRY = "INSERT INTO Wards(OneBed,TwoBed,ThreeBed,NumberOfPatient,Charges,"
			+ "ResponsibleNurse) VALUES (?,?,?,?,?,?)";
	private final String ADD_ENTRY = "INSERT INTO Wards(WardID,OneBed,TwoBed,ThreeBed,NumberOfPatient,Charges,"
			+ "ResponsibleNurse) VALUES (?,?,?,?,?,?,?)";
	private final String UPDATE_ENTRY = "UPDATE Wards SET OneBed=?,TwoBed=?,ThreeBed=?,"
			+ "NumberOfPatient=?,Charges=?,ResponsibleNurse=? WHERE WardID=?";
	private final String UPDATE_KEY = "UPDATE CheckIn SET WardNumber=0 WHERE WardNumber=?";
	private final String DELETE_ENTRY = "DELETE FROM Wards WHERE WardID = ?";
	
	private Connection con;
	
	public WardSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Method used to select all the wards that are currently in the database.
	 * 
	 * @return A list of all ward objects stored in the database.
	 */
	public ArrayList<Ward> selectAll() {
		ArrayList<Ward> wards = new ArrayList<>();
		ResultSet rs = null;
		
		try( PreparedStatement stm = con.prepareStatement( SELECT_ALL ) ) {
			rs = stm.executeQuery();
			Ward w = null;
			while( rs.next() ) {
				int id = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(id,oB,twB,thB,occupied,ch,nur);// Create ward with information
				wards.add( w );// Add ward to array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return wards;
	}
	
	/**
	 * Method used to get three bed ward that is not occupied
	 * @param id The wardID used to search for.
	 * @return A list of wards with matching WardID number.
	 */
	public ArrayList<Ward> getNoneOccupiedThreeBedWards() {
		ArrayList<Ward> wards = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement("SELECT * FROM Wards WHERE ThreeBed = 1 AND NumberOfPatient <=2 ")){
			//stm.setInt(1, 0);
			rs = stm.executeQuery();
			Ward w = null;
			while( rs.next() ) {
				int wid = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(wid,oB,twB,thB,occupied, ch,nur);// Create ward with information
				wards.add( w );// Add ward to array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wards;
	}
	/**
	 * Method used to get one bed ward that is not occupied
	 * @param id The wardID used to search for.
	 * @return A list of wards with matching WardID number.
	 */
	public ArrayList<Ward> getNoneOccupiedOneBedWards() {
		ArrayList<Ward> wards = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement("SELECT * FROM Wards WHERE OneBed=1 AND NumberOfPatient = 0")){
			//stm.setInt(1, 0);
			rs = stm.executeQuery();
			Ward w = null;
			while( rs.next() ) {
				int wid = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(wid,oB,twB,thB,occupied, ch,nur);// Create ward with information
				wards.add( w );// Add ward to array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wards;
	}
	/**
	 *  Method used to get three bed ward that is not occupied
	 * @param id The wardID used to search for.
	 * @return A list of wards with matching WardID number.
	 */
	public ArrayList<Ward> getNoneOccupiedTwoBedWards() {
		ArrayList<Ward> wards = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement("SELECT * FROM Wards WHERE TwoBed = 1 AND NumberOfPatient <= 1")){
			//stm.setInt(1, 0);
			rs = stm.executeQuery();
			Ward w = null;
			while( rs.next() ) {
				int wid = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(wid,oB,twB,thB,occupied, ch,nur);// Create ward with information
				wards.add( w );// Add ward to array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wards;
	}
	
	
	/**
	 * Method used to grab wards by id number.
	 * @param id The wardID used to search for.
	 * @return A list of wards with matching WardID number.
	 */
	public Ward getByID( int id ) {
		ResultSet rs = null;
		Ward w = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ID) ) {
			stm.setInt(1, id);
			rs = stm.executeQuery();
			while( rs.next() ) {
				int wid = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(wid,oB,twB,thB,occupied,ch,nur);// Create ward with information
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	public int getPercentage(int wID) {
		ResultSet rs = null;
		int id = 0;
		try( PreparedStatement stm = con.prepareStatement( SELECT_PERCENTAGE ) ) {
			stm.setInt(1, wID);
			rs = stm.executeQuery();
			while( rs.next() ) {
				id = rs.getInt(1);// Grabs WardID
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * Method used to grab a list of wards that a specific nurse is responsible
	 * for.
	 * 
	 * @param NurseID The ID number of the nurse being searched for.
	 * @return A list of all wards that the nurse is responsible for.
	 */
	public ArrayList<Ward> getByNurse( int NurseID ) {
		ArrayList<Ward> wards = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_NURSE) ) {
			stm.setInt(1, NurseID);
			rs = stm.executeQuery();
			Ward w = null;
			while( rs.next() ) {
				int wid = rs.getInt("WardID");// Grabs WardID
				int oB = rs.getInt("OneBed");// Grabs OneBed
				int twB = rs.getInt("TwoBed");// Grabs TwoBed
				int thB = rs.getInt("ThreeBed");// Grabs ThreeBed
				int occupied = rs.getInt("NumberOfPatient");
				int ch = rs.getInt("Charges");// Grabs Charge
				int nur = rs.getInt("ResponsibleNurse");// Grabs ResponsibleNurse
				
				w = new Ward(wid,oB,twB,thB,occupied,ch,nur);// Create ward with information
				wards.add( w );// Add ward to array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wards;
	}
	
	/**
	 * Method used to add a new ward to the database.  This ward should not have
	 * a WardID assigned to it yet.
	 * 
	 * @param w The ward being added to the database.
	 * @return True or false depending on if the ward was added to the database.
	 */
	public boolean addNewWard( Ward w ) {
		boolean added = false;
		if( !(w instanceof Ward) )
			return added;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setInt(1, w.getWardID());// Set WardID
			stm.setInt(2, w.getOneBed());// Set OneBed
			stm.setInt(3, w.getTwoBed());// Set TwoBed
			stm.setInt(4, w.getThreeBed());// Set ThreeBed
			stm.setInt(5, w.getNumberOfPatient());
			stm.setInt(6, w.getCharges());// Set Charge
			stm.setInt(7, w.getRespNurse());// Set ResponsibleNurse
			
			if( stm.executeUpdate() == 1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used to add a new ward to the database.  This ward should not have
	 * a WardID assigned to it yet.
	 * 
	 * @param w The ward being added to the database.
	 * @return True or false depending on if the ward was added to the database.
	 */
	public boolean addWard( Ward w ) {
		boolean added = false;
		if( !(w instanceof Ward) )
			return added;
		try( PreparedStatement stm = con.prepareStatement(ADD_NEW_ENTRY) ) {
			stm.setInt(1, w.getOneBed());// Set OneBed
			stm.setInt(2, w.getTwoBed());// Set TwoBed
			stm.setInt(3, w.getThreeBed());// Set ThreeBed
			stm.setInt(4, w.getNumberOfPatient());
			stm.setInt(5, w.getCharges());// Set Charge
			stm.setInt(6, w.getRespNurse());// Set ResponsibleNurse
			
			if( stm.executeUpdate() == 1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used to update information about a ward in the database.
	 * 
	 * @param w The ward with updated information.
	 * @return True or false if the database was updated or not.
	 */
	public boolean updateWard( Ward w ) {
		boolean updated = false;
		if( !(w instanceof Ward) )
			return updated;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setInt(1, w.getOneBed());// Set OneBed
			stm.setInt(2, w.getTwoBed());// Set TwoBed
			stm.setInt(3, w.getThreeBed());// Set ThreeBed
			stm.setInt(4, w.getNumberOfPatient());
			stm.setInt(5, w.getCharges());// Set Charge
			stm.setInt(6, w.getRespNurse());// Set ResponsibleNurse
			stm.setInt(7, w.getWardID());// Set WardID
			
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	/**
	 * Method used to delete a ward from the database.
	 * 
	 * @param w The ward being deleted.
	 * @return Returns if the ward was deleted or not.
	 */
	public boolean deleteWard(int w) {
		try( PreparedStatement stm = con.prepareStatement(UPDATE_KEY) ) {
			stm.setInt(1, w);
			
			if( stm.executeUpdate() < 0 )
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean deleted = false;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setInt( 1, w );
			
			if( stm.executeUpdate() == 1 )
				deleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public boolean checkInWard(int wardID){
		boolean updated = false;
		ResultSet rs = null;
		int numberOfBed;
		try{
			PreparedStatement stm = con.prepareStatement("SELECT NumberOfPatient FROM Wards WHERE WardId=?");
			stm.setInt( 1, wardID );
			rs = stm.executeQuery();
			rs.next();
			numberOfBed = rs.getInt("NumberOfPatient");
			if(numberOfBed > 2){
				return false;
			}
			numberOfBed += 1;
			stm = con.prepareStatement("UPDATE Wards SET NumberOfPatient=? WHERE WardId=?");
			stm.setInt(1, numberOfBed);
			stm.setInt(2, wardID);
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	public boolean checkOutWard(int wardID){
		boolean updated = false;
		ResultSet rs = null;
		int numberOfBed;
		try{
			PreparedStatement stm = con.prepareStatement("SELECT NumberOfPatient FROM Wards WHERE WardId=?");
			stm.setInt( 1, wardID );
			rs = stm.executeQuery();
			rs.next();
			numberOfBed = rs.getInt("NumberOfPatient");
			if(numberOfBed < 1){
				return false;
			}
			numberOfBed -= 1;
			stm = con.prepareStatement("UPDATE Wards SET NumberOfPatient=? WHERE WardId=?");
			stm.setInt(1, numberOfBed);
			stm.setInt(2, wardID);
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
		
	}
	
	public void clearTable() {
		String clear = "DELETE FROM Wards";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
