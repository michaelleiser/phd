package org.bfh.phd.questionary;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.bfh.phd.EntityManager;
import org.bfh.phd.FilledQuestionnaire;
import org.bfh.phd.LoginController;
import org.bfh.phd.interfaces.IAnswer;
import org.bfh.phd.interfaces.IFilledQuestionnaire;

@ManagedBean(name = "elbow", eager = true)
@ViewScoped
public class Elbow implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static List<String> quests;
	private String questselected;
	private List<String> answerCheckbox;
	private String answerString;
	private String answerRadioButton;
	private IFilledQuestionnaire filledQuestionnaire = new FilledQuestionnaire();
	
	public Elbow(){
	}
	
	static {
		EntityManager em = new EntityManager();
		quests = em.getTemplateNames();
	}
		
	public void safe(LoginController lc){
		filledQuestionnaire.setQuestionnaireName(questselected);
		lc.addAnswer(filledQuestionnaire);
		filledQuestionnaire = new FilledQuestionnaire();
	}
	
	public void addString(final AjaxBehaviorEvent event){
		IAnswer a = new AnswerString();
		a.setAnswer(answerString);
		filledQuestionnaire.addAnswers(a);
		System.out.println("->" + answerString);
	}
	
	public void addRadioButton(final AjaxBehaviorEvent event){
		IAnswer a = new AnswerRadioButton();
		a.setAnswer(answerRadioButton);
		filledQuestionnaire.addAnswers(a);
		System.out.println("->" + answerRadioButton);
	}
	
	public void addCheckbox(final AjaxBehaviorEvent event){
		IAnswer a = new AnswerCheckbox();
		a.setAnswer(answerCheckbox);
		filledQuestionnaire.addAnswers(a);
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
		UIComponent comp = evt.getComponent();
		questselected = (String) comp.getAttributes().get("value");
		return "";
	}

	public String getQuestselected() {
		return questselected;
	}

	public void setQuestselected(String questselected) {
		this.questselected = questselected;
	}
}
