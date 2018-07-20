package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.SignIn;

/**
 * This class is responsible for handling the sql actions in the SignIn table.
 * 
 * Table Structure:
 * VARCHAR(100) UserName, VARCHAR(100) PassWord, VARCHAR(50) Role
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class SignInSQL {
	private final String SELECT_ALL = "SELECT * FROM SignIn";
	private final String SELECT_SOME = "SELECT * FROM SignIn WHERE userName = ?";
	private final String ADD_ENTRY = "INSERT INTO SignIn(UserName,PassWord,Role)"
			+ " VALUES (?,?,?)";
	private final String UPDATE_ENTRY = "UPDATE SignIn SET PassWord = ?, Role = ?"
			+ "WHERE UserName = ?";
	private final String DELETE_ENTRY = "DELETE FROM SignIn WHERE UserName = ? AND PassWord = ?";
	
	private Connection con;
	
	public SignInSQL( Connection con ) {
		this.con = con;
	}
	
	/**
	 * Method used to return all the entries in the SignIn table.
	 * @return A list of all the entries currently in the SignIn table.
	 */
	public ArrayList<SignIn> getAllSignIn() {
		ArrayList<SignIn> signIns = new ArrayList<SignIn>();// array for all sign ins
		ResultSet rs = null;// result set to hold the results of the query
		
		try( PreparedStatement stm = con.prepareStatement( SELECT_ALL ) ) {
			rs = stm.executeQuery();// executes the select all
			SignIn s = null;
			while( rs.next() ) {
				String userName = rs.getString("UserName");// grab user name
				String password = rs.getString("PassWord");// grab user password
				String role = rs.getString("Role");// grab user role
				s = new SignIn(userName, password, role);// Create sign in object
				signIns.add(s);// Add new sign in to list
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return signIns;
	}
	
	/**
	 * Method used to select only specific user names from the entries in the
	 * database.  Useful for comparing user names to passwords.
	 * @param Username The user name that was entered into the system.
	 * @return Returns an array of all users matching that user name.
	 */
	public ArrayList<SignIn> getSignIn( String Username ) {
		ArrayList<SignIn> signIns = new ArrayList<SignIn>();// array for all sign ins
		ResultSet rs = null;// result set to hold the results of the query
		try( PreparedStatement stm = con.prepareStatement( SELECT_SOME ) ) {
			stm.setString( 1, Username );
			rs = stm.executeQuery();
			SignIn s = null;
			while( rs.next() ) {
				String username = rs.getString( "UserName" );
				String password = rs.getString("PassWord");
				String role = rs.getString("Role");
				s = new SignIn(username, password, role);
				signIns.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return signIns;
	}
	
	/**
	 * Method used to add a new SignIn object into the existing database.
	 * 
	 * @param s The new SignIn object to be entered into the system.
	 * @return Return true or false depending on if the entry was added or not.
	 */
	public boolean addSignIn( SignIn s ) {
		boolean added = false;
		// Checks to make sure it is a SignIn object
		if( !(s instanceof SignIn) )
			return added;
		try( PreparedStatement stm = con.prepareStatement(ADD_ENTRY) ) {
			stm.setString( 1, s.getUserName() );// Sets user name
			stm.setString( 2, s.getPassword() );// Sets password
			stm.setString( 3, s.getRole() );// Sets role
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
	public boolean updateSignIn( SignIn s ) {
		boolean updated = false;
		if( !(s instanceof SignIn) )
			return updated;
		try( PreparedStatement stm = con.prepareStatement(UPDATE_ENTRY) ) {
			stm.setString(1, s.getPassword());
			stm.setString(2,s.getRole() );
			stm.setString(3, s.getUserName());
			
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
	public boolean deleteSignIn( SignIn s ) {
		boolean deleted = false;
		if( !(s instanceof SignIn ) )
			return deleted;
		try( PreparedStatement stm = con.prepareStatement(DELETE_ENTRY) ) {
			stm.setString( 1, s.getUserName() );
			stm.setString( 2, s.getPassword() );
			
			stm.execute();
			
			deleted = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	public void clearTable() {
		String clear = "DELETE FROM SignIn";
		try(PreparedStatement stm = con.prepareStatement(clear)) {
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
