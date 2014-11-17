package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.interfaces.IQuestion;


public class QuestionRadioButton implements IQuestion <String, String> {
	
	private String type = "RadioButton";
	private String question;
	private List<String> answerPossibilities = new ArrayList<String>();
	private List<String> answer = new ArrayList<String>();

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
		return question;
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
	
	public String getType(){
		return type;
	}
}