package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Staff;

/**
* This class is used for dealing with the SQL Actions related to the Staff table
* in the database.
* 
* StaffID, Name, Age, Gender, JobTitle, PhoneNumber, Address, Department, ProfessionalTitle
* 
* @author Jesse Liddle - jaliddl2
*/
public class StaffSQL {
	private final String SELECT_ALL = "SELECT * FROM Staff";
	private final String SELECT_ALL_GRP = "SELECT * FROM Staff ORDER BY JobTitle";
	private final String SELECT_DEPT = "SELECT * FROM Staff WHERE Department=?";
	private final String SELECT_ID = "SELECT * FROM Staff WHERE StaffID = ?";
	private final String ADD_ENTRY = "INSERT INTO Staff(StaffID,Name,Age,Gender,JobTitle,"
			+ "PhoneNumber,Address,Department,ProfessionalTitle) VALUES (?,?,?,?,?,?,?,?,?)";
	private final String ADD_NEW_ENTRY = "INSERT INTO Staff(Name,Age,Gender,JobTitle,"
			+ "PhoneNumber,Address,Department,ProfessionalTitle) VALUES (?,?,?,?,?,?,?,?)";
	private final String UPDATE_ENTRY = "UPDATE Staff SET Name=?,Age=?,Gender=?,"
			+ "JobTitle=?,PhoneNumber=?,Address=?,Department=?,ProfessionalTitle=? WHERE StaffID=?";
	private final String UPDATE_KEY = "UPDATE MedicalRecord SET StaffID = null WHERE StaffID=?";
//	private final String UPDATE_KEY2 = "UPDATE MedicalRecord SET Specialist = null WHERE Specialist=?";
	private final String UPDATE_KEY3 = "UPDATE Wards SET ResponsibleNurse = null WHERE ResponsibleNurse=?";
	private final String DELETE_ENTRY = "DELETE FROM Staff WHERE StaffID = ?";
	
	private Connection con;
	
	public StaffSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Method used to select all entries current in the staff table.
	 * 
	 * @return An ArrayList of all staff members currently in the database.
	 */
	public ArrayList<Staff> getAllStaff() {
		ArrayList<Staff> members = new ArrayList<Staff>();
		ResultSet rs = null;
		Staff s = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ALL) ) {
			rs = stm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("StaffID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				String gender = rs.getString("Gender");
				String jobTitle = rs.getString("JobTitle");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				String dept = rs.getString("Department");
				String profTitle = rs.getString("ProfessionalTitle");
				s = new Staff(id, name, age, gender, jobTitle, phoneNumber, address, dept, profTitle);
				members.add( s );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	/**
	 * Method used to select all staff members and group them according to their
	 * department.
	 * 
	 * @return An array of all staff members grouped by their departments.
	 */
	public ArrayList<Staff> getAllStaffGroup() {
		ArrayList<Staff> members = new ArrayList<Staff>();
		ResultSet rs = null;
		Staff s = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ALL_GRP) ) {
			rs = stm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("StaffID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				String gender = rs.getString("Gender");
				String jobTitle = rs.getString("JobTitle");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				String dept = rs.getString("Department");
				String profTitle = rs.getString("ProfessionalTitle");
				s = new Staff(id, name, age, gender, jobTitle, phoneNumber, address, dept, profTitle);
				members.add( s );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	/**
	 * Get staff members by the department that they work in.
	 * 
	 * @param Dept The department being searched.
	 * @return An array of all the staff members in that department.
	 */
	public ArrayList<Staff> getStaffbyDept(String Dept) {
		ArrayList<Staff> members = new ArrayList<Staff>();
		ResultSet rs = null;
		Staff s = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_DEPT) ) {
			stm.setString(1, Dept);
			
			rs = stm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("StaffID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				String gender = rs.getString("Gender");
				String jobTitle = rs.getString("JobTitle");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				String dept = rs.getString("Department");
				String profTitle = rs.getString("ProfessionalTitle");
				s = new Staff(id, name, age, gender, jobTitle, phoneNumber, address, dept, profTitle);
				members.add( s );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public ArrayList<Staff> getStaffbyID(int ID) {
		ArrayList<Staff> members = new ArrayList<Staff>();
		ResultSet rs = null;
		Staff s = null;
		try( PreparedStatement stm = con.prepareStatement(SELECT_ID) ) {
			stm.setInt(1, ID);
			
			rs = stm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("StaffID");
				String name = rs.getString("Name");
				int age = rs.getInt("Age");
				String gender = rs.getString("Gender");
				String jobTitle = rs.getString("JobTitle");
				String phoneNumber = rs.getString("PhoneNumber");
				String address = rs.getString("Address");
				String dept = rs.getString("Department");
				String profTitle = rs.getString("ProfessionalTitle");
				s = new Staff(id, name, age, gender, jobTitle, phoneNumber, address, dept, profTitle);
				members.add( s );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	/**
	 * Method used to add a new staff member to the database.
	 * 
	 * @param s The new staff member being added to the system.
	 * @return Returns if the staff member was added or not.
	 */
	public boolean addNewStaff( Staff s ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_NEW_ENTRY) ) {
			stm.setString(1, s.getName());
			stm.setInt(2, s.getAge());
			stm.setString(3, s.getGender());
			stm.setString(4, s.getJobTitle());
			stm.setString(5, s.getPhoneNumber());
			stm.setString(6, s.getAddress());
			stm.setString(7, s.getDepartment());
			stm.setString(8, s.getProfessionalTitle());
			
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
	public boolean addStaff( Staff s ) {
		boolean added = false;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setInt(1, s.getStaffID());
			stm.setString(2, s.getName());
			stm.setInt(3, s.getAge());
			stm.setString(4, s.getGender());
			stm.setString(5, s.getJobTitle());
			stm.setString(6, s.getPhoneNumber());
			stm.setString(7, s.getAddress());
			stm.setString(8, s.getDepartment());
			stm.setString(9, s.getProfessionalTitle());
			
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
	public boolean updateStaff( Staff s ) {
		boolean updated = false;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setString(1, s.getName());
			stm.setInt(2, s.getAge());
			stm.setString(3, s.getGender());
			stm.setString(4, s.getJobTitle());
			stm.setString(5, s.getPhoneNumber());
			stm.setString(6, s.getAddress());
			stm.setString(7, s.getDepartment());
			stm.setString(8, s.getProfessionalTitle());
			stm.setInt(9, s.getStaffID());
			
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
	public boolean deleteStaff( int s ) {
		try( PreparedStatement stm = con.prepareStatement(UPDATE_KEY) ) {
			stm.setInt(1, s);
			
			if( stm.executeUpdate() < 0 )
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try( PreparedStatement stm = con.prepareStatement(UPDATE_KEY2) ) {
//			stm.setInt(1, s);
//			
//			if( stm.executeUpdate() < 0 )
//				return false;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		try( PreparedStatement stm = con.prepareStatement(UPDATE_KEY3) ) {
			stm.setInt(1, s);
			
			if( stm.executeUpdate() < 0 )
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean deleted = false;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setInt(1, s);
			
			if( stm.executeUpdate() == 1 )
				deleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public void clearTable() {
		String clear = "DELETE FROM Staff";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clear = "ALTER TABLE Staff AUTO_INCREMENT = 1";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
