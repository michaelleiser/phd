package org.bfh.phd.questionnaire;

import java.util.Arrays;

import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.IQuestion;

public class QuestionCheckbox implements IQuestion <String[], String[]> {
	
	private String question;
	private int id;
	private static int dbId;
	private String[] answerPossibilities = new String[10];
	private String[] answer;
	private String type = "Checkbox";
	private int i = 0;

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
		answerPossibilities[i] = o;
		i++;
	}

	public String[] getAnswerPossibilities() {
		return answerPossibilities;
	}
	
	public String getAnswerPossibilitiesToString() {
		return Arrays.toString(answerPossibilities);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@Override
	public String[] getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswerPossibilities(String[] o) {
		this.answerPossibilities = o;		
	}

	public void setAnswer(String[] s) {
		if(s != null){
		this.answer = s;
		}
	}
	
	public String getAnswerToString(){
		StringBuilder sb = new StringBuilder();
		for(String s: answer){
			sb.append(s + ",");
		}
		if(sb.length()>1){
		sb.delete(sb.length()-1, sb.length());
		}
		return sb.toString();
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
}