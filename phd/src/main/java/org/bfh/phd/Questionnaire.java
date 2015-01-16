package org.bfh.phd;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.questionnaire.AnswerCheckbox;
import org.bfh.phd.questionnaire.AnswerRadioButton;
import org.bfh.phd.questionnaire.AnswerString;



@ManagedBean(name = "questionnaridata", eager = true)
@SessionScoped
public class Questionnaire implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager em = new EntityManager();
	
	private int quest;
	private int id;
	private Date date;
	private String op;
	private int patient;
	private ArrayList<IAnswer> a;
	private List<String> answerCheckbox;
	private String answerRadioButton;
	private String answerString;
	
	public Questionnaire() {
		System.out.println("Questionnaire");		
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public List<Questionnaire> getListOfQuestionnaris(){
		return em.searchQuestionnaires(patient);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public int getPatient() {
		return patient;
	}

	public void setPatient(int patient) {
		this.patient = patient;
	}

	public int getQuest() {
		return quest;
	}

	public void setQuest(int quest) {
		this.quest = quest;
	}

	public ArrayList<IAnswer> getA() {
		return a;
	}

	public void setA(IAnswer a) {
		this.a.add(a);
	}
		
	public void addString(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		IAnswer a = new AnswerString();
		a.setAnswer(answerString);
		while(this.a.size() < i2){
			this.a.add(null);
		}
		this.a.set(i2-1, a);
		System.out.println("->" + answerString);
	}
	
	public void addRadioButton(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		IAnswer a = new AnswerRadioButton();
		a.setAnswer(answerRadioButton);
		while(this.a.size() < i2){
			this.a.add(null);
		}
		this.a.set(i2-1, a);
		System.out.println("->" + answerRadioButton);
	}
	
	public void addCheckbox(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		IAnswer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		while(this.a.size() < i2){
			this.a.add(null);
		}
		this.a.set(i2-1, a);
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
}

