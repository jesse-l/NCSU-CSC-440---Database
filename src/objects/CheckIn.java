package objects;

/**
 * Class used for handling the CheckIn data type.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class CheckIn {
	/** The id number of the check in record */
	private int CheckInID;
	/** The patient the record is associated with */
	private int PatientID;
	/** The bed number the patient is assigned to */
	private int BedNumber;
	/** The ward id the patient is assigned to */
	private int WardID;
	/** The data when the visit starts */
	private String StartDate;
	/** The data when the visit ends */
	private String EndDate;
	/**	Registration fee for the check-in */
	private int RegFee;
	
//	/**
//	 * Method used to create a CheckIn record with both a start date
//	 * and an end date.
//	 * 
//	 * @param PatientID The Patient ID number being checked in.
//	 * @param BedNumber The bed number the patient is assigned to.
//	 * @param WardID The ID number of the ward the patient is assigned.
//	 * @param StartDate The start date of treatment.
//	 * @param EndDate The end date of treatment.
//	 * @param RegFee The registration fee.
//	 */
//	public CheckIn( int PatientID, int BedNumber, int WardID,
//			String StartDate, String EndDate, int RegFee ) {
//		this.PatientID = PatientID;
//		this.BedNumber = BedNumber;
//		this.WardID = WardID;
//		this.StartDate = StartDate;
//		this.EndDate = EndDate;
//		this.RegFee = RegFee;
//	}
	
	/**
	 * Method used to create a CheckIn record with both a start date
	 * and an end date with no bed number.
	 * 
	 * @param PatientID The Patient ID number being checked in.
	 * @param BedNumber The bed number the patient is assigned to.
	 * @param WardID The ID number of the ward the patient is assigned.
	 * @param StartDate The start date of treatment.
	 * @param EndDate The end date of treatment.
	 * @param RegFee The registration fee.
	 */
	public CheckIn( int PatientID, int WardID,
			String StartDate, String EndDate, int RegFee ) {
		this.PatientID = PatientID;
		this.BedNumber = BedNumber;
		this.WardID = WardID;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.RegFee = RegFee;
	}
	
	/**
	 * Method used to read from the CheckIn table in the database.
	 * 
	 * @param CheckInID The ID number for the record.
	 * @param PatientID The Patient ID number being checked in.
	 * @param BedNumber The bed number the patient is assigned to.
	 * @param WardID The ID number of the ward the patient is assigned.
	 * @param StartDate The start date of treatment.
	 * @param EndDate The end date of treatment.
	 * @param RegFee The registration fee.
	 */
	public CheckIn( int CheckInID, int PatientID,
			int WardID, String StartDate, String EndDate, int RegFee ) {
		this.CheckInID = CheckInID;
		this.PatientID = PatientID;
		this.BedNumber = BedNumber;
		this.WardID = WardID;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.RegFee = RegFee;
	}

	/**
	 * @return the checkInID
	 */
	public int getCheckInID() {
		return CheckInID;
	}

	/**
	 * @param checkInID the checkInID to set
	 */
	public void setCheckInID(int checkInID) {
		CheckInID = checkInID;
	}

	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return PatientID;
	}

	/**
	 * @param patientID the patientID to set
	 */
	public void setPatientID(int patientID) {
		PatientID = patientID;
	}

	/**
	 * @return the bedNumber
	 */
	public int getBedNumber() {
		return BedNumber;
	}

	/**
	 * @param bedNumber the bedNumber to set
	 */
	public void setBedNumber(int bedNumber) {
		BedNumber = bedNumber;
	}

	/**
	 * @return the wardID
	 */
	public int getWardID() {
		return WardID;
	}

	/**
	 * @param wardID the wardID to set
	 */
	public void setWardID(int wardID) {
		WardID = wardID;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return StartDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return EndDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	/**
	 * @return the regFee
	 */
	public int getRegFee() {
		return RegFee;
	}

	/**
	 * @param regFee the regFee to set
	 */
	public void setRegFee(int regFee) {
		RegFee = regFee;
	}
}