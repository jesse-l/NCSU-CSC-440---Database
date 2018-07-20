package objects;

/**
 * This class is used to store information about a staff
 * member in the hospital.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Staff {
	/** The id number for the staff member */
	private int StaffID;
	/** The name of the staff member */ 
	private String name;
	/** The age of the staff member */
	private int age;
	/** The gender of the staff member */
	private String gender;
	/** The job title of the staff member */
	private String jobTitle;
	/** The phone number of the staff member */
	private String phoneNumber;
	/** The address of the staff member */
	private String address;
	/** The staff member's main department */
	private String Department;
	/** The professional title for the staff member */
	private String ProfessionalTitle;
	
	
	/**
	 * Used when creating a new staff member.  Does not have a StaffID
	 * yet.
	 * 
	 * @param name Name of the staff member
	 * @param age Age of the staff member
	 * @param gender Gender of the staff member
	 * @param jobTitle Position the staff member holds
	 * @param phoneNumber Phone number for the staff member
	 * @param address Address for the staff member
	 * @param department The staff member's department
	 * @param professionalTitle The professional title of the staff member
	 */
	public Staff(String name, int age, String gender, String jobTitle, String phoneNumber, String address,
			String department, String professionalTitle) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.phoneNumber = phoneNumber;
		this.address = address;
		Department = department;
		ProfessionalTitle = professionalTitle;
	}

	/**
	 * Used when reading from the database to get all fields.
	 * 
	 * @param StaffID ID number for the staff member
	 * @param name Name of the staff member
	 * @param age Age of the staff member
	 * @param gender Gender of the staff member
	 * @param jobTitle Position the staff member holds
	 * @param phoneNumber Phone number for the staff member
	 * @param address Address for the staff member
	 * @param department The staff member's department
	 * @param professionalTitle The professional title of the staff member
	 */
	public Staff(int staffID, String name, int age, String gender, String jobTitle, String phoneNumber, String address,
			String department, String professionalTitle) {
		super();
		StaffID = staffID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.phoneNumber = phoneNumber;
		this.address = address;
		Department = department;
		ProfessionalTitle = professionalTitle;
	}

	/**
	 * @return the staffID
	 */
	public int getStaffID() {
		return StaffID;
	}

	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		StaffID = staffID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return Department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		Department = department;
	}

	/**
	 * @return the professionalTitle
	 */
	public String getProfessionalTitle() {
		return ProfessionalTitle;
	}

	/**
	 * @param professionalTitle the professionalTitle to set
	 */
	public void setProfessionalTitle(String professionalTitle) {
		ProfessionalTitle = professionalTitle;
	}
	
}
