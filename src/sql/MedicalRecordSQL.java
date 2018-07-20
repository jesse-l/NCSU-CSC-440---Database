package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.MedicalRecord;
import objects.Patient;

/**
 * Classed used for handling the SQL actions for Medical Record objects.
 * 
 * RecordID, PatientID, StartDate , EndDate, StaffID, Prescription, Diagnosis,
 * 		Test, Results, Treatment, ConsultFee, TestFee, TreatmentFee, Specialist
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class MedicalRecordSQL {
	private PatientSQL pSQL;
	private final String SELECT_ALL = "SELECT * FROM MedicalRecord";
	private final String SELECT_PATIENT = "SELECT * FROM MedicalRecord WHERE PatientID=?";
	private final String SELECT_BY_TIME_PERIOD = "SELECT * FROM MedicalRecord WHERE PatientID=? AND StartDate >= ? AND EndDate <= ?";
	private final String SELECT_DOCTOR = "SELECT * FROM MedicalRecord WHERE StaffID=?";
	private final String SELECT_DOCTOR_PATIENTS = "SELECT Patient.PatientID FROM (MedicalRecord INNER JOIN Patient) "
			+ "WHERE StaffID=? AND status ='In Hospital' AND Patient.PatientID=MedicalRecord.PatientID";
	private final String ADD_NEW_ENTRY = "INSERT INTO MedicalRecord(PatientID,StartDate,"
			+ "EndDate,StaffID,Prescription,Diagnosis,Test,Results,Treatment,ConsultFee,"
			+ "TestFee,TreatmentFee,Specialist) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String ADD_ENTRY = "INSERT INTO MedicalRecord(RecordID,PatientID,StartDate,"
			+ "EndDate,StaffID,Prescription,Diagnosis,Test,Results,Treatment,ConsultFee,"
			+ "TestFee,TreatmentFee,Specialist) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String DELETE_ENTRY = "DELETE FROM MedicalRecord WHERE RecordID = ?";
	private final String UPDATE_ENTRY = "UPDATE MedicalRecord SET PatientID=?,StartDate=?,"
			+ "EndDate=?,StaffID=?,Prescription=?,Diagnosis=?,Test=?,Results=?,Treatment=?,"
			+ "ConsultFee=?,TestFee=?,TreatmentFee=?,Specialist=? WHERE RecordID=?";
	private Connection con;
	
	public MedicalRecordSQL( Connection con ) {
		this.con = con;
		pSQL= new PatientSQL(con);
	}
	
	public ArrayList<MedicalRecord> getAllMedicalRecords() {
		ArrayList<MedicalRecord> mr = new ArrayList<>();
		try( PreparedStatement stm = con.prepareStatement(SELECT_ALL) ) {
			ResultSet rs = stm.executeQuery();
			MedicalRecord m = null;
			while(rs.next()) {
				int id = rs.getInt("RecordID");
				int pID = rs.getInt("PatientID");
				int sID = rs.getInt("StaffID");
				String sD = rs.getString("StartDate");
				String eD = rs.getString("EndDate");
				String pre = rs.getString("Prescription");
				String diag = rs.getString("Diagnosis");
				String test = rs.getString("Test");
				String res = rs.getString("Results");
				String treat = rs.getString("Treatment");
				int cF = rs.getInt("ConsultFee");
				int tF = rs.getInt("TestFee");
				int trF = rs.getInt("TreatmentFee");
				int sp = rs.getInt("Specialist");
				
				m = new MedicalRecord(id, pID, sD, eD, sID, pre, diag, test, res, treat, cF, tF, trF, sp);
				mr.add( m );// Add MR to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mr;
	}
	
	/**
	 * Method used to select all medical records for a specific patient
	 *
	 * @param PatientID The ID for the patient.
	 * @return An array of all medical records for given patient.
	 */
	public ArrayList<MedicalRecord> getAllRecordsForPatient(int PatientID) {
		ArrayList<MedicalRecord> mr = new ArrayList<MedicalRecord>();
		try( PreparedStatement stm = con.prepareStatement(SELECT_PATIENT) ) {
			stm.setInt(1, PatientID);
			
			ResultSet rs = stm.executeQuery();
			MedicalRecord m = null;
			while(rs.next()) {
				int id = rs.getInt("RecordID");
				int pID = rs.getInt("PatientID");
				int sID = rs.getInt("StaffID");
				String sD = rs.getString("StartDate");
				String eD = rs.getString("EndDate");
				String pre = rs.getString("Prescription");
				String diag = rs.getString("Diagnosis");
				String test = rs.getString("Test");
				String res = rs.getString("Results");
				String treat = rs.getString("Treatment");
				int cF = rs.getInt("ConsultFee");
				int tF = rs.getInt("TestFee");
				int trF = rs.getInt("TreatmentFee");
				int sp = rs.getInt("Specialist");
				
				m = new MedicalRecord(id, pID, sD, eD, sID, pre, diag, test, res, treat, cF, tF, trF, sp);
				mr.add( m );// Add MR to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mr;
	}
	
	/**
	 * Get staff members by the department that they work in.
	 * 
	 * @param Dept The department being searched.
	 * @return An array of all the staff members in that department.
	 */
	public ArrayList<MedicalRecord> getRecordsOfDoctor(int StaffID) {
		ArrayList<MedicalRecord> mr = new ArrayList<MedicalRecord>();
		try( PreparedStatement stm = con.prepareStatement(SELECT_DOCTOR) ) {
			stm.setInt(1, StaffID);
			
			ResultSet rs = stm.executeQuery();
			MedicalRecord m = null;
			while(rs.next()) {
				int id = rs.getInt("RecordID");
				int pID = rs.getInt("PatientID");
				int sID = rs.getInt("StaffID");
				String sD = rs.getString("StartDate");
				String eD = rs.getString("EndDate");
				String pre = rs.getString("Prescription");
				String diag = rs.getString("Diagnosis");
				String test = rs.getString("Test");
				String res = rs.getString("Results");
				String treat = rs.getString("Treatment");
				int cF = rs.getInt("ConsultFee");
				int tF = rs.getInt("TestFee");
				int trF = rs.getInt("TreatmentFee");
				int sp = rs.getInt("Specialist");
				
				m = new MedicalRecord(id, pID, sD, eD, sID, pre, diag, test, res, treat, cF, tF, trF, sp);
				mr.add( m );// Add MR to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mr;
	}
	
	/**
	 * Get staff members by the department that they work in.
	 * 
	 * @param Dept The department being searched.
	 * @return An array of all the staff members in that department.
	 */
	public ArrayList<Patient> getPatientRecordsOfDoctor(int StaffID) {
		ArrayList<Patient> p = new ArrayList<Patient>();
		try( PreparedStatement stm = con.prepareStatement(SELECT_DOCTOR_PATIENTS) ) {
			stm.setInt(1, StaffID);
			
			ResultSet rs = stm.executeQuery();
			Patient m = null;
			while(rs.next()) {
				int pID = rs.getInt("PatientID");
				
				m = pSQL.getPatientsByID(pID);
				p.add( m );// Add MR to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	 /**
   * Get staff members by the department that they work in.
   * 
   * @param Dept The department being searched.
   * @return An array of all the staff members in that department.
   */
  public ArrayList<MedicalRecord> getPatientRecordsByTime(int patientID, String sDate, String eDate) {
    ArrayList<MedicalRecord> mr = new ArrayList<>();
    try( PreparedStatement stm = con.prepareStatement(SELECT_BY_TIME_PERIOD) ) {
      stm.setInt(1, patientID);
      stm.setString(2, sDate);
      stm.setString(3, eDate);
      ResultSet rs = stm.executeQuery();
      MedicalRecord m = null;
      while(rs.next()) {
        int id = rs.getInt("RecordID");
        int pID = rs.getInt("PatientID");
        int sID = rs.getInt("StaffID");
        String sD = rs.getString("StartDate");
        String eD = rs.getString("EndDate");
        String pre = rs.getString("Prescription");
        String diag = rs.getString("Diagnosis");
        String test = rs.getString("Test");
        String res = rs.getString("Results");
        String treat = rs.getString("Treatment");
        int cF = rs.getInt("ConsultFee");
        int tF = rs.getInt("TestFee");
        int trF = rs.getInt("TreatmentFee");
        int sp = rs.getInt("Specialist");
        
        m = new MedicalRecord(id, pID, sD, eD, sID, pre, diag, test, res, treat, cF, tF, trF, sp);
        mr.add( m );// Add MR to the array
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return mr;
  }
	

	/**
	 * Method used to add a new staff member to the database.
	 * 
	 * @param s The new staff member being added to the system.
	 * @return Returns if the staff member was added or not.
	 */
	public boolean addNewRecord( MedicalRecord m ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_NEW_ENTRY) ) {
			stm.setInt(1, m.getPatientID());
			stm.setString(2, m.getStartDate());
			stm.setString(3, m.getEndDate());
			stm.setInt(4, m.getResponsibleDoctor());
			stm.setString(5, m.getPrescription());
			stm.setString(6, m.getDiagnostic());
			stm.setString(7, m.getTest());
			stm.setString(8, m.getResult());
			stm.setString(9, m.getTreatment());
			stm.setFloat(10, m.getConsultFee());
			stm.setFloat(11, m.getTestFee());
			stm.setFloat(12, m.getTreatmentFee());
			stm.setInt(13, m.getSpecialist());
			
			if( stm.executeUpdate()==1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Add a staff member to the database with an existing staff id number.
	 * @param s The staff member being added to the table
	 * @return Returns if the staff member was added or not.
	 */
	public boolean addRecord( MedicalRecord m ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setInt(2, m.getRecordID());
			stm.setInt(2, m.getPatientID());
			stm.setString(3, m.getStartDate());
			stm.setString(4, m.getEndDate());
			stm.setInt(5, m.getResponsibleDoctor());
			stm.setString(6, m.getPrescription());
			stm.setString(7, m.getDiagnostic());
			stm.setString(8, m.getTest());
			stm.setString(9, m.getResult());
			stm.setString(10, m.getTreatment());
			stm.setFloat(11, m.getConsultFee());
			stm.setFloat(12, m.getTestFee());
			stm.setFloat(13, m.getTreatmentFee());
			stm.setInt(14, m.getSpecialist());
			
			if( stm.executeUpdate()==1 )
				added = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Update information about a staff member in the database.
	 * 
	 * @param s Staff member being updated in the table.
	 * @return Returns if the staff member was updated or not.
	 */
	public boolean updateRecord( MedicalRecord m ) {
		boolean updated = false;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setInt(1, m.getPatientID());
			stm.setString(2, m.getStartDate());
			stm.setString(3, m.getEndDate());
			stm.setInt(4, m.getResponsibleDoctor());
			stm.setString(5, m.getPrescription());
			stm.setString(6, m.getDiagnostic());
			stm.setString(7, m.getTest());
			stm.setString(8, m.getResult());
			stm.setString(9, m.getTreatment());
			stm.setFloat(10, m.getConsultFee());
			stm.setFloat(11, m.getTestFee());
			stm.setFloat(12, m.getTreatmentFee());
			stm.setInt(13, m.getSpecialist());
			stm.setInt(14, m.getRecordID());
			
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	/**
	 * Method used to delete a staff member from the table.
	 * @param s Staff member being deleted.
	 * @return Returns if the staff member was deleted or not.
	 */
	public boolean deleteRecord( MedicalRecord m ) {
		boolean deleted = false;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setInt(1, m.getRecordID());
			
			if( stm.executeUpdate() == 1 )
				deleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public void clearTable() {
		String clear = "DELETE FROM MedicalRecord";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear = "ALTER TABLE MedicalRecord AUTO_INCREMENT = 1";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
