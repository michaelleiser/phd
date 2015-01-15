package org.bfh.phd.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.bfh.phd.Department;
import org.bfh.phd.Department_Has_Staff;
import org.bfh.phd.FilledQuestionnaire;
import org.bfh.phd.Patient;
import org.bfh.phd.Questionnaire;
import org.bfh.phd.Staff;
import org.bfh.phd.questionnaire.QuestionnaireTools;

/**
 * An Entity manager contains all entities.
 * 
 * @author leism3, koblt1
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
	 * Activate or deactivate a staff member
	 * @param s
	 *            is the staff member
	 * @param b
	 *            activate = true / deactivate = false
	 */
	public void setActivateStaff(Staff s, boolean b);
	
	/**
	 * Set the group key for a staff in a department group.
	 * @param s
	 * 			is the staff
	 * @param dhs
	 * 			is the department group
	 * @param secret
	 * 			is the encrypted group key
	 */
	public void setGroupKey(Staff s, Department_Has_Staff dhs, String secret);
	
	/**
	 * @return
	 * 			a list of all department groups
	 */
	public List<Department_Has_Staff> get_Department_Has_Staffs();

	/**
	 * @param id
	 * 			is the department id
	 * @return
	 * 			the department with the specified id
	 */
	public Department getDepartment(int id);

	/**
	 * @return
	 * 			a list of all departments
	 */
	public List<Department> getDepartments();
	
	/**
	 * Create a new department with the staff as the owner of the department group.
	 * @param d
	 * 			is the department
	 * @param s
	 *          is the staff that creates the department
	 * @param key
	 * 			is the encrypted group key from the staff
	 */
	public void createDepartment(Department d, Staff s, String key);

	/**
	 * Add a new staff member to a department group.
	 * @param name
	 *          is the name of the department
	 * @param s
	 * 			is the staff member
	 */
	public void addToDepartment(String name, Staff s);
	
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
	 * Returns a list of staffs in a group except yourself.
	 * @param name
	 * 			of the staff
	 * @param dhs
	 * 			is the department group
	 * @param staff
	 * 			is you
	 * @return
	 * 			a list of staffs in a group except yourself.
	 */
	public List<Staff> searchStaffsInGroup(String name, Department_Has_Staff dhs, Staff staff);

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
	 * Update the patient.
	 * @param patient
	 * 			to be updated
	 */
	public void updatePatient(Patient patient);

	/** 
	 * @param id its the identification number of a answer set
	 * @param questionnaireName is the name of the template, that questions we need. 
	 * @return a list of filled questionnaires pairs
	 */
	public abstract IFilledQuestionnaire getFilledQuestion(int id, String questionnaireName);

//	/** Search the Questionnaires from one person
//	 * @param id is the patient identification number
//	 * @return a list of Questionnaire data set
//	 */
//	public abstract List<Questionnaire> searchQuestionnaires(int id);

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
	public void deleteTemplateQuestion(QuestionnaireTools q) throws SQLException;

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
