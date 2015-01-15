package org.bfh.phd.questionnaire;

import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.IQuestion;


public class QuestionString implements IQuestion <String, String> {
	
	private String type = "String";
	private String question;
	private String answer = "";
	private static int dbId;


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
		System.err.println("setAnswerPossibities not possible because its a String question. QuestionString Line 32");		
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
	
	public String getAnswer(){
		return this.answer;
	}
	
	public void setAnswer(String s){
		this.answer = s;
	}
	
	public String getType(){
		return type;
	}

	@Override
	public String getAnswerPossibilities() {
		System.err.println("not possible");
		return null;
	}

	@Override
	public void setAnswerPossibilities(String o) {
		System.err.println("not possible");
	}
	
	public void save(LoginController lc){
		lc.updateAnswer(this);
	}

	@Override
	public int getDBid() {
		return dbId;
	}
	
	@Override
	public void setDBid(int i) {
		dbId = i;
	}
	
	@Override
	public String getAnswerToString() {
		return answer;
	}
}