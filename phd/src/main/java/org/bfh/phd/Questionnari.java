package org.bfh.phd;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.questionary.Answer;
import org.bfh.phd.questionary.AnswerCheckbox;
import org.bfh.phd.questionary.AnswerRadioButton;
import org.bfh.phd.questionary.AnswerString;
import org.bfh.phd.questionary.Question;


@ManagedBean(name = "questionnaridata", eager = true)
@SessionScoped
public class Questionnari implements Serializable {
	
	private EntityManager em = new EntityManager();
	
	private int quest;
	private int id;
	private Date date;
	private String op;
	private int patient;
//	private ArrayList<Question> q;
	private ArrayList<Answer> a;

//	public int getQ1() {
//		return q1;
//	}
//
//	public void setQ1(int q1) {
//		this.q1 = q1;
//	}

//	public int getQ2() {
//		return q2;
//	}
//
//	public void setQ2(int q2) {
//		this.q2 = q2;
//	}
//
//	public int getQ3() {
//		return q3;
//	}
//
//	public void setQ3(int q3) {
//		this.q3 = q3;
//	}
//
//	public int getQ4() {
//		return q4;
//	}
//
//	public void setQ4(int q4) {
//		this.q4 = q4;
//	}
//	
//	public int getQ5() {
//		return q5;
//	}
//
//	public void setQ5(int q5) {
//		this.q5 = q5;
//	}
//	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
//		
//	public void setQuestion(){
//		String answer ="'" + q1 + "','" + q2 + "','" + q3 + "','" + q4 + "','" + q5 + "'";
//		em.addFilledQuestionnaire(answer);
//	}
	
//	public List<Questionnari> getQuestionnaris(){
//		return em.getQuestionnaris(id);
//	}
	
	public List<Questionnari> getListOfQuestionnaris(){
		return em.searchQuestionnaris(patient);
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

	public ArrayList<Answer> getA() {
		return a;
	}

	public void setA(Answer a) {
		this.a.add(a);
	}
	
	public void safe(){
		System.out.println("******");
		EntityManager em = new EntityManager();
		em.addAnswer("knee", a, patient);
		for(Answer b : a){
			System.out.println("->>" + b);
		}
	}
	
	
	
	public void addString(final AjaxBehaviorEvent event){
		String i1 = event.getComponent().getId();
		int i2 = Integer.parseInt(i1.substring(5));
		Answer a = new AnswerString();
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
		Answer a = new AnswerRadioButton();
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
		Answer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		while(this.a.size() < i2){
			this.a.add(null);
		}
		this.a.set(i2-1, a);
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

