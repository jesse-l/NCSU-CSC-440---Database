package objects;

/**
 * Class used for storing information about the wards of the hospital.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class Ward {
	private int WardID;
	private int oneBed;
	private int twoBed;
	private int threeBed;
	private int numberOfPatient;
	private int charges;
	private int respNurse;
	
	/**
	 * Creating a ward without it already existing in the database.
	 * 
	 * @param oneB  Number of single beds in the ward.
	 * @param twoB Number of double beds in the ward.
	 * @param threeB Number of triple beds in the ward.
	 * @param charges Charges for staying in the ward.
	 * @param respNurse The nurse responsible for the ward.
	 */
	public Ward(int oneB, int twoB, int threeB, int occupied, int charges, int respNurse ) {
		this.oneBed = oneB;
		this.twoBed = twoB;
		this.threeBed = threeB;
		this.numberOfPatient = occupied;
		this.charges = charges;
		this.respNurse = respNurse;
	}
	
	/**
	 * Creating a ward that already existing in the database.
	 * 
	 * @param wardID The id of the ward.
	 * @param oneB  Number of single beds in the ward.
	 * @param twoB Number of double beds in the ward.
	 * @param threeB Number of triple beds in the ward.
	 * @param charges Charges for staying in the ward.
	 * @param respNurse The nurse responsible for the ward.
	 */
	public Ward(int wardID, int oneB, int twoB, int threeB, int occupied, int charges, int respNurse ) {
		this.WardID = wardID;
		this.oneBed = oneB;
		this.twoBed = twoB;
		this.threeBed = threeB;
		this.numberOfPatient = occupied;
		this.charges = charges;
		this.respNurse = respNurse;
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
	 * @return the oneBed
	 */
	public int getOneBed() {
		return oneBed;
	}

	/**
	 * @param oneBed the oneBed to set
	 */
	public void setOneBed(int oneBed) {
		this.oneBed = oneBed;
	}

	/**
	 * @return the twoBed
	 */
	public int getTwoBed() {
		return twoBed;
	}

	/**
	 * @param twoBed the twoBed to set
	 */
	public void setTwoBed(int twoBed) {
		this.twoBed = twoBed;
	}

	/**
	 * @return the threeBed
	 */
	public int getThreeBed() {
		return threeBed;
	}

	/**
	 * @param threeBed the threeBed to set
	 */
	public void setThreeBed(int threeBed) {
		this.threeBed = threeBed;
	}

	/**
	 * @return the charges
	 */
	public int getCharges() {
		return charges;
	}

	/**
	 * @param charges the charges to set
	 */
	public void setCharges(int charges) {
		this.charges = charges;
	}

	/**
	 * @return the respNurse
	 */
	public int getRespNurse() {
		return respNurse;
	}

	/**
	 * @param respNurse the respNurse to set
	 */
	public void setRespNurse(int respNurse) {
		this.respNurse = respNurse;
	}


	public int getNumberOfPatient() {
		return numberOfPatient;
	}

	public void setNumberOfPatient(int numberOfPatient) {
		this.numberOfPatient = numberOfPatient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + WardID;
		result = prime * result + charges;
		result = prime * result + numberOfPatient;
		result = prime * result + oneBed;
		result = prime * result + respNurse;
		result = prime * result + threeBed;
		result = prime * result + twoBed;
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
		Ward other = (Ward) obj;
		if (WardID != other.WardID)
			return false;
		if (charges != other.charges)
			return false;
		if (numberOfPatient != other.numberOfPatient)
			return false;
		if (oneBed != other.oneBed)
			return false;
		if (respNurse != other.respNurse)
			return false;
		if (threeBed != other.threeBed)
			return false;
		if (twoBed != other.twoBed)
			return false;
		return true;
	}
}
