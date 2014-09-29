package org.phd.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		String stm2 = "INSERT INTO testdb.doctor(name, password, role_role_id) VALUES(?, ?, ?);";
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
		String stm1 = "SELECT * FROM testdb.question WHERE q5 = " + i + ";" ;
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
		String stm2 = "INSERT INTO testdb.question(q1, q2, q3, q4, q5) VALUES("+answer+");";
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
				PatientData d = new PatientData();
				d.setPatientdata_id(rs.getInt("patientdata_id"));
				d.setFirstdata(rs.getString("firstdata"));
				d.setSeconddata(rs.getString("seconddata"));
				data.add(d);
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
				PatientData p = new PatientData();
				p.setPatientdata_id(rs.getInt("patientdata_id"));
				p.setFirstdata(rs.getString("firstdata"));
				p.setSeconddata(rs.getString("seconddata"));
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

	public void updatePatient(Patient p) {
		init();
		String stm = "UPDATE patient SET firstname=?, lastname=? WHERE patient_id=?";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, p.getFirstname());
			pst.setString(2, p.getLastname());
			pst.setInt(3, p.getPatientid());
			pst.execute();
//			rs = pst.getResultSet();
//			if (rs.next()) {
//				PatientData p = new PatientData();
//				p.setPatientdata_id(rs.getInt("patientdata_id"));
//				p.setFirstdata(rs.getString("firstdata"));
//				p.setSeconddata(rs.getString("seconddata"));
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
//				PatientData p = new PatientData();
//				p.setPatientdata_id(rs.getInt("patientdata_id"));
//				p.setFirstdata(rs.getString("firstdata"));
//				p.setSeconddata(rs.getString("seconddata"));
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
		String stm1 = "SELECT * FROM testdb.quest LEFT JOIN testdb.op ON testdb.quest.op_id = testdb.op.id WHERE patient_id=" + id + ";" ;
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
	
	//TODO from - to
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
				PatientData p = new PatientData();
				p.setPatientdata_id(rs.getInt("patientdata_id"));
				p.setFirstdata(rs.getString("firstdata"));
				p.setSeconddata(rs.getString("seconddata"));
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

	public void createPatient(Patient p) {
		String stm = "INSERT INTO testdb.patient(firstname, lastname) VALUES(?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, p.getFirstname());
			pst.setString(2, p.getLastname());
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

	public void createPatientData(PatientData pd, Patient p) {
		String stm = "INSERT INTO testdb.patientdata(patient_patient_id, firstdata, seconddata) VALUES(?, ?, ?);";
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

	
	
	
	
	
	
	public Knee getKneeQuestion() {
		Knee k = new Knee();
		init();
		String stm = "SELECT * FROM knee;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Question q = new QuestionString();
				String t = rs.getString("type");
				String s = rs.getString("question");
				q.setType(t);
				q.setQuestion(s);
				k.addQuestion(q);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return k;
	}
	
	public Knee getKneeAnswer() {
		Knee k = new Knee();
		List<Question> questions = k.getQuestions();
		init();
		String stm = "SELECT * FROM knee_answer;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			int i = 1;
			while (rs.next()) {
				Answer a = null;
				String type = questions.get(i-1).getType();		// !!!!!!!
				System.out.println("type " + type);
				if(type.equals("String")){
					String s = rs.getString(i);
					a = new AnswerString();
					a.setAnswer(s);
				} else if(type.equals("RadioButton")){
					String s = rs.getString(i);
					a = new AnswerString();
					a.setAnswer(s);
				} else if(type.equals("Checkbox")){
					String s = rs.getString(i);
					a = new AnswerString();
					a.setAnswer(s);
				} else {
					
				}
				k.addAnswer(a);
				i++;
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return k;
	}
}
