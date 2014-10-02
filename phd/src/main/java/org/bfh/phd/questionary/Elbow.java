package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;

@ManagedBean(name = "elbow", eager = true)
@ViewScoped
public class Elbow {
	
	private int id;
	private List<Question> questions = new ArrayList<Question>();
	private List<Answer> answers = new ArrayList<Answer>();
	
	public Elbow(){
		answers.add(new AnswerString());
		answers.add(new AnswerString());
		answers.add(new AnswerRadioButton());
		answers.add(new AnswerCheckbox());
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
		EntityManager em = new EntityManager();
		em.addAnswer("elbow", answers);
		for(Answer a : answers){
			System.out.println("->>" + a);
		}
	}
	
	
	
	public void addString(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerString();
		a.setAnswer(answerString);
		answers.set(i2-1, a);
		System.out.println("->" + answerString);
	}
	
	public void addRadioButton(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerRadioButton();
		a.setAnswer(answerRadioButton);
		answers.set(i2-1, a);
		System.out.println("->" + answerRadioButton);
	}
	
	public void addCheckbox(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		answers.set(i2-1, a);
		System.out.println("->" + answerCheckbox);
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
