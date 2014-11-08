package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.Question;


public class QuestionRadioButton implements Question <String, String, List<String>> {
	
	private String type;
	private String question;
	private List<String> answerPossibilities = new ArrayList<String>();
	private List<String> answer = new ArrayList<String>();

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
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public List<String> getAnswer(){
		return this.answer;
	}
	
	@Override
	public void setAnswer(String s){
		this.answer.add(s);
	}
}