package org.phd.test;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import junit.framework.TestCase;

public class TestClass extends TestCase {

	@Test
	public void testExample() {
		Patient p = new Patient();
		p.setFirstname("ABC");
		assertEquals("ABC", p.getFirstname());
	}
	
	@Test
	public void testLogin(){
		LoginController l = new LoginController();
		l.login("drx", "12345");
		assertTrue(l.getLoggedin());
	}
	
	@Test
	public void testLoginLogout(){
		LoginController l = new LoginController();
		l.login("dry", "12345");
		assertTrue(l.getLoggedin());
		l.logout();
		assertFalse(l.getLoggedin());
	}
	
	@Test
	public void testRegisterNewDoctor(){
		Random r = new Random();
		String username = "Dummy" + r.nextInt();
		String password = "123";
		LoginController l = new LoginController();
		l.login(username, password);
		assertFalse(l.getLoggedin());
		l.registernew(username, password, "doctor");
		l.login(username, password);
		assertTrue(l.getLoggedin());
	}
	
	@Test
	public void testCreatePatient(){
		
	}

	@Test
	public void testSearchPatient() {
		LoginController l = new LoginController();
		l.setPatientid(1);
		List<Patient> list = l.getPatient();
		assertTrue(list != null);
	}
	
	@Test
	public void testCreatePatientData(){
		// TODO
	}
	
	@Test
	public void testSearchPatientData(){
		// TODO		
	}
	
	@Test
	public void testEditPatientData(){
		// TODO
	}
	
	@Test
	public void testCreateGroup(){
		// TODO
	}
	
	@Test
	public void testEditGroup(){
		// TODO
	}
	
	@Test
	public void testSearchPatientDataForStatistics(){
		// TODO
	}
}
