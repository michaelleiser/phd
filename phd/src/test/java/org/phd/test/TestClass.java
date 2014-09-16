package org.phd.test;

import java.util.List;
import org.junit.Test;
import junit.framework.TestCase;

public class TestClass extends TestCase {

	@Test
	public void testmethod() {
		Patient p = new Patient();
		p.setFirstname("ABC");
		assertEquals("ABC", p.getFirstname());
	}

	@Test
	public void testmethoddb() {
		List<Patient> patients = Patient.getPatients();
		for (Patient p : patients) {
			System.out.println(p.getFirstname() + p.getLastname());
		}
		assertNotNull(patients.size());
	}
	
	@Test
	public void testlogin() {
		UserData ud = new UserData();
		ud.setName("drx");
		ud.setPassword("12345");
		assertEquals("loggedin", ud.login());
	}
	
	@Test
	public void testRegister(){
		UserData ud = new UserData();
		ud.setName("aa");
		ud.setPassword("123");
		System.out.println(ud.registernew());
		assertEquals("home", ud.registernew());
	}

}
