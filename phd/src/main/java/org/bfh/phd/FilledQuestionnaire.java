package org.bfh.phd;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.IQuestion;

/**
 * @author leism3, koblt1
 *
 */
public class FilledQuestionnaire implements IFilledQuestionnaire {

	private Timestamp date;
	private int id, patientid;
	private String questionnaireName;
	private List<IAnswer> answers = new ArrayList<IAnswer>();
	private List<IQuestion> questions = new ArrayList<IQuestion>();
	
	@Override
	public String getQuestionnaireName() {
		return questionnaireName;
	}

	@Override
	public void setQuestionnaireName(String questionnaireName) {
		this.questionnaireName = questionnaireName;
	}

	@Override
	public Timestamp getDate() {
		return date;
	}

	@Override
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public List<IQuestion> getQuestions() {
		return questions;
	}

	@Override
	public void setQuestions(List<IQuestion> questions) {
		this.questions = questions;
	}	

	@Override
	public void addQuestions(IQuestion question) {
		this.questions.add(question);
	}

	@Override
	public List<IAnswer> getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(List<IAnswer> answers) {
		this.answers = answers;
	}

	@Override
	public void addAnswers(IAnswer answer) {
		this.answers.add(answer);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void saveFilledQuestionnaire(LoginController lc){
		lc.saveFilledQuestionnaire();
	}
	
	public void saveUpdate(LoginController lc){
		lc.saveUpdate();
	}

	@Override
	public int getPatientId() {
		return patientid;
	}

	@Override
	public void setPatientId(int id) {
		this.patientid = id;		
	}
}
