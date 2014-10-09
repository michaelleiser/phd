package org.bfh.phd;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bfh.phd.questionary.Answer;
import org.bfh.phd.questionary.AnswerCheckbox;
import org.bfh.phd.questionary.AnswerRadioButton;
import org.bfh.phd.questionary.AnswerString;
import org.bfh.phd.questionary.Elbow;
import org.bfh.phd.questionary.Knee;
import org.bfh.phd.questionary.Question;
import org.bfh.phd.questionary.QuestionCheckbox;
import org.bfh.phd.questionary.QuestionRadioButton;
import org.bfh.phd.questionary.QuestionString;


@ManagedBean(name = "entityManager", eager = true)
@SessionScoped
public class EntityManager {
	
	private List<Staff> staff;	
	private List<Patient> patient;
	private List<PatientData> patientdata;
	private List<Group> group;
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private Paginator p1 = new Paginator(10);
	private Paginator p2 = new Paginator(10);
	private Paginator p3 = new Paginator(10);
	
	//Testvariablen
	private String sss = "elbow"; 
	
	public EntityManager(){
		initStaff();
		initPatient();
		initPatientData();
		initGroup();
	}




	public List<Staff> getStaff() {
		return this.staff;
	}
	
	public Staff getStaff(int id) {
		for(Staff s : this.staff){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
	
	public Staff getStaff(String name, String password) {
		for(Staff s : this.staff){
			if(s.getName().equals(name) && s.getPassword().equals(password)){
				return s;
			}
		}
		return null;
	}
	
	public List<Staff> getStaffs(String name) {
		List<Staff> staff = new ArrayList<Staff>();
		for(Staff s : this.staff){
			if(name.equals("") || s.getName().contains(name)){
				staff.add(s);
			}
		}
		return staff;
	}
	public List<Staff> getStaffsNotInGroup(Staff activeUser, Patient activePatient, String name) { // TODO
		List<Staff> staff1 = this.getStaff();

		List<Staff> staff = new ArrayList<Staff>();
		for(Staff s : staff1){
			staff.add(s);
		}
		List<Group> groups = this.getGroups(activeUser, activePatient);
		
		for(Iterator<Staff> i = staff.iterator() ; i.hasNext(); ){
			Staff s = i.next();
			for(Group g : groups){
				if(g.getStaff().getId() == s.getId()){
					i.remove();
				}
			}
		}
		return staff;
	}
	
	public void addStaffToGroup(Staff staff, Patient activePatient) {
		System.out.println("addtogr: " + staff + activePatient);
		String stm = "INSERT INTO staff_has_patient(staff_staff_id, patient_patient_id, owner, rwaccess) VALUES(?, ?, 'false', 'true');";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, staff.getId());
			pst.setInt(2, activePatient.getPatientid());
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initGroup();
	}
	public void removeStaffFromGroup(Staff staff, Patient activePatient) {
		System.out.println("Remfromgr: " + staff + activePatient);
		String stm = "DELETE FROM staff_has_patient WHERE staff_staff_id=? AND patient_patient_id=?";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, staff.getId());
			pst.setInt(2, activePatient.getPatientid());
			pst.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initGroup();
	}

	
	public List<Patient> getPatient(){
		return this.patient;
	}
	
	

	public Patient getPatient(int patientid) {
//		Patient p = null;
//		init();
//		String stm = "SELECT * FROM patient WHERE patient_id=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, patientid);
//			pst.execute();
//			rs = pst.getResultSet();
//			if (rs.next()) {
//				p = new Patient();
//				p.setPatientid(rs.getInt("patient_id"));
//				p.setFirstname(rs.getString("firstname"));
//				p.setLastname(rs.getString("lastname"));
//				p.setBirth(rs.getString("birthday"));
//				p.setStreet(rs.getString("street"));
//				p.setNr(rs.getInt("nr"));
//				p.setCity(rs.getString("city"));
//				p.setZip(rs.getString("zip"));
//				p.setTelnumber(rs.getInt("telnumber"));
//				p.setGender(rs.getString("gender"));
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return p;
		for(Patient p : this.patient){
			if(p.getPatientid() == patientid){
				return p;
			}
		}
		return null;
	}
	
//	public List<Patient> getPatients() {
//		List<Patient> patients = new ArrayList<Patient>();
//		init();
//		String stm = "SELECT * FROM patient;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				Patient p = new Patient();
//				p.setPatientid(rs.getInt("patient_id"));
//				p.setFirstname(rs.getString("firstname"));
//				p.setLastname(rs.getString("lastname"));
//				p.setBirth(rs.getString("birthday"));
//				p.setStreet(rs.getString("street"));
//				p.setNr(rs.getInt("nr"));
//				p.setCity(rs.getString("city"));
//				p.setZip(rs.getString("zip"));
//				p.setTelnumber(rs.getInt("telnumber"));
//				p.setGender(rs.getString("gender"));
//				patients.add(p);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return patients;
//	}
	
	public List<Patient> getPatientsWithRestrictions(Staff activeUser) {
		List<Patient> patients = new ArrayList<Patient>();
		init();
//		String stm = "SELECT * FROM patient";// WHERE staff_staff_id=?;";
		String stm = "SELECT * FROM staff_has_patient INNER JOIN patient ON staff_has_patient.patient_patient_id=patient.patient_id WHERE staff_has_patient.staff_staff_id=?";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, activeUser.getId());
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Patient p = new Patient();
				p.setPatientid(rs.getInt("patient_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setBirth(rs.getString("birthday"));
				p.setStreet(rs.getString("street"));
				p.setNr(rs.getInt("nr"));
				p.setCity(rs.getString("city"));
				p.setZip(rs.getString("zip"));
				p.setTelnumber(rs.getInt("telnumber"));
				p.setGender(rs.getString("gender"));
				patients.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return patients;
	}

	// Parameter sollte warscheinlich STAFF sein
	public void registernew(String name, String password, int i) {
		String stm1 = "SELECT * FROM staff WHERE name=?;";
		String stm2 = "INSERT INTO staff(name, password, role_role_id) VALUES(?, ?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				return;
			} else {
				pst = con.prepareStatement(stm2);
				pst.setString(1, name);
				pst.setString(2, password);
				pst.setInt(3, i);
				pst.executeUpdate();
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initStaff(); // TODO Da sonst nicht geupdated wird nach dem insert
		return;
	}

	public List<Questionnari> getQuestionnaris(int i) {
		ArrayList<Questionnari> ids = new ArrayList<Questionnari>(); 
		String stm1 = "SELECT * FROM question WHERE q5 = " + i + ";" ;
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
					Questionnari q = new Questionnari();
					q.setQ1(rs.getInt("q1"));
					q.setQ2(rs.getInt("q2"));
					q.setQ3(rs.getInt("q3"));
					q.setQ4(rs.getInt("q4"));
					q.setQ5(rs.getInt("q5"));
					q.setId(rs.getInt("id"));
					ids.add(q);
				}
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return ids;
	}
	
	public List<Question> getQuestionnaris2(int id) {
		String quest = "knee";
		List<Question> questions = this.getQuestions(quest);
		init();
		String stm = "SELECT * FROM " + quest + "_answer WHERE " + quest + "_answer_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			System.err.println(pst);
			pst.execute();
			rs = pst.getResultSet();
			
			if (rs.next()) {
				for(int i = 0; i < questions.size(); i++){
					questions.get(i).addAnswerPossibility(rs.getString(i+2));
//					if(type.equals("String")){
//						String s = rs.getString(i+2);
//						a = new AnswerString();
//						a.setAnswer(s);
//					} else if(type.equals("RadioButton")){
//						String s = rs.getString(i+2);
//						a = new AnswerRadioButton();
//						a.addAnswer(s);
//					} else if(type.equals("Checkbox")){
//						String s = rs.getString(i+2);
//						a = new AnswerCheckbox();
//						a.addAnswer(s);
//					} else {
//					
//					}
				}
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return questions;
	}
	
	public void addFilledQuestionnaire(String answer) {
		String stm2 = "INSERT INTO question(q1, q2, q3, q4, q5) VALUES("+answer+");";
		init();
		try {
			pst = con.prepareStatement(stm2);
			pst.execute();
			
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		return;
	}

	public List<Tools> getFilledQuestion(int id){
		List<Question> q = getQuestions(sss);
		List<Answer> a = getAnswers(sss, id);
		List<Tools> list = new ArrayList<Tools>();
		if(q.size()==a.size()){
			for(int i = 0; i < a.size(); i++){
				String s = q.get(i).getType();
				Tools t = new Tools();
				if(s.equals("Checkbox")){
				Tools to = new Tools();
				to.setString(q.get(i).getQuestion());
				list.add(to);
				List<String> b =  (List<String>) a.get(i).getAnswer();
				for(int c = 0; c < b.size(); c++){
				t = new Tools();

				t.setString(b.get(c));
				list.add(t);
				}
				}else if(s.equals("RadioButton")){
					Tools to = new Tools();
					to.setString(q.get(i).getQuestion());
					list.add(to);
					t.setString((String) a.get(i).getAnswer());
					list.add(t);
				}else if(s.equals("String")) {
					Tools to = new Tools();
					to.setString(q.get(i).getQuestion());
					list.add(to);
					t.setString((String) a.get(i).getAnswer());
					list.add(t);
				}else{}
			}
		}
		return list;
		}
	
	public List<PatientData> getPatientDatas(int patientid) {
		List<PatientData> data = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata WHERE patient_patient_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientid);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				PatientData pd = new PatientData();
				pd.setPatientdata_id(rs.getInt("patientdata_id"));
				pd.setFirstdata(rs.getString("firstdata"));
				pd.setSeconddata(rs.getString("seconddata"));
				pd.setInserttime(rs.getDate("inserttime"));
				data.add(pd);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return data;
	}

	public List<PatientData> getPatientData(int patientdataid) {
		List<PatientData> list = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata WHERE patientdata_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientdataid);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				PatientData pd = new PatientData();
				pd.setPatientdata_id(rs.getInt("patientdata_id"));
				pd.setFirstdata(rs.getString("firstdata"));
				pd.setSeconddata(rs.getString("seconddata"));
				pd.setInserttime(rs.getDate("inserttime"));
				list.add(pd);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public void updatePatient(Patient p) {
		init();
		String stm = "UPDATE patient SET firstname=?, lastname=?, birthday=?, street=?, nr=?, city=?, zip=?, telnumber=?, gender=? WHERE patient_id=?";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, p.getFirstname());
			pst.setString(2, p.getLastname());
			pst.setString(3, p.getBirth());
			pst.setString(4, p.getStreet());
			pst.setInt(5, p.getNr());
			pst.setString(6, p.getCity());
			pst.setString(7, p.getZip());
			pst.setInt(8, p.getTelnumber());
			pst.setString(9, p.getGender());
			pst.setInt(10, p.getPatientid());
			pst.executeUpdate();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
	}
	
	public void updatePatientData(PatientData pd) {
		init();
		String stm = "UPDATE patientdata SET firstdata=?, seconddata=? WHERE patientdata_id=?";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, pd.getFirstdata());
			pst.setString(2, pd.getSeconddata());
			pst.setInt(3, pd.getPatientdata_id());
			pst.executeUpdate();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
	}

	public List<PatientData> getPatientdatas() {
		return patientdata;
	}

	public void setPatientdatas(List<PatientData> patientdatas) {
		this.patientdata = patientdatas;
	}

	public List<Questionnari> searchQuestionnaris(int id) {
		init();
		ArrayList<Questionnari> questionnaris = new ArrayList<Questionnari>(); 
		String stm1 = "SELECT * FROM quest LEFT JOIN op ON quest.op_id = op.id WHERE patient_id=" + id + ";" ;
		try {
			pst = con.prepareStatement(stm1);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
					Questionnari q = new Questionnari();
					q.setDate(rs.getDate("date"));
					q.setOp(rs.getString("opart"));
					q.setId(rs.getInt("quest_id"));
					questionnaris.add(q);
				}
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return questionnaris;
	}
	
	//TODO firstname lastname
	public List<Patient> searchPatient(String name) {
//		List<Patient> list = new ArrayList<Patient>();
//		init();
//		String stm = "SELECT * FROM patient WHERE firstname LIKE ?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setString(1, "%" + firstname + "%");
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				Patient p = new Patient();
//				p.setPatientid(rs.getInt("patient_id"));
//				p.setFirstname(rs.getString("firstname"));
//				p.setLastname(rs.getString("lastname"));
//				p.setBirth(rs.getString("birthday"));
//				p.setStreet(rs.getString("street"));
//				p.setNr(rs.getInt("nr"));
//				p.setCity(rs.getString("city"));
//				p.setZip(rs.getString("zip"));
//				p.setTelnumber(rs.getInt("telnumber"));
//				p.setGender(rs.getString("gender"));
//				list.add(p);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return list;
		List<Patient> patient = new ArrayList<Patient>();
		for(Patient p : this.patient){
			if(name.equals("") || p.getFirstname().toUpperCase().contains(name.toUpperCase())){
				patient.add(p);
			}
		}
		return patient;
	}
	

	public List<PatientData> searchPatientData(String operation) {
		List<PatientData> list = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata WHERE firstdata=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, operation);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				PatientData pd = new PatientData();
				pd.setPatientdata_id(rs.getInt("patientdata_id"));
				pd.setFirstdata(rs.getString("firstdata"));
				pd.setSeconddata(rs.getString("seconddata"));
				pd.setInserttime(rs.getDate("inserttime"));
				list.add(pd);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	//TODO from - to
	public List<PatientData> searchPatientData(String operation, Date from, Date to) {
		List<PatientData> list = new ArrayList<PatientData>();
		init();
		if(from == null){
			from = new Date();
			from.setTime(0);						// 1970-01-01
		}
		if(to == null){
			to = new Date();
			to.setTime(new Long("4102444800000"));	// 2100-01-01
		}
		String stm = "SELECT * FROM patientdata WHERE firstdata=? AND inserttime BETWEEN ? AND ?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, operation);
			pst.setDate(2, new java.sql.Date(from.getTime()));
			pst.setDate(3, new java.sql.Date(to.getTime()));
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				PatientData pd = new PatientData();
				pd.setPatientdata_id(rs.getInt("patientdata_id"));
				pd.setFirstdata(rs.getString("firstdata"));
				pd.setSeconddata(rs.getString("seconddata"));
				pd.setInserttime(rs.getDate("inserttime"));
				list.add(pd);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public void createPatient(Patient p, Staff activeUser) {
		if((activeUser != null) && (activeUser.getRole() == 1)){
			String stm1 = "SELECT * FROM patient WHERE firstname=? AND lastname=?;";
			String stm2 = "INSERT INTO patient(firstname, lastname, birthday, street, nr, city, zip, telnumber, gender) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			init();
			try {
				pst = con.prepareStatement(stm1);
				pst.setString(1, p.getFirstname());
				pst.setString(2, p.getLastname());
				pst.execute();
				rs = pst.getResultSet();
				if(rs.next()){
					return;
				}
				pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, p.getFirstname());
				pst.setString(2, p.getLastname());
				pst.setString(3, p.getBirth());
				pst.setString(4, p.getStreet());
				pst.setInt(5, p.getNr());
				pst.setString(6, p.getCity());
				pst.setString(7, p.getZip());
				pst.setInt(8, p.getTelnumber());
				pst.setString(9, p.getGender());
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if(rs.next()){
					long patient_id = rs.getLong(1);
					stm2 = "INSERT INTO staff_has_patient(staff_staff_id, patient_patient_id, owner, rwaccess) VALUES(?,?,'true','true')";
					pst = con.prepareStatement(stm2);
					pst.setLong(1, activeUser.getId());
					pst.setLong(2, patient_id);
					pst.executeUpdate();
				}
				closeWithoutRs();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeWithoutRs();
			}
			initPatient();	// TODO da sonst nicht geupdated wird nach dem insert
		}
	}

//	public void createPatientData(Patient p, PatientData pd) {
//		String stm = "INSERT INTO patientdata(patient_patient_id, firstdata, seconddata) VALUES(?, ?, ?);";
//		init();
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, p.getPatientid());
//			pst.setString(2, pd.getFirstdata());
//			pst.setString(3, pd.getSeconddata());
//			pst.executeUpdate();
//			closeWithoutRs();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeWithoutRs();
//		}
//		return;
//	}

	
	
	
	
	
	
	public List<Question> getQuestions(String quest) {
//		String quest = "knee";
//		if(o instanceof Knee){
//			quest = "knee";
//			System.out.println("EM: knee");
//		} else if (o instanceof Elbow){
//			quest = "elbow";
//			System.out.println("EM: elbow");
//		} else {
//			
//		}
		
		
		List<Question> questions = new ArrayList<Question>();
		init();
		String stm = "SELECT * FROM " + quest + ";";
		try {
			pst = con.prepareStatement(stm);
//			pst.setString(1, quest);		// don't know why does not work
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Question question = null;
				int id = rs.getInt(quest + "_id");
				String t = rs.getString("type");
				String q = rs.getString("question");
				if(t.equals("String")){
					question = new QuestionString();
					question.setId(id);
					question.setType(t);
					question.setQuestion(q);
				} else if(t.equals("RadioButton")){
					question = new QuestionRadioButton();
					question.setId(id);
					question.setType(t);
					question.setQuestion(q);
					List<String> list = this.getPossibilities(quest, rs.getInt(quest + "_possibilities_" + quest + "_possibilities_id"));	
					question.setAnswerPossibilities(list);
				} else if(t.equals("Checkbox")) {
					question = new QuestionCheckbox();
					question.setId(id);
					question.setType(t);
					question.setQuestion(q);
					List<String> list = this.getPossibilities(quest, rs.getInt(quest + "_possibilities_" + quest + "_possibilities_id"));	
					question.setAnswerPossibilities(list);
				} else {
					
				}
				questions.add(question);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return questions;
	}
	
	
	
	
	public List<String> getPossibilities(String quest, int id) {
		List<String> possibilities = new ArrayList<String>();
//		init();
		String stm = "SELECT * FROM " + quest + "_possibilities WHERE " + quest + "_possibilities_id=?;";
		try {
			PreparedStatement pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (rs.next()) {
				for(int i = 0 ; i < 3 ; i++){
					String s = rs.getString(i+2);
					possibilities.add(s);
				}
			}
			rs.close();
			pst.close();
//			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			close();
		}
		return possibilities;
	}
	
	
	
	public List<Answer> getAnswers(String quest, int id) {
		List<Answer> answers = new ArrayList<Answer>();
		List<Question> questions = this.getQuestions(quest);
		init();
		String stm = "SELECT * FROM " + quest + "_answer WHERE " + quest + "_answer_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				for(int i = 0; i < questions.size(); i++){
					Answer a = null;
					String type = questions.get(i).getType();
					if(type.equals("String")){
						String s = rs.getString(i+2);
						a = new AnswerString();
						a.setAnswer(s);
					} else if(type.equals("RadioButton")){
						String s = rs.getString(i+2);
						a = new AnswerRadioButton();
						a.addAnswer(s);
					} else if(type.equals("Checkbox")){
						String s = rs.getString(i+2);
						a = new AnswerCheckbox();
						a.setAnswer(toArray(s));
					} else {
					
					}
					answers.add(a);
				}
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return answers;
	}

	private ArrayList<String> toArray(String s) {
		String b = "";
		s = s.replace("[", "");
		s = s.replace("]", "");
		String[] a = s.split(",", 10 );
		ArrayList<String> c = new ArrayList<String>();
		for (int i = 0; i < a.length; i++){
		c.add(a[i]);
		}
		return c;
	}
	public void addAnswer(String quest, List<Answer> a) {
		int size = a.size();
		String stm = "INSERT INTO " + quest + "_answer(answer1";
		for(int i = 2 ; i <= size ; i++){
			stm = stm + ", answer" + i;
		}
		stm = stm + ", patient_patient_id) VALUES(?";		// TODO
		for(int i = 2 ; i <= size ; i++){
			stm = stm + ",?";
		}
		stm = stm + ", 1);";								// TODO
		init();
		try {
			pst = con.prepareStatement(stm);
			
			for(int i = 0 ; i < size ; i++){
				pst.setString(i+1, a.get(i).toString());			
			}
			pst.execute();
//			rs = pst.getResultSet();
//			if(rs.next()) {
//				return;
//			}
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		return;
	}

	public List<Elbow> searchPatientData2(String op) {
		List<Elbow> elbowlist = new ArrayList<Elbow>();
		init();
		String stm = "SELECT * FROM elbow_answer;";
		try {
			pst = con.prepareStatement(stm);
//			pst.setString(1, operation);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				List<Answer> answerlist = new ArrayList<Answer>();
				Elbow e = new Elbow();
				Answer a1 = new AnswerString();
				a1.addAnswer(rs.getString("answer1"));
				Answer a2 = new AnswerString();
				a2.addAnswer(rs.getString("answer2"));
				Answer a3 = new AnswerString();
				a3.addAnswer(rs.getString("answer3"));
				Answer a4 = new AnswerString();
				a4.addAnswer(rs.getString("answer4"));

				answerlist.add(a1);
				answerlist.add(a2);
				answerlist.add(a3);
				answerlist.add(a4);
				e.setAnswers(answerlist);
				System.out.println("->" + e.getAnswers());
				
				elbowlist.add(e);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return elbowlist;
	}

	public List<Knee> searchPatientData3(String op) {
		List<Knee> kneelist = new ArrayList<Knee>();
		init();
		String stm = "SELECT * FROM knee_answer;";
		try {
			pst = con.prepareStatement(stm);
//			pst.setString(1, operation);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				List<Answer> answerlist = new ArrayList<Answer>();
				Knee e = new Knee();
				Answer a1 = new AnswerString();
				a1.addAnswer(rs.getString("answer1"));
				Answer a2 = new AnswerString();
				a2.addAnswer(rs.getString("answer2"));
				Answer a3 = new AnswerString();
				a3.addAnswer(rs.getString("answer3"));
				Answer a4 = new AnswerString();
				a4.addAnswer(rs.getString("answer4"));
				Answer a5 = new AnswerString();
				a5.addAnswer(rs.getString("answer5"));

				answerlist.add(a1);
				answerlist.add(a2);
				answerlist.add(a3);
				answerlist.add(a4);
				answerlist.add(a5);
				e.setAnswers(answerlist);
				System.out.println("->" + e.getAnswers());
				
				kneelist.add(e);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return kneelist;
	}

	public List<Knee> getAnswers(String quest) {
		List<Knee> knees = new ArrayList<Knee>();
		List<Question> questions = this.getQuestions(quest);
		List<Answer> answers = new ArrayList<Answer>();
		init();
		String stm = "SELECT * FROM " + quest + "_answer;";
		try {
			pst = con.prepareStatement(stm);
//			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				for(int i = 0; i < questions.size(); i++){
					Answer a = null;
					String type = questions.get(i).getType();
					if(type.equals("String")){
						String s = rs.getString(i+2);
						a = new AnswerString();
						a.setAnswer(s);
					} else if(type.equals("RadioButton")){
						String s = rs.getString(i+2);
						a = new AnswerRadioButton();
						a.addAnswer(s);
					} else if(type.equals("Checkbox")){
						String s = rs.getString(i+2);
						a = new AnswerCheckbox();
						a.addAnswer(s);
					} else {
					
					}
					answers.add(a);
				}
				Knee k = new Knee();
				k.setAnswers(answers);
				knees.add(k);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return knees;
	}

	public List<Patient> searchPatientWithRestrictions(String firstname, Staff activeUser) {
		List<Patient> patients = new ArrayList<Patient>();
		init();
//		String stm = "SELECT * FROM patient";// WHERE staff_staff_id=?;";
		String stm = "SELECT * FROM staff_has_patient INNER JOIN patient ON staff_has_patient.patient_patient_id=patient.patient_id WHERE staff_has_patient.staff_staff_id=? AND patient.firstname LIKE ?";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, activeUser.getId());
			pst.setString(2, "%" + firstname + "%");
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Patient p = new Patient();
				p.setPatientid(rs.getInt("patient_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setBirth(rs.getString("birthday"));
				p.setStreet(rs.getString("street"));
				p.setNr(rs.getInt("nr"));
				p.setCity(rs.getString("city"));
				p.setZip(rs.getString("zip"));
				p.setTelnumber(rs.getInt("telnumber"));
				p.setGender(rs.getString("gender"));
				patients.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return patients;
	}

	public List<Group> getGroups(Staff activeUser, Patient activePatient) {
//		List<Group> groups = new ArrayList<Group>();
//		init();
//		String stm = "SELECT * FROM staff_has_patient WHERE patient_patient_id=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, activePatient.getPatientid());
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				Group g = new Group();
//				int staffid = rs.getInt("staff_staff_id");
//				int patientid = rs.getInt("patient_patient_id");
//				g.setStaff(staffid);
//				g.setPatient(patientid);
//				g.setOwner(rs.getString("owner"));
//				groups.add(g);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return groups;
		
		List<Group> list = new ArrayList<Group>();
		for(Group g : this.group){
			if(g.getPatient().getPatientid() == activePatient.getPatientid()){
				list.add(g);
			}
		}
		return list;
	}

	
	
	
	private void initStaff() {
		staff = new ArrayList<Staff>();
		String stm = "SELECT * FROM staff;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()) {
				Staff s = new Staff();
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setPassword(rs.getString("password"));
				s.setRole(rs.getInt("role_role_id"));
				staff.add(s);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		p3.setSize(staff.size());
	}
	
	private void initPatient() {
		patient = new ArrayList<Patient>();
		init();
		String stm = "SELECT * FROM patient;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Patient p = new Patient();
				p.setPatientid(rs.getInt("patient_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				p.setBirth(rs.getString("birthday"));
				p.setStreet(rs.getString("street"));
				p.setNr(rs.getInt("nr"));
				p.setCity(rs.getString("city"));
				p.setZip(rs.getString("zip"));
				p.setTelnumber(rs.getInt("telnumber"));
				p.setGender(rs.getString("gender"));
				patient.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		p1.setSize(patient.size());
	}	
	



	private void initPatientData() {
		patientdata = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				PatientData pd = new PatientData();
				pd.setPatientdata_id(rs.getInt("patient_patient_id"));
				pd.setFirstdata(rs.getString("firstdata"));
				pd.setSeconddata(rs.getString("seconddata"));
//				pd.setInserttime(rs.getString("inserttime"));
				patientdata.add(pd);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		p2.setSize(patientdata.size());
	}
	

	private void initGroup() {
		this.group = new ArrayList<Group>();
		init();
		String stm = "SELECT * FROM staff_has_patient;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Group g = new Group();
				int staffid = rs.getInt("staff_staff_id");
				int patientid = rs.getInt("patient_patient_id");
//				g.setStaff(staffid);
//				g.setPatient(patientid);
				g.setStaff(this.getStaff(staffid));
				g.setPatient(this.getPatient(patientid));
				g.setOwner(rs.getBoolean("owner"));
				g.setRwaccess(rs.getBoolean("rwaccess"));
				this.group.add(g);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}




	public boolean isOwner(Staff activeUser, Patient p) {
		for(Group g : this.group){
			if((activeUser.getId() == g.getStaff().getId()) &&
					(p.getPatientid() == g.getPatient().getPatientid())){
				return g.getOwner();
			}
		}
		return false;
	}
	public boolean rwAccess(Staff activeUser, Patient p) {
		for(Group g : this.group){
			if((activeUser.getId() == g.getStaff().getId()) &&
					(p.getPatientid() == g.getPatient().getPatientid())){
				return g.getRwaccess();
			}
		}
		return false;
	}
	
	
	
	private void init() {
		con = MyConnection.getConnection();
	}
	
	private void closeWithoutRs() {
		try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void close() {
		try {
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public Paginator getP1() {
		return p1;
	}

	public void setP1(Paginator p) {
		this.p1 = p;
	}

	public Paginator getP2() {
		return p2;
	}

	public void setP2(Paginator p) {
		this.p2 = p;
	}
	
	public Paginator getP3() {
		return p3;
	}

	public void setP3(Paginator p) {
		this.p3 = p;
	}

	public List<Patient> getPatientsWithRWAccess(Staff activeUser) {
		List<Patient> list = new ArrayList<Patient>();
		
		for(Group g : this.group){
			if((g.getStaff().getId() == activeUser.getId()) && g.getRwaccess()){
				Patient p = this.getPatient(g.getPatient().getPatientid());
				list.add(p);
			}
		}
		
		return list;
	}

	public List<ListOfQuestionnari> searchData(int id){
		List<ListOfQuestionnari> quest = new ArrayList<ListOfQuestionnari>();
		String stm = "SELECT t.typ, answer_id, date FROM questionnaire q INNER JOIN typ t ON q.typ = t.id WHERE patient_patient_id = ?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()){
				ListOfQuestionnari list = new ListOfQuestionnari();
				list.setDate(rs.getDate("date"));
				list.setQuestId(rs.getInt("answer_id"));
				list.setTypOfQuest(rs.getString("typ"));
				quest.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quest;
	}
}
