package objects;

/**
 * This class is used to store information about the patient.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Patient {
	/** The ID number for the patient */
	private int patientID;
	/** The name of the patient */
	private String name;
	/** The patient's date of birth */
	private String DateofBirth;
	/** The patient's SSN */
	private int SSN;
	/** The gender of the patient */
	private String gender;
	/** The status of the patient */
	private String status;
	/** Phone number the patient can be reached at */
	private String phoneNumber;
	/** An address for the patient to be reached at */
	private String address;
	
	/**
	 * Method used for reading patients from the database.
	 * 
	 * @param patientID The ID number for the patient
	 * @param name The name of the patient
	 * @param DateofBirth The patient's date of birth
	 * @param SSN The patient's SSN
	 * @param gender The gender of the patient
	 * @param status The status of the patient
	 * @param phoneNumber Phone number the patient can be reached at
	 * @param address An address for the patient to be reached at
	 */
	public Patient(int patientID, String name, String DateofBirth, int SSN, String gender,
			String status, String phoneNumber, String address) {
		this.patientID = patientID;
		this.name = name;
		this.DateofBirth = DateofBirth;
		this.SSN = SSN;
		this.gender = gender;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	/**
	 * Method used for creating a new patient to be added into the database.
	 * 
	 * @param name The name of the patient
	 * @param DateofBirth The patient's date of birth
	 * @param SSN The patient's SSN
	 * @param gender The gender of the patient
	 * @param status The status of the patient
	 * @param phoneNumber Phone number the patient can be reached at
	 * @param address An address for the patient to be reached at
	 */
	public Patient( String name, String DateofBirth, int SSN, String gender,
			String status, String phoneNumber, String address) {
		this.name = name;
		this.DateofBirth = DateofBirth;
		this.SSN = SSN;
		this.gender = gender;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}

	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		this.patientID = patientID;
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
	 * @return the dateofBirth
	 */
	public String getDateofBirth() {
		return DateofBirth;
	}

	/**
	 * @param dateofBirth the dateofBirth to set
	 */
	public void setDateofBirth(String dateofBirth) {
		DateofBirth = dateofBirth;
	}

	/**
	 * @return the sSN
	 */
	public int getSSN() {
		return SSN;
	}

	/**
	 * @param sSN the sSN to set
	 */
	public void setSSN(int sSN) {
		SSN = sSN;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DateofBirth == null) ? 0 : DateofBirth.hashCode());
		result = prime * result + SSN;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + patientID;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (DateofBirth == null) {
			if (other.DateofBirth != null)
				return false;
		} else if (!DateofBirth.equals(other.DateofBirth))
			return false;
		if (SSN != other.SSN)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patientID != other.patientID)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
