package org.bfh.phd.questionary;

import java.util.List;


public class QuestionString implements Question <String, String> {
	
	private String type;
	private String question;

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void setType(String o) {
		type = o;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(String o) {
		question = o;
	}

	@Override
	public String toString(){
		return id + " " + type + " " + question;
	}

	@Override
	public void addAnswerPossibility(String o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAnswerPossibilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAnswerPossibilities(List<String> o) {
		// TODO Auto-generated method stub
		
	}

	
	private int id;

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
}
