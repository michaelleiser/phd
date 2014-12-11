package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;
import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.IQuestion;


public class QuestionCheckbox implements IQuestion <String, String, List<String>, List<String>> {
	
	private String question;
	private int id;
	private static int dbId;
	private List<String> answerPossibilities = new ArrayList<String>();
	private List<String> answer = new ArrayList<String>();
	private String type = "Checkbox";

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

	public List<String> getAnswerPossibilities() {
		return answerPossibilities;
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
	public List<String> getAnswer() {
		return answer;
	}
	
	@Override
	public void setAnswerPossibilities(List<String> o) {
		this.answerPossibilities = o;		
	}

	public void setAnswer(List<String> s) {
		this.answer = s;
	}
	
	public void save(LoginController lc){
		lc.updateAnswer(this);
	}

	@Override
	public int getDBid() {
		return dbId;
	}
}