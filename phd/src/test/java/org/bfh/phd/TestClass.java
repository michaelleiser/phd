//package org.bfh.phd;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import org.bfh.crypto.MYCRYPTO;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.junit.Test;
//
//import junit.framework.TestCase;
//
//public class TestClass extends TestCase {
//	
//	private String doctorname = "drx";
//	private String statisticianname = "statx";
//	private String password = "8cb2237d0679ca88db6464eac60da96345513964"; // sha1 of 12345
////	private String password = MYCRYPTO.SHA1("12345");
//	private String department = "Bern";
//	
//	@Test
//	public void testExample() throws ParseException {
//		JSONObject json = new JSONObject();		//JSONObject = Map ; JSONArray = List
//		json.put("firstname", "ABC");
//		json.put("lastname", "DEF");
////		Patient p = new Patient();
//		p.setPersonalData(json.toString());
//
//		JSONParser parser = new JSONParser();
//		Object o = parser.parse(p.getPersonalData());
//
//		JSONObject json2 = (JSONObject) o;
//		assertEquals("ABC", json2.get("firstname"));
//		assertEquals("DEF", json2.get("lastname"));
//	}
//	
////	@Test
////	public void testLogin(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		assertTrue(lc.getLoggedin());
////	}
////	
////	@Test
////	public void testLoginLogout(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		assertTrue(lc.getLoggedin());
////		
////		lc.logout();
////		assertFalse(lc.getLoggedin());
////	}
////
////	@Test	// TODO Sometimes fails, don't know why
////	public void testCreateNewGroup(){
////		Random r = new Random();
////		String departmentname = "Dummy" + r.nextInt();
////		Department d = new Department();
////		d.setName(departmentname);
////		LoginController l = new LoginController();
////		l.setDepartmentselected(departmentname);
////		l.login(doctorname, password);
////		assertFalse(l.getLoggedin());
////		
////		Staff s = new Staff();
////		s.setName(doctorname);
////		s.setPassword(password);
////		s.setRole(1);
////		l.registernew(s, d, "an encrypted group key");
////		
////		l.setDepartmentselected(departmentname);
////		l.login(doctorname, password);
////		assertTrue(l.getLoggedin());
////	}
////
////	@Test	// TODO Sometimes fails, don't know why
////	public void testRegisterNewDoctorAndActivate(){
////		Random r = new Random();
////		String username = "Dummy" + r.nextInt();
////		Staff s = new Staff();
////		s.setName(username);
////		s.setPassword(password);
////		s.setRole(1);
////		
////		LoginController l = new LoginController();
////		l.setDepartmentselected(department);
////		l.login(username, password);
////		assertFalse(l.getLoggedin());
////		
////		l.registernew(s);
////		l.login(username, password);
////		assertFalse(l.getLoggedin());
////
////		l.login(doctorname, password);
////		l.activateStaff(l.getStaffs(username).get(0), "an encrypted group key");
////		l.logout();
////		
////		l.setDepartmentselected(department);
////		l.login(username, password);
////		assertTrue(l.getLoggedin());
////	}
////	
////	@Test
////	public void testGetPatient() {
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		Patient p = lc.getPatient(1);
////		assertTrue(p != null);
////	}
////	
////	@Test
////	public void testCreatePatient(){
////		Random r = new Random();
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		List<Patient> listBefore = lc.getPatients();
////		Patient p = new Patient();
////		
////		JSONObject json = new JSONObject();
////		json.put("firstname", "Dummy" + r.nextInt());
////		json.put("lastname", "Dummy" + r.nextInt());
////		
////		p.setPersonalData(json.toString());
////
////		lc.createPatient(p);
////		List<Patient> listAfter = lc.getPatients();
////		assertTrue(listBefore.size() + 1 == listAfter.size());
////	}
////	
////	@Test
////	public void testSearchPatient(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
//////		List<Patient> emptylist = lc.searchPatient("DummyWhichDoesNotExist");
//////		assertTrue(emptylist.size() == 0);
//////		List<Patient> list = lc.searchPatient("Dummy");
//////		assertTrue(list.size() != 0);
////		List<Patient> list = lc.getPatients();
////		// search patient or filtering has to be done in JavaScript
////		assertTrue(list.size() != 0);
////	}
////	
////	@Test
////	public void testEditPatient() throws ParseException{
////		Random r = new Random();
////		String firstname = "UPDATE" + r.nextInt();
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		Patient p = lc.getPatient(101);
////		JSONParser parser = new JSONParser();
////		Object o = parser.parse(p.getPersonalData());
////		JSONObject json = (JSONObject) o;
////		json.put("firstname", firstname);
////		p.setPersonalData(json.toString());
////		lc.updatePatient(p);
////
////		LoginController lc1 = new LoginController();
////		Patient p1 = lc1.getPatient(101);
////		
////		JSONParser parser1 = new JSONParser();
////		Object o1 = parser1.parse(p.getPersonalData());
////		JSONObject json1 = (JSONObject) o1;
////
////		assertEquals(firstname, json1.get("firstname"));
////	}
////	
////	
////	
////
//////	@Test
//////	public void testGetPatientData(){
//////		LoginController lc = new LoginController();
//////		lc.login(doctorname, password);
//////		lc.setActivePatient(lc.getPatient(1));
//////		List<PatientData> list = lc.getPatientDatas();
//////		for(PatientData pd : list){
//////			System.out.println("gpds " + pd);
//////		}
//////		assertTrue(list.size() >= 1);
//////		lc.setPatientdataid(3);
//////		List<PatientData> list2 = lc.getPatientData();
//////		for(PatientData pd : list2){
//////			System.out.println("gpd " + pd);
//////		}
//////		assertTrue(list2.size() == 1);
//////	}
////	
//////	@Test
//////	public void testCreatePatientData(){
//////		Random r = new Random();
//////		LoginController lc = new LoginController();
//////		lc.login(doctorname, password);
//////		lc.setActivePatient(lc.getPatient(1));
//////		List<PatientData> listBefore = lc.getPatientDatas();
//////		PatientData pd = new PatientData();
//////		pd.setFirstdata("knee");
//////		pd.setSeconddata("Dummy" + r.nextInt());
//////		Patient p = lc.getPatient(1);
//////		lc.createPatientData(p, pd);
//////		List<PatientData> listAfter = lc.getPatientDatas();
//////		assertTrue(listBefore.size() + 1 == listAfter.size());
//////	}
////	
////	@Test		// TODO will change soon
////	public void testSearchPatientData(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		List<PatientData> listElbow = lc.searchPatientData("elbow");
////		for(PatientData pd : listElbow){
////			System.out.println("spd " + pd);
////		}
////		assertTrue(listElbow.size() >= 0);
////		List<PatientData> listKnee = lc.searchPatientData("knee");
////		for(PatientData pd : listKnee){
////			System.out.println("spd " + pd);
////		}
////		assertTrue(listKnee.size() >= 0);
////	}
////	
////	
//////	@Test
//////	public void testSearchPatientData2(){
//////		LoginController lc = new LoginController();
//////		lc.login(doctorname, password);
//////		List<Elbow> listElbow = lc.searchPatientData2("elbow");
//////		for(Elbow pd : listElbow){
//////			System.out.println("spd " + pd.getAnswers());
//////		}
//////		assertTrue(listElbow.size() >= 0);
//////		List<Knee> listKnee = lc.searchPatientData3("knee");
//////		for(Knee pd : listKnee){
//////			System.out.println("spd " + pd.getAnswers());
//////		}
//////		assertTrue(listKnee.size() >= 0);
//////	}
////	
////	
////
//////	@Test
//////	public void testEditPatientData(){
//////		Random r = new Random();
//////		String seconddata = "UPDATE" + r.nextInt();
//////		LoginController lc = new LoginController();
//////		lc.login(doctorname, password);
//////		lc.setPatientdataid(1);
////////		lc.setActivePatient(lc.getPatient().get(0));
//////		List<PatientData> l = lc.getPatientData();
//////		PatientData pd = l.get(0);
//////		pd.setSeconddata(seconddata);
//////		lc.updatePatientData(pd);
//////		PatientData pd1 = l.get(0);
//////		assertEquals(seconddata, pd1.getSeconddata());
//////	}
//////	
//////	
//////	
//////	
//////	
//////	@Test
//////	public void testSearchPatientDataForStatistics(){
//////		// TODO
//////	}
//////
//////	@Test
//////	public void testPrintKneeQuestionnary(){
//////		LoginController l = new LoginController();
//////		List<Question> questions = l.getQuestions("knee");
//////		List<Answer> answers = l.getAnswers("knee", 34);
//////		Knee k = new Knee();
//////		k.setQuestions(questions);
//////		k.setAnswers(answers);
//////		for(Question q : k.getQuestions()){
//////			System.out.println("Frage " + q);
//////			List<String> pos = q.getAnswerPossibilities();
//////			if(pos != null){
//////				for(String s : pos){
//////					System.out.println("-> " + s);
//////				}			
//////			}
//////		}
//////		
//////		for(Answer a : k.getAnswers()){
//////			System.out.println("Antwort " + a);
//////		}
//////		assertTrue(questions.size() == answers.size());
//////	}
//////	
//////	@Test
//////	public void testAddKneeAnswer() {
//////		LoginController lc = new LoginController();
//////		
//////		Knee k = new Knee();
//////		Answer a = new AnswerString();
//////		a.addAnswer("ans_s1");
//////		Answer b = new AnswerString();
//////		b.addAnswer("ans_s2");
//////		Answer c = new AnswerRadioButton();
//////		c.addAnswer("ans_rb1");
//////		c.addAnswer("ans_rb2");
//////		Answer d = new AnswerCheckbox();
//////		d.addAnswer("ans_cb1");
//////		d.addAnswer("ans_cb2");
//////		Answer e = new AnswerCheckbox();
//////		e.addAnswer("ans_cb3");
//////		
//////		k.addAnswer(a);
//////		k.addAnswer(b);
//////		k.addAnswer(c);
//////		k.addAnswer(d);
//////		k.addAnswer(e);
//////		
//////		List<Knee> listBefore = lc.getAnswers("knee");
//////		lc.addAnswer("knee", k);
//////		List<Knee> listAfter = lc.getAnswers("knee");
//////		assertTrue(listBefore.size() + 1 == listAfter.size());
//////	}
////
////	
////	@Test
////	public void testGetStaffs(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		List<Staff> staffs = lc.getStaffs();
////		assertTrue(staffs.size() >= 0);
////	}
////	
////	@Test
////	public void testSearchStaffsFromDepartment(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		List<Staff> staffs = lc.getStaffs("");
//////		for(Staff s : staffs){
//////			System.out.println("->" + s);
//////		}
////		assertTrue(staffs.size() >= 0);
////	}
////	
////	@Test
////	public void testIsOwner(){
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		List<Patient> list = lc.getPatients();
////		assertTrue(lc.isOwner(list.get(0)));
////	}
////	
////	@Test
////	public void testReadAccess() throws ParseException{
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		Patient p = lc.getPatient(102);
////		
////		JSONParser parser = new JSONParser();
////		Object o = parser.parse(p.getPersonalData());
////		JSONObject json = (JSONObject) o;
////		assertTrue(json.get("firstname").equals("test"));
////		
////		json.put("firstname", "this is not accepted");
////		p.setPersonalData(json.toString());
////		lc.updatePatient(p);
////		lc.logout();
////		
////		LoginController lc1 = new LoginController();
////		lc1.setDepartmentselected(department);
////		lc1.login(doctorname, password);
////		Patient p1 = lc1.getPatient(102);
////		JSONParser parser1 = new JSONParser();
////		Object o1 = parser1.parse(p1.getPersonalData());
////		JSONObject json1 = (JSONObject) o1;
////		assertTrue(json1.get("firstname").equals("test"));
////	}
////
////	@Test
////	public void testWriteAccess() throws ParseException{
////		LoginController lc = new LoginController();
////		lc.setDepartmentselected(department);
////		lc.login(doctorname, password);
////		Patient p = lc.getPatient(103);
////		
////		JSONParser parser = new JSONParser();
////		Object o = parser.parse(p.getPersonalData());
////		JSONObject json = (JSONObject) o;
////		assertTrue(json.get("firstname").equals("test"));
////		
////		json.put("firstname", "test");
////		p.setPersonalData(json.toString());
////		lc.updatePatient(p);
////		lc.logout();
////		
////		LoginController lc1 = new LoginController();
////		lc1.setDepartmentselected(department);
////		lc1.login(doctorname, password);
////		Patient p1 = lc1.getPatient(103);
////		JSONParser parser1 = new JSONParser();
////		Object o1 = parser1.parse(p1.getPersonalData());
////		JSONObject json1 = (JSONObject) o1;
////		assertTrue(json1.get("firstname").equals("test"));
////	}
////	
//////	@Test
//////	public void testAESEncryption(){
//////		MYCRYPTO mycrypto = new MYCRYPTO();
//////		String message = "This is encrypted session key ;)";
//////		String encrypted = mycrypto.AESencode(message);
//////		String decrypted = mycrypto.AESdecode(encrypted);
//////		assertEquals(message, decrypted);
//////	}
//////
//////	@Test
//////	public void testRSAEncryption(){
//////		MYCRYPTO mycrypto = new MYCRYPTO();
//////		String message = "This is encrypted session key ;)";
//////		String encrypted = mycrypto.RSAencode(message);
//////		String decrypted = mycrypto.RSAdecode(encrypted);
//////		assertEquals(message, decrypted);
//////	}
//
//}
