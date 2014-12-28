package org.bfh.phd;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;
import org.bfh.phd.interfaces.IQuestion;

public class FilledQuestionnaire implements IFilledQuestionnaire {

	private Timestamp date;
	private int id;
	private String questionnaireName;
	private List<IAnswer> answers = new ArrayList<IAnswer>();
	private List<IQuestion> questions = new ArrayList<IQuestion>();
	private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	
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

	@Override
	public ArrayList<ArrayList<String>> getList(){		// TODO ???
//		for(int i = 0; i<answers.size(); i++){
//			ArrayList<String> a = new ArrayList<String>();
//			a.add(questions.get(i).toString());
//			a.add(answers.get(i).toString());
//			list.add(a);
//		}
//		return list;
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void save(LoginController lc){			// TODO ???
		System.out.println("save");
	}
	
	public void saveFilledQuestionnaire(LoginController lc){
		lc.saveFilledQuestionnaire();
	}
	
	public void saveUpdate(LoginController lc){
		lc.saveUpdate();
	}
}
