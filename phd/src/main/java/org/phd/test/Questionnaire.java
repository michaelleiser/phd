package org.phd.test;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "questionnaire", eager = true)
@SessionScoped
public class Questionnaire implements Serializable {
	
	private static int i = 0;
	public static String[] favNumber3;
	private Object[] objects = new Object[3];
	private static ArrayList<Object> array = new ArrayList<Object>();


	private String[] favNumber;
	private Map<String,Object> answer;
	private String question;
	
	public void setAnswer(String a1,String a2,String a3,String a4,Object o1,Object o2,Object o3,Object o4){
		answer.put(a1, o1);
		answer.put(a2, o2);
		answer.put(a3, o3);
		answer.put(a4, o4);
	}
	
	public Map<String, Object> getAnswer(){
		return answer;
	}
	
	public String getFav(){
		return Arrays.toString(favNumber);
	}
	
	public void setQuestion(String q){
		this.question = q;
	}
	
	public String getQuestion(){
		return question;
	}
	
	
	public static List<Questionnaire> getQuestions(){
		ArrayList<Questionnaire> questions = new ArrayList<Questionnaire>();
		Questionnaire q = new Questionnaire();
		q.setAnswer("a", "b", "c", "d", "1", "2", "3", "4");;
//		q.add(e);
		q.setQuestion("wtf?");
		
		
		return questions;
	}
}
