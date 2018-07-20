package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.BillingRecord;
import objects.CheckIn;
import objects.MedicalRecord;
import objects.Patient;
import objects.Staff;
import objects.Ward;

/**
 * This class is responsible for handling the majority of the sql
 * actions used in the system.
 *
 * @author Jesse Liddle - jaliddl2
 */
public class SqlController {
//	private final String SQL_url = "jdbc:mysql://localhost/";
//	private final String SQL_un = "root";
//	private final String SQL_pw = "";
	private final String SQL_url = "jdbc:mysql://<IP ADDRESS>";
	private final String SQL_un = "username";
	private final String SQL_pw = "password";

	private WardSQL wSQL;
	private StaffSQL sSQL;
	private PatientSQL pSQL;
	private MedicalRecordSQL mSQL;
	private BillingRecordSQL bSQL;
	private CheckInSQL cSQL;

	private Connection con;
	private int TESTING = 0;// Variable for testing code

	/**
	 * Creates the sql controller to be used for this project
	 */
	public SqlController() {
		try {
			con = DriverManager.getConnection(SQL_url, SQL_un, SQL_pw);// Create connection to the database
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);// Set connection to DB to only read committed entries
//			PreparedStatement ps;
//			try {
//				ps = con.prepareStatement("CREATE DATABASE WolfHospital");
//				ps.execute();
//			} catch (SQLException e) {
////				e.printStackTrace();
//			}
//			ps = con.prepareStatement("USE WolfHospital");
//			ps.execute();
			createTables();// Runs all the create tables in case they do not already exists.
			if( TESTING == 1)
				System.out.println("Connection was made");
			wSQL = new WardSQL( con );// Ward SQL controller
			sSQL = new StaffSQL( con );// Staff SQL controller
			pSQL = new PatientSQL( con );// Patient SQL controller
			mSQL = new MedicalRecordSQL( con );// Medical Record SQL controller
			bSQL = new BillingRecordSQL( con );// Billing Record SQL controller
			cSQL = new CheckInSQL(con);
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection();
		}
	}

	/**
	 * Method used to create the tables in the database if they do not already exists.
	 */
	private void createTables() {
		new CreateTables( con );
	}

	/**
	 * Method used to select all wards.
	 * @return Array list of all the wards.
	 */
	public ArrayList<Ward> getAllWards() {
		return wSQL.selectAll();
	}

	/**
	 * Method used to select all staff.
	 * @return Array list of all the staff members.
	 */
	public ArrayList<Staff> getAllStaff() {
		return sSQL.getAllStaff();
	}

	/**
	 * Method used to select all patient.
	 * @return Array list of all the patients.
	 */
	public ArrayList<Patient> getAllPatient() {
		return pSQL.getAllPatients();
	}

	/**
	 * Method used to select all medical records.
	 * @return Array list of all the medical records.
	 */
	public ArrayList<MedicalRecord> getAllMedicalRecord() {
		return mSQL.getAllMedicalRecords();
	}

	/**
	 * Method used to select all medical records.
	 * @return Array list of all the medical records.
	 */
	public ArrayList<MedicalRecord> getAllMedicalRecordPatient(int PatientID) {
		return mSQL.getAllRecordsForPatient(PatientID);
	}

	public ArrayList<Patient> getDoctorCurrentPatients(int dID) {
		return mSQL.getPatientRecordsOfDoctor(dID);
	}

	public int getWardPercentage(int wID) {
		return wSQL.getPercentage(wID);
	}

	/**
	 * Method used to delete an object that exists in the table.
	 *
	 * @param obj The object being deleted from the table.
	 * @return Returns if the object was deleted or not.
	 */
	public boolean deleteObject( int id, char objType ) {
		boolean deleted = false;
		if( objType == 'w' )
			deleted = wSQL.deleteWard( id );
		if( objType == 's' )
			deleted = sSQL.deleteStaff( id );
		if( objType == 'p' )
			deleted = pSQL.deleteRecord( id );
		if( objType == 'b' )
			deleted = bSQL.deleteBillingRecord( id );
		return deleted;
	}

	public boolean updateObject( Object obj ) {
		boolean updated = false;
		if( (obj instanceof Ward) )
			updated = wSQL.updateWard( (Ward)obj );
		if( (obj instanceof Staff) )
			updated = sSQL.updateStaff( (Staff)obj );
	    if( (obj instanceof Patient) )
	      updated = pSQL.updatePatient( (Patient)obj );
	    if( (obj instanceof MedicalRecord) )
	      updated = mSQL.updateRecord( (MedicalRecord)obj );
	    if( (obj instanceof BillingRecord) )
	      updated = bSQL.updateBillingRecord( (BillingRecord)obj );
	    if( (obj instanceof CheckIn) )
		      updated = cSQL.updateCheckIn( (CheckIn)obj );
		return updated;
	}

	/**
	 * Method used to add a new object into the correct table.
	 *
	 * @param obj The object being added into the table.
	 * @return Returns if the object was added or not.
	 */
	public boolean addNewObject( Object obj ) {
		boolean added = false;
		if( (obj instanceof Ward) )
			added = wSQL.addNewWard( (Ward)obj );
		if( (obj instanceof Staff) )
			added = sSQL.addNewStaff( (Staff)obj );
		if( (obj instanceof Patient) )
	      added = pSQL.addNewPatient( (Patient)obj );
	    if( (obj instanceof MedicalRecord) )
	      added = mSQL.addNewRecord( (MedicalRecord)obj );
	    if( (obj instanceof BillingRecord) )
	      added = bSQL.addNewBillingRecord( (BillingRecord)obj );
	    if( (obj instanceof CheckIn) )
		      added = cSQL.addCheckIn( (CheckIn)obj );

		return added;
	}

	/**
	 * Method used to add an object to the correct table that
	 * already has an existing ID number.
	 * @param obj The object being added to the table.
	 * @return Returns if the object was added or not.
	 */
	public boolean addObject( Object obj ) {
		boolean added = false;
		if( (obj instanceof Ward) )
			added = wSQL.addWard( (Ward)obj );
		if( (obj instanceof Staff) )
			added = sSQL.addStaff( (Staff)obj );

		return added;
	}

	/**
	 * Method used to get a list of wards managed by a nurse.
	 * @param NurseID The nurse id number searching for.
	 * @return An array of all wards managed by one nurse.
	 */
	public ArrayList<Ward> selectWardByNurse( int NurseID ) {
		return wSQL.getByNurse(NurseID);
	}

	/**
	 * Method used to get a list of wards managed by a nurse.
	 * @param s The nurse that is being searched for.
	 * @return An array of all wards managed by one nurse.
	 */
	public ArrayList<Ward> selectWardByNurse( Staff s ) {
		if( s.getJobTitle().equalsIgnoreCase("NURSE") )
			return wSQL.getByNurse( s.getStaffID() );
		else
			return null;
	}

	/**
	 * Checks the ward of a specific size whether it is occupied or not
	 * @param bedsCount the bed-size of the ward
	 * @return a list of all the wards that are not occupied and have the specified bed size
	 */
	public ArrayList<Ward> selectWardByBeds(int bedsCount) {
	  ArrayList<Ward> wards = null;
	  if(bedsCount == 1) {
	    wards = wSQL.getNoneOccupiedOneBedWards();
	  } else if(bedsCount == 2) {
	    wards = wSQL.getNoneOccupiedTwoBedWards();
	  } else if(bedsCount == 3) {
	    wards = wSQL.getNoneOccupiedThreeBedWards();
	  } else if(bedsCount == 0) {
	    wards = wSQL.selectAll();
	  }
	  return wards;
	}

	/**
	 * Checks a patient into a ward - calls the SQL class to add an occupant to the ward
	 * @param wardID the id of the ward the patient is checking into
	 * @return whether it was successful or not
	 */
	public boolean checkInWard(int wardID) {
	  return wSQL.checkInWard(wardID);
	}

	/**
	 * Checks a patient out of a ward - calls the SQL class to remove an occupant to the ward
	 * @param wardID the id of the ward the patient is checking out of
	 * @return whether it was successful or not
	 */
	public boolean checkOutWard(int wardID) {
    return wSQL.checkOutWard(wardID);
  }

	/**
	 * Method used to get all staff sorted by their job title.
	 *
	 * @return Returns an array of staff members sorted by job title.
	 */
	public ArrayList<Staff> staffByGroupJob() {
		return sSQL.getAllStaffGroup();
	}

	/**
	 * Gets the medical records for a patient over a given time period
	 * @param pid the patient id
	 * @param sDate the start date to compare against
	 * @param eDate the end date to compare against
	 * @return
	 */
	public ArrayList<MedicalRecord> medRecordsByDate(int pid, String sDate, String eDate) {
	  return mSQL.getPatientRecordsByTime(pid, sDate, eDate);
	}

	/**
	 * Method used to drop one specific table.
	 *
	 * @param tableName The name of the table to be dropped.
	 * @return Returns if the table was dropped or not.
	 */
	public boolean dropTable(String tableName) {
		String s = "DROP TABLE ?";
		boolean dropped = false;
		try( PreparedStatement stm = con.prepareStatement(s) ) {
			stm.setString(1, tableName);
			if( stm.executeUpdate() == 1 )
				dropped = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dropped;
	}

	/**
	 * Method mainly used for testing.  Returns the connection to the database.
	 * @return Returns the connection to the database.
	 */
	public Connection getConnection() {
		return con;
	}

	/**
	 * Method used for closing the connection when the program is exited.
	 */
	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPatientsPerMonth(String bDate, String eDate) {
		return cSQL.getCheckInFrom(bDate, eDate).size();
	}

	public CheckIn getCheckIn(int cID) {
		return cSQL.getCheckInByID(cID);
	}
}
