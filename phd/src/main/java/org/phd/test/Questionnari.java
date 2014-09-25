package org.phd.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "fragebogen", eager = true)
@SessionScoped
public class Questionnari implements Serializable {
	
	private EntityManager em = new EntityManager();
	
	private int q1;
	private int q2;
	private int q3;
	private int q4;
	private int q5;
	private int id;

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
		
	public String setQuestion(){
		Random r = new Random();
		this.q5 = r.nextInt(10);
		String answer ="'" + q1 + "','" + q2 + "','" + q3 + "','" + q4 + "','" + q5 + "'";
		em.addFilledQuestionnaire(answer);
		return "";
	}
	
	public List<Questionnari> getQuestionnaris(){
		return em.getQuestionnaris(id);
	}
	
}

