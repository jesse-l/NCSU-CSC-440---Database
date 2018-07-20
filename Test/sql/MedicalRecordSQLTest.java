/**
 * 
 */
package sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.MedicalRecord;
import objects.Patient;
import objects.Staff;

/**
 * @author Josue
 *
 */
public class MedicalRecordSQLTest {
	private SqlController ctrl;
	private StaffSQL ssql;
	private PatientSQL psql;
	private MedicalRecordSQL sql;
	private Staff s1 = new Staff(1,"Bob", 21, "M", "Doctor", "323", "123 SeeMe Road","","");
	private Staff s2 = new Staff(2,"Jill", 30, "F", "Doctor", "213", "134 ABC St","","");
	private Patient p1 = new Patient(1, "name", "DateofBirth", 111, "m", "status", "phoneNumber", "address");
	private Patient p2 = new Patient(2, "name2", "DateofBirth2", 222, "f", "In Hospital", "phoneNumber2", "address2");
	private MedicalRecord mr1 = new MedicalRecord(1, 1, "startDate", "endDate", 1, "p", "d", "t", "r", "treatment", 400, 500, 600, 2);
	private MedicalRecord mr2 = new MedicalRecord(2, 1, "startDate2", "endDate2", 1, "p2", "d2", "t2", "r2", "treatment2", 400, 500, 600, 2);
	private MedicalRecord mr3 = new MedicalRecord(3, 2, "startDate", "endDate", 2, "p", "d", "t", "r", "treatment", 400, 500, 600, 1);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new SqlController();
		ssql = new StaffSQL(ctrl.getConnection());
		psql = new PatientSQL(ctrl.getConnection());
		sql = new MedicalRecordSQL(ctrl.getConnection());
		sql.clearTable();
		psql.clearTable();
		ssql.clearTable();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sql.clearTable();
		psql.clearTable();
		ssql.clearTable();
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#getAllMedicalRecords()}.
	 */
	@Test
	public void testGetAllMedicalRecords() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#getAllRecordsForPatient(int)}.
	 */
	@Test
	public void testGetAllRecordsForPatient() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
		
		assertTrue( sql.getAllRecordsForPatient(1).size() == 2 );
		assertTrue( sql.getAllRecordsForPatient(2).size() == 1 );
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#getRecordsOfDoctor(int)}.
	 */
	@Test
	public void testGetRecordsOfDoctor() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
		
		assertTrue( sql.getRecordsOfDoctor(1).size() == 2 );
		assertTrue( sql.getRecordsOfDoctor(2).size() == 1 );
	}
	
	/**
	 * Test method for {@link sql.MedicalRecordSQL#getRecordsOfDoctor(int)}.
	 */
	@Test
	public void testGetPatientRecordsOfDoctor() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
		
		assertEquals( sql.getPatientRecordsOfDoctor(1).size(), 0 );
		assertTrue( sql.getPatientRecordsOfDoctor(2).size() == 1 );
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#addNewRecord(objects.MedicalRecord)}.
	 */
	@Test
	public void testAddNewRecord() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
		
		MedicalRecord mr4 = new MedicalRecord(3, 2, "startDate2", "endDate2", 2, "p2", "d2", "t2", "r2", "treatment2", 400, 500, 600, 1);
		assertTrue( sql.addNewRecord(mr4) );
		assertTrue( sql.getAllMedicalRecords().size() == 4 );
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#updateRecord(objects.MedicalRecord)}.
	 */
	@Test
	public void testUpdateRecord() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		
		mr1.setConsultFee(0);
		assertTrue( sql.updateRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().get(0).getConsultFee() == 0 );
	}

	/**
	 * Test method for {@link sql.MedicalRecordSQL#deleteRecord(objects.MedicalRecord)}.
	 */
	@Test
	public void testDeleteRecord() {
		ssql.addNewStaff(s1);
		ssql.addNewStaff(s2);
		psql.addNewPatient(p1);
		psql.addNewPatient(p2);
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
		assertTrue( sql.addNewRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.addNewRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.addNewRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 3 );
		assertTrue( sql.deleteRecord(mr1) );
		assertTrue( sql.getAllMedicalRecords().size() == 2 );
		assertTrue( sql.deleteRecord(mr2) );
		assertTrue( sql.getAllMedicalRecords().size() == 1 );
		assertTrue( sql.deleteRecord(mr3) );
		assertTrue( sql.getAllMedicalRecords().size() == 0 );
	}

}
