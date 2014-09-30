package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;


public class QuestionRadioButton implements Question <String, String> {
	
	private String type;
	private String question;
	private List<String> possibilities = new ArrayList<String>();

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
		return type + " " + question;
	}

	@Override
	public void addAnswerPossibility(String o) {
		possibilities.add(o);
	}

	@Override
	public List<String> getAnswerPossibilities() {
		return possibilities;
	}

	@Override
	public void setAnswerPossibilities(List<String> o) {
		possibilities = o;
	}

}
