package org.bfh.phd.interfaces;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author leism3, koblt1
 */
public interface IFilledQuestionnaire {

	/** Get the name of this questionnaire.
	 * @return the name of this questionnaire
	 */
	public abstract String getQuestionnaireName();

	/** Set the name of this questionnaire.
	 * @param questionnaireName is the questionnairename of this questionnaire
	 */
	public abstract void setQuestionnaireName(String questionnaireName);

	/** Get the current day from the Questionnaire.
	 * @return the date of creating the questionnaire
	 */
	public abstract Timestamp getDate();

	/** Set the Date of creating questionnaire.
	 * @param date insert the date of creating
	 */
	public abstract void setDate(Timestamp date);

	/** Get all questions of this questionnaire.
	 * @return a list of questions
	 */
	public abstract List<IQuestion> getQuestions();

	/** Insert a list of questions, override all other questions into the Object.
	 * @param questions a list of filled questions
	 */
	public abstract void setQuestions(List<IQuestion> questions);

	/** Add a question to the list of questions.
	 * @param question the question that's add to the list
	 */
	public abstract void addQuestions(IQuestion question);

	/** Get all answers from a questionnaire.
	 * @return a list of answers
	 */
	public abstract List<IAnswer> getAnswers();

	/** Insert a list of answers, override all other answers into the Object.
	 * @param answers a list of filled answers
	 */
	public abstract void setAnswers(List<IAnswer> answers);

	/** Add a answer to the list of answers.
	 * @param answer the answer that's add to the list
	 */
	public abstract void addAnswers(IAnswer answer);
	
	/** This method get back the id of the patient
	 * @return the id of patient
	 */
	public int getPatientId();
	
	/** Set the patient id
	 * @param id the patient number
	 */
	public void setPatientId(int id);
}