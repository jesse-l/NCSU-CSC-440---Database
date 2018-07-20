package ui;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import objects.*;
import sql.*;

/**
 * The command line input/output for the project
 * @author Rose Isadore
 */
public class FrontEnd {
  /** The scanner to read input from the user*/
  private static Scanner input;
  /** The controller with the DB connection that calls all the SQL classes*/
  private static SqlController controller;
  
  /**
   * Handles the users choices/input to determine what actions to take
   * @param args
   */
  public static void main(String[] args) {
    input = new Scanner(System.in);
    controller = new SqlController();
    System.out.println("Hello! To quit at any time, input 0;");
    System.out.print("What role are you? Doctor (D), Billing Personnel (B), Administrator (A), or Receptionist (R): ");
    char role = input.next().charAt(0);
    if (role == 'a' || role == 'A') {
      adminChoices();
    } else if (role == 'b' || role == 'B') {
      billingChoices();
    } else if (role == 'd' || role == 'D') {
      doctorChoices();
    } else if (role == 'r' || role == 'R') {
      recepChoices();
    } else if (role == '0') {
      System.out.println("Goodbye");
    } else {
      System.out.println("That was not an option.");
    }
    input.close();
  }

  /**
   * If the user is an administrator, they get these prompts to choose what to do
   */
  public static void adminChoices() {
    int choice = -1;
    while (choice != 0) {
      System.out.println("\nWhat would you like to do?:");
      System.out.println("1: Enter information for new staff");
      System.out.println("2: Update information for existing staff");
      System.out.println("3: Delete existing staff from database");
      System.out.println("---");
      System.out.println("4: Enter information for new patient");
      System.out.println("5: Update information for existing patient");
      System.out.println("6: Delete existing patient from database");
      System.out.println("---");
      System.out.println("7: Create new Ward");
      System.out.println("8: Update Ward information");
      System.out.println("9: Delete Ward");
      System.out.println("---");
      System.out.println("10: View Reports");
      choice = input.nextInt();
      switch (choice){
        case 0: break;
        case 1:
          createNewStaff();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 2:
          updateStaff();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 3:
          deleteObject("staff");
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 4:
          createNewPatient();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 5:
          updatePatient();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 6:
          deleteObject("patient");
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 7:
          createNewWard();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 8:
          updateWard();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 9:
          deleteObject("ward");
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 10:
          System.out.println("Which Report?:");
          System.out.println("1: The medical history for a given patient over a given time period");
          System.out.println("2: The current usage status for all wards/beds");
          System.out.println("3: The number of patients per month");
          System.out.println("4: The ward-usage percentage");
          System.out.println("5: All the patients a given doctor is currently responsible for");
          System.out.println("6: Hospital staff grouped by their role");
          int choice2 = input.nextInt();
          switch (choice2){
            case 0: break;
            case 1:
              reportPatientMedRecords();
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            case 2:
              reportWardUsage();
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            case 3:
              reportNumberPatientsPerMonth();
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            case 4:
              reportWardUsagePercentage();
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            case 5:
              reportDoctorCurrentPatients();
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            case 6:
              printStaff(controller.staffByGroupJob());
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
            default: 
              System.out.print("That was not an option");
              System.out.println("Press 1 to continue");
              input.nextInt();
              break;
          }
          break;
        default: 
          System.out.print("That was not an option");
          break;
      }
    }
    
  }

/**
 * If the user is a Billing Personnel, these are the prompts they 
 * use to determine what actions to take
 */
public static void billingChoices() {
    int choice = -1;
    while (choice != 0) {
      System.out.println("What would you like to do?:");
      System.out.println("1: Generate new bill for a patient");
      System.out.println("2: Update a bill for a patient");
      choice = input.nextInt();
      switch (choice){
        case 0: break;
        case 1:
          createNewBill();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 2:
          updateBill();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        default: 
          System.out.print("That was not an option");
          break;
      }
    }
  }

  /**
   * If the user is a Doctor, these are the prompts they 
   * use to determine what actions to take
   */
  public static void doctorChoices() {
    int choice = -1;
    while (choice != 0) {
      System.out.println("What would you like to do?:");
      System.out.println("1: Create medical Record for patient treatment");
      System.out.println("2: Update a Medical Record for a Patient");
      System.out.println("3: Report the medical history for a given patient");
      System.out.println("4: Report Current Patients for Doctor");
      choice = input.nextInt();
      switch (choice){
        case 0: break;
        case 1:
          createNewMR();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 2:
          updateMR();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 3:
          reportPatientMedRecords();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 4:
          reportDoctorCurrentPatients();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        default: 
          System.out.print("That was not an option");
          break;
      }
    }
  }
  
  /**
   * If the user is a Receptionist, these are the prompts they 
   * use to determine what actions to take
   */
  public static void recepChoices() {
    int choice = -1;
    while (choice != 0) {
      System.out.println("What would you like to do?:");
      System.out.println("1: Check-In patient");
      System.out.println("---");
      System.out.println("2: Check-Out patient");
      System.out.println("---");
      System.out.println("3: Create medical Record for Check-in");
      System.out.println("4: Update Check-in Medical Record for a Patient");
      
      choice = input.nextInt();
      switch (choice){
        case 0: break;
        case 1:
          checkIn();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 2:
          checkOut();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 3:
          createNewMRCheckIn();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        case 4:
          updateMRCheckIn();
          System.out.println("Press 1 to continue");
          input.nextInt();
          break;
        default: 
          System.out.print("That was not an option");
          break;
      }
    }
  }
  
  /**
   * Checks a patient out of a ward.
   * Calls the controller to perform the action of the check-out
   */
  private static boolean releaseWard(int wid) {
    boolean success = controller.checkOutWard(wid);
    if(success) {
      System.out.println("Patient successfully checked out.");
      return true;
    } else {
      System.out.println("Check-out was unsuccessful.");
      return false;
    }
  }
  
  /**
   * Checks a patient into a ward.
   * Calls the controller to perform the action of the check-in
   */
  private static boolean assignWard(int wid) {
  	boolean success = controller.checkInWard(wid);
  	if(success) {
  	  System.out.println("Patient successfully checked in.");
  	  return true;
  	} else {
  	  System.out.println("Check-in was unsuccessful.");
  	  return false;
  	}
  }
  
  /**
   * Checks which wards are available based on bed size
   */
  private static void checkWards() {
    System.out.print("What bed-size is perferred for the Ward? (0, 1, 2, or 3; 0 for all Wards): ");
    int bedsCount = input.nextInt();
    printWards(controller.selectWardByBeds(bedsCount));
  }
  
  /**
   * Checks in a patient
   */
  private static void checkIn() {
	  System.out.print("Is this an existing patient? (Y/N)");
      String response = input.next();
      if (response.equalsIgnoreCase("N")) {
    	  createNewPatient();
      } else {
    	  System.out.print("Please enter Patient ID: ");
    	  int pID = input.nextInt();
    	  System.out.print("Please enter Start Date(mm/dd/yyyy): ");
    	  String bdate = input.next();
    	  while(!checkdate(bdate)){
    		  System.out.println("Please enter valid date");
    		  System.out.print("Begin date(mm/dd/yyyy):  ");
    		  input.nextLine();
    		  bdate = input.nextLine();
    	  }
    	  System.out.print("Please enter Registration Fee: ");
    	  int fee = input.nextInt();
    	  checkWards();
    	  System.out.print("What ward would like to check the patient into? (Give ward ID): ");
    	  int wid = input.nextInt();
    	  if (assignWard(wid)) {
    		  CheckIn in = new CheckIn(pID, wid, bdate, null, fee);
    		  controller.addNewObject(in);
    	  }
      }
  }
  
  /**
   * Checks in a patient
   */
  private static void checkOut() {
	  System.out.print("Enter CheckInID: ");
      int cID = input.nextInt();
      CheckIn c = controller.getCheckIn(cID);
      System.out.print("Enter End Date(mm/dd/yyyy): ");
      String endDate = input.next();
      while(!checkdate(endDate)){
		  System.out.println("Please enter valid date");
		  System.out.print("Begin date(mm/dd/yyyy):  ");
		  input.nextLine();
		  endDate = input.nextLine();
	  }
      c.setEndDate(endDate);
      int wid = c.getWardID();
      if(releaseWard(wid)) {
    	  controller.updateObject(c);
      }
  }

  /**
   * Helper method to assign fields from the user to an Staff object
   * and call the controller to add it to the database.
   */
  private static void createNewStaff() {
    System.out.println("Input information for the following fields:");
    System.out.print("Name: ");
    input.nextLine();
    String name = input.nextLine();
    System.out.print("Age: ");
    int age = input.nextInt();
    System.out.print("Gender: ");
    String gender = input.next();
    System.out.print("Job Title: ");
    input.nextLine();
    String jobTitle = input.nextLine();
    System.out.print("Phone Number: ");
    String phoneNumber = input.next();
    System.out.print("Address: ");
    input.nextLine();
    String address = input.nextLine();
    System.out.print("Department: ");
    String department = input.nextLine();
    System.out.print("Professional Title: ");
    String professionalTitle = input.nextLine();
    Staff newStaff = new Staff(name, age, gender, jobTitle, phoneNumber, address, department, professionalTitle);
    boolean worked = controller.addNewObject(newStaff);
    if(worked) {
      System.out.println("New staff member successfully added to the system.");
    } else {
      System.out.println("Create was unsuccessful.");
    }
  }

  /**
   * Helper method to assign fields from the user to an Patient object
   * and call the controller to add it to the database.
   */
  private static void createNewPatient() {
    System.out.println("Input information for the following fields:");
    System.out.print("Name: ");
    input.nextLine();
    String name = input.nextLine();
    System.out.print("Gender: ");
    String gender = input.next();
    System.out.print("Date of Birth: ");
    String dob = input.next();
    System.out.print("Phone Number: ");
    String phoneNumber = input.next();
    System.out.print("Address: ");
    input.nextLine();
    String address = input.nextLine();
    System.out.print("SSN: ");
    int ssn = input.nextInt();
    Patient newPatient = new Patient(name, dob, ssn, gender, null, phoneNumber, address);
    boolean worked = controller.addNewObject(newPatient);
    if(worked) {
      System.out.println("New patient successfully added to the system.");
    } else {
      System.out.print("Create was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Ward object
   * and call the controller to add it to the database.
   */
  private static void createNewWard() {
    System.out.println("Input information for the following fields:");
    System.out.print("Number of one-beds: ");
    int oneB = input.nextInt();
    System.out.print("Number of two-beds: ");
    int twoB = input.nextInt();
    System.out.print("Number of three-beds: ");
    int threeB = input.nextInt();
    System.out.print("Nurse (staff ID) assigned to the ward: ");
    int nurse = input.nextInt();
    Ward newWard = new Ward(oneB, twoB, threeB, 0, 0, nurse);
    boolean worked = controller.addNewObject(newWard);
    if(worked) {
      System.out.println("New ward successfully added to the system.");
    } else {
      System.out.println("Create was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Medical Record object
   * and call the controller to add it to the database.
   * This is used by Doctors to create records for treatments and tests.
   */
  private static void createNewMR() {
    System.out.println("Input any relevant information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("Staff ID (for attending doctor): ");
    int sid = input.nextInt();
    System.out.print("Start Date: ");
    String sDate = input.next();
    System.out.print("End Date: ");
    String eDate = input.next();
    System.out.print("Perscription: ");
    input.nextLine();
    String persription = input.nextLine();
    System.out.print("Diagnosis: ");
    String diagnosis  = input.nextLine();
    System.out.print("Test: ");
    String test  = input.nextLine();
    System.out.print("Test Results: ");
    String result  = input.nextLine();
    System.out.print("Treatment: ");
    String treatment  = input.nextLine();
    System.out.print("Consulting Fee: ");
    float cFee = input.nextFloat();
    System.out.print("Test Fee: ");
    float tFee = input.nextFloat();
    System.out.print("Treatment Fee: ");
    float trFee = input.nextFloat();
    System.out.print("Referred Specialist (Staff ID): ");
    int specialists = input.nextInt();
    MedicalRecord newMR = new MedicalRecord(pid, sDate, eDate, sid, persription,
        diagnosis, test, result, treatment, cFee, tFee, trFee, specialists);
    try {
    	controller.getConnection().setAutoCommit(false);
		boolean worked = controller.addNewObject(newMR);
		if(worked) {
		  controller.getConnection().commit();;
		  System.out.println("New Medical Record successfully added to the system.");
		} else {
		  controller.getConnection().rollback();;
		  System.out.println("Create was unsuccessful.");
		}
	} catch (SQLException e) {
		if (controller.getConnection() != null) {
			try {
				controller.getConnection().rollback();
				controller.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}
	}
  }
  /**
   * Helper method to assign fields from the user to an Medical Record object
   * and call the controller to add it to the database.
   * This one is used by Receptionists to create them only for check-ins.
   */
  private static void createNewMRCheckIn() {
    System.out.println("Input information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("Staff ID (for attending doctor): ");
    int sid = input.nextInt();
    System.out.print("Start Date: ");
    String sDate = input.next();
    System.out.print("Consulting Fee: ");
    float cFee = input.nextFloat();
    MedicalRecord newMR = new MedicalRecord(pid, sDate, null, sid, null,
        null, null, null, null, cFee, 0, 0, 0);
    boolean worked = controller.addNewObject(newMR);
    if(worked) {
      System.out.println("New Medical Record successfully added to the system.");
    } else {
      System.out.println("Create was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an BillingRecord object
   * and call the controller to add it to the database.
   */
  private static void createNewBill() {
    System.out.println("Input information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("SSN: ");
    int ssn = input.nextInt();
    System.out.print("Payment Type: ");
    String payType = input.next();
    System.out.print("Card Number: ");
    String cardNum = input.next();
    System.out.print("Billing Address: ");
    input.nextLine();
    String bAddress = input.nextLine();
    System.out.print("Amount Due: ");
    int amount = input.nextInt();
    System.out.print("Visit Date: ");
    String visitDate = input.next();
    BillingRecord newBill = new BillingRecord(pid, ssn, payType, cardNum, bAddress ,amount, visitDate);
    try {
    	controller.getConnection().setAutoCommit(false);
	    boolean worked = controller.addNewObject(newBill);
	    if(worked) {
	      controller.getConnection().commit();
	      System.out.println("New Billing Record successfully added to the system.");
	    } else {
	      controller.getConnection().rollback();
	      System.out.println("Create was unsuccessful.");
	    }
	    controller.getConnection().setAutoCommit(true);
    } catch (SQLException e) {
    	if (controller.getConnection() != null) {
			try {
				controller.getConnection().rollback();
				controller.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}
    }
    
  }
  
  /**
   * Helper method to assign fields from the user to an Staff object
   * and call the controller to update it in the database.
   */
  private static void updateStaff() {
    System.out.print("Give the Id for the staff member whose information you would like to update: ");
    int id = input.nextInt();
    System.out.println("Input information for the following fields:");
    System.out.print("Name: ");
    input.nextLine();
    String name = input.nextLine();
    System.out.print("Age: ");
    int age = input.nextInt();
    System.out.print("Gender: ");
    String gender = input.next();
    System.out.print("Job Title: ");
    input.nextLine();
    String jobTitle = input.nextLine();
    System.out.print("Phone Number: ");
    String phoneNumber = input.next();
    System.out.print("Address: ");
    input.nextLine();
    String address = input.nextLine();
    System.out.print("Department: ");
    String department = input.nextLine();
    System.out.print("Professional Title: ");
    String professionalTitle = input.nextLine();
    Staff oldStaff = new Staff(id, name, age, gender, jobTitle, phoneNumber, address, department, professionalTitle);
    boolean worked = controller.updateObject(oldStaff);
    if(worked) {
      System.out.println("Staff member's information was updated successfully.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Patient object
   * and call the controller to update it in the database.
   */
  private static void updatePatient() {
    System.out.print("Give the Id for the patient whose information you would like to update: ");
    int id = input.nextInt();
    System.out.println("Input information for the following fields:");
    System.out.print("Name: ");
    input.nextLine();
    String name = input.nextLine();
    System.out.print("Gender: ");
    String gender = input.next();
    System.out.print("Date of Birth: ");
    String dob = input.next();
    System.out.print("Phone Number: ");
    String phoneNumber = input.next();
    System.out.print("Address: ");
    input.nextLine();
    String address = input.nextLine();
    System.out.print("SSN: ");
    int ssn = input.nextInt();
    System.out.print("Status: ");
    input.nextLine();
    String status = input.nextLine();
    Patient oldPatient = new Patient(id, name, dob, ssn, gender, status, phoneNumber, address);
    boolean worked = controller.updateObject(oldPatient);
    if(worked) {
      System.out.println("Patient's information was updated successfully.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Ward object
   * and call the controller to update it in the database.
   */
  private static void updateWard() {
    System.out.print("Give the Id for the ward whose information you would like to update: ");
    int id = input.nextInt();
    System.out.println("Input information for the following fields:");
    System.out.print("Number of one-beds: ");
    int oneB = input.nextInt();
    System.out.print("Number of two-beds: ");
    int twoB = input.nextInt();
    System.out.print("Number of three-beds: ");
    int threeB = input.nextInt();
    System.out.print("Nurse (staff ID) assigned to the ward: ");
    int nurse = input.nextInt();
    Ward oldWard = new Ward(id, oneB, twoB, threeB, 0, 0, nurse);
    boolean worked = controller.updateObject(oldWard);
    if(worked) {
      System.out.println("Ward's information was updated successfully.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Medical Record object
   * and call the controller to update it in the database.
   */
  private static void updateMR() {
    System.out.print("For what Medical Record? (Give ID): ");
    int mid = input.nextInt();
    System.out.println("Input any relevant information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("Staff ID (for attending doctor): ");
    int sid = input.nextInt();
    System.out.print("Start Date: ");
    String sDate = input.next();
    System.out.print("End Date: ");
    String eDate = input.next();
    System.out.print("Perscription: ");
    input.nextLine();
    String persription = input.nextLine();
    System.out.print("Diagnosis: ");
    String diagnosis  = input.nextLine();
    System.out.print("Test: ");
    String test  = input.nextLine();
    System.out.print("Test Results: ");
    String result  = input.nextLine();
    System.out.print("Treatment: ");
    String treatment  = input.nextLine();
    System.out.print("Consulting Fee: ");
    float cFee = input.nextFloat();
    System.out.print("Test Fee: ");
    float tFee = input.nextFloat();
    System.out.print("Treatment Fee: ");
    float trFee = input.nextFloat();
    System.out.print("Referred Specialist (Staff ID): ");
    int specialists = input.nextInt();
    MedicalRecord newMR = new MedicalRecord(mid, pid, sDate, eDate, sid, persription,
        diagnosis, test, result, treatment, cFee, tFee, trFee, specialists);
    boolean worked = controller.updateObject(newMR);
    if(worked) {
      System.out.println("Medical updated in the system.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an Medical Record object
   * and call the controller to update it in the database.
   */
  private static void updateMRCheckIn() {
    System.out.print("For what Medical Record? (Give ID): ");
    int mid = input.nextInt();
    System.out.println("Input information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("Staff ID (for attending doctor): ");
    int sid = input.nextInt();
    System.out.print("Start Date: ");
    String sDate = input.next();
    System.out.print("Consulting Fee: ");
    float cFee = input.nextFloat();
    MedicalRecord newMR = new MedicalRecord(mid, pid, sDate, null, sid, null,
        null, null, null, null, cFee, 0, 0, 0);
    boolean worked = controller.updateObject(newMR);
    if(worked) {
      System.out.println("Medical Record updated in the system.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to assign fields from the user to an BillingRedcord object
   * and call the controller to update it in the database.
   */
  private static void updateBill() {
    System.out.print("For what Billing Record? (Give ID): ");
    int bid = input.nextInt();
    System.out.println("Input information for the following fields:");
    System.out.print("Patient ID: ");
    int pid = input.nextInt();
    System.out.print("SSN: ");
    int ssn = input.nextInt();
    System.out.print("Payment Type: ");
    String payType = input.next();
    System.out.print("Card Number: ");
    String cardNum = input.next();
    System.out.print("Billing Address: ");
    input.nextLine();
    String bAddress = input.nextLine();
    System.out.print("Amount Due: ");
    int amount = input.nextInt();
//    System.out.print("Record: ");
//    input.nextLine();
//    String record = input.nextLine();
    System.out.print("Visit Date: ");
    String visitDate = input.next();
    BillingRecord newBill = new BillingRecord(bid, pid, ssn, payType, cardNum, 
        bAddress, amount, null, visitDate);
    boolean worked = controller.updateObject(newBill);
    if(worked) {
      System.out.println("Billing Record updated in the system.");
    } else {
      System.out.println("Update was unsuccessful.");
    }
  }
  
  /**
   * Helper method to call the controller to delete an object from the database
   * @param objType the type of object the is to be deleted
   *        p = patient
   *        w = ward
   *        s = staff
   */
  private static void deleteObject(String objType) {
    boolean worked = false;
    if(objType.equals("patient")) {
      System.out.println("What is the patient ID of the patient you wish to delete?");
      int patientID = input.nextInt();
      worked = controller.deleteObject(patientID, 'p');
    } else if(objType.equals("ward")) {
      System.out.println("What is the ward ID of the ward you wish to delete?");
      int wardID = input.nextInt();
      worked = controller.deleteObject(wardID, 'w');
    } else if(objType.equals("staff")) {
      System.out.println("What is the staff ID of the staff member you wish to delete?");
      int staffID = input.nextInt();
      worked = controller.deleteObject(staffID, 's');
    }
    if(worked) {
      System.out.println("Deleted successfully.");
    } else {
      System.out.println("Deletion failed.");
    }
  }
  
  /**
   * Ask for the patient and time period needed to report a patient's medical record
   */
  private static void reportPatientMedRecords() {
    System.out.print("For which patient (Give patient ID)?  ");
    int pID = input.nextInt();
    System.out.print("Begin date(mm/dd/yyyy):  ");
    String sDate = input.next();
    while(!checkdate(sDate)){
      System.out.println("Please enter valid date");
      System.out.print("Begin date(mm/dd/yyyy):  ");
      sDate = input.next();
    }
    System.out.print("End date(mm/dd/yyyy):  ");
    String eDate = input.next();
    while(!checkdate(eDate)){
      System.out.println("Please enter valid date");
      System.out.print("End date(mm/dd/yyyy):  ");
      eDate = input.next();
    }
    ArrayList<MedicalRecord> mr = controller.medRecordsByDate(pID, sDate, eDate);
    printMedicalRecords(mr);
  }
  
  /**
   * Helper methods that gets the patients a current doctor is assigned to
   * Calls the controller to get the information and calls a second helper method
   * to print the information
   */
  private static void reportDoctorCurrentPatients() {
	System.out.print("For which Doctor (Give doctor ID)?  ");
	int dID = input.nextInt();
	ArrayList<Patient> p = controller.getDoctorCurrentPatients(dID);
    printPatients(p);
	
  }

  /**
   * Helper methods that gets the patients a current doctor is assigned to
   * Calls the controller to get the information and calls a second helper method
   * to print the information
   */
  private static void reportWardUsagePercentage() {
	System.out.print("For which Ward (Give ward ID)?  ");
	int wID = input.nextInt();
    int p = controller.getWardPercentage(wID);
	printPrecentage(wID, p);
	
  }

  /**
   * Helper methods that gets the number of patients per month
   * Calls the controller to get the information
   */
  private static void reportNumberPatientsPerMonth() {
	  System.out.println("Please enter month period");
	  System.out.print("Begin date(mm/dd/yyyy):  ");
	  String bDate = input.nextLine();
	  while(!checkdate(bDate)){
		  System.out.println("Please enter valid date");
		  System.out.print("Begin date(mm/dd/yyyy):  ");
		  bDate = input.next();
	  }
	  System.out.print("End date(mm/dd/yyyy):  ");
	  String eDate = input.next();
	  while(!checkdate(eDate)){
		  System.out.println("Please enter valid date");
		  System.out.print("End date(mm/dd/yyyy):  ");
		  eDate = input.next();
	  }
	  int p = controller.getPatientsPerMonth(bDate, eDate);
	  printNumberPerMonth(bDate,eDate,p);
  }

private static boolean checkdate(String date) {
	if (date.length() == 10 && date.indexOf('/') == 2) {
		String day = date.substring(0,2);
		String mmyyyy = date.substring(3);
		if (date.indexOf('/') == 2) {
			String month = mmyyyy.substring(0,date.indexOf('/'));
			String year = mmyyyy.substring(3);
			if ((Integer.parseInt(day) > 0 && Integer.parseInt(day) < 13)
					&& (Integer.parseInt(month) > 0 && Integer.parseInt(day) < 32)
					&& (Integer.parseInt(year) > 0)) {
				return true;
			}
		}
	}
	return false;
}

/**
   * Helper methods that gets the usage of wards
   * Calls the controller to get the information
   */
  private static void reportWardUsage() {
	  printWards(controller.selectWardByBeds(0));
  }

  /**
   * Prints a table of all the Staff objects in an array
   * @param staff an array of the staff members
   */
  private static void printStaff(ArrayList<Staff> staff) {

    System.out.printf("%8s   %20s   %10s   %3s   %20s   %30s   %15s   %15s", 
    		"Role", "Name", "ID", "Age", "Phone Number", "Address","Department", "Professional Title");
    System.out.println("\n-----------------------------------------------------------------------------------"
    		+ "-----------------");
    for(int i = 0; i < staff.size(); i++) {
      Staff s = staff.get(i);
      System.out.printf("%8s   %20s   %10d   %3s   %20s   %30s   %15s   %15s", 
    		  s.getJobTitle(),s.getName(),s.getStaffID(),s.getAge(),s.getPhoneNumber(),s.getAddress(),s.getDepartment(),
    		  s.getProfessionalTitle());
      System.out.println("\n-----------------------------------------------------------------------------------"
      		+ "-----------------");
    }
  }
  
  /**
   * Prints a table of all the Medical Records objects in an array
   * @param mr an array of medical records
   */
  private static void printMedicalRecords(ArrayList<MedicalRecord> mr) {
    System.out.printf("%9s   %10s   %10s   %10s   %9s   %20s   %25s   %20s   %11s   %20s   %14s   %10s   %13s   %19s", 
    		"Record id", "Patient id", "Start Date", "End Date", "Doctor id",
    		"Prescription", "Diagnosis", "Test", "Test Result", "Treatment", "Consultant Fee",
    		"Test Fee", "Treatment Fee", "Referred Specialist");
    System.out.println("\n--------------------------------------------------------------------------------------"
    		+ "------------------------------------------------------------------------------");
    for(int i = 0; i < mr.size(); i++) {
      MedicalRecord m = mr.get(i);
      System.out.printf("%9d   %10d   %10s   %10s   %9s   %20s   %25s   %20s   %11s   %20s   %14f   %10f   %13f   %19d", 
    		  m.getRecordID(),m.getPatientID(),m.getStartDate(),m.getEndDate(), m.getResponsibleDoctor(),
    		  m.getPrescription(),m.getDiagnostic(),m.getTest(),m.getResult(),m.getTreatment(),m.getConsultFee(),
    		  m.getTestFee(),m.getTreatmentFee(),m.getSpecialist());
      System.out.println("\n--------------------------------------------------------------------------------------"
      		+ "------------------------------------------------------------------------------");
    }
  }
  /**
   * Prints a table of all the Patient objects in an array
   * @param ps an array of patients
   */
  private static void printPatients(ArrayList<Patient> ps) {
    System.out.printf("%8s   %20s   %13s   %10s   %6s   %20s   %12s   %30s", 
    		"Patient id", "Name", "Date of Birth", "SSN", "Gender",
    		"Status", "Phone Number", "Address");
    System.out.println("\n----------------------------------------------------------------------------------");
    for(int i = 0; i < ps.size(); i++) {
      Patient p = ps.get(i);
      System.out.printf("%8d   %20s   %13s   %10d   %6s   %20s   %12s   %30s", 
    		  p.getPatientID() , p.getName(),p.getDateofBirth(),p.getSSN(),p.getGender(),p.getStatus(),
              p.getPhoneNumber(), p.getAddress());
      System.out.println("\n----------------------------------------------------------------------------------");
    }
  }
  /**
   * Prints a table of all the Ward objects in an array
   * @param ws an array of wards
   */
  private static void printWards(ArrayList<Ward> ws) {
    System.out.printf("%7s   %17s   %18s   %7s   %13s   %13s   %15s", 
    		"Ward id", "Responsible Nurse", "Number of Patients", "Charges", "One Bed Rooms",
    		"Two Bed Rooms", "Three Bed Rooms");
	System.out.println("\n----------------------------------------------------------------------------------");
    for(int i = 0; i < ws.size(); i++) {
      Ward w = ws.get(i);
      System.out.printf("%7d   %17s   %18d   %7d   %13d   %13d   %15d", w.getWardID(), w.getRespNurse(), w.getNumberOfPatient(), w.getCharges(), w.getOneBed(), w.getTwoBed(), w.getThreeBed());
      System.out.println("\n----------------------------------------------------------------------------------");
    }
  }
  
  private static void printPrecentage(int id, int p) {
	  System.out.printf("%7s   %15s", "Ward id", "Percentage used");
	  System.out.println("\n----------------------------");
	  System.out.printf("%7d   %15d\n", id, p);
  }
  
  private static void printNumberPerMonth(String bDate, String eDate, int p) {
	  System.out.println("Format:\tDate Range, Number of Patients"
		        + "\n---------------------------------");
	  System.out.println(bDate +"-" + eDate +", " + p);
	  System.out.printf("%21s   %17d", "Ward id", "Percentage used");
	  System.out.println("\n---------------------------------------");
	  System.out.printf("%10s-%10s   %17d\n", bDate,eDate, p);
	}
}