package org.bfh.phd.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.bfh.phd.Department_Has_Staff;
import org.bfh.phd.FilledQuestionnaire;
import org.bfh.phd.Patient;
import org.bfh.phd.Questionnaire;
import org.bfh.phd.Staff;
import org.bfh.phd.questionnaire.QuestionnaireTools;

/**
 * @author leism3, koblt1
 *
 */
public interface IEntityManager {

	/**
	 * Register a new staff and returns the new staff.
	 * @param staff
	 * 			to be registered
	 * @param admin
	 * 			if is admin
	 * @return
	 * 			the registered staff
	 */
	public Staff registerNew(Staff staff, boolean admin);

	/**
	 * @return
	 * 			a list of all staffs
	 */
	public List<Staff> getStaffs();

	/**
	 * @param name
	 * 			to filter the return list
	 * @return
	 * 			a list of all staffs that contain the filter name
	 */
	public List<Staff> getStaffs(String name);

	/**
	 * @param id
	 * 			of the staff
	 * @return
	 * 			a staff with the specified id or null if it does not exist
	 */
	public Staff getStaff(int id);

	/**
	 * @param name
	 * 			of the staff
	 * @param password
	 * 			of the staff
	 * @return
	 * 			a staff with the specified name and password or null if it does not exist
	 */
	public Staff getStaff(String name, String password);

	/**
	 * Update the staff.
	 * @param staff
	 * 			to be updated
	 */
	public void updateStaff(Staff staff);

	/**
	 * @return
	 * 			a list of all patients
	 */
	public List<Patient> getPatients();

	/**
	 * @param staff
	 * 			the staff
	 * @return
	 * 			a list of all patients readable by the staff
	 */
	public List<Patient> getPatients(Staff staff);

	/**
	 * @param dhs
	 * 			the department group
	 * @param staff
	 * 			the staff
	 * @return
	 * 			a list of all patients in the department group readable by the staff
	 */
	public List<Patient> searchPatients(Department_Has_Staff dhs, Staff staff);

	/**
	 * @param activeUser
	 * 			the active user
	 * @param patientid
	 * 			of the patient
	 * @return
	 * 			a patient with the the specified id readable by the active user
	 */
	public Patient getPatient(Staff activeUser, int patientid);

	/**
	 * Create a new patient in the department group with the staff as the owner.
	 * @param patient
	 * 			the patient to be created
	 * @param dhs
	 * 			the department group
	 * @param staff
	 * 			the staff
	 */
	public void createPatient(Patient patient, Department_Has_Staff dhs, Staff staff);

	/**
	 * Update the patient which is writable by the staff.
	 * @param patient
	 * 			to be updated
	 * @param staff
	 * 			the staff
	 */
	public void updatePatient(Patient patient, Staff staff);

	/** 
	 * @param id its the identification number of a answer set
	 * @param questionnaireName is the name of the template, that questions we need. 
	 * @return a list of filled questionnaires pairs
	 */
	public abstract IFilledQuestionnaire getFilledQuestion(int id, String questionnaireName);

	/** Search the Questionnaires from one person
	 * @param id is the patient identification number
	 * @return a list of Questionnaire data set
	 */
	public abstract List<Questionnaire> searchQuestionnaris(int id);

	/**
	 * Search all questions to an template
	 * @param quest is the name of the template
	 * @return a list of questions
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<IQuestion> getQuestions(String quest);

	/**
	 * Search all answers to a question
	 * @param id is the key of the answer that possibilities we search
	 * @return a list of possible answers
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<IAnswer> getAnswers(int id);

	/** This Method create a new question to a new template
	 * @param typ is the type of a question.
	 * @param question the question
	 * @param nameOfTemplate
	 * @param pos they are the possible answer for this question by String a empty String.
	 */
	public abstract void addQuestionnaireTemplate(String typ, String question, String nameOfTemplate, List<String> pos, int fragenr);

	/**
	 * Initialization of all Templates 
	 */
	public abstract void initOperationTyp();

	/**
	 * @return the types of question that is usable
	 */
	public Map<String, Integer> getType();

	/** Create a complete Questionnaire 
	 * @param name is the templatename that Questionnaire we search
	 * @return a complete Questionnaire
	 */
	public List<QuestionnaireTools> getTemplate(String name);

	/** delete a question from a template
	 * @param q is the template question
	 * @throws SQLException
	 */
	public void deletTemplateQuestion(QuestionnaireTools q) throws SQLException;

	/** edit a question from a template
	 * @param q is the template question
	 * @throws SQLException
	 */
	public void editQuestion(QuestionnaireTools q) throws SQLException;

	/** insert a answer into the database
	 * @param a is the answer string 
	 * @return the generated key of this answer
	 * @throws SQLException
	 */
	public int insertAnswer(String a) throws SQLException;
	
	/** Update the question number, All number after eNumber are incremented
	 * @param templateNameSelected is the name of the template
	 * @param eNumber is the first number they are incremented
	 */
	public void changeQuestionNr(String templateNameSelected, int eNumber);
	
	/** Add all Answers to the database
	 * @param activePatient is the patient that has a new questionnaire
	 */
	public void addAnswer(Patient activePatient);

	/** Get all filled questionnaires from all departments
	 * @return a collection of FilledQuestionnaires
	 */
	public List<FilledQuestionnaire> getFilledQuestionnaires();
}
