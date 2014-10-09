package org.bfh.phd;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "questionnaridata", eager = true)
@SessionScoped
public class Questionnari implements Serializable {
	
	private EntityManager em = new EntityManager();
	
	private int quest;
	private int q1;
	private int q2;
	private int q3;
	private int q4;
	private int q5;
	private int id;
	private Date date;
	private String op;
	private int patient;

	public int getQ1() {
		return q1;
	}

	public void setQ1(int q1) {
		this.q1 = q1;
	}

	public int getQ2() {
		return q2;
	}

	public void setQ2(int q2) {
		this.q2 = q2;
	}

	public int getQ3() {
		return q3;
	}

	public void setQ3(int q3) {
		this.q3 = q3;
	}

	public int getQ4() {
		return q4;
	}

	public void setQ4(int q4) {
		this.q4 = q4;
	}
	
	public int getQ5() {
		return q5;
	}

	public void setQ5(int q5) {
		this.q5 = q5;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
		
	public void setQuestion(){
		String answer ="'" + q1 + "','" + q2 + "','" + q3 + "','" + q4 + "','" + q5 + "'";
		em.addFilledQuestionnaire(answer);
	}
	
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
		
}
