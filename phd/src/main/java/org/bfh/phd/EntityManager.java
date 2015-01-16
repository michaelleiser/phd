package org.bfh.phd;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IEntityManager;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.IQuestion;
import org.bfh.phd.questionnaire.AnswerCheckbox;
import org.bfh.phd.questionnaire.AnswerRadioButton;
import org.bfh.phd.questionnaire.AnswerString;
import org.bfh.phd.questionnaire.QuestionCheckbox;
import org.bfh.phd.questionnaire.QuestionRadioButton;
import org.bfh.phd.questionnaire.QuestionString;
import org.bfh.phd.questionnaire.QuestionnaireTools;

/**
 * The Entity manager contains all entities.
 * All entities are manageable and manipulatable through this manager.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "entityManager", eager = true)
@SessionScoped
public class EntityManager implements IEntityManager, Serializable {

	private static final long serialVersionUID = 1L;

	private List<Staff> staffs;	
	private List<Department_Has_Staff> department_Has_Staffs;
	private List<Patient> patients;
	private List<Department> departments;
	@SuppressWarnings("rawtypes")
	private List<IQuestion> questions;
	private List<String> operationtypes;
	private List<FilledQuestionnaire> filledQuestionnaires;
	private HashMap<String, FilledQuestionnaire> emptyQuestionnaires;
	private HashMap<String, String> csvs = new HashMap<String, String>();

	private Map<String, Integer> type;
	@SuppressWarnings("unused")
	private String templatename = "";
	private FilledQuestionnaire tmp;

	private MyConnection mycon = null;

	private Connection con = null, con2 = null;
	private PreparedStatement pst = null, pst2 = null;
	private ResultSet rs = null, rs2 = null;
	
	private PaginatorPatientData paginatorPatientData = new PaginatorPatientData();
	private PaginatorGroup paginatorGroup = new PaginatorGroup();

	public EntityManager() {
		mycon = new MyConnection();
		initDepartment();
		initStaff();
		initDepartment_Has_Staff();
		initPatient();
		initOperationTyp();
		initType();
		initQuestionnaire();
		initEmptyQuestionnaire();
	}

	@Override
	public Staff registerNew(Staff s, boolean activated) {
		long l = 0;
		String stm = "INSERT INTO staff(name, salt, password, privateKey, publicKey, role_role_id, isActivated) VALUES(?, ?, ?, ?, ?, ?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, s.getName());
			pst.setString(2, s.getSalt());
			pst.setString(3, s.getPassword());
			pst.setString(4, s.getPrivateKey());
			pst.setString(5, s.getPublicKey());
			pst.setInt(6, s.getRole());
			pst.setString(7, Boolean.toString(activated));
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if(rs.next()){
				l = rs.getLong(1);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initStaff();
		return this.getStaff((int) l);
	}

	@Override
	public void setActivateStaff(Staff s, boolean b) {
		String stm = "UPDATE staff SET isActivated=? WHERE staff_id=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, Boolean.toString(b));
			pst.setInt(2, s.getId());
			pst.execute();
			rs = pst.getResultSet();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		initStaff();
		initDepartment_Has_Staff();
	}

	@Override
	public void setGroupKey(Staff s, Department_Has_Staff dhs, String secret) {
		String stm = "UPDATE department_has_staff SET encryptedKey=? WHERE department_department_id=? AND staff_staff_id=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, secret);
			pst.setInt(2, dhs.getDepartment_has_staff_id());
			pst.setInt(3, s.getId());
			pst.execute();
			rs = pst.getResultSet();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		initDepartment_Has_Staff();
	}

	/**
	 * Get the department group with all staffs of a specific department.
	 * @param d
	 * 			is the department
	 * @return
	 * 			the department group
	 */
	public Department_Has_Staff getDepartment_Has_Staff(Department d) {
		Department_Has_Staff dhs = new Department_Has_Staff();
		String stm = "SELECT * FROM department_has_staff WHERE department_department_id=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, d.getDepartment_id());
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				dhs.setDepartment_has_staff_id(rs
						.getInt("department_department_id"));
				dhs.setDepartment(d);
				dhs.addStaff(this.getStaff(rs.getInt("staff_staff_id")));
				dhs.addEncryptedGroupKey(rs.getString("encryptedKey"));
				if (Boolean.parseBoolean(rs.getString("owner"))) {
					dhs.setOwner(this.getStaff(rs.getInt("staff_staff_id")));
				}
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dhs;
	}

	@Override
	public List<Department_Has_Staff> get_Department_Has_Staffs() {
		return this.department_Has_Staffs;
	}

	@Override
	public Department getDepartment(int id) {
		for(Department d : this.departments) {
			if(d.getDepartment_id() == id) {
				return d;
			}
		}
		return null;
	}

	@Override
	public List<Department> getDepartments() {
		return this.departments;
	}

	@Override
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
			if (rs.next()) {
				System.err.println("EntityManager - Department already exists");
			} else {
				pst = con.prepareStatement(stm2,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, d.getName());
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				int id = 0;
				if (rs.next()) {
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
		initDepartment();
	}

	@Override
	public void addToDepartment(String name, Staff s) {
		String stm1 = "SELECT * FROM department WHERE name=?;";
		String stm2 = "INSERT INTO department_has_staff(department_department_id, staff_staff_id, owner) VALUES(?,?,?);";
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				pst = con.prepareStatement(stm2);
				pst.setInt(1, rs.getInt("department_id"));
				pst.setInt(2, s.getId());
				pst.setString(3, "false");
				// pst.setString(4, "encryptedKey"); // Not necessary yet, written in activation process
				pst.executeUpdate();
			} else {
				System.err.println("EntityManager - Department does not exist");
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initDepartment();
	}

	@Override
	public List<Staff> getStaffs() {
		return this.staffs;
	}

	@Override
	public List<Staff> getStaffs(String name) {
		List<Staff> staff = new ArrayList<Staff>();
		for(Staff s : this.staffs){
			if(name.equals("") || s.getName().contains(name)){
				staff.add(s);
			}
		}
		return staff;
	}

	@Override
	public List<Staff> searchStaffsInGroup(String name, Department_Has_Staff dhs, Staff staff) {
		List<Staff> staffs = new ArrayList<Staff>();
		for (Staff s : dhs.getStaffs()) {
			if (name.equals("") || s.getName().toUpperCase().contains(name.toUpperCase())) {
				staffs.add(s);
			}
		}
		staffs.remove(staff);
		paginatorGroup.setSize(staffs.size());
		return staffs;
	}

	@Override
	public Staff getStaff(int id) {
		for(Staff s : this.staffs){
			if(s.getId() == id){
				return s;
			}
		}
		return null;
	}

	@Override
	public Staff getStaff(String name, String password) {
		for(Staff s : this.staffs){
			if(s.getName().equals(name) && s.getPassword().equals(password)){
				return s;
			}
		}
		return null;
	}

	@Override
	public void updateStaff(Staff s) {
		String stm = "UPDATE staff SET name=?, salt=?, password=?, privateKey=?, publicKey=? WHERE staff_id=?";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, s.getName());
			pst.setString(2, s.getSalt());
			pst.setString(3, s.getPassword());
			pst.setString(4, s.getPrivateKey());
			pst.setString(5, s.getPublicKey());
			pst.setInt(6, s.getId());
			pst.executeUpdate();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
	}

	@Override
	public List<Patient> getPatients() {
		return this.patients;
	}

	@Override
	public void createPatient(Patient p, Department_Has_Staff dhs, Staff s) {
		String stm = "INSERT INTO patient(readaccess, writeaccess, insertaccess, staff_staff_id, encryptedPersonalData, department_department_id) VALUES(?, ?, ?, ?, ?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, "false");
			pst.setString(2, "false");
			pst.setString(3, "false");
			pst.setInt(4, s.getId());
			pst.setString(5, p.getPersonalData());
			pst.setInt(6, dhs.getDepartment().getDepartment_id());
			pst.executeUpdate();
			closeWithoutRs();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeWithoutRs();
		}
		initPatient();
	}

	@Override
	public void updatePatient(Patient p) {
		String stm = "UPDATE patient SET readaccess=?, writeaccess=?, insertaccess=?, encryptedPersonalData=? WHERE patient_id=?";
		init();
		try {
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

	@Override
	public List<FilledQuestionnaire> getFilledQuestionnaires() {
		return filledQuestionnaires;
	}

	public FilledQuestionnaire getFilledQuestionnaire2(int id) {
		for (FilledQuestionnaire fq : filledQuestionnaires) {
			if (fq.getId() == id) {
				this.tmp = fq;
				return fq;
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateAnswer(IQuestion iq) {
		for (int i = 0; i < tmp.getQuestions().size(); i++) {
			if (tmp.getQuestions().get(i).getQuestion().equals(iq.getQuestion())) {
				tmp.getQuestions().get(i).setAnswer(iq.getAnswer());
				tmp.getAnswers().get(i).setAnswer(iq.getAnswer());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void updateQuestionnaireHasAnswer() {
		System.out.println("update");
		init();
		String stm2 = "UPDATE questionnaire_has_answer SET answer_id=? WHERE default_id=?;";
		try {
			int j = 0;
			for (IQuestion iq : tmp.getQuestions()) {
				int i = 0;
				if(iq instanceof QuestionCheckbox){
					i = insertAnswer(iq.getAnswerToString());
				}else{
					i = insertAnswer(iq.getAnswer().toString());
				}
				pst = con.prepareStatement(stm2);
				pst.setInt(1, i);
				pst.setInt(2, tmp.getAnswers().get(j).getDb());
				pst.executeUpdate();
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initQuestionnaire();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public FilledQuestionnaire getFilledQuestion(int id,
			String questionnaireName) {
		List<IQuestion> q = getQuestions(questionnaireName);
		List<IAnswer> a = getAnswers(id);
		FilledQuestionnaire f = new FilledQuestionnaire();
		f.setId(id);
		f.setQuestionnaireName(questionnaireName);
		if (q.size() == a.size()) {
			for (int i = 0; i < a.size(); i++) {
				if (q.get(i) instanceof QuestionCheckbox) {
					AnswerCheckbox b = (AnswerCheckbox) a.get(i);
					q.get(i).setDBid(b.getDb());
					f.addAnswers(b);
					String[] t = new String[b.getAnswerList().size()];
					int j = 0;
					for(String s : b.getAnswerList()){
						t[j] = s;
						j++;
					}
					q.get(i).setAnswer(t);
					f.addQuestions(q.get(i));
				} else if (q.get(i) instanceof QuestionRadioButton) {
					AnswerRadioButton b = new AnswerRadioButton();
					b.setDb(a.get(i).getDb());
					b.setAnswer(a.get(i).toString());
					q.get(i).setDBid(b.getDb());
					f.addAnswers(b);
					q.get(i).setAnswer(b.getAnswer());
					f.addQuestions(q.get(i));
				} else if (q.get(i) instanceof QuestionString) {
					AnswerString b = new AnswerString();
					b.setDb(a.get(i).getDb());
					b.setAnswer(a.get(i).toString());
					q.get(i).setDBid(b.getDb());
					f.addAnswers(b);
					q.get(i).setAnswer(b.getAnswer());
					f.addQuestions(q.get(i));
				} else {
				}
			}
			String stm = "SELECT date, patient_patient_id FROM questionnaire q JOIN questionnaire_template_name tn ON q.questionnaire_template_name_id = tn.questionnaire_template_name_id WHERE questionnaire_has_answer_id = ?;";
			try {
				init();
				pst = con.prepareStatement(stm);
				pst.setInt(1, id);
				pst.execute();
				rs = pst.getResultSet();
				while (rs.next()) {
					f.setDate(rs.getTimestamp("date"));
					f.setPatientId(rs.getInt("patient_patient_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				 close();
			}
		}
		return f;
	}
	
	private String[] StringToArray(String s){
	s = s.replace("[", "");
	s = s.replace("]", "");
	s = s.replace("\"", "");
//	s = s.replace(", ", ",");
	String[] list = s.split(",");
	return list;
}
	
	public List<Questionnaire> searchQuestionnaires(int id) {
		System.out.println("EM 520");
		init();
		ArrayList<Questionnaire> questionnaris = new ArrayList<Questionnaire>();
		String stm1 = "SELECT * FROM quest LEFT JOIN op ON quest.op_id = op.id WHERE patient_id="
				+ id + ";";
		try {
			pst = con.prepareStatement(stm1);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Questionnaire q = new Questionnaire();
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
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<IQuestion> getQuestions(String quest) {
		List<IQuestion> questions = new ArrayList<IQuestion>();
		init();
		String stm = "SELECT questionnumber, type, question, possibilities_id FROM question_has_template t JOIN questionnaire_template_name n ON t.questionnaire_template_name_id = n.questionnaire_template_name_id JOIN question q2 ON t.question_id = q2.question_id JOIN question_type ON q2.question_type_id = question_type.question_type_id WHERE name=? order by questionnumber;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, quest);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				IQuestion question = null;
				int id = rs.getInt("questionnumber");
				int pos_id = rs.getInt("possibilities_id");
				String t = rs.getString("type");
				String q = rs.getString("question");
				if (t.equals("String")) {
					question = new QuestionString();
					question.setId(id);
					question.setQuestion(q);
					question.setAnswer(" ");
				} else if (t.equals("RadioButton")) {
					question = new QuestionRadioButton();
					question.setId(id);
					question.setQuestion(q);
					question.setAnswerPossibilities(getPossibleAnswers(pos_id));
					question.setAnswer(" ");
				} else if (t.equals("Checkbox")) {
					question = new QuestionCheckbox();
					question.setId(id);
					question.setQuestion(q);
					question.setAnswerPossibilities(getPossibleAnswersAsArray(pos_id));
					question.setAnswer(null);
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
		return questions;
	}

	/**
	 * return the possible answers from the selected question
	 * 
	 * @param id
	 *            the identifier key to find the possibilities into the database
	 * @return the answer as a collection of Strings
	 */
	private List<String> getPossibleAnswers(int id) {
		init2();
		List<String> list = new ArrayList<String>();
		String stm = "SELECT answer FROM possibilities p JOIN pos_answer pa ON p.pos_answer_id=pa.pos_answer_id WHERE p.possibilities_id =?;";
		try {
			pst2 = con2.prepareStatement(stm);
			pst2.setInt(1, id);
			pst2.execute();
			rs2 = pst2.getResultSet();
			while (rs2.next()) {
				list.add(rs2.getString("answer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close2();
		}
		return list;
	}
	
	/**
	 * return the possible answers from the selected question
	 * 
	 * @param id
	 *            the identifier key to find the possibilities into the database
	 * @return the answer as a collection of Strings
	 */
	private String[] getPossibleAnswersAsArray(int id) {
		List<String> list = getPossibleAnswers(id);
		String[] s = new String[list.size()];
		for(int j = 0; j < list.size(); j++){
			s[j] = list.get(j);
		}
		return s;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<IAnswer> getAnswers(int id) {
		List<IAnswer> answers = new ArrayList<IAnswer>();
		init();
		String stm = "SELECT default_id, answer, t.type AS type FROM questionnaire_has_answer qha JOIN answer a ON qha.answer_id = a.answer_id JOIN question_type t ON qha.question_type_id = t.question_type_id WHERE qha.questionnaire_id = ? ORDER BY default_id asc;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				IAnswer a = null;
				String type = rs.getString("type");
				String answer = rs.getString("answer");
				if (type.equals("String")) {
					a = new AnswerString();
					a.setDb(rs.getInt("default_id"));
					a.setAnswer(answer);
				} else if (type.equals("RadioButton")) {
					a = new AnswerRadioButton();
					a.setDb(rs.getInt("default_id"));
					a.setAnswer(answer);
				} else if (type.equals("Checkbox")) {
					a = new AnswerCheckbox();
					a.setDb(rs.getInt("default_id"));
					a.setAnswer(StringToArray(answer));
				} else {
					
				}
				answers.add(a);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return answers;
	}

	/**
	 * Search all questionnaires from one patient
	 * 
	 * @param id
	 *            is the identification number of the patient
	 * @return
	 */
	public List<ListOfQuestionnaire> searchDatas(int id) {
		List<ListOfQuestionnaire> quest = new ArrayList<ListOfQuestionnaire>();
		String stm = "SELECT date, questionnaire_has_answer_id AS answer_id, name FROM questionnaire q JOIN questionnaire_template_name n ON q.questionnaire_template_name_id = n.questionnaire_template_name_id WHERE q.patient_patient_id = ? ORDER BY date DESC;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, id);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				ListOfQuestionnaire list = new ListOfQuestionnaire();
				list.setDate(rs.getTimestamp("date"));
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

	@Override
	public void addQuestionnaireTemplate(String type, String question,
			String nameOfTemplate, List<String> pos, int fragenr) {
		init();
		long key = 0, templatenr = 0;
		String stm = "INSERT INTO question (possibilities_id, question_type_id, question, questionnumber) VALUES(?,?,?,?);";
		String stm2 = "INSERT INTO questionnaire_template_name(name) VALUES (?);";
		String stm3 = "INSERT INTO question_has_template (questionnaire_template_name_id, question_id) VALUES (?,?);";
		String stm4 = "SELECT questionnaire_template_name_id FROM questionnaire_template_name WHERE name = ?;";
		try {
			int i = 0;
			if (type != "String") {
				i = setPossibilities(pos);
			}
			int j = (Integer) this.type.get(type);
			pst = con.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, i);
			pst.setInt(2, j);
			pst.setString(3, question);
			pst.setInt(4, fragenr);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
			}
			pst = con.prepareStatement(stm4);
			pst.setString(1, nameOfTemplate);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				templatenr = rs.getInt("questionnaire_template_name_id");
			} else {
				pst = con.prepareStatement(stm2,
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, nameOfTemplate);
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					templatenr = rs.getLong(1);
				}
			}
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
		initOperationTyp();
		initEmptyQuestionnaire();
	}

	/**
	 * Insert all possibilities that have a question to the database. Attention
	 * its create duplicates in case of edit some possible answer that the other
	 * question isn't corrupt.
	 * 
	 * @param pos
	 *            the list of possible answers
	 * @return the generated key of this set of possibilities
	 * @throws SQLException
	 */
	private int setPossibilities(List<String> pos) throws SQLException {
		int i = 1;
		int l = 0;
		String stm = "SELECT MAX(possibilities_id) FROM possibilities;";
		String stm2 = "INSERT INTO pos_answer (answer) VALUES (?);";
		String stm3 = "INSERT INTO possibilities (possibilities_id,pos_answer_id) VALUES (?,?);";
		pst = con.prepareStatement(stm);
		pst.execute();
		rs = pst.getResultSet();
		if (rs.next()) {
			i += rs.getInt("MAX(possibilities_id)");
		}
		for (int j = 0; j < pos.size(); j++) {
			pst = con.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, pos.get(j));
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				l = (int) rs.getLong(1);
			}
			pst = con.prepareStatement(stm3);
			pst.setInt(1, i);
			pst.setInt(2, (int) l);
			pst.execute();
		}
		return i;
	}

	public FilledQuestionnaire getEmptyQuestionnaire(String s) {
		tmp = emptyQuestionnaires.get(s);
		return tmp;
	}

	@Override
	public Map<String, Integer> getType() {
		return type;
	}

	public List<String> getTemplateNames() {
		return operationtypes;
	}

	@Override
	public List<QuestionnaireTools> getTemplate(String name) {
		init();
		List<QuestionnaireTools> quest = new ArrayList<QuestionnaireTools>();
		String stm = "SELECT questionnumber, q.question_id, name, q.possibilities_id, question, type FROM question_has_template t  join questionnaire_template_name n On n.questionnaire_template_name_id=t.questionnaire_template_name_id join question q On t.question_id=q.question_id join question_type ty On q.question_type_id=ty.question_type_id WHERE name = ? ORDER BY questionnumber;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				QuestionnaireTools q = new QuestionnaireTools();
				q.setId(rs.getInt("questionnumber"));
				q.setDbId(rs.getInt("question_id"));
				q.setQuestion(rs.getString("question"));
				q.setType(rs.getString("type"));

				List<String> list = getPossibleAnswers(rs.getInt("possibilities_id"));
				for (String s : list) {
					q.addPossibleAnswer(s);
				}
				quest.add(q);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return quest;
	}
		
	@Override
	public void deleteTemplateQuestion(QuestionnaireTools q) throws SQLException {
		init();
		init2();
		String stm = "DELETE FROM question WHERE question_id=?;";
		if (q.getType() != "String") {
			String stm1 = "SELECT possibilities_id FROM question WHERE question_id=?;";
			String stm2 = "SELECT pos_answer_id FROM possibilities WHERE possibilities_id=?;";
			String stm3 = "DELETE FROM possibilities WHERE possibilities_id=?";
			String stm4 = "DELETE FROM pos_answer WHERE pos_answer_id=?";
			int i = 0;

			pst = con.prepareStatement(stm1);
			pst.setInt(1, q.getDbId());
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				i = rs.getInt("possibilities_id");
			}
			pst = con.prepareStatement(stm2);
			pst.setInt(1, i);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				pst2 = con2.prepareStatement(stm4);
				pst2.setInt(1, rs.getInt("pos_answer_id"));
				pst2.execute();
			}
			pst = con.prepareStatement(stm3);
			pst.setInt(1, i);
			pst.execute();
		}
		pst = con.prepareStatement(stm);
		pst.setInt(1, q.getDbId());
		pst.execute();
		closeWithoutRs2();
		close();
		initEmptyQuestionnaire();
	}

	@Override
	public void editQuestion(QuestionnaireTools q) throws SQLException {
		init();
		init2();
		String stm = "UPDATE question SET question=? WHERE question_id=?;";
		String stm1 = "SELECT possibilities_id FROM question WHERE question_id=?;";
		String stm2 = "SELECT pos_answer_id FROM possibilities WHERE possibilities_id=?;";
		String stm3 = "UPDATE pos_answer SET answer=? WHERE pos_answer_id=?;";
		pst = con.prepareStatement(stm);
		pst.setString(1, q.getQuestion());
		pst.setInt(2, q.getDbId());
		pst.executeUpdate();
		if (q.getType() != "String") {
			pst = con.prepareStatement(stm1);
			pst.setInt(1, q.getDbId());
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				int i = rs.getInt("possibilities_id");
				pst = con.prepareStatement(stm2);
				pst.setInt(1, i);
				pst.execute();
				rs = pst.getResultSet();
				int j = 0;
				while (rs.next()) {
					pst2 = con2.prepareStatement(stm3);
					pst2.setString(1, q.getAnswer().get(j));
					pst2.setInt(2, rs.getInt("pos_answer_id"));
					pst2.executeUpdate();
					j++;
				}
			}
		}
		closeWithoutRs2();
		close();
		initEmptyQuestionnaire();
	}

	@Override
	public void changeQuestionNr(String templateNameSelected, int eNumber) {
		System.out.println("EM 1004");
		// TODO einfügen
		init();
		String stm = "SELECT q.id, questionnumber FROM question q JOIN question_has_template t ON q.id = t.question_id JOIN questionnaire_template_name n ON t.id = n.id WHERE name = ? AND q.questionnumber > ?;";
		String stm2 = "UPDATE question SET questionnumber=? WHERE id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, templateNameSelected);
			pst.setInt(2, eNumber);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				pst = con.prepareStatement(stm2);
				pst.setInt(2, rs.getInt("id"));
				pst.setInt(1, rs.getInt("fragenr") + 1);
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// TODO delet after einfügen
	@SuppressWarnings("rawtypes")
	public void addAnswer(FilledQuestionnaire f) {
		init();
		try {
			int j = createQuestionnaireDataSet(f.getPatientId(),
					getNextInsertID(),
					getTemplateNr(f.getQuestionnaireName()));
			for (IAnswer a : f.getAnswers()) {
				int k = insertAnswer(a.toString());
				int l;
				if (a instanceof AnswerString) {
					l = 1;
				} else if (a instanceof AnswerCheckbox) {
					l = 3;
				} else {
					l = 2;
				}
				System.out.println(j);
				System.out.println(k);
				System.out.println(l);
				insertDatasetToAnswer(j, k, l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initQuestionnaire();
	}
	
	@SuppressWarnings("rawtypes")
	public void addAnswer(Patient activePatient) {
		init();
		try {
			int j = createQuestionnaireDataSet(activePatient.getPatientid(),
					getNextInsertID(),
					getTemplateNr(tmp.getQuestionnaireName()));
			for (IAnswer a : tmp.getAnswers()) {
				int k = insertAnswer(a.toString());
				int l;
				if (a instanceof AnswerString) {
					l = 1;
				} else if (a instanceof AnswerCheckbox) {
					l = 3;
				} else {
					l = 2;
				}
				insertDatasetToAnswer(j, k, l);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		initQuestionnaire();
	}

	/**
	 * Search the right template
	 * 
	 * @param template
	 *            is the name of the searched template
	 * @return the id of this template
	 */
	private int getTemplateNr(String template) {
		int i = 1;
		String stm = "SELECT questionnaire_template_name_id FROM questionnaire_template_name WHERE name = ?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, template);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * Create a new dataset to questionnaire_has_answer
	 * 
	 * @param j
	 *            the id of the questionnaire
	 * @param k
	 *            the answer id into the database
	 * @param l
	 *            the type of question
	 * @throws SQLException
	 */
	private void insertDatasetToAnswer(int j, int k, int l) throws SQLException {
		String stm = "INSERT INTO questionnaire_has_answer (questionnaire_id, answer_id, question_type_id) VALUES (?,?,?);";
		pst = con.prepareStatement(stm);
		pst.setInt(1, j);
		pst.setInt(2, k);
		pst.setInt(3, l);
		pst.execute();
	}

	/**
	 * Create a new questionnaire
	 * 
	 * @param id
	 *            is the id of the patient
	 * @param i
	 *            is the questionnaire_has_answer id
	 * @param qId
	 *            is the id of template
	 * @return the questionnaire_has_answer id
	 * @throws SQLException
	 */
	private int createQuestionnaireDataSet(int id, int i, int qId)
			throws SQLException {
		String stm = "INSERT INTO questionnaire (patient_patient_id, date, questionnaire_template_name_id, questionnaire_has_answer_id) VALUES (?,?,?,?);";
		pst = con.prepareStatement(stm);
		pst.setInt(1, id);
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		pst.setTimestamp(2, new Timestamp(now.getTime()));
		pst.setInt(3, qId);
		pst.setInt(4, i);
		pst.execute();
		return i;
	}

	/**
	 * Search the last questionnaire_has_answer entry and get back the next possible primary key
	 * 
	 * @return the the actual questionnaire_has_answer id
	 * @throws SQLException
	 */
	private int getNextInsertID() throws SQLException {
		init2();
		int i = 0;
		String stm = "SELECT MAX(questionnaire_id) FROM questionnaire_has_answer;";
		pst2 = con2.prepareStatement(stm);
		pst2.execute();
		rs2 = pst2.getResultSet();
		if (rs2.next()) {
			if (null != rs2.getObject(1)) {
				i = rs2.getInt(1);
			}
		}
		close2();
		return ++i;
	}

	@Override
	public int insertAnswer(String a) throws SQLException {
		init2();
		int i = 1;
		String stm = "SELECT answer_id FROM answer WHERE answer = ?;";
		String stm2 = "INSERT INTO answer (answer) VALUES ('" + a + "');";
		pst2 = con2.prepareStatement(stm);
		pst2.setString(1, a);
		pst2.execute();
		rs2 = pst2.getResultSet();
		if (rs2.next()) {
			i = rs2.getInt("answer_id");
		} else {
			pst2 = con2.prepareStatement(stm2, Statement.RETURN_GENERATED_KEYS);
			pst2.executeUpdate();
			rs2 = pst2.getGeneratedKeys();
			if (rs2.first()) {
				i = rs2.getInt(1);
			}
		}
		close2();
		return i;
	}

	@SuppressWarnings("rawtypes")
	public FilledQuestionnaire getEmptyQuestionnaires(String questionnaireName) {
		List<IQuestion> q = getQuestions(questionnaireName);
		FilledQuestionnaire f = new FilledQuestionnaire();
		f.setId(0);
		f.setQuestionnaireName(questionnaireName);
		for (int i = 0; i < q.size(); i++) {
			if (q.get(i) instanceof QuestionCheckbox) {
				AnswerCheckbox b = new AnswerCheckbox();
				f.addAnswers(b);
				f.addQuestions(q.get(i));
			} else if (q.get(i) instanceof QuestionRadioButton) {
				AnswerRadioButton b = new AnswerRadioButton();
				f.addAnswers(b);
				f.addQuestions(q.get(i));
			} else if (q.get(i) instanceof QuestionString) {
				AnswerString b = new AnswerString();
				f.addAnswers(b);
				f.addQuestions(q.get(i));
			} else {
			}
		}
		return f;
	}

	public boolean isLegalTemplate(String template) {
		for(String s: operationtypes){
			if(s.equals(template)){
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void generateCsvFiles() {
		long starttime = System.currentTimeMillis();
		long endtime;
		
		String total = "";
		String questionnaire = "";
			
			for (int i = 0; i < operationtypes.size(); i++) {
				String name = operationtypes.get(i);
				System.out.println(name);
				total += "\n\n" + name + "\n\n";
				questionnaire += "\n\n" + name + "\n\n";
				FilledQuestionnaire eq = emptyQuestionnaires.get(name);
				total +="ID;";
				questionnaire +="ID;";				
				for (IQuestion iq : eq.getQuestions()) {
					total += iq.getQuestion() + ";";
					questionnaire += iq.getQuestion() + ";";
				}
				total += "\n\n";
				questionnaire += "\n\n";
				for (IFilledQuestionnaire fq : filledQuestionnaires) {
					if (fq.getQuestionnaireName().equals(name)) {
						total += fq.getPatientId() + ";";
						questionnaire += fq.getPatientId() + ";";	
						System.out.println( "ID"+fq.getPatientId());
						for (IAnswer answer : fq.getAnswers()) {
							String s = answer.toString();
							s.replace("[", "");
							s.replace("]", "");
							total += s + ";";
							questionnaire += s + ";";
						}
						total += "\n";
						questionnaire += "\n";
					}
				}
				csvs.put(operationtypes.get(i), questionnaire);
				questionnaire = "";
			}
			csvs.put("total", total);
			
		endtime = System.currentTimeMillis();
		System.err.println("Das erstellen der CSVs hat "
				+ (endtime - starttime) + "[ms] benötigt");
	}

	public String getCSV(String template) {
		template = template.replace("_", " ");
		return csvs.get(template);
	}

	/**
	 * Initialization of all departments.
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
		} finally {
			close();
		}
	}

	/**
	 * Initialization of all staffs.
	 */
	private void initStaff() {
		staffs = new ArrayList<Staff>();
		String stm = "SELECT * FROM staff;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Staff s = new Staff();
				s.setId(rs.getInt("staff_id"));
				s.setName(rs.getString("name"));
				s.setSalt(rs.getString("salt"));
				s.setPassword(rs.getString("password"));
				s.setPrivateKey(rs.getString("privateKey"));
				s.setPublicKey(rs.getString("publicKey"));
				s.setRole(rs.getInt("role_role_id"));
				s.setActivated(Boolean.parseBoolean(rs.getString("isActivated")));
				staffs.add(s);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * Initialization of all department groups.
	 */
	private void initDepartment_Has_Staff() {
		this.department_Has_Staffs = new ArrayList<Department_Has_Staff>();
		for(Department d: this.departments) {
			Department_Has_Staff dhs = getDepartment_Has_Staff(d);
			this.department_Has_Staffs.add(dhs);
		}
	}

	/**
	 * Initialization of all patients.
	 */
	private void initPatient() {
		patients = new ArrayList<Patient>();
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
				p.setWriteaccess(Boolean.parseBoolean(rs
						.getString("writeaccess")));
				p.setInsertaccess(Boolean.parseBoolean(rs
						.getString("insertaccess")));
				p.setOwner(this.getStaff(rs.getInt("staff_staff_id")));
				p.setPersonalData(rs.getString("encryptedPersonalData"));
				p.setDepartment(this.getDepartment(rs
						.getInt("department_department_id")));
				patients.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * Initialization of all templates.
	 */
	public void initOperationTyp() {
		operationtypes = new ArrayList<String>();
		init();
		String stm = "SELECT name FROM questionnaire_template_name;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				operationtypes.add(rs.getString("name"));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * Initialization of all question types.
	 */
	private void initType() {
		type = new HashMap<String, Integer>();
		String stm = "SELECT * FROM question_type";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				type.put(rs.getString("type"), rs.getInt("question_type_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	/**
	 * Initialization of all filled questionnaires.
	 */
	private void initQuestionnaire() {
		init2();
		List<Integer> i = new ArrayList<Integer>();
		List<String> s = new ArrayList<String>();
		filledQuestionnaires = new ArrayList<FilledQuestionnaire>();
		String stm = "SELECT questionnaire_has_answer_id, name FROM questionnaire q JOIN questionnaire_template_name tn ON q.questionnaire_template_name_id = tn.questionnaire_template_name_id;";
		try {
			pst2 = con2.prepareStatement(stm);
			pst2.execute();
			rs2 = pst2.getResultSet();
			while (rs2.next()) {
				i.add(rs2.getInt("questionnaire_has_answer_id"));
				s.add(rs2.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close2();
		}
		for (int j = 0; j < i.size(); j++) {
			FilledQuestionnaire f = getFilledQuestion(i.get(j), s.get(j));
			filledQuestionnaires.add(f);
		}
	}

	/**
	 * Initialization of all filled questionnaires.
	 */
	private void initEmptyQuestionnaire() {
		emptyQuestionnaires = new HashMap<String, FilledQuestionnaire>();
		for (String s : operationtypes) {
			emptyQuestionnaires.put(s, getEmptyQuestionnaires(s));
		}
	}

	/**
	 * Initialization of the first database connection.
	 */
	private void init() {
		con = mycon.getConnection();
	}

	/**
	 * Initialization of the second database connection.
	 */
	private void init2(){
		con2 = mycon.getConnection();
	}

	/**
	 * Closes the database connection with result set.
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

	/**
	 * Closes the database connection with result set.
	 */
	private void close2() {
		if(rs2 != null){
		try {
			rs2.close();
			pst2.close();
			con2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst2 != null) {
				try {
					pst2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con2 != null) {
				try {
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		}
	}

	/**
	 * Closes the database connection without result set.
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

	/**
	 * Closes the database connection without result set.
	 */
	private void closeWithoutRs2() {
		try {
			pst2.close();
			con2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst2 != null) {
				try {
					pst2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con2 != null) {
				try {
					con2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

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

	@SuppressWarnings("rawtypes")
	public List<IQuestion> getQuestions() {
		return questions;
	}

	@SuppressWarnings("rawtypes") 
	public void setQuestions(List<IQuestion> questions) {
		this.questions = questions;
	}
}