package objects;

/**
 * Class used for handling Medical Record objects.
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class MedicalRecord {
	private int RecordID;
	private int PatientID;
	private String StartDate;
	private String EndDate;
	private int ResponsibleDoctor;
	private String Prescription;
	private String Diagnostic;
	private String Test;
	private String Result;
	private String Treatment;
	private float ConsultFee;
	private float TestFee;
	private float TreatmentFee;
	private int Specialist;
	
	/**
	 * Method used to create a new Medical Record.
	 * 
	 * @param patientID
	 * @param startDate
	 * @param endDate
	 * @param responsibleDoctor
	 * @param prescription
	 * @param diagnostic
	 * @param test
	 * @param result
	 * @param treatment
	 * @param consultFee
	 * @param testFee
	 * @param treatmentFee
	 * @param specialist
	 */
	public MedicalRecord(int patientID, String startDate, String endDate, int responsibleDoctor, String prescription,
			String diagnostic, String test, String result, String treatment, float consultFee, float testFee,
			float treatmentFee, int specialist) {
		super();
		PatientID = patientID;
		StartDate = startDate;
		EndDate = endDate;
		ResponsibleDoctor = responsibleDoctor;
		Prescription = prescription;
		Diagnostic = diagnostic;
		Test = test;
		Result = result;
		Treatment = treatment;
		ConsultFee = consultFee;
		TestFee = testFee;
		TreatmentFee = treatmentFee;
		Specialist = specialist;
	}

	public MedicalRecord(int recordID, int patientID, String startDate, String endDate, int responsibleDoctor,
			String prescription, String diagnostic, String test, String result, String treatment, float consultFee,
			float testFee, float treatmentFee, int specialist) {
		super();
		RecordID = recordID;
		PatientID = patientID;
		StartDate = startDate;
		EndDate = endDate;
		ResponsibleDoctor = responsibleDoctor;
		Prescription = prescription;
		Diagnostic = diagnostic;
		Test = test;
		Result = result;
		Treatment = treatment;
		ConsultFee = consultFee;
		TestFee = testFee;
		TreatmentFee = treatmentFee;
		Specialist = specialist;
	}



	/**
	 * @return the recordID
	 */
	public int getRecordID() {
		return RecordID;
	}

	/**
	 * @param recordID the recordID to set
	 */
	public void setRecordID(int recordID) {
		RecordID = recordID;
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
	 * @return the responsibleDoctor
	 */
	public int getResponsibleDoctor() {
		return ResponsibleDoctor;
	}

	/**
	 * @param responsibleDoctor the responsibleDoctor to set
	 */
	public void setResponsibleDoctor(int responsibleDoctor) {
		ResponsibleDoctor = responsibleDoctor;
	}

	/**
	 * @return the prescription
	 */
	public String getPrescription() {
		return Prescription;
	}

	/**
	 * @param prescription the prescription to set
	 */
	public void setPrescription(String prescription) {
		Prescription = prescription;
	}

	/**
	 * @return the diagnostic
	 */
	public String getDiagnostic() {
		return Diagnostic;
	}

	/**
	 * @param diagnostic the diagnostic to set
	 */
	public void setDiagnostic(String diagnostic) {
		Diagnostic = diagnostic;
	}

	/**
	 * @return the test
	 */
	public String getTest() {
		return Test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		Test = test;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return Result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		Result = result;
	}

	/**
	 * @return the treatment
	 */
	public String getTreatment() {
		return Treatment;
	}

	/**
	 * @param treatment the treatment to set
	 */
	public void setTreatment(String treatment) {
		Treatment = treatment;
	}

	/**
	 * @return the consultFee
	 */
	public float getConsultFee() {
		return ConsultFee;
	}

	/**
	 * @param consultFee the consultFee to set
	 */
	public void setConsultFee(float consultFee) {
		ConsultFee = consultFee;
	}

	/**
	 * @return the testFee
	 */
	public float getTestFee() {
		return TestFee;
	}

	/**
	 * @param testFee the testFee to set
	 */
	public void setTestFee(float testFee) {
		TestFee = testFee;
	}

	/**
	 * @return the treatmentFee
	 */
	public float getTreatmentFee() {
		return TreatmentFee;
	}

	/**
	 * @param treatmentFee the treatmentFee to set
	 */
	public void setTreatmentFee(float treatmentFee) {
		TreatmentFee = treatmentFee;
	}

	/**
	 * @return the specialist
	 */
	public int getSpecialist() {
		return Specialist;
	}

	/**
	 * @param specialist the specialist to set
	 */
	public void setSpecialist(int specialist) {
		Specialist = specialist;
	}
	
}
