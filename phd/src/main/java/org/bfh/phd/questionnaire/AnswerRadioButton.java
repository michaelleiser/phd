package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IAnswer;

public class AnswerRadioButton implements IAnswer<String, String> {
	
	private String answer = "";
	private int db;
	
	public AnswerRadioButton(){
		
	}
	public List<String> getTest(){
		ArrayList<String> a = new ArrayList<String>();
		a.add(answer);
		return a;
	}
	
	@Override
	public String getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswer(String l) {
		answer = l;
	}

	@Override
	public void addAnswer(String s){
		answer = s;
	}
	
	@Override
	public String toString(){;
		return answer.toString();
	}
	
	public List<String> asList(){
		List<String> s = new ArrayList<String>(); 
		s.add(answer);
		return s;
	}
	@Override
	public void setDb(int db) {
		this.db = db;		
	}
	@Override
	public int getDb() {
		return db;
	}
}