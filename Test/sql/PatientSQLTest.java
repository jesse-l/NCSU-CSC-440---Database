/**
 * 
 */
package sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.Patient;

/**
 * @author Josue
 *
 */
public class PatientSQLTest {
	private SqlController ctrl;
	private PatientSQL sql;
	private Patient p1 = new Patient(1, "name", "DateofBirth", 111, "f", "status", "phoneNumber", "address");
	private Patient p2 = new Patient(2, "name2", "DateofBirth2", 222, "m", "status2", "phoneNumber2", "address2");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new SqlController();
		sql = new PatientSQL(ctrl.getConnection());
		sql.clearTable();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sql.clearTable();
	}

	/**
	 * Test method for {@link sql.PatientSQL#getAllPatients()}.
	 */
	@Test
	public void testGetAllPatients() {
		assertTrue( sql.getAllPatients().size() == 0 );
		assertTrue( sql.addNewPatient(p1) );
		assertTrue( sql.getAllPatients().size() == 1 );
		assertTrue( sql.addNewPatient(p2) );
		assertTrue( sql.getAllPatients().size() == 2 );
	}

	/**
	 * Test method for {@link sql.PatientSQL#getPatientsByID(int)}.
	 */
	@Test
	public void testGetPatientsByID() {
		assertTrue( sql.getAllPatients().size() == 0 );
		assertTrue( sql.addNewPatient(p1) );
		assertTrue( sql.getAllPatients().size() == 1 );
		assertTrue( sql.addNewPatient(p2) );
		assertTrue( sql.getAllPatients().size() == 2 );
		
		assertTrue( sql.getPatientsByID(1).equals(p1) );
		assertTrue( sql.getPatientsByID(2).equals(p2) );
	}

	/**
	 * Test method for {@link sql.PatientSQL#updatePatient(objects.Patient)}.
	 */
	@Test
	public void testUpdatePatient() {
		assertTrue( sql.getAllPatients().size() == 0 );
		assertTrue( sql.addNewPatient(p1) );
		assertTrue( sql.getAllPatients().size() == 1 );
		
		p1.setStatus("ok");// Change Password
		assertTrue( sql.updatePatient(p1) );
		
		assertTrue( sql.getAllPatients().get(0).getStatus().equals("ok") );
	}

	/**
	 * Test method for {@link sql.PatientSQL#addNewPatient(objects.Patient)}.
	 */
	@Test
	public void testAddNewPatient() {
		assertTrue( sql.getAllPatients().size() == 0 );
		assertTrue( sql.addNewPatient(p1) );
		assertTrue( sql.getAllPatients().size() == 1 );
		assertTrue( sql.addNewPatient(p2) );
		assertTrue( sql.getAllPatients().size() == 2 );
		Patient p3 = new Patient(3, "name3", "DateofBirth3", 333, "m", "status3", "phoneNumber3", "address3");
		assertTrue( sql.addNewPatient(p3) );
		assertTrue( sql.getAllPatients().size() == 3 );
	}

	/**
	 * Test method for {@link sql.PatientSQL#deleteRecord(int)}.
	 */
	@Test
	public void testDeleteRecord() {
		assertTrue( sql.getAllPatients().size() == 0 );
		assertTrue( sql.addNewPatient(p1) );
		assertTrue( sql.getAllPatients().size() == 1 );
		assertTrue( sql.deleteRecord(1) );
		assertTrue( sql.getAllPatients().size() == 0 );
	}

}
