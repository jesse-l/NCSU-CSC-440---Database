package sql;

import static org.junit.Assert.assertTrue;

import objects.Staff;

public class testStaffSQL {
	private SqlController ctrl;
	private StaffSQL sql;
	private Staff s1 = new Staff(101, "Bob", 21, "M", "Nurse", "323", "123 SeeMe Road","","");
	private Staff s2 = new Staff("Jill", 30, "F", "Doctor", "213", "134 ABC St","","");
	
	public testStaffSQL() {
		ctrl = new SqlController();
		
	}
	
	public void testInsert() {
		assertTrue( true );
	}
}
