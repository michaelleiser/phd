package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;

public class AnswerCheckbox implements IAnswer <String[], String> {
	
	private List<String> answer = new ArrayList<String>();
	private int db;
	
	public AnswerCheckbox(){	
	}
	
	@Override
	public String[] getAnswer() {
		String[] s = new String[answer.size()];
		for(int i = 0; i < answer.size(); i++){
			s[i] = answer.get(i);
		}
		return s;
	}
	
	public List<String> getAnswerList(){
		return answer;
	}

	@Override
	public void setAnswer(String[] l) {
		answer = new ArrayList<String>();
		for(int i = 0; i < l.length; i++){
			answer.add(l[i]);
		}
	}

	@Override
	public void addAnswer(String s){
		answer.add(s);
	}
	
	@Override
	public String toString(){;
	return answer.toString().replace("[", "\"").replace("]", "\"");
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