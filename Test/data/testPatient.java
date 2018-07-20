package data;

import objects.Patient;

/**
 * Class used to create Patient #1
 * 
 * @author Jesse Liddle - jaliddl2
 */
public class testPatient {
	private int PID = 3001;
	private int ssn = 2131;
	private String name = "John";
	private String DoB = "02/22/1986";
	private int age = 31;
	private String gender = "f";
	private String phone = "513";
	private String address = "81 ABC St , NC 27";
	private String status = "Treatment complete";
	
	public testPatient() {
		// Does nothing
	}
	
	/**
	 * Method used to return a patient with the information of the test
	 * patient provided to us.
	 * 
	 * @return A test patient
	 */
	public Patient getTestPatient() {
		return new Patient(PID, name, DoB, ssn, gender, status, phone, address);
	}
}
