package org.bfh.phd;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	private List<Patient> patients;
	private List<Staff> staff;
	private List<PatientData> patientdatas;
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public EntityManager(){
		this.patients = new ArrayList<Patient>();
		this.staff = new ArrayList<Staff>();;
	}
	
	// parameter sollte warscheinlich STAFF sein
	public Staff getStaff(String name, String password) {
		Staff s = null;
		String stm = "SELECT * FROM doctor WHERE name=? AND password=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				s = new Staff();
				s.setName(rs.getString("name"));
				s.setPassword(rs.getString("password"));
				s.setRole(rs.getInt("role_role_id"));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return s;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public Patient getPatient(int patientid) {
		Patient p = null;
		init();
		String stm = "SELECT * FROM patient WHERE patient_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientid);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				p = new Patient();
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
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return p;
	}
	
	public List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();
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
		String stm1 = "SELECT * FROM doctor WHERE name=?;";
		String stm2 = "INSERT INTO doctor(name, password, role_role_id) VALUES(?, ?, ?);";
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
				pst.execute();
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
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
			pst.execute();
//			rs = pst.getResultSet();
//			if (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patientdata_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
//			}
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
			pst.execute();
//			rs = pst.getResultSet();
//			if (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patientdata_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
//			}
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
	}

	public List<PatientData> getPatientdatas() {
		return patientdatas;
	}

	public void setPatientdatas(List<PatientData> patientdatas) {
		this.patientdatas = patientdatas;
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
	public List<Patient> searchPatient(String firstname) {
		List<Patient> list = new ArrayList<Patient>();
		init();
		String stm = "SELECT * FROM patient WHERE firstname LIKE ?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, "%" + firstname + "%");
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
				list.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
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

	public void createPatient(Patient p) {
		String stm = "INSERT INTO patient(firstname, lastname, birthday, street, nr, city, zip, telnumber, gender) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		init();
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

	public void createPatientData(Patient p, PatientData pd) {
		String stm = "INSERT INTO patientdata(patient_patient_id, firstdata, seconddata) VALUES(?, ?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, p.getPatientid());
			pst.setString(2, pd.getFirstdata());
			pst.setString(3, pd.getSeconddata());
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
		List<Answer> answers = new ArrayList<Answer>();;
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
						a.addAnswer(s);
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

	public void addAnswer(String quest, List<Answer> a) {
		int size = a.size();
		String stm = "INSERT INTO " + quest + "_answer(answer1";
		for(int i = 2 ; i <= size ; i++){
			stm = stm + ", answer" + i;
		}
		stm = stm + ") VALUES(?";
		for(int i = 2 ; i <= size ; i++){
			stm = stm + ",?";
		}
		stm = stm + ");";
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
}
