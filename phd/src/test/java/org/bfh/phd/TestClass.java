package org.bfh.phd;

import java.util.List;
import java.util.Random;

import org.bfh.phd.questionary.*;
import org.junit.Test;

import junit.framework.TestCase;

public class TestClass extends TestCase {
	
	private String doctorname = "drx";
	private String statisticianname = "statx";
	private String password = "12345";

	@Test
	public void testExample() {
		Patient p = new Patient();
		p.setFirstname("ABC");
		assertEquals("ABC", p.getFirstname());
	}
	
//	@Test
//	public void testLogin(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		assertTrue(lc.getLoggedin());
//	}
//	
//	@Test
//	public void testLoginLogout(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		assertTrue(lc.getLoggedin());
//		lc.logout();
//		assertFalse(lc.getLoggedin());
//	}
//	
//	@Test
//	public void testRegisterNewDoctor(){
//		Random r = new Random();
//		String username = "Dummy" + r.nextInt();
//		String password = "";
//		LoginController l = new LoginController();
//		l.login(username, password);
//		assertFalse(l.getLoggedin());
//		l.registernew(username, password, 1);	// 1 = Doctor, 2 = Statistician
//		l.login(username, password);
//		assertTrue(l.getLoggedin());
//	}
//	
//	
//	
//	@Test
//	public void testGetPatient() {
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		Patient p = lc.getPatient(1);
//		assertTrue(p != null);
//	}
//
//	
//	@Test
//	public void testCreatePatient(){
//		Random r = new Random();
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> listBefore = lc.getPatients();
//		Patient p = new Patient();
//		p.setFirstname("Dummy" + r.nextInt());
//		lc.createPatient(p);
//		List<Patient> listAfter = lc.getPatients();
//		assertTrue(listBefore.size() + 1 == listAfter.size());
//	}
//	
//	@Test
//	public void testSearchPatient(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> listnull = lc.searchPatient("DummyWhichDoesNotExist");
//		assertTrue(listnull.size() == 0);
//		
//		List<Patient> list = lc.searchPatient("Dummy");
//		for(Patient p : list){
//			System.out.println("sp " + p);
//		}
//		assertTrue(list.size() != 0);
//	}
//	
//	@Test
//	public void testEditPatient(){
//		Random r = new Random();
//		String firstname = "UPDATE" + r.nextInt();
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		Patient p = lc.getPatient(1);
//		p.setFirstname(firstname);
//		lc.updatePatient(p);
//		LoginController lc1 = new LoginController();
//		Patient p1 = lc1.getPatient(1);
//		assertEquals(firstname, p1.getFirstname());
//	}
//	
//	
//	
//
//	@Test
//	public void testGetPatientData(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		lc.setActivePatient(lc.getPatient(1));
//		List<PatientData> list = lc.getPatientDatas();
//		for(PatientData pd : list){
//			System.out.println("gpds " + pd);
//		}
//		assertTrue(list.size() >= 1);
//		lc.setPatientdataid(3);
//		List<PatientData> list2 = lc.getPatientData();
//		for(PatientData pd : list2){
//			System.out.println("gpd " + pd);
//		}
//		assertTrue(list2.size() == 1);
//	}
//	
//	@Test
//	public void testCreatePatientData(){
//		Random r = new Random();
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		lc.setActivePatient(lc.getPatient(1));
//		List<PatientData> listBefore = lc.getPatientDatas();
//		PatientData pd = new PatientData();
//		pd.setFirstdata("knee");
//		pd.setSeconddata("Dummy" + r.nextInt());
//		Patient p = lc.getPatient(1);
//		lc.createPatientData(p, pd);
//		List<PatientData> listAfter = lc.getPatientDatas();
//		assertTrue(listBefore.size() + 1 == listAfter.size());
//	}
//	
//	@Test
//	public void testSearchPatientData(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<PatientData> listElbow = lc.searchPatientData("elbow");
//		for(PatientData pd : listElbow){
//			System.out.println("spd " + pd);
//		}
//		assertTrue(listElbow.size() >= 0);
//		List<PatientData> listKnee = lc.searchPatientData("knee");
//		for(PatientData pd : listKnee){
//			System.out.println("spd " + pd);
//		}
//		assertTrue(listKnee.size() >= 0);
//	}
//	
//	
//	@Test
//	public void testSearchPatientData2(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Elbow> listElbow = lc.searchPatientData2("elbow");
//		for(Elbow pd : listElbow){
//			System.out.println("spd " + pd.getAnswers());
//		}
//		assertTrue(listElbow.size() >= 0);
//		List<Knee> listKnee = lc.searchPatientData3("knee");
//		for(Knee pd : listKnee){
//			System.out.println("spd " + pd.getAnswers());
//		}
//		assertTrue(listKnee.size() >= 0);
//	}
//	
//	
//
//	@Test
//	public void testEditPatientData(){
//		Random r = new Random();
//		String seconddata = "UPDATE" + r.nextInt();
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		lc.setPatientdataid(1);
////		lc.setActivePatient(lc.getPatient().get(0));
//		List<PatientData> l = lc.getPatientData();
//		PatientData pd = l.get(0);
//		pd.setSeconddata(seconddata);
//		lc.updatePatientData(pd);
//		PatientData pd1 = l.get(0);
//		assertEquals(seconddata, pd1.getSeconddata());
//	}
//	
//	
//	
//	
//	
//	@Test
//	public void testSearchPatientDataForStatistics(){
//		// TODO
//	}
//	
//	@Test
//	public void testCreateGroup(){
//		// TODO
//	}
//	
//	@Test
//	public void testEditGroup(){
//		// TODO
//	}
//	
//	@Test
//	public void testPrintKneeQuestionnary(){
//		LoginController l = new LoginController();
//		List<Question> questions = l.getQuestions("knee");
//		List<Answer> answers = l.getAnswers("knee", 34);
//		Knee k = new Knee();
//		k.setQuestions(questions);
//		k.setAnswers(answers);
//		for(Question q : k.getQuestions()){
//			System.out.println("Frage " + q);
//			List<String> pos = q.getAnswerPossibilities();
//			if(pos != null){
//				for(String s : pos){
//					System.out.println("-> " + s);
//				}			
//			}
//		}
//		
//		for(Answer a : k.getAnswers()){
//			System.out.println("Antwort " + a);
//		}
//		assertTrue(questions.size() == answers.size());
//	}
//	
//	@Test
//	public void testAddKneeAnswer() {
//		LoginController lc = new LoginController();
//		
//		Knee k = new Knee();
//		Answer a = new AnswerString();
//		a.addAnswer("ans_s1");
//		Answer b = new AnswerString();
//		b.addAnswer("ans_s2");
//		Answer c = new AnswerRadioButton();
//		c.addAnswer("ans_rb1");
//		c.addAnswer("ans_rb2");
//		Answer d = new AnswerCheckbox();
//		d.addAnswer("ans_cb1");
//		d.addAnswer("ans_cb2");
//		Answer e = new AnswerCheckbox();
//		e.addAnswer("ans_cb3");
//		
//		k.addAnswer(a);
//		k.addAnswer(b);
//		k.addAnswer(c);
//		k.addAnswer(d);
//		k.addAnswer(e);
//		
//		List<Knee> listBefore = lc.getAnswers("knee");
//		lc.addAnswer("knee", k);
//		List<Knee> listAfter = lc.getAnswers("knee");
//		assertTrue(listBefore.size() + 1 == listAfter.size());
//	}
//
//
//	
//	@Test
//	public void testGetPatientsWithRestricitons() {
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> list = lc.getPatientsWithRestrictions();
//		assertTrue(list.size() >= 1);
//		
////		LoginController lc2 = new LoginController();
////		lc2.login(statisticianname, password);
////		List<Patient> list2 = lc2.getPatientsWithRestrictions();
////		assertTrue(list2.size() == 0);
//	}
//	@Test
//	public void testSerachPatientsWithRestricitons() {
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> list = lc.searchPatientWithRestrictions("ummy");
//		assertTrue(list.size() >= 1);
//	}
//
//	
//	@Test
//	public void testGetStaff(){
//		LoginController lc = new LoginController();
//		List<Staff> staffs = lc.getStaffs();
//		assertTrue(staffs.size() >= 0);
//	}
//	
//	@Test
//	public void testGroup(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		lc.setActivePatient(lc.getPatient(1));
//		List<Group> list = lc.getGroups();
//		for(Group g : list){
//			System.out.println("->" + g);
//		}
//	}
//	
//	@Test
//	public void testSearchStaff(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Staff> list = lc.getStaffs("");
//		for(Staff g : list){
//			System.out.println("->" + g);
//		}
//	}
//	
//	@Test
//	public void testIsOwner(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> list = lc.getPatients();
//		for(Patient p : list){
//			System.out.println(">>>>"+p.getPatientid() + p+lc.isOwner(p));
//		}
//	}
//	
//	
//	
//	@Test
//	public void testGetOnlyRWAccessPatients(){
//		LoginController lc = new LoginController();
//		lc.login(doctorname, password);
//		List<Patient> list = lc.getPatientsWithRWAccess();
//		for(Patient p : list){
//			System.out.println(">>>>"+p.getPatientid() + p+lc.isOwner(p));
//		}
//	}
	
	@Test
	public void testGetStaffsNotInGroup(){
		LoginController lc = new LoginController();
		lc.login(doctorname, password);
		lc.setActivePatient(lc.getPatient(1));

		List<Staff> staffs = lc.getStaffsNotInGroup("um");
		for(Staff s : staffs){
			System.out.println("-->"+s);
		}
//		lc.addStaffToGroup(lc.getStaffs().get(7));
	}
	
	
}
