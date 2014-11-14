package org.bfh.phd;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IEntityManager;
import org.bfh.phd.interfaces.IQuestion;
import org.bfh.phd.questionary.AnswerCheckbox;
import org.bfh.phd.questionary.AnswerRadioButton;
import org.bfh.phd.questionary.AnswerString;
import org.bfh.phd.questionary.QuestionCheckbox;
import org.bfh.phd.questionary.QuestionRadioButton;
import org.bfh.phd.questionary.QuestionString;
import org.bfh.phd.questionary.QuestionnairTools;

/**
 * @author leism3, koblt1
 *
 */
@ManagedBean(name = "entityManager", eager = true)
@SessionScoped
public class EntityManager implements IEntityManager, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	private List<Staff> staff;	
	private List<Patient> patient;
	private List<PatientData> patientdata;
	private List<Department> departments;
	@SuppressWarnings("rawtypes")
	private List<IQuestion> questions;
	private List<String> operationtyp;
	private List<String> error;
	private List<FilledQuestionnaire> filledQuestionnaires;


	private Map<String, Integer> typ;
	private String templatename = "";
	
	
	private MyConnection mycon = null;
	
	private Connection con = null, con2 = null;
	private PreparedStatement pst = null, pst2 = null;
	private ResultSet rs = null, rs2 = null;
	
//	private PaginatorPatient paginatorPatient = new PaginatorPatient();
	private PaginatorPatientData paginatorPatientData = new PaginatorPatientData();
	private PaginatorGroup paginatorGroup = new PaginatorGroup();
		
	public EntityManager(){
		mycon = new MyConnection();
		initDepartment();
		initStaff();
		initPatient();
		initPatientData();
		initOperationTyp();
		initTyp();
		initQuestionnaire();
	}

	@Override
	public List<Staff> getStaffs() {
		return this.staff;
	}
	
	@Override
	public List<FilledQuestionnaire> getFilledQuestionnaires() {
		return filledQuestionnaires;
	}
	
	@Override
	public Staff getStaff(int id) {
		for(Staff s : this.staff){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}
	
	@Override
	public Staff getStaff(String name, String password) {
		for(Staff s : this.staff){
			if(s.getName().equals(name) && s.getPassword().equals(password)){
				return s;
			}
		}
		return null;
	}
	
	@Override
	public List<Staff> getStaffs(String name) {
		List<Staff> staff = new ArrayList<Staff>();
		for(Staff s : this.staff){
			if(name.equals("") || s.getName().contains(name)){
				staff.add(s);
			}
		}
		return staff;
	}

	@Override
	public List<Patient> getPatients(Staff activeUser){
		List<Patient> list = new ArrayList<Patient>();
		for(Patient p : this.patient){
			if(p.getReadaccess() || (p.getOwner().equals(activeUser))){
				list.add(p);
			}
		}
		return list;
	}

	@Override
	public List<Patient> getPatient(){
		return this.patient;
	}

	@Override
	public Patient getPatient(Staff activeUser, int patientid) {
		for(Patient p : this.patient){
			if((p.getReadaccess() || (p.getOwner().equals(activeUser))) && (p.getPatientid() == patientid)){
				return p;
			}
		}
		return null;
	}

	@Override
	public Staff registernew(Staff s, boolean activated) {
		long l = 0;
//		String stm1 = "SELECT * FROM staff WHERE name=?;";
		String stm2 = "INSERT INTO staff(name, password, privateKey, publicKey, role_role_id, isActivated) VALUES(?, ?, ?, ?, ?, ?);";
		init();
		try {
//			pst = con.prepareStatement(stm1);
//			pst.setString(1, s.getName());
//			pst.execute();
//			rs = pst.getResultSet();
//			if(rs.next()) {
//				return null;
//			} else {
				pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, s.getName());
				pst.setString(2, s.getPassword());
				pst.setString(3, s.getPrivateKey());
				pst.setString(4, s.getPublicKey());
				pst.setInt(5, s.getRole());
				pst.setString(6, Boolean.toString(activated));
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if(rs.next()){
					l = rs.getLong(1);
				}
//			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initStaff(); // TODO Da sonst nicht geupdated wird nach dem insert
		return this.getStaff((int) l);
	}

//	public List<Questionnair> getQuestionnaris(int i) {
//		ArrayList<Questionnair> ids = new ArrayList<Questionnair>(); 
//		String stm1 = "SELECT * FROM question WHERE q5 = " + i + ";" ;
//		init();
//		try {
//			pst = con.prepareStatement(stm1);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				Questionnair q = new Questionnair();
////					q.setQ1(rs.getInt("q1"));
////					q.setQ2(rs.getInt("q2"));
////					q.setQ3(rs.getInt("q3"));
////					q.setQ4(rs.getInt("q4"));
////					q.setQ5(rs.getInt("q5"));
//					q.setId(rs.getInt("id"));
//					ids.add(q);
//				}
//				close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//			return ids;
//	}
	

	//TODO not used
	@SuppressWarnings("rawtypes")
	@Override
	public List<IQuestion> getQuestionnaris2(int id) {
		System.out.println("Questionnaris2  id:"+id);
//		String quest = "knee";
//		List<IQuestion> questions = this.getQuestions(quest);
//		init();
//		String stm = "SELECT * FROM " + quest + "_answer WHERE " + quest + "_answer_id=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, id);
//			System.err.println(pst);
//			pst.execute();
//			rs = pst.getResultSet();
//			
//			if (rs.next()) {
//				for(int i = 0; i < questions.size(); i++){
//					questions.get(i).addAnswerPossibility(rs.getString(i+2));
////					if(type.equals("String")){
////						String s = rs.getString(i+2);
////						a = new AnswerString();
////						a.setAnswer(s);
////					} else if(type.equals("RadioButton")){
////						String s = rs.getString(i+2);
////						a = new AnswerRadioButton();
////						a.addAnswer(s);
////					} else if(type.equals("Checkbox")){
////						String s = rs.getString(i+2);
////						a = new AnswerCheckbox();
////						a.addAnswer(s);
////					} else {
////					
////					}
//				}
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return questions;
		return null;
	}
		
	/* (non-Javadoc)
	 * @see org.bfh.phd.Interfacetest#getFilledQuestion2(int)
	 */
	//TODO not used
	@SuppressWarnings("rawtypes")
	@Override
	public List<IQuestion> getFilledQuestion2(int id){
//		List<IQuestion> q = getQuestions("knee");
//		List<IAnswer> a = getAnswers(id);
//		System.err.println(id);
//		System.err.println(a);
//		System.err.println(a.size());
//		System.err.println(q.size());
//		if(q.size()==a.size()){
//			for(int i = 0; i < a.size(); i++){
//				String s = q.get(i).getType();
//				if(s.equals("Checkbox")){
//					List<String> list = q.get(i).getAnswerPossibilities();
//					int k = 0;
//					ArrayList<String> b = (ArrayList<String>) a.get(i).getAnswer();
//					for(int j = 0; j < list.size(); j++)	
//						if(list.get(j).equals(b.get(k))){
//							q.get(i).setAnswer("true");
//						}else{
//							q.get(i).setAnswer("false");
//						}
//				}else if(s.equals("RadioButton")){
//					List<String> list = q.get(i).getAnswerPossibilities();	
//					for(int j = 0; j < list.size(); j++){
//						if(list.get(j).equals(a.get(i).getAnswer())){
//							q.get(i).setAnswer("true");
//						}else{
//							q.get(i).setAnswer("false");
//						}
//					}
//				}else if(s.equals("String")) {
//					q.get(i).setAnswer((String)a.get(i).getAnswer());
//				}else{}
//			}
//		}
//		return q;
		return null;
	}
	

	/* (non-Javadoc)
	 * @see org.bfh.phd.Interfacetest#getFilledQuestion(int, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public FilledQuestionnaire getFilledQuestion(int id, String questionnaireName){
		List<IQuestion> q = getQuestions(questionnaireName);
		List<IAnswer> a = getAnswers(id);
		FilledQuestionnaire f = new FilledQuestionnaire();
		f.setQuestionnaireName(questionnaireName);
//		List<Tools> list = new ArrayList<Tools>();
		if(q.size()==a.size()){
			for(int i = 0; i < a.size(); i++){
//				Tools t = new Tools();
				if(q.get(i) instanceof QuestionCheckbox){
					AnswerCheckbox b = new AnswerCheckbox();
					b.setAnswer(Arrays.asList(a.get(i).toString().split(",")));
					f.addAnswers(b);
					f.addQuestions(q.get(i));
					System.out.println(q.get(i));
//					Tools to = new Tools();
//					to.setString(q.get(i).getQuestion());
//					list.add(to);
//					String b = a.get(i).toString();
//					List<String> d = Arrays.asList(b.split(","));
//					for(int c = 0; c < d.size(); c++){
//					t = new Tools();
//					System.out.println(d);
//					t.setString(d.get(c));
//					System.out.println(t.getA());
//					list.add(t);
//					}
				}else if(q.get(i) instanceof QuestionRadioButton){
					AnswerRadioButton b = new AnswerRadioButton();
					b.setAnswer(a.get(i).toString());
					f.addAnswers(b);
					f.addQuestions(q.get(i));
					System.out.println(q.get(i));
//					Tools to = new Tools();
//					to.setString(q.get(i).getQuestion());
//					list.add(to);
//					t.setString(a.get(i).toString());
//					list.add(t);
				}else if(q.get(i) instanceof QuestionString) {
					AnswerString b = new AnswerString();
					b.setAnswer(a.get(i).toString());
					f.addAnswers(b);
					f.addQuestions(q.get(i));
					System.out.println(q.get(i));
//					Tools to = new Tools();
//					to.setString(q.get(i).getQuestion());
//					list.add(to);
//					t.setString(a.get(i).toString());
//					list.add(t);
				}else{}
			}
			String stm = "SELECT date FROM questionnaire q JOIN q_template_name tn ON q.template_name_id = tn.id WHERE answer_id = ?;";
			try {
				init();
				pst = con.prepareStatement(stm);
				pst.setInt(1, id);
				pst.execute();
				rs = pst.getResultSet();
				while(rs.next()){
					f.setDate(rs.getDate(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}			
		}
		return f;
		}
	
//	public List<PatientData> getPatientDatas(int patientid) {
//		List<PatientData> data = new ArrayList<PatientData>();
//		init();
//		String stm = "SELECT * FROM patientdata WHERE patient_patient_id=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, patientid);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patientdata_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
//				pd.setInserttime(rs.getDate("inserttime"));
//				data.add(pd);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		paginatorPatientData.setSize(data.size());
//		return data;
//	}

	/**
	 * @param patientdataid
	 * @return
	 */
	/**
	 * @param patientdataid
	 * @return
	 */
//	public List<PatientData> getPatientData(int patientdataid) {
//		List<PatientData> list = new ArrayList<PatientData>();
//		init();
//		String stm = "SELECT * FROM patientdata WHERE patientdata_id=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setInt(1, patientdataid);
//			pst.execute();
//			rs = pst.getResultSet();
//			if (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patientdata_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
//				pd.setInserttime(rs.getDate("inserttime"));
//				list.add(pd);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return list;
//	}

	/* (non-Javadoc)
	 * @see org.bfh.phd.interfaces.IEntityManager#updateStaff(org.bfh.phd.Staff)
	 */
	@Override
	public void updateStaff(Staff activeUser) {
		init();
		String stm = "UPDATE staff SET name=?, password=?, privateKey=?, publicKey=? WHERE staff_id=?";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, activeUser.getName());
			pst.setString(2, activeUser.getPassword());
			pst.setString(3, activeUser.getPrivateKey());
			pst.setString(4, activeUser.getPublicKey());
			pst.setInt(5, activeUser.getId());
			pst.executeUpdate();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
	}
	
	@Override
	public void updatePatient(Patient p, Staff activeUser) {
		if(this.writeaccess(p, activeUser)){
			init();
			String stm = "UPDATE patient SET readaccess=?, writeaccess=?, insertaccess=?, encryptedPersonalData=? WHERE patient_id=?";
			try {
				System.out.println("jfkdlsajf");
				pst = con.prepareStatement(stm);
				pst.setString(1, Boolean.toString(p.getReadaccess()));
				pst.setString(2, Boolean.toString(p.getWriteaccess()));
				pst.setString(3, Boolean.toString(p.getInsertaccess()));
				pst.setString(4, p.getPersonalData());
				pst.setInt(5, p.getPatientid());
				pst.executeUpdate();
				closeWithoutRs();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeWithoutRs();
			}
		}
	}
	
	/** Updates Patient data
	 * @param pd the new Patient data
	 */
//	public void updatePatientData(PatientData pd) {
//		init();
//		String stm = "UPDATE patientdata SET firstdata=?, seconddata=? WHERE patientdata_id=?";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setString(1, pd.getFirstdata());
//			pst.setString(2, pd.getSeconddata());
//			pst.setInt(3, pd.getPatientdata_id());
//			pst.executeUpdate();
//			closeWithoutRs();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeWithoutRs();
//		}
//	}

	public List<PatientData> getPatientdatas() {
		return patientdata;
	}

	public void setPatientdatas(List<PatientData> patientdatas) {
		this.patientdata = patientdatas;
	}

	/* (non-Javadoc)
	 * @see org.bfh.phd.Interfacetest#searchQuestionnaris(int)
	 */
	@Override
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
	
	@Override
	public List<Patient> searchPatients(Department_Has_Staff dhs, Staff activeUser) {
		List<Patient> patient = new ArrayList<Patient>();
		for(Patient p : this.patient){
			if(p.getDepartment().equals(dhs.getDepartment())){
				if(p.getReadaccess() || (p.getOwner().equals(activeUser))){
					patient.add(p);		
				}		
			}
		}
//		paginatorPatient.setSize(patient.size());
		return patient;
	}
	

	/** search any patients they had a special operation
	 * @param operation is the type of operation
	 * @return data set with all patient they had this operation
	 */
//	public List<PatientData> searchPatientData(String operation) {
//		List<PatientData> list = new ArrayList<PatientData>();
//		init();
//		String stm = "SELECT * FROM patientdata WHERE firstdata=?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setString(1, operation);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patientdata_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
//				pd.setInserttime(rs.getDate("inserttime"));
//				list.add(pd);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		paginatorPatientData.setSize(list.size());
//		return list;
//	}
	
	
	
	
	public List<PatientData> searchPatientDatas(){
		List<PatientData> list = new ArrayList<PatientData>();
		for(Patient p : patient){
			for(PatientData pd : p.getPatientData()){
				if(true){
					list.add(pd);
				}
			}
		}
		return list;
	}
	
	/** search any patients they had a special operation between from and to.
	 * @param operation is the type of operation
	 * @param from start date (default 1970-01-01)
	 * @param to end date (default 2100-01-01)
	 * @return data set with all patient they had this operation
	 */
//	public List<ListOfQuestionnari> searchPatientDatas(String operation, Date from, Date to) {
//		List<ListOfQuestionnari> list = new ArrayList<ListOfQuestionnari>();
//		init();
//		if(from == null){
//			from = new Date();
//			from.setTime(0);						// 1970-01-01
//		}
//		if(to == null){
//			to = new Date();
//			to.setTime(new Long("4102444800000"));	// 2100-01-01
//		}
//		String stm = "SELECT * FROM questionnaire WHERE inserttime BETWEEN ? AND ?;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.setDate(1, new java.sql.Date(from.getTime()));
//			pst.setDate(2, new java.sql.Date(to.getTime()));
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
////				PatientData pd = new PatientData();
////				pd.setPatientdata_id(rs.getInt("patientdata_id"));
////				pd.setFirstdata(rs.getString("firstdata"));
////				pd.setSeconddata(rs.getString("seconddata"));
////				pd.setInserttime(rs.getDate("inserttime"));
////				list.add(pd);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		paginatorPatientData.setSize(list.size());
//		return list;
//	}

	@Override
	public void createPatient(Patient p, Department_Has_Staff dhs, Staff activeUser) {
		if((activeUser != null) && (activeUser.getRole() == 1)){
			String stm2 = "INSERT INTO patient(readaccess, writeaccess, insertaccess, staff_staff_id, encryptedPersonalData, department_department_id) VALUES(?, ?, ?, ?, ?, ?);";
			init();
			try {
				pst = con.prepareStatement(stm2);
				pst.setString(1, "false");
				pst.setString(2, "false");
				pst.setString(3, "false");
				pst.setInt(4, activeUser.getId());
				pst.setString(5, p.getPersonalData());
				pst.setInt(6, dhs.getDepartment().getDepartment_id());
				pst.executeUpdate();
				closeWithoutRs();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeWithoutRs();
			}
			initPatient();	// TODO da sonst nicht geupdated wird nach dem insert
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<IQuestion> getQuestions(String quest) {
		if(!templatename.equals(quest)){		
		List<IQuestion> questions = new ArrayList<IQuestion>();
		init();
		String stm = "SELECT fragenr, typ, question, pos_id FROM testdb.q_template t JOIN q_template_name n ON t.id = n.id JOIN question2 q2 ON t.question_id = q2.id JOIN q_typ ON q2.type_id = q_typ.id WHERE name=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, quest);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				IQuestion question = null;
				int id = rs.getInt("fragenr");
				int pos_id = rs.getInt("pos_id");
				String t = rs.getString("typ");
				String q = rs.getString("question");
				if(t.equals("String")){
					question = new QuestionString();
					question.setId(id);
					question.setQuestion(q);
				} else if(t.equals("RadioButton")){
					question = new QuestionRadioButton();
					question.setId(id);
					question.setQuestion(q);
					question.setAnswerPossibilities(getPossibleAnswers(pos_id));
				} else if(t.equals("Checkbox")) {
					question = new QuestionCheckbox();
					question.setId(id);
					question.setQuestion(q);	
					question.setAnswerPossibilities(getPossibleAnswers(pos_id));
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
		this.templatename = quest;
		this.questions = questions;
		}
		return questions;
	}
	
	/** return the possible answers from the selected question
	 * @param id the identifier key to find the possibilities into the database
	 * @return the answer as a collection of Strings
	 */
	private List<String> getPossibleAnswers(int id) {
		System.out.println("Entitimanager 684 is used");
		List<String> list = new ArrayList<String>();
		String stm = "SELECT answer FROM posibilitis p JOIN pos_answer pa ON p.pos_id=pa.id WHERE p.id=?;";
		try{
			pst2 = con2.prepareStatement(stm);
			pst2.setInt(1, id);
			pst2.execute();
			rs2 = pst2.getResultSet();
			while(rs2.next()){
				list.add(rs2.getString("answer"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally{
		}
		return list;
	}

	/** return the possible answers from the selected question
	 * @param id the identifier key to find the possibilities into the database
	 * @return the answer as a collection of Strings
	 */
	public List<String> getPossibilities(int id) {
		System.out.println("Entitimanager 704 is used");
		List<String> possibilities = new ArrayList<String>();
		String stm = "SELECT * FROM posibilitis p INNER JOIN pos_answer a On p.pos_id = a.id WHERE p.id=?;";
		try {
			PreparedStatement pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			while(rs.next()){
				possibilities.add(rs.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return possibilities;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<IAnswer> getAnswers(int id) {
		List<IAnswer> answers = new ArrayList<IAnswer>();
		init();
		String stm = "SELECT answer, t.typ AS typ FROM testdb.questionnair_has_answer qha JOIN answer a ON qha.answer_id = a.id JOIN q_typ t ON qha.typ = t.id WHERE qha.id = ?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()){
				IAnswer a = null;
				String type = rs.getString("typ");
				String answer = rs.getString("answer");
				if(type.equals("String")){
					a = new AnswerString();
					a.setAnswer(answer);
				}else if(type.equals("RadioButton")){
					a = new AnswerRadioButton();
					a.setAnswer(answer);
				}else if(type.equals("Checkbox")){
					a = new AnswerCheckbox();
					a.setAnswer(Arrays.asList(answer.split(",")));
				}else{
					throw new RuntimeException("QuestionType not implemented");
				}
				answers.add(a);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
//		Collections.reverse(answers); not used
		return answers;
	}
	
	private int getTyp(String quest) throws SQLException {
		String stm = "SELECT id FROM typ WHERE typ='"+quest+"';";
		pst = con.prepareStatement(stm);
		pst.execute();
		rs = pst.getResultSet();
		if(rs.next()){
			return rs.getInt("id");
		}
		return rs.getInt("id");
	}

//	public List<Elbow> searchPatientData2(String op) {
//		System.out.println("elbow");
//		List<Elbow> elbowlist = new ArrayList<Elbow>();
//		init();
//		String stm = "SELECT * FROM elbow_answer;";
//		try {
//			pst = con.prepareStatement(stm);
////			pst.setString(1, operation);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				List<IAnswer> answerlist = new ArrayList<IAnswer>();
//				Elbow e = new Elbow();
//				IAnswer a1 = new AnswerString();
//				a1.addAnswer(rs.getString("answer1"));
//				IAnswer a2 = new AnswerString();
//				a2.addAnswer(rs.getString("answer2"));
//				IAnswer a3 = new AnswerString();
//				a3.addAnswer(rs.getString("answer3"));
//				IAnswer a4 = new AnswerString();
//				a4.addAnswer(rs.getString("answer4"));
//
//				answerlist.add(a1);
//				answerlist.add(a2);
//				answerlist.add(a3);
//				answerlist.add(a4);
//				e.setAnswers(answerlist);
//				System.out.println("->" + e.getAnswers());
//				
//				elbowlist.add(e);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		System.out.println(elbowlist);
//		return elbowlist;
//	}

//	public List<Knee> searchPatientData3(String op) {
//		System.out.println("knee");
//		List<Knee> kneelist = new ArrayList<Knee>();
//		init();
//		String stm = "SELECT * FROM knee_answer;";
//		try {
//			pst = con.prepareStatement(stm);
////			pst.setString(1, operation);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				List<Answer> answerlist = new ArrayList<Answer>();
//				Knee e = new Knee();
//				Answer a1 = new AnswerString();
//				a1.addAnswer(rs.getString("answer1"));
//				Answer a2 = new AnswerString();
//				a2.addAnswer(rs.getString("answer2"));
//				Answer a3 = new AnswerString();
//				a3.addAnswer(rs.getString("answer3"));
//				Answer a4 = new AnswerString();
//				a4.addAnswer(rs.getString("answer4"));
//				Answer a5 = new AnswerString();
//				a5.addAnswer(rs.getString("answer5"));
//
//				answerlist.add(a1);
//				answerlist.add(a2);
//				answerlist.add(a3);
//				answerlist.add(a4);
//				answerlist.add(a5);
//				e.setAnswers(answerlist);
//				System.out.println("->" + e.getAnswers());
//				
//				kneelist.add(e);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		System.out.println(kneelist);
//		return kneelist;
//	}
//
//	public List<Knee> getAnswers(String quest) {
//		List<Knee> knees = new ArrayList<Knee>();
//		List<Question> questions = this.getQuestions(quest);
//		List<Answer> answers = new ArrayList<Answer>();
//		init();
//		String stm = "SELECT * FROM " + quest + "_answer;";
//		try {
//			pst = con.prepareStatement(stm);
////			pst.setInt(1, id);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				for(int i = 0; i < questions.size(); i++){
//					Answer a = null;
//					String type = questions.get(i).getType();
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
//					answers.add(a);
//				}
//				Knee k = new Knee();
//				k.setAnswers(answers);
//				knees.add(k);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		return knees;
//	}

	
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
				s.setPrivateKey(rs.getString("privateKey"));
				s.setPublicKey(rs.getString("publicKey"));
				s.setRole(rs.getInt("role_role_id"));
				s.setActivated(Boolean.parseBoolean(rs.getString("isActivated")));
				staff.add(s);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	/** Initialization of all patients
	 *
	 */
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
				p.setReadaccess(Boolean.parseBoolean(rs.getString("readaccess")));
				p.setWriteaccess(Boolean.parseBoolean(rs.getString("writeaccess")));
				p.setInsertaccess(Boolean.parseBoolean(rs.getString("insertaccess")));
				p.setOwner(this.getStaff(rs.getInt("staff_staff_id")));
				p.setPersonalData(rs.getString("encryptedPersonalData"));
				p.setDepartment(this.getDepartment(rs.getInt("department_department_id")));
				patient.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}	
	
	/** Initialization of all question types
	 *
	 */
	private void initTyp() {
		typ = new HashMap<String, Integer>();
		String stm = "SELECT * FROM q_typ";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				typ.put(rs.getString("typ"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	/** Initialization of patient data
	 * 
	 */
	private void initPatientData() {
		patientdata = new ArrayList<PatientData>();
//		init();
//		String stm = "SELECT * FROM questionnaire;";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.execute();
//			rs = pst.getResultSet();
//			while (rs.next()) {
//				PatientData pd = new PatientData();
//				pd.setPatientdata_id(rs.getInt("patient_patient_id"));
//				pd.setFirstdata(rs.getString("firstdata"));
//				pd.setSeconddata(rs.getString("seconddata"));
////				pd.setInserttime(rs.getString("inserttime"));
//				patientdata.add(pd);
//			}
//			close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
	}


	public boolean isOwner(Patient patient, Staff activeUser) {
		if(patient.getOwner().equals(activeUser)){
			return true;
		}
		return false;
	}
	public boolean readaccess(Patient p, Staff activeUser) {
		if(isOwner(p, activeUser)){
			return true;
		}
		return p.getReadaccess();
	}
	public boolean writeaccess(Patient p, Staff activeUser) {
		if(isOwner(p, activeUser)){
			return true;
		}
		return p.getWriteaccess();
	}
	public boolean insertaccess(Patient p, Staff activeUser) {
		if(isOwner(p, activeUser)){
			return true;
		}
		return p.getInsertaccess();
	}

	/** Initialization of the two database connections
	 *
	 */
	private void init() {
		con = mycon.getConnection();
		con2 = mycon.getConnection();
	}
	
	/** Close the database connection without result set
	 *
	 */
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
	
	/** Close the database connection with result set
	 *
	 */
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
	
//	public PaginatorPatient getPaginatorPatient() {
//		return paginatorPatient;
//	}
//
//	public void setPaginatorPatient(PaginatorPatient p) {
//		this.paginatorPatient = p;
//	}

	public PaginatorPatientData getPaginatorPatientData() {
		return paginatorPatientData;
	}

	public void setPaginatorPatientData(PaginatorPatientData p) {
		this.paginatorPatientData = p;
	}

	public PaginatorGroup getPaginatorGroup() {
		return paginatorGroup;
	}

	public void setPaginatorGroup(PaginatorGroup p) {
		this.paginatorGroup = p;
	}
	
	/** Search all questionnaires from one patient
	 * @param id is the identification number of the patient
	 * @return
	 */
	public List<ListOfQuestionnari> searchData(int id){
		List<ListOfQuestionnari> quest = new ArrayList<ListOfQuestionnari>();
		String stm = "SELECT date, answer_id, name FROM questionnaire q JOIN q_template_name n ON q.template_name_id = n.id WHERE q.patient_patient_id = ?;";
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
				list.setTypOfQuest(rs.getString("name"));
				quest.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		paginatorPatientData.setSize(quest.size());
		return quest;
	}
	
	public List<Department> getDepartments() {
		return this.departments;
	}
	
	/** Initialization of all departments
	 * 
	 */
	private void initDepartment() {
		departments = new ArrayList<Department>();
		init();
		String stm = "SELECT * FROM department;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Department d = new Department();
				d.setDepartment_id(rs.getInt("department_id"));
				d.setName(rs.getString("name"));
				departments.add(d);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close();
		}
	}
	

	/** Create a new department
	 * @param d
	 * @param s is the staff member they create the department
	 * @param key
	 */
	public void createDepartment(Department d, Staff s, String key) {
		String stm1 = "SELECT * FROM department WHERE name=?;";
		String stm2 = "INSERT INTO department(name) VALUES(?);";
		String stm3 = "INSERT INTO department_has_staff(department_department_id, staff_staff_id, owner, encryptedKey) VALUES(?,?,?,?);";
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, d.getName());
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				// Department already exists
			} else {
				pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, d.getName());
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				int id = 0;
				if(rs.next()){
					id = rs.getInt(1);
				}
				pst = con.prepareStatement(stm3);
				pst.setInt(1, id);
				pst.setInt(2, s.getId());
				pst.setString(3, "true");
				pst.setString(4, key);
				pst.executeUpdate();
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initDepartment(); // TODO Da sonst nicht geupdated wird nach dem insert
	}
	
	/** Add a new staff member to a department
	 * @param name is the name of the department
	 * @param s is the staff member
	 */
	public void addToDepartment(String name, Staff s) {
		String stm1 = "SELECT * FROM department WHERE name=?;";
		@SuppressWarnings("unused")
		String stm2 = "INSERT INTO department(name) VALUES(?);";
		String stm3 = "INSERT INTO department_has_staff(department_department_id, staff_staff_id, owner) VALUES(?,?,?);";
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				pst = con.prepareStatement(stm3);
				pst.setInt(1, rs.getInt("department_id"));
				pst.setInt(2, s.getId());
				pst.setString(3, "false");
//				pst.setString(4, "encryptedKey");	// Not necessary yet, written in activation process
				pst.executeUpdate();
			} else {
				// Department does not exist
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initDepartment(); // TODO Da sonst nicht geupdated wird nach dem insert
	}
	
	/**
	 * @param name
	 * @param dhs
	 * @param activeUser
	 * @return
	 */
	public List<Staff> searchStaffs(String name, Department_Has_Staff dhs, Staff activeUser) {
		List<Staff> staff = new ArrayList<Staff>();
		for(Staff s : dhs.getStaff()){
			if(name.equals("") || s.getName().toUpperCase().contains(name.toUpperCase())){
				staff.add(s);
			}			
		}
		staff.remove(activeUser);
		paginatorGroup.setSize(staff.size());
		return staff;
	}
	
	/** Activate or deactivate a staff member
	 * @param s is the staff member 
	 * @param b activate = true / deactivate = false
	 */
	public void setActivateStaff(Staff s, boolean b){
		String stm = "UPDATE staff SET isActivated=? WHERE staff_id=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, Boolean.toString(b));
			pst.setInt(2, s.getId());
			pst.execute();
			rs = pst.getResultSet();
//			if(rs.next()) {
//				return;
//			}
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		initStaff(); // TODO Da sonst nicht geupdated wird nach dem insert
	}
	
	/**
	 * @param d
	 * @return
	 */
	public Department_Has_Staff getDepartment_Has_Staff(Department d) {
		Department_Has_Staff dhs = new Department_Has_Staff();
		init();
		String stm = "SELECT * FROM department_has_staff WHERE department_department_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, d.getDepartment_id());
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				dhs.setDepartment_has_staff_id(rs.getInt("department_department_id"));
				dhs.setDepartment(d);
				dhs.addStaff(this.getStaff(rs.getInt("staff_staff_id")));
				dhs.addEncryptedGroupKey(rs.getString("encryptedKey"));
				if(Boolean.parseBoolean(rs.getString("owner"))){
					dhs.setOwner(this.getStaff(rs.getInt("staff_staff_id")));;
				}
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close();
		}
		return dhs;
	}

	private Department getDepartment(int i) {
		for(Department d : this.departments){
			if(d.getDepartment_id() == i){
				return d;
			}
		}
		return null;
	}
	
	/**
	 * @param s
	 * @param dhs
	 * @param secret
	 */
	public void setGroupKey(Staff s, Department_Has_Staff dhs, String secret){
		String stm = "UPDATE department_has_staff SET encryptedKey=? WHERE department_department_id=? AND staff_staff_id=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, secret);
			pst.setInt(2, dhs.getDepartment_has_staff_id());
			pst.setInt(3, s.getId());
			pst.execute();
			rs = pst.getResultSet();
//			if(rs.next()) {
//				return;
//			}
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
//		initStaff(); // TODO Da sonst nicht geupdated wird nach dem insert
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addQuestionnaireTemplate(String typ, String question, String nameOfTemplate, List<String> pos, int fragenr){
		init();
		long key = 0, templatenr = 0; 
		String stm = "INSERT INTO question2 (pos_id, type_id, question, fragenr) VALUES(?,?,?,?);";
		String stm2 = "INSERT INTO q_template_name(name) VALUES (?);";
		String stm3 = "INSERT INTO q_template (id, question_id) VALUES (?,?);";
		String stm4 = "SELECT id FROM q_template_name WHERE name = ?;";
		Map map = getQuestType();
			try {
				int i = 0;
				if(typ != "String"){
					i = setPosibilitis(pos);
				}
				int j = (Integer) map.get(typ);
				pst = con.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, i);
				pst.setInt(2, j);
				pst.setString(3, question);
				pst.setInt(4, fragenr);
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if(rs.next()){
					key = rs.getLong(1);
				}
				pst = con.prepareStatement(stm4);
				pst.setString(1, nameOfTemplate);
				pst.execute();
				rs = pst.getResultSet();
				if(rs.next()){
					templatenr = rs.getInt("id");
				}else{
				pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, nameOfTemplate);
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if(rs.next()){
					templatenr = rs.getLong(1);
				}}	
				pst = con.prepareStatement(stm3);
				pst.setInt(1, (int) templatenr);
				pst.setInt(2, (int) key);
				pst.execute();
				close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
	}
	
	/**
	 * Insert all possibilities that have a question to the database. Attention its create duplicates in case of edit some possible answer that the other question isn't corrupt.  
	 * @param pos the list of possible answers
	 * @return the generated key of this set of possibilities
	 * @throws SQLException
	 */
	private int setPosibilitis(List<String> pos) throws SQLException{
		int i=1;
		int l=0;
		String stm = "SELECT MAX(id) FROM posibilitis;";
		String stm2 = "INSERT INTO pos_answer (answer) VALUES (?);";
		String stm3 = "INSERT INTO posibilitis (id,pos_id) VALUES (?,?);";
		pst = con.prepareStatement(stm);
		pst.execute();
		rs = pst.getResultSet();
		if(rs.next()){
			i += rs.getInt("MAX(id)");
		}
		for(int j = 0; j < pos.size(); j++){
			pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, pos.get(j));
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if(rs.next()){
				l = (int) rs.getLong(1);
			}
			pst = con.prepareStatement(stm3);
			pst.setInt(1, i);
			pst.setInt(2, (int) l);
			pst.execute();
		}
		return i;
	}
	
	@Override
	public void initOperationTyp(){
		operationtyp = new ArrayList<String>();
		init();
		String stm = "SELECT name FROM q_template_name;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()){
				operationtyp.add(rs.getString(1));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	/** Initialization of all filled questionnaires
	 * 
	 */
	private void initQuestionnaire() {
		filledQuestionnaires = new ArrayList<FilledQuestionnaire>();
		String stm = "SELECT answer_id, name FROM questionnaire q JOIN q_template_name tn ON q.template_name_id = tn.id;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				filledQuestionnaires.add(getFilledQuestion(rs.getInt("answer_id"), rs.getString("name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally  {
			close();
		}
	}
	
	@Override
	public Map<String, Integer> getType(){
		return typ;
	}
	
	public List<String> getTemplateNames(){
		return operationtyp;
	}
	
	//TODO test with hasmap ready to delet
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private HashMap getQuestType(){
		HashMap map = new HashMap();
		String stm = "SELECT * FROM q_typ;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()){
				map.put(rs.getString("typ"), rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public List<QuestionnairTools> getTemplate(String name){
		init();
		List<QuestionnairTools> quest = new ArrayList<QuestionnairTools>();
		String stm = "SELECT fragenr, q.id, name, pos_id, question, typ FROM q_template t  join q_template_name n On n.id=t.id join question2 q On t.question_id=q.id join q_typ ty On q.type_id=ty.id WHERE name = ? ORDER BY fragenr;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
				while (rs.next()) {
					System.out.println();
					QuestionnairTools q = new QuestionnairTools();
					q.setId(rs.getInt("fragenr"));
					q.setDbId(rs.getInt("id"));
					q.setQuestion(rs.getString("question"));
					q.setType(rs.getString("typ"));

					List<String> list = getPossibilities(rs.getInt("pos_id"));
					for(String s: list){
						q.addPossibleAnswer(s);
					}
					quest.add(q);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close();
		}
		return quest;
	}
		
	public void getS(){
		for(String s : error){
		System.out.println(s);
		}
	}

	@Override
	public void deletTemplateQuestion(QuestionnairTools q) throws SQLException{
		init();
		String stm = "DELETE FROM question2 WHERE id=?;";
		if(q.getType()!="String"){
			String stm1 = "SELECT pos_id FROM question2 WHERE id=?;";
			String stm2 = "SELECT pos_id FROM posibilitis WHERE id=?;";
			String stm3 = "DELETE FROM posibilitis WHERE id=?";
			String stm4 = "DELETE FROM pos_answer WHERE id=?";
			int i = 0;

				pst = con.prepareStatement(stm1);
				pst.setInt(1, q.getDbId());
				pst.execute();
				rs = pst.getResultSet();
				if(rs.next()){
				i = rs.getInt("pos_id");
				}
				pst = con.prepareStatement(stm2);
				pst.setInt(1, i);
				pst.execute();
				rs = pst.getResultSet();
				while(rs.next()){
					pst2 = con2.prepareStatement(stm4);
					pst2.setInt(1, rs.getInt("pos_id"));
					pst2.execute();
				}
				pst = con.prepareStatement(stm3);
				pst.setInt(1, i);
				pst.execute();
			}
		pst = con.prepareStatement(stm);
		pst.setInt(1, q.getDbId());
		pst.execute();
		close();
	}

	@Override
	public void editQuestion(QuestionnairTools q) throws SQLException {
		init();
		String stm = "UPDATE question2 SET question=? WHERE id=?;";
		String stm1 = "SELECT pos_id FROM question2 WHERE id=?;";
		String stm2 = "SELECT pos_id FROM posibilitis WHERE id=?;";
		String stm3 = "UPDATE pos_answer SET answer=? WHERE id=?;";
		pst = con.prepareStatement(stm);
		pst.setString(1, q.getQuestion());
		pst.setInt(2, q.getDbId());
		pst.executeUpdate();
		if(q.getType() != "String"){
		pst = con.prepareStatement(stm1);
		pst.setInt(1, q.getDbId());
		pst.execute();
		rs = pst.getResultSet();
		if(rs.next()){
			int i = rs.getInt("pos_id");	
			pst = con.prepareStatement(stm2);
			pst.setInt(1, i);
			pst.execute();
			rs = pst.getResultSet();
			int j = 0;
			while (rs.next()) {
				pst2 = con2.prepareStatement(stm3);
				pst2.setString(1,q.getAnswer().get(j));
				pst2.setInt(2,rs.getInt("pos_id"));
				pst2.executeUpdate();
				j++;
			}
		}
		}
		close();
		}

	@Override
	public void changeQuestionNr(String templateNameSelected, int eNumber) {
		init();
		String stm = "SELECT q.id, fragenr FROM testdb.question2 q JOIN q_template t ON q.id = t.question_id JOIN q_template_name n ON t.id = n.id WHERE name = ? AND q.fragenr > ?;";
		String stm2 = "UPDATE question2 SET fragenr=? WHERE id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, templateNameSelected);
			pst.setInt(2, eNumber);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				pst = con.prepareStatement(stm2);
				pst.setInt(2, rs.getInt("id"));
				pst.setInt(1, rs.getInt("fragenr")+1);
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
		close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	// TODO
	public void addAnswer(Patient activePatient, FilledQuestionnaire f) {
		init();
		try{
		int j = createQuestionnaireDataSet(activePatient.getPatientid(), getLastInsertID(), getTemplateNr(f.getQuestionnaireName()));
		for(IAnswer a : f.getAnswers()){
				int k = insertAnswer(a.toString());
				insertDatasetToAnswer(j,k, typ.get(a.getTyp()));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	/** Search the right template
	 * @param template is the name of the searched template
	 * @return the id of this template
	 */
	private int getTemplateNr(String template) {
		int i = 1;	
		String stm = "SELECT id FROM q_template_name WHERE name = ?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, template);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()){
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return i;
	}

	/** Create a new dataset to questionnair_has_answer
	 * @param j the id of the questionnaire
	 * @param k the answer id into the database
	 * @param l the type of question
	 * @throws SQLException
	 */
	private void insertDatasetToAnswer(int j, int k, int l) throws SQLException {
		String stm = "INSERT INTO questionnair_has_answer (id, answer_id, typ) VALUES (?,?,?);";
		pst = con.prepareStatement(stm);
		pst.setInt(1, j);
		pst.setInt(2, k);
		pst.setInt(3, l);
		pst.execute();
	}

	/** Create a new questionnaire
	 * @param id is the id of the patient
	 * @param i is the questionnair_has_answer id
	 * @param qId is the id of template
	 * @return the questionnair_has_answer id
	 * @throws SQLException
	 */
	private int createQuestionnaireDataSet(int id, int i, int qId) throws SQLException {
		String stm = "INSERT INTO questionnaire (patient_patient_id, date, template_name_id, answer_id) VALUES (?,?,?,?);";
		pst = con.prepareStatement(stm);
		pst.setInt(1, id);
		pst.setDate(2, new java.sql.Date(new Date().getTime()));
		pst.setInt(3, qId);
		pst.setInt(4, i);
		pst.execute();
		return i;
	}

	/** Search the last questionnair_has_answer entry
	 * @return the the actual questionnair_has_answer id
	 * @throws SQLException
	 */
	private int getLastInsertID() throws SQLException {
		int i = 1;
		String stm = "SELECT MAX(id) FROM questionnair_has_answer;";
		pst2 = con2.prepareStatement(stm);
		pst2.execute();
		rs2 = pst2.getResultSet();
		if(rs2.next()){
			if(null != rs2.getObject(1)){
			i+=rs2.getInt(1);
			}
		}
		return i;
	}

	@Override
	public int insertAnswer(String a) throws SQLException{
		int i = 1;
		String stm = "SELECT id FROM answer WHERE answer = ?;";
		String stm2 = "INSERT INTO answer (answer) VALUES ('"+a+"');";
		pst2 = con2.prepareStatement(stm);
		pst2.setString(1, a);
		pst2.execute();
		rs2 = pst2.getResultSet();
		System.out.println(pst2);
		System.out.println(rs2);
		if(rs2.next()){
			i=rs2.getInt("id");
		}else{
			pst2 = con2.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
			pst2.executeUpdate();
			rs2 = pst2.getGeneratedKeys();
			if(rs2.first()){
				i = rs2.getInt(1);
			}
		}
		return i;
	}

	public void setExeption(SecurityException e) {
		// TODO Auto-generated method stub
		
	}

	public void setExeption(IOException e) {
		// TODO Auto-generated method stub
		
	}
}
