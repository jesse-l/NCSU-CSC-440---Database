/**
 * 
 */
package sql;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.Staff;
import objects.Ward;


/**
 * @author Josue
 *
 */
public class WardSQLTest {
	private SqlController ctrl;
	private WardSQL sql;
	private StaffSQL nsql;
	private Staff nurse = new Staff(1,"nurse",30,"F","nurse","32","Street name","nursing","n");
	private Ward w1 = new Ward(1,1,0,0,1,100,1);
	private Ward w2 = new Ward(2,0,0,1,1,300,1);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ctrl = new SqlController();
		sql = new WardSQL(ctrl.getConnection());
		nsql = new StaffSQL(ctrl.getConnection());
		sql.clearTable();
		nsql.clearTable();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		sql.clearTable();
		nsql.clearTable();
	}


	/**
	 * Test method for {@link sql.WardSQL#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
	}

	/**
	 * Test method for {@link sql.WardSQL#getNoneOccupiedThreeBedWards()}.
	 */
	@Test
	public void testGetNoneOccupiedThreeBedWards() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		assertTrue( sql.getNoneOccupiedThreeBedWards().size() == 1 );
	}

	/**
	 * Test method for {@link sql.WardSQL#getNoneOccupiedOneBedWards()}.
	 */
	@Test
	public void testGetNoneOccupiedOneBedWards() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		assertTrue( sql.getNoneOccupiedOneBedWards().size() == 0 );
	}

	/**
	 * Test method for {@link sql.WardSQL#getNoneOccupiedTwoBedWards()}.
	 */
	@Test
	public void testGetNoneOccupiedTwoBedWards() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		assertTrue( sql.getNoneOccupiedTwoBedWards().size() == 0 );
	}

	/**
	 * Test method for {@link sql.WardSQL#getByID(int)}.
	 */
	@Test
	public void testGetByID() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addNewWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addNewWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		assertTrue( sql.getByID(1).equals(w1) );
		assertTrue( sql.getByID(2).equals(w2) );
	}

	/**
	 * Test method for {@link sql.WardSQL#getByNurse(int)}.
	 */
	@Test
	public void testGetByNurse() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		assertTrue( sql.getByNurse(1).size() == 2 );
	}

	/**
	 * Test method for {@link sql.WardSQL#addNewWard(objects.Ward)}.
	 */
	@Test
	public void testAddNewWard() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addNewWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.addNewWard(w2) );
		assertTrue( sql.selectAll().size() == 2 );
		
		Ward w3 = new Ward(3,0,2,0,1,200,1);
		assertTrue( sql.addNewWard(w3) );
		assertTrue( sql.selectAll().size() == 3 );
	}

	/**
	 * Test method for {@link sql.WardSQL#updateWard(objects.Ward)}.
	 */
	@Test
	public void testUpdateWard() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addNewWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		
		w1.setCharges(1);;// Change Password
		assertTrue( sql.updateWard(w1) );
		
		assertTrue( sql.selectAll().get(0).getCharges() == 1 );
		
	}

	/**
	 * Test method for {@link sql.WardSQL#deleteWard(int)}.
	 */
	@Test
	public void testDeleteWard() {
		nsql.addStaff(nurse);
		assertTrue( sql.selectAll().size() == 0 );
		assertTrue( sql.addNewWard(w1) );
		assertTrue( sql.selectAll().size() == 1 );
		assertTrue( sql.deleteWard(1) );
		assertTrue( sql.selectAll().size() == 0 );
	}

	/**
	 * Test method for {@link sql.WardSQL#checkInWard(int)}.
	 */
	@Test
	public void testCheckInWard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sql.WardSQL#checkOutWard(int)}.
	 */
	@Test
	public void testCheckOutWard() {
		fail("Not yet implemented");
	}

}
