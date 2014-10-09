package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;


public class QuestionCheckbox implements Question <String, String> {
	
	private String type;
	private String question;
	private List<String> answerPossibilities = new ArrayList<String>();

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
		answerPossibilities.add(o);
	}

	@Override
	public List<String> getAnswerPossibilities() {
		return answerPossibilities;
	}

	@Override
	public void setAnswerPossibilities(List<String> o) {
		answerPossibilities = o;
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