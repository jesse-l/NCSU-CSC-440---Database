package objects;

/**
 * Class used for holding login information in the table.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class SignIn {
	/** The user name of the account */
	private String userName;
	/** The password of the account */
	private String password;
	/** The role of the user at the hospital */
	private String role;
	
	/**
	 * Create a new account to sign into the system with.
	 * @param userName Username for the new account in the system.
	 * @param password Pass word for the new account in the system.
	 * @param role The role of the user for the account.
	 */
	public SignIn( String userName, String password, String role ) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
