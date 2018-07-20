package sql;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.BillingRecord;
import objects.Patient;
import sql.BillingRecordSQL;
import sql.SqlController;

/**
 * @author Josue
 *
 */
public class BillingRecordSQLTest {
	private BillingRecordSQL sql;
	private SqlController ctrl;
	private PatientSQL psql;
	private BillingRecord b = new BillingRecord(1, 1, 1233334567, "card", "123456789", "123 street", 25, "none", "today");
	private BillingRecord c = new BillingRecord(2, 2, 1314523461, "card", "363453452", "1223 street", 35, "none", "tomorrow");
	private BillingRecord d = new BillingRecord(3, 2, 1314523461, "card", "363453452", "1223 street", 35, "none", "tomorrow");
	private Patient p = new Patient(1, "a", "b", 0, "f", "c", "d", "e");
	private Patient p2 = new Patient(2, "c", "d", 0, "f", "c", "d", "e");

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new SqlController();
		sql = new BillingRecordSQL(ctrl.getConnection());
		psql = new PatientSQL(ctrl.getConnection());
		sql.clearTable();
		psql.clearTable();
		psql.addNewPatient(p);
		psql.addNewPatient(p2);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sql.clearTable();
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#BillingRecordSQL(java.sql.Connection)}.
	 */
	@Test
	public void testBillingRecordSQL() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(b);
		assertTrue(sql.selectAll().size()==1);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==2);
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(b);
		assertTrue(sql.selectAll().size()==1);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==2);
		
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#selectByPatient(objects.Patient)}.
	 */
	@Test
	public void testSelectByPatient() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(b);
		assertTrue(sql.selectAll().size()==1);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==2);
		
		assertTrue(sql.selectByPatient(p).size()==1);
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#addNewBillingRecord(objects.BillingRecord)}.
	 */
	@Test
	public void testAddNewBillingRecord() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(b);
		assertTrue(sql.selectAll().size()==1);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==2);
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#addBillingRecord(objects.BillingRecord)}.
	 */
	@Test
	public void testAddBillingRecord() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==1);
		sql.addBillingRecord(d);
		assertTrue(sql.selectAll().size()==2);
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#updateBillingRecord(objects.BillingRecord)}.
	 */
	@Test
	public void testUpdateBillingRecord() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==1);
		sql.updateBillingRecord(d);
		assertTrue(sql.selectAll().size()==1);
		
	}

	/**
	 * Test method for {@link sql.BillingRecordSQL#deleteBillingRecord(int)}.
	 */
	@Test
	public void testDeleteBillingRecord() {
		assertTrue(sql.selectAll().size()==0);
		sql.addNewBillingRecord(c);
		assertTrue(sql.selectAll().size()==1);
		sql.deleteBillingRecord(1);
		assertTrue(sql.selectAll().size()==0);
	}



}
