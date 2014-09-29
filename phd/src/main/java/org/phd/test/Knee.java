package org.phd.test;

import java.util.ArrayList;
import java.util.List;

public class Knee {
	
	private int id;
	private static List<Question> questions;
	private static List<Answer> answers;
	
	public Knee(){
		
	}
	
	static {
		questions = new ArrayList<Question>();
		answers = new ArrayList<Answer>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void addQuestion(Question q) {
		questions.add(q);
	}

	
	
	public static List<Question> getQuestions() {
		return questions;
	}
	public static void setQuestions(List<Question> questions) {
		Knee.questions = questions;
	}
	public static List<Answer> getAnswers() {
		return answers;
	}
	public static void setAnswers(List<Answer> answers) {
		Knee.answers = answers;
	}
	public void addAnswer(Answer a) {
//		answers.add(a);
	}

}
