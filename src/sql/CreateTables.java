package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class used for creating the tables in the database if they do 
 * not already exist inside the database.  Also, sets up constraints
 * on the tables as needed.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CreateTables {
	private int testing = 1;
	private Connection con;
	private Statement stm = null;
	private int rs;
	
	/**
	 * Constructor for the CreateTables class.  This is used to create
	 * all the tables needed for the database if they do not already
	 * exist in the system.  Also, sets up the primary and foreign keys
	 * as needed for each table in the system.
	 * 
	 * @param con The connection to the database. 
	 */
	public CreateTables( Connection con ) {
		this.con = con;
		createAllTables();
	}
	
	/**
	 * Method used to call all the other create table methods.
	 */
	private void createAllTables() {
		createPatientTable();
		createSignInTable();
		createStaffTable();
		createWardsTable();
		createCheckInTable();
		createMedicalRecordTable();
		createBillingTable();
	}
	
	/**
	 * Method used to create the SignIn table if it does not already
	 * exist in the database.
	 */
	private void createSignInTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			// String used to create the SignIn table
			String sql = "CREATE TABLE IF NOT EXISTS SignIn ("
					+ "UserName VARCHAR(100) NOT NULL,"
					+ "PassWord VARCHAR(100) NOT NULL,"
					+ "Role VARCHAR(50) NOT NULL,"
					+ "PRIMARY KEY( UserName, PassWord )"
					+ ");";
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created SignIn Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to create the Staff table if it does not already
	 * exist in the database.
	 */
	private void createStaffTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Staff ("
					+ "StaffID BIGINT NOT NULL AUTO_INCREMENT,"
					+ "Name VARCHAR(50) NOT NULL,"
					+ "Age int,"
					+ "Gender VARCHAR(6),"
					+ "JobTitle VARCHAR(50) NOT NULL,"
					+ "PhoneNumber VARCHAR(15),"
					+ "Address VARCHAR(100),"
					+ "Department VARCHAR(50),"
					+ "ProfessionalTitle VARCHAR(50),"
					+ "PRIMARY KEY( StaffID )"
					+ ");";
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created Staff Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method used to create the CheckIn table if it does not already
	 * exist in the database.
	 */
	private void createCheckInTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			String sql = "CREATE TABLE IF  NOT EXISTS CheckIn("
					+ "CheckInID BIGINT NOT NULL AUTO_INCREMENT,"
					+ "PatientID BIGINT NOT NULL,"
//					+ "BedType int,"
					+ "WardNumber BIGINT NOT NULL,"
					+ "StartDate VARCHAR(20) NOT NULL,"
					+ "EndDate VARCHAR(20),"
					+ "RegistrationFee int,"
					+ "PRIMARY KEY( CheckInID ),"
					+ "FOREIGN KEY(PatientID) REFERENCES Patient(PatientID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "FOREIGN KEY(WardNumber) REFERENCES Wards(WardID) ON UPDATE CASCADE"
					+ ");";
			
			rs = stm.executeUpdate( sql );
			
			if( rs ==1 && testing == 1 ) {
				System.out.println("Created CheckIn Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to create the Billing table if it does not already
	 * exist in the database.
	 */
	private void createBillingTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			// String used to creat the table
			String sql = "CREATE TABLE IF NOT EXISTS BillingRecord ("
					+ "AccountID BIGINT NOT NULL AUTO_INCREMENT,"
					+ "PatientID BIGINT NOT NULL,"
					+ "PayerSSN int,"
					+ "PaymentType VARCHAR(10) NOT NULL,"
					+ "CardNumber VARCHAR(20),"
					+ "BillingAddress VARCHAR(100),"
					+ "Amount int NOT NULL,"
					+ "Record VARCHAR(300) DEFAULT NULL,"
					+ "VisitDate VARCHAR(15),"
					+ "PRIMARY KEY( AccountID ),"
					+ "FOREIGN KEY(PatientID) REFERENCES Patient(PatientID) ON UPDATE CASCADE ON DELETE CASCADE"
					+ ");";
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created Billing Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to create the MedicalRecords table if it does not
	 * already exist in the database.
	 */
	private void createMedicalRecordTable() {
		stm= null;
		rs = 0;
		try {
			stm = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS MedicalRecord ("
					+ "RecordID BIGINT NOT NULL AUTO_INCREMENT,"
					+ "PatientID BIGINT NOT NULL,"
					+ "StartDate VARCHAR(20),"
					+ "EndDate VARCHAR(20),"
					+ "StaffID BIGINT NOT NULL,"
					+ "Prescription VARCHAR(100),"
					+ "Diagnosis VARCHAR(300),"
					+ "Test VARCHAR(100),"
					+ "Results VARCHAR(100),"
					+ "Treatment VARCHAR(100),"
					+ "ConsultFee int,"
					+ "TestFee int,"
					+ "TreatmentFee int,"
					+ "Specialist BIGINT,"
					+ "PRIMARY KEY( RecordID ),"
					+ "FOREIGN KEY(PatientID) REFERENCES Patient(PatientID) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "FOREIGN KEY(StaffID) REFERENCES Staff(StaffID) ON UPDATE CASCADE"
//					+ "FOREIGN KEY(Specialist) REFERENCES Staff(StaffID) ON UPDATE CASCADE"
					+ ");";
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created MedicalRecord Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to create the Patient table if it does not already
	 * exist in the database.
	 */
	private void createPatientTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			// String used to create the Patient table
			String sql = "CREATE TABLE IF NOT EXISTS Patient ("
					+ "PatientID BIGINT NOT NULL AUTO_INCREMENT,"
					+ "SSN int,"
					+ "Name VARCHAR(50) NOT NULL,"
					+ "DateofBirth VARCHAR(20),"
					+ "Gender VARCHAR(6),"
					+ "Status VARCHAR(50),"
					+ "Age int,"
					+ "PhoneNumber VARCHAR(15),"
					+ "Address VARCHAR(100),"
					+ "PRIMARY KEY( PatientID )"
					+ ");";
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created Patient Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method used to create the wards table if it does not
	 * already exists in the database.
	 */
	private void createWardsTable() {
		stm = null;
		rs = 0;
		try {
			stm = con.createStatement();
			// String used to create the Wards table
			String sql = "CREATE TABLE IF NOT EXISTS Wards("
					+ "WardId BIGINT AUTO_INCREMENT NOT NULL,"
					+ "OneBed int NOT NULL,"
					+ "TwoBed int Not Null,"
					+ "ThreeBed int NOT NULL,"
					+ "NumberOfPatient int NOT NULL,"
					+ "Charges int,"
					+ "ResponsibleNurse BIGINT,"
					+ "PRIMARY KEY( WardID ),"
					+ "FOREIGN KEY(ResponsibleNurse) REFERENCES Staff(StaffID) ON UPDATE CASCADE);";
			
			rs = stm.executeUpdate( sql );
			
			if( rs == 1 && testing == 1 ) {
				System.out.println("Created Wards Table.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
