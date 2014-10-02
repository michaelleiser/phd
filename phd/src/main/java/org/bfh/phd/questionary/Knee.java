package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "knee", eager = true)
@ViewScoped
public class Knee {
	
	private int id;
	private List<Question> questions = new ArrayList<Question>();
	private List<Answer> answers = new ArrayList<Answer>();
	
	public Knee(){
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void addQuestion(Question question) {
		questions.add(question);
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public void safe(){
		System.out.println("save" + questions + answers);
		for(Answer a : answers){
			System.out.println("save..." + a);
		}
	}
	
	
	
	public void addString(final AjaxBehaviorEvent event){
		
		System.out.println("->" + answerString);
		Answer a = new AnswerString();
		a.setAnswer(answerString);
		this.addAnswer(a);
	}
	
	public void addRadioButton(final AjaxBehaviorEvent event){
		System.out.println("->" + answerRadioButton);
		Answer a = new AnswerRadioButton();
		a.setAnswer(answerRadioButton);
		this.addAnswer(a);
	}
	
	public void addCheckbox(final AjaxBehaviorEvent event){
		System.out.println("->" + answerCheckbox);
		Answer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		this.addAnswer(a);
	}
	


	private String answerString;
	public String getAnswerString() {
		return answerString;
	}
	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
	
	private String answerRadioButton;
	public String getAnswerRadioButton() {
		return answerRadioButton;
	}
	public void setAnswerRadioButton(String answerRadioButton) {
		this.answerRadioButton = answerRadioButton;
	}
	
	private List<String> answerCheckbox;
	public List<String> getAnswerCheckbox() {
		return answerCheckbox;
	}
	public void setAnswerCheckbox(List<String> answerCheckbox) {
		this.answerCheckbox = answerCheckbox;
	}
}
