package objects;

/**
 * This class is used to creating Billing Record objects.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class BillingRecord {
	/** The ID number of the record */
	private int BillingID;
	/** The Patient ID number who the record is for */
	private int PatientID;
	/** private int MedicalRecordID; */
	/** The SSN number of the person responsible for payment */
	private int SSN;
	/** The method of payment for the bill */
	private String PaymentType;
	/** If using card, the card number for the bill */
	private String CardNumber;
	/** The amount of the bill */
	private int amount;
	/** The date of the visit being billed for. */
	private String VisitDate;
	/** Patinet's Billing Address*/
	private String BillingAddress;
	/** Patinet's Record*/
	private String Record;
	
	/**
	 * Method used to create a Billing Record that already exists
	 * in the database system.
	 * 
	 * @param BillingID The ID number for the billing record
	 * @param PatientID ID number for the patient
	 * @param SSN SSN of the person responsible for payment
	 * @param PaymentType Method of payment
	 * @param CardNumber Credit card number if using card
	 * @param amount The amount due for the bill
	 * @param VisitDate The date the visit occurred on.
	 */
	public BillingRecord( int BillingID, int PatientID, int SSN,
			String PaymentType, String CardNumber, String BillingAddress, int amount, String Record, String VisitDate ) {
		this.BillingID = BillingID;
		this.PatientID = PatientID;
		this.SSN = SSN;
		this.PaymentType = PaymentType;
		this.CardNumber = CardNumber;
		this.setBillingAddress(BillingAddress);
		this.amount = amount;
		this.setRecord(Record);
		this.VisitDate = VisitDate;
	}
	
	/**
	 * Method used to create new billing records, as the record ID is 
	 * auto generated when inserted into the table.
	 * 
	 * @param PatientID ID number for the patient
	 * @param SSN SSN of the person responsible for payment
	 * @param PaymentType Method of payment
	 * @param CardNumber Credit card number if using card
	 * @param amount The amount due for the bill
	 * @param VisitDate The date the visit occurred on.
	 */
	public BillingRecord( int PatientID, int SSN, String PaymentType,
			String CardNumber, String BillingAddress, int amount, String VisitDate ) {
		this.PatientID = PatientID;
		this.SSN = SSN;
		this.PaymentType = PaymentType;
		this.CardNumber = CardNumber;
		this.BillingAddress = BillingAddress;
		this.amount = amount;
		this.VisitDate = VisitDate;
	}

	/**
	 * @return the billingID
	 */
	public int getBillingID() {
		return BillingID;
	}

	/**
	 * @param billingID the billingID to set
	 */
	public void setBillingID(int billingID) {
		BillingID = billingID;
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
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return PaymentType;
	}

	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return CardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the visitDate
	 */
	public String getVisitDate() {
		return VisitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(String visitDate) {
		VisitDate = visitDate;
	}

	public String getRecord() {
		return Record;
	}

	public void setRecord(String record) {
		Record = record;
	}

	public String getBillingAddress() {
		return BillingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		BillingAddress = billingAddress;
	}
}