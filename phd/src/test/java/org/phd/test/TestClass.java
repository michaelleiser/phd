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
		LoginController lc = new LoginController();
		lc.login("drx", "12345");
		assertTrue(lc.getLoggedin());
	}
	
	@Test
	public void testLoginLogout(){
		LoginController lc = new LoginController();
		lc.login("dry", "12345");
		assertTrue(lc.getLoggedin());
		lc.logout();
		assertFalse(lc.getLoggedin());
	}
	
	@Test
	public void testRegisterNewDoctor(){
		Random r = new Random();
		String username = "Dummy" + r.nextInt();
		String password = "";
		LoginController l = new LoginController();
		l.login(username, password);
		assertFalse(l.getLoggedin());
		l.registernew(username, password, 1);	// 1 = Doctor, 2 = Statistician
		l.login(username, password);
		assertTrue(l.getLoggedin());
	}
	
	
	
	@Test
	public void testGetPatient() {
		LoginController lc = new LoginController();
		lc.setPatientid(1);
		List<Patient> list = lc.getPatient();
		assertTrue(list != null);
	}
	
	@Test
	public void testCreatePatient(){
		Random r = new Random();
		LoginController lc = new LoginController();
		List<Patient> listBefore = lc.getPatients();
		Patient p = new Patient();
		p.setFirstname("Dummy" + r.nextInt());
		lc.createPatient(p);
		List<Patient> listAfter = lc.getPatients();
		assertTrue(listBefore.size() + 1 == listAfter.size());
	}
	
	@Test
	public void testSearchPatient(){
		LoginController lc = new LoginController();
		List<Patient> listnull = lc.searchPatient("DummyWhichDoesNotExist");
		assertTrue(listnull.size() == 0);
		
		List<Patient> list = lc.searchPatient("Dummy");
		for(Patient p : list){
			System.out.println("sp " + p);
		}
		assertTrue(list.size() != 0);
	}
	
	@Test
	public void testEditPatient(){
		Random r = new Random();
		String firstname = "UPDATE" + r.nextInt();
		LoginController lc = new LoginController();
		lc.setPatientid(1);
		List<Patient> l = lc.getPatient();
		Patient p = l.get(0);
		p.setFirstname(firstname);
		lc.updatePatient(p);
		Patient p1 = l.get(0);
		assertEquals(firstname, p1.getFirstname());
	}
	
	
	

	@Test
	public void testGetPatientData(){
		LoginController lc = new LoginController();
		lc.setPatientid(1);
		List<PatientData> list = lc.getPatientDatas();
		for(PatientData pd : list){
			System.out.println("gpds " + pd);
		}
		lc.setPatientdataid(3);
		List<PatientData> list2 = lc.getPatientData();
		for(PatientData pd : list2){
			System.out.println("gpd " + pd);
		}
	}
	
	@Test
	public void testCreatePatientData(){
		Random r = new Random();
		LoginController lc = new LoginController();
		lc.setPatientid(1);
		List<PatientData> listBefore = lc.getPatientDatas();
		PatientData pd = new PatientData();
		pd.setFirstdata("knee");
		pd.setSeconddata("Dummy" + r.nextInt());
		List<Patient> list = lc.getPatient();
		Patient p = list.get(0);
		lc.createPatientData(pd, p);
		List<PatientData> listAfter = lc.getPatientDatas();
		assertTrue(listBefore.size() + 1 == listAfter.size());
	}
	
	@Test
	public void testSearchPatientData(){
		LoginController lc = new LoginController();
		List<PatientData> listElbow = lc.searchPatientData("elbow");
		for(PatientData pd : listElbow){
			System.out.println("spd " + pd);
		}
		assertTrue(listElbow.size() != 0);
		List<PatientData> listKnee = lc.searchPatientData("knee");
		for(PatientData pd : listKnee){
			System.out.println("spd " + pd);
		}
		assertTrue(listKnee.size() != 0);
	}

	@Test
	public void testEditPatientData(){
		Random r = new Random();
		String seconddata = "UPDATE" + r.nextInt();
		LoginController lc = new LoginController();
		lc.setPatientdataid(1);
		List<PatientData> l = lc.getPatientData();
		PatientData pd = l.get(0);
		pd.setSeconddata(seconddata);
		lc.updatePatientData(pd);
		PatientData pd1 = l.get(0);
		assertEquals(seconddata, pd1.getSeconddata());
	}
	
	
	
	
	
	@Test
	public void testSearchPatientDataForStatistics(){
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
	

}
