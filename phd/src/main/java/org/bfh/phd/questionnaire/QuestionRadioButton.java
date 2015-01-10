package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.IQuestion;


public class QuestionRadioButton implements IQuestion <List<String>, String > {
	
	private String type = "RadioButton";
	private String question;
	private int dbId;
	private List<String> answerPossibilities = new ArrayList<String>();
	private String answer = "";

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

	public String getAnswer(){
		return this.answer;
	}
	
	public void setAnswer(String s){
		this.answer = s;
	}
	
	public String getType(){
		return type;
	}
	
//	public void setAnswer(final AjaxBehaviorEvent event) {
//		System.out.println("aufruf");
//		String s = event.getComponent().getAttributes().get("change").toString();
//		System.out.println(s);
//	}
	
	public void save(LoginController lc){
		lc.updateAnswer(this);
	}

	@Override
	public int getDBid() {
		return this.dbId;
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