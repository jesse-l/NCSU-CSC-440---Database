package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.BillingRecord;
import objects.Patient;
import objects.Ward;

/**
 * 
 * AccountID, PatientID, PayerSSN, PaymentType, CardNumber, BillingAddress, Amount,
 * 		Record, VisitDate
 * 
 * @author tao
 */
public class BillingRecordSQL {
	/**connection*/
	private Connection con;
	/**status of the method*/
	private String status= "";
	private final String SELECT_ALL = "SELECT * FROM BillingRecord";
	private final String SELECT_PATIENT = "SELECT * FROM BillingRecord WHERE PatientID = ?";
	private final String SELECT_VISIT = "SELECT * FROM BinningRecord WHERE VisitDate = ?";
	private final String ADD_NEW_ENTRY = "INSERT INTO BillingRecord(PatientID,PayerSSN,PaymentType,"
			+ "CardNumber,BillingAddress,Amount,Record,VisitDate) VALUES (?,?,?,?,?,?,?,?)";
	private final String ADD_ENTRY = "INSERT INTO BillingRecord(AccountID,PatientID,PayerSSN,PaymentType,"
			+ "CardNumber,BillingAddress,Amount,Record,VisitDate) VALUES (?,?,?,?,?,?,?,?,?)";
	private final String DELETE_ENTRY = "DELETE FROM BillingRecord WHERE AccountID = ?";
	private final String UPDATE_ENTRY = "UPDATE BillingRecord Set PatientID=?, PayerSSN=?, PaymentType=?,"
			+ "CardNumber=?,BillingAddress=?,Amount=?,Record=?,VisitDate=? WHERE AccountID=?";
	
	
	/**
	 * Constructor for this class.
	 * 
	 * @param con The connection to the database.
	 */
	public BillingRecordSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Method for selecting all the billing records.
	 * 
	 * @return Array list of all billing records.
	 */
	public ArrayList<BillingRecord> selectAll() {
		ArrayList<BillingRecord> br = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ALL) ) {
			rs = stm.executeQuery();
			BillingRecord b = null;
			while( rs.next() ) {
				int bID = rs.getInt("AccountID");
				int pID = rs.getInt("PatientID");
				int ssn = rs.getInt("PayerSSN");
				String pT = rs.getString("PaymentType");
				String cN = rs.getString("CardNumber");
				String bA = rs.getString("BillingAddress");
				int a = rs.getInt("Amount");
				String rec = rs.getString("Record");
				String vD = rs.getString("VisitDate");
				b = new BillingRecord(bID, pID, ssn, pT, cN, bA, a, rec, vD);
				br.add( b );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	/**
	 * Method used to select billing records by patient id number
	 * 
	 * @param p The patient that is being searched for
	 * @return Array list of all billing records for that patient
	 */
	public ArrayList<BillingRecord> selectByPatient(Patient p) {
		ArrayList<BillingRecord> br = new ArrayList<>();
		ResultSet rs = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_PATIENT) ) {
			stm.setInt(1, p.getPatientID());
			
			rs = stm.executeQuery();
			BillingRecord b = null;
			while( rs.next() ) {
				int bID = rs.getInt("AccountID");
				int pID = rs.getInt("PatientID");
				int ssn = rs.getInt("PayerSSN");
				String pT = rs.getString("PaymentType");
				String cN = rs.getString("CardNumber");
				String bA = rs.getString("BillingAddress");
				int a = rs.getInt("Amount");
				String rec = rs.getString("Record");
				String vD = rs.getString("VisitDate");
				b = new BillingRecord(bID, pID, ssn, pT, cN, bA, a, rec, vD);
				br.add( b );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	
	/**
	 * Method for adding a new billing record to the table.
	 * 
	 * @param b The billing record being inserted
	 * @return Returns if the record was added or not.
	 */
	public boolean addNewBillingRecord( BillingRecord b ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_NEW_ENTRY) ) {
			stm.setInt(1, b.getPatientID());// Set Patient ID
			stm.setInt(2, b.getSSN());
			stm.setString(3, b.getPaymentType());
			stm.setString(4, b.getCardNumber());

			stm.setString(5, b.getBillingAddress());
			stm.setInt(6, b.getAmount());
			stm.setString(7, b.getRecord());
			stm.setString(8, b.getVisitDate());
			
			if( stm.executeUpdate() == 1 )
				added = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method for adding a billing record with a record id.
	 * 
	 * @param b The billing record being inserted
	 * @return Returns if the record was added or not.
	 */
	public boolean addBillingRecord( BillingRecord b ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setInt(1, b.getBillingID());
			stm.setInt(2, b.getPatientID());// Set Patient ID
			stm.setInt(3, b.getSSN());
			stm.setString(4, b.getPaymentType());
			stm.setString(5, b.getCardNumber());
			stm.setString(6, b.getBillingAddress());
			stm.setInt(7, b.getAmount());
			stm.setString(8, b.getRecord());
			stm.setString(9, b.getVisitDate());
			
			if( stm.executeUpdate() == 1 )
				added = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	/**
	 * Method used for updating a billing record.
	 * 
	 * @param b The billing record being updated
	 * @return Returns if the record was updated or not.
	 */
	public boolean updateBillingRecord( BillingRecord b ) {
		boolean updated = false;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setInt(1, b.getPatientID());// Set Patient ID
			stm.setInt(2, b.getSSN());
			stm.setString(3, b.getPaymentType());
			stm.setString(4, b.getCardNumber());
			stm.setString(5, b.getBillingAddress());
			stm.setInt(6, b.getAmount());
			stm.setString(7, b.getRecord());
			stm.setString(8, b.getVisitDate());
			stm.setInt(9, b.getBillingID());
			
			if( stm.executeUpdate() == 1 )
				updated = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	
	/**
	 * Method for deleting billing records from the table.
	 * 
	 * @param b The billing record being deleted from the table.
	 * @return Returns if the record was deleted or not.
	 */
	public boolean deleteBillingRecord( int b ) {
		boolean deleted = false;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setInt(1, b);
			
			if( stm.executeUpdate() == 1 )
				deleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	@SuppressWarnings("resource")
	/**
	 * This method will check if the Billing record already exists
	 * if exist, it will update the record
	 * if not exist, it will add a new record
	 * @param record
	 * @return the record is added or updated
	 */
	public String creatBillingAccount(BillingRecord record){
		
		ResultSet rs = null;
		
		try{
			PreparedStatement stm = con.prepareStatement("SELECT * FROM BillingRecord WHERE PatientID = ?");
			stm.setInt(1, record.getPatientID());
			
			rs = stm.executeQuery();
			
			if(rs == null){
				stm = con.prepareStatement("INSERT INTO BillingRecord(PatientID, PayerSSN, "
						+ "PaymentType, CardNumber, BillingAddress, Amount, Record, "
						+ "VisitDate) VALUES (?,?,?,?,?,?,?,?)");
				
				stm.setInt(1, record.getPatientID());
				stm.setInt(2, record.getSSN());
				stm.setString(3, record.getPaymentType());
				stm.setString(4, record.getCardNumber());
				stm.setString(5, record.getBillingAddress());
				stm.setInt(6, record.getAmount());
				stm.setString(7, record.getRecord());
				stm.setString(8, record.getVisitDate());
				if(stm.executeUpdate() == 1)
					status = "Billing record is successfully added";
			} else {
				stm = con.prepareStatement("UPDATE BillingRecord SET PayerSSN = ?, PaymentType = ?, "
						+ "CardNumber = ?, BillingAddress = ?, Amount = ?, Record = ?, "
						+ "VisitDate = ? WHERE PatientID = ?");
				
				
				stm.setInt(1, record.getSSN());
				stm.setString(2, record.getPaymentType());
				stm.setString(3, record.getCardNumber());
				stm.setString(4, record.getBillingAddress());
				stm.setInt(5, record.getAmount());
				stm.setString(6, record.getRecord());
				stm.setString(7, record.getVisitDate());
				
				stm.setInt(8, record.getPatientID());
				if(stm.executeUpdate() == 1)
					status = "Billing record is successfully updated";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	/**
	 * check if there is any space in ward
	 * return true if there is space
	 * false if not
	 * @param w
	 * @return status of ward
	public boolean checkSpace(Ward w){
		Boolean s = false;
		ResultSet rs = null;
		
		PreparedStatement stm;
		try {
			stm = con.prepareStatement("SELECT * FROM Wards WHERE Occupied = ?");
			stm.setInt(1, 0);
			
			rs = stm.executeQuery();
			
			if(rs == null){
			
			s = false;
			} else {
				s = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	**/
	public void clearTable(){
		String clear = "DELETE FROM BillingRecord";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear = "ALTER TABLE BillingRecord AUTO_INCREMENT = 1";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
