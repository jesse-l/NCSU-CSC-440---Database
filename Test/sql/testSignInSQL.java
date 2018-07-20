package sql;

import objects.SignIn;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testSignInSQL {
	private SqlController ctrl;
	private SignInSQL sql;
	private SignIn SI1 = new SignIn("bob", "123", "idiot");
	private SignIn SI2 = new SignIn("Mike", "1A", "Doctor");
	
	public testSignInSQL() {
		ctrl = new SqlController();
		sql = new SignInSQL( ctrl.getConnection() );
	}
	
	@After
	@Before
	public void clearTable() {
		sql.clearTable();
	}
	
	@Test
	public void testSelectAll() {
		assertTrue( sql.getAllSignIn().size() == 0 );
		assertTrue( sql.addSignIn(SI1) );
		assertTrue( sql.getAllSignIn().size() == 1 );
		assertTrue( sql.addSignIn(SI2) );
		assertTrue( sql.getAllSignIn().size() == 2 );
	}
	
	@Test
	public void testDelete() {
		assertTrue( sql.getAllSignIn().size() == 0 );
		assertTrue( sql.addSignIn(SI1) );
		assertTrue( sql.getAllSignIn().size() == 1 );
		assertTrue( sql.addSignIn(SI2) );
		assertTrue( sql.getAllSignIn().size() == 2 );
		
		assertTrue( sql.deleteSignIn(SI1) );
		assertTrue( sql.getAllSignIn().size() == 1 );
		assertTrue( sql.deleteSignIn(SI2) );
		assertTrue( sql.getAllSignIn().size() == 0 );
		
		assertTrue( sql.getAllSignIn().size() == 0 );
		assertTrue( sql.addSignIn(SI2) );
		assertTrue( sql.getAllSignIn().size() == 1 );
		assertTrue( sql.addSignIn(SI1) );
		assertTrue( sql.getAllSignIn().size() == 2 );
		
		
	}
	
	@Test
	public void testUpdate() {
		assertTrue( sql.getAllSignIn().size() == 0 );
		assertTrue( sql.addSignIn(SI1) );
		assertTrue( sql.getAllSignIn().size() == 1 );
		
		SI1.setPassword("bcd");// Change Password
		assertTrue( sql.updateSignIn(SI1) );
		
		assertTrue( sql.getAllSignIn().get(0).getPassword().equals("bcd") );
		
		assertTrue( sql.addSignIn(SI2) );
		assertTrue( sql.getAllSignIn().size() == 2 );
		
		SI2.setPassword("Test");
		assertTrue( sql.updateSignIn(SI1) );
		
		SignIn s = sql.getSignIn( SI2.getUserName() ).get(0);
		assertTrue( s.getPassword().equals("Test") );
		
		
	}
}
