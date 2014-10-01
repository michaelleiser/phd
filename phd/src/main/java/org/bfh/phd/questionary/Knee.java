package org.bfh.phd.questionary;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "knee", eager = true)
@RequestScoped
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
	}
	
	
	
	public void add(final AjaxBehaviorEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("->"+event + event.getComponent().getAttributes());
	}

}
