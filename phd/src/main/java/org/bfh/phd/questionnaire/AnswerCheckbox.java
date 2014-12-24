package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;

public class AnswerCheckbox implements IAnswer <List<String>, String> {
	
	private List<String> answer = new ArrayList<String>();
	private int db;
	
	public AnswerCheckbox(){	
	}
	
	@Override
	public List<String> getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(List<String> l) {
		answer = l;
	}

	@Override
	public void addAnswer(String s){
		answer.add(s);
	}
	
	@Override
	public String toString(){;
	return answer.toString().replace("[", "").replace("]", "");
	}

	public String getAnswersAsString() {
		return answer.toString();
	}

	@Override
	public int getDb() {
		return db;
	}

	@Override
	public void setDb(int db) {
		this.db = db;
	}
}