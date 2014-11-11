package org.bfh.phd.questionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;
import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.Answer;
import org.bfh.phd.interfaces.Question;

@ManagedBean(name = "elbow", eager = true)
@ViewScoped
public class Elbow implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Question> questions = new ArrayList<Question>();
	private List<Answer> answers = new ArrayList<Answer>();
	private static List<String> quests;
	private static String questselected;
	private List<String> answerCheckbox;
	private String answerString;
	private String answerRadioButton;
	
	public Elbow(){
	}
	
	static {
		EntityManager em = new EntityManager();
		quests = em.getTemplateNames();
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
	
	public void safe(LoginController lc){
		lc.addAnswer(answers, questselected);
	}
	
	public void addString(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerString();
		a.setAnswer(answerString);
		while(answers.size() < i2){
			answers.add(null);
		}
		answers.set(i2-1, a);
		System.out.println("->" + answerString);
	}
	
	public void addRadioButton(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerRadioButton();
		a.setAnswer(answerRadioButton);
		while(answers.size() < i2){
			answers.add(null);
		}
		answers.set(i2-1, a);
		System.out.println("->" + answerRadioButton);
	}
	
	public void addCheckbox(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		while(answers.size() < i2){
			answers.add(null);
		}
		answers.set(i2-1, a);
		System.out.println("->" + answerCheckbox);
	}
	
	public String getAnswerString() {
		return answerString;
	}
	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
	
	public String getAnswerRadioButton() {
		return answerRadioButton;
	}
	public void setAnswerRadioButton(String answerRadioButton) {
		this.answerRadioButton = answerRadioButton;
	}
	
	public List<String> getAnswerCheckbox() {
		return answerCheckbox;
	}
	public void setAnswerCheckbox(List<String> answerCheckbox) {
		this.answerCheckbox = answerCheckbox;
	}
		
	public List<String> getQuests(){
		return quests;
	}

	public String questChanged(ActionEvent evt) {
		System.out.println(evt);
		UIComponent comp = evt.getComponent();
		System.out.println(questselected);
		questselected = (String) comp.getAttributes().get("value");
		return "";
	}

	public String getQuestselected() {
		System.out.println("get Quest " + questselected);
		return questselected;
	}

	public void setQuestselected(String questselected) {
		System.out.println(questselected);
		this.questselected = questselected;
	}
}
